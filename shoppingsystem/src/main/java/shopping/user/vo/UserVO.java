package shopping.user.vo;

import shopping.basic.vo.BasicVO;

/**
 * @author songjz Dec 14, 2014
 */
public class UserVO extends BasicVO {

  /**
   * 姓名
   */
  private String name;

  /**
   * 电话
   */
  private String tel;

  /**
   * 省
   */
  private String province;

  /**
   * 市
   */
  private String city;

  /**
   * 区
   */
  private String district;

  /**
   * 详细地址
   */
  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

}
