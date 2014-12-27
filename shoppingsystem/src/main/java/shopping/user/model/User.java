package shopping.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import shopping.basic.model.BasicModel;
import shopping.user.vo.UserVO;
import shopping.util.BeanUtil;

/**
 * @author songjz Dec 14, 2014
 */
@Entity
@Table(name = "T_USER")
public class User extends BasicModel {

  private static final long serialVersionUID = 1757622108536761729L;

  /**
   * 姓名
   */
  private String name;

  /**
   * 密码
   */
  private String password;

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

  /**
   * 是否是系统资源
   */
  @Column(columnDefinition = "BOOLEAN")
  private boolean isSystemResource;

  public UserVO cloneToVO() {
    UserVO vo = new UserVO();
    BeanUtil.copySimpleProperties(this, vo);
    return vo;
  }

  public static List<UserVO> cloneToVOList(List<User> list) {
    if (list == null || list.size() == 0) {
      return null;
    }
    List<UserVO> vos = new ArrayList<UserVO>();
    for (User u : list) {
      vos.add(u.cloneToVO());
    }
    return vos;
  }

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

  public boolean isSystemResource() {
    return isSystemResource;
  }

  public void setSystemResource(boolean isSystemResource) {
    this.isSystemResource = isSystemResource;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
