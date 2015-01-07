package shopping.user.vo;

import shopping.basic.vo.BasicVO;

/**
 * @author songjz Dec 14, 2014
 */
public class UserVO extends BasicVO {

  /**
   * 登录名
   */
  private String loginId;

  /**
   * 密码
   */
  private String password;

  /**
   * 姓名
   */
  private String name;

  /**
   * 性别
   */
  private String gender;

  /**
   * 电话
   */
  private String tel;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 是否删除
   */
  private boolean deleted;

  /**
   * 是否是系统资源
   */
  private boolean isSystemResource;

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public boolean isSystemResource() {
    return isSystemResource;
  }

  public void setSystemResource(boolean isSystemResource) {
    this.isSystemResource = isSystemResource;
  }

}
