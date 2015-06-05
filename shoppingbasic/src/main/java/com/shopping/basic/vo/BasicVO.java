package com.shopping.basic.vo;

import java.util.Date;

/**
 * @author songjz Dec 17, 2014
 */
public class BasicVO {

  /**
   * 主键
   */
  private Long id;

  /**
   * 创建时间
   */
  private Date createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
