package com.shopping.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.shopping.basic.model.BasicModel;
import com.shopping.user.vo.UserVO;
import com.shopping.util.BeanUtil;

/**
 * @author songjz Dec 14, 2014
 */
@Entity
@Table(name = "T_USER")
public class User extends BasicModel {

  private static final long serialVersionUID = 1757622108536761729L;

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
  @Column(columnDefinition = "BOOLEAN")
  private boolean deleted;

  /**
   * 是否是系统资源
   */
  @Column(columnDefinition = "BOOLEAN")
  private boolean isSystemResource;

  public UserVO cloneToVO() {
    UserVO vo = new UserVO();
    BeanUtil.copySimpleProperties(this, vo);
    vo.setPassword(null);
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
