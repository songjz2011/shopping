package shopping.util;

import java.util.ArrayList;
import java.util.List;

import shopping.util.error.StatusCode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * HTTP操作结果, 对于核心系统来说，每次请求必须返回这么一个数据结构的JSON表示.
 * 
 * @author songjz
 */
public class OperationResult {
  // 如果返回的数据不需要分页，则在序列化JSON的时候排除pager相关的字段.
  private static List<String> pagerFields = null;

  static {
    pagerFields = new ArrayList<String>();
    pagerFields.add("page");
    pagerFields.add("pageSize");
    pagerFields.add("totalPage");
    pagerFields.add("recordSize");
  }

  /**
   * 操作结果, {@link RESULT#SUCCESS}或{@link RESULT#FAIL}.
   */
  public String result;
  public String msg;
  public Object data;
  /**
   * 错误码，如果result为fail则根据错误码检测是什么出错原因
   */
  public int code;

  public int page;
  public int pageSize;
  public int totalPage;
  public int recordSize;

  public OperationResult() {
  }

  private OperationResult(RESULT result, String msg, Object data, int code) {
    this.result = result.name();
    this.msg = msg;
    this.data = data;
    this.code = code;
  }

  /**
   * 简单的告知客户端操作成功.
   */
  public static String success() {
    SimplePropertyPreFilter filter = new SimplePropertyPreFilter("result");
    return JSON.toJSONString(new OperationResult(RESULT.SUCCESS, null, null,
        StatusCode.SUCCESS.code), filter);
  }

  /**
   * 返回简单类型(无级联、嵌套等关系)的数据到客户端, 复杂类型请使用
   * {@link OperationResult#success(Object, SimplePropertyPreFilter)}
   * 
   * @param data
   *          要返回给客户端的简单类型数据.
   * @return {@link OperationResult}
   */
  public static String success(Object data) {
    return new OperationResult(RESULT.SUCCESS, null, data,
        StatusCode.SUCCESS.code).toJSONString();
  }

  public static String success(Object data, Pager pager) {
    OperationResult result =
        new OperationResult(RESULT.SUCCESS, null, data, StatusCode.SUCCESS.code);
    if (pager != null) {
      result.page = pager.page;
      result.pageSize = pager.pageSize;
      result.recordSize = pager.recordSize;
    }
    return result.toJSONString();
  }

  /**
   * 系统操作失败, 并补充失败原因.
   */
  public static String fail(String msg) {
    return fail(msg, StatusCode.SYSTEM_ERROR.code);
  }

  /**
   * 操作失败, 并补充失败原因.
   */
  public static String fail(StatusCode statusCode) {
    SimplePropertyPreFilter filter =
        new SimplePropertyPreFilter("result", "msg", "code");
    return JSON.toJSONString(new OperationResult(RESULT.FAIL, statusCode.label, null,
        statusCode.code), filter);
  }

  /**
   * 操作失败, 并补充失败原因.
   */
  public static String fail(String msg, int code) {
    SimplePropertyPreFilter filter =
        new SimplePropertyPreFilter("result", "msg", "code");
    return JSON.toJSONString(new OperationResult(RESULT.FAIL, msg, null, code),
        filter);
  }

  /**
   * 如果page、pageSize等分页信息都为0，则不返回这些数据的JSON.
   */
  private String toJSONString() {
    if (page == 0 && pageSize == 0 && totalPage == 0 && recordSize == 0) {
      PropertyPreFilter filter = new PropertyPreFilter() {
        @Override
        public boolean apply(JSONSerializer serializer, Object object,
            String name) {
          if (pagerFields.contains(name)) {
            return false;
          }
          return true;
        }
      };
      return JSON.toJSONString(this, filter);
    } else {
      calculateTotalPage();
      return JSON.toJSONString(this);
    }
  }

  /**
   * <pre>
   * 计算总页数
   * </pre>
   * 
   * @return
   */
  private void calculateTotalPage() {
    if (totalPage != 0 || pageSize == 0) {
      return;
    }
    totalPage = (recordSize - 1) / pageSize + 1;
  }

  public enum RESULT {
    /**
     * 操作成功
     */
    SUCCESS,
    /**
     * 操作失败
     */
    FAIL;
  }

}
