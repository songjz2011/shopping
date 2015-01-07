package shopping.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import shopping.basic.model.BasicModel;
import shopping.basic.vo.BasicVO;

/**
 * 用户地址
 * 
 * @author songjz Dec 28, 2014
 */
@Entity
@Table(name = "T_USER_ADDRESS")
public class UserAddress extends BasicModel {

  private static final long serialVersionUID = 8088451199358251974L;

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

  public BasicVO cloneToVO() {
    return null;
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
