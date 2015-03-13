package shopping.util.error;

/**
 * 错误码定义 NOTE:如果需要请自己加入，不要修改之前的错误码
 * 
 * @author songjz
 */
public enum StatusCode {
  // 0-200 系统级
  SUCCESS(0, "操作成功"),
  SYSTEM_ERROR(1, "系统错误"),
  DATA_NOT_FOUND(2, "数据不存在"),
  SYSTEM_NOT_LOGIN(3, "请登录"),
  SYSTEM_LOGIN_TIME_OUT(4, "登录超时，请重新登录"),

  // 200 - 299 用户管理
  USER_LOGIN_ERROR(200, "您输入的账户名和密码不匹配，请重新输入");

  public Integer code;
  public String label;

  private StatusCode(Integer code, String label) {
    this.code = code;
    this.label = label;
  }

  /**
   * 获取状态码描述
   * 
   * @param code
   *          状态码
   * @return 状态码描述，如果没有返回空串
   */
  public static String getLabel(int code) {
    String result = "";
    for (StatusCode status : StatusCode.values()) {
      if (status.code == code) {
        result = status.label;
      }
    }
    return result;
  }
}
