package shopping.util.error;

/**
 * 请求表单参数错误提示
 * 
 * @author jzsong@rongxintx.com
 */
public class RequestParamError {

  /**
   * 错误的字段
   */
  private String key;

  /**
   * 错误的字段提示信息
   */
  private String message;

  public RequestParamError() {

  }

  public RequestParamError(String key, String message) {
    this.key = key;
    this.message = message;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
