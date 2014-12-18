package shopping.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author songjz Dec 17, 2014
 */
@SuppressWarnings("unused")
public class BeanUtilTest {

  private Bean bean;

  private BeanChild beanChild;

  @Before
  public void before() {
    this.beanChild = new BeanChild();
    beanChild.setName("张三child");

    this.bean = new Bean();
    bean.setAge(new BigDecimal(1));
    bean.setBeanChild(beanChild);
    bean.setDate(new Date());
    bean.setId(1l);
    bean.setName("张三");
  }

  @Test
  public void copySimplePropertiesTest() {
    BeanChildVO beanChildVO = new BeanChildVO();
    BeanUtil.copySimpleProperties(beanChild, beanChildVO);
    assertEquals(beanChild.getName(), beanChildVO.getName());

    BeanVO beanVO = new BeanVO();
    BeanUtil.copySimpleProperties(bean, beanVO);
    assertEquals(new BigDecimal(1), beanVO.getAge());
    assertNull(beanVO.getBeanChild());
  }

  private class Bean {
    private Long id;

    private String name;

    private Date date;

    private BigDecimal age;

    private BeanChild beanChild;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Date getDate() {
      return date;
    }

    public void setDate(Date date) {
      this.date = date;
    }

    public BigDecimal getAge() {
      return age;
    }

    public void setAge(BigDecimal age) {
      this.age = age;
    }

    public BeanChild getBeanChild() {
      return beanChild;
    }

    public void setBeanChild(BeanChild beanChild) {
      this.beanChild = beanChild;
    }

  }

  private class BeanChild {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

  }

  private class BeanVO {
    private Long id;

    private String name;

    private Date date;

    private BigDecimal age;

    private BeanChildVO beanChild;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Date getDate() {
      return date;
    }

    public void setDate(Date date) {
      this.date = date;
    }

    public BigDecimal getAge() {
      return age;
    }

    public void setAge(BigDecimal age) {
      this.age = age;
    }

    public BeanChildVO getBeanChild() {
      return beanChild;
    }

    public void setBeanChild(BeanChildVO beanChild) {
      this.beanChild = beanChild;
    }
  }

  private class BeanChildVO {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
