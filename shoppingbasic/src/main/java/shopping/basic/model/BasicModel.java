package shopping.basic.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import shopping.basic.vo.BasicVO;

/**
 * @author songjz Dec 14, 2014
 */
@MappedSuperclass
public abstract class BasicModel implements java.io.Serializable {

  private static final long serialVersionUID = 3328164259426786939L;

  /**
   * 主键
   */
  @Id
  @GeneratedValue
  private Long id;

  /**
   * 创建时间
   */
  private Date createTime;

  public abstract BasicVO cloneToVO();

  @Transient
  public boolean isNew() {
    if (id == null) {
      return true;
    }
    return false;
  }

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
