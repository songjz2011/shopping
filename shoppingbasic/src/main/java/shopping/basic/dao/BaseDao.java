package shopping.basic.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shopping.util.Pager;

/**
 * @author songjz Dec 17, 2014
 */
@SuppressWarnings("unchecked")
public abstract class BaseDao<T> {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  private Session getCurrentSession() {
    return sessionFactory.openSession();
  }

  protected abstract Class<T> getClazz();

  /**
   * <pre>
   * 保存
   * </pre>
   * 
   * @return
   */

  public T save(T obj) {
    this.getCurrentSession().saveOrUpdate(obj);
    return obj;
  }

  /**
   * <pre>
   * 删除
   * </pre>
   */

  public void delete(T obj) {
    this.getCurrentSession().delete(obj);
  }

  /**
   * <pre>
   * 根据ID查询
   * </pre>
   * 
   * @param id
   * @return
   */

  public T findById(Object id) {
    return (T) this.getCurrentSession().get(getClazz(), (Serializable) id);
  }

  /**
   * <pre>
   * 查询所有
   * </pre>
   * 
   * @return
   */

  public final List<T> findAll() {
    return this.getCurrentSession().createQuery("from " + getClazz().getName()).list();
  }

  /**
   * <pre>
   * 根据Hql查询
   * </pre>
   * 
   * @param hql
   * @return
   */
  public List<T> find(String hql) {
    return find(hql, null, null);
  }

  /**
   * <pre>
   * 根据Hql查询
   * </pre>
   * 
   * @param hql
   * @param params
   * @return
   */
  public List<T> find(String hql, List<Object> params) {
    return find(hql, params, null);
  }

  /**
   * <pre>
   * 根据Hql查询，带分页
   * </pre>
   * 
   * @param hql
   * @param params
   *          : 查询条件参数
   * @param pager
   *          : 分页
   * @return
   */
  public List<T> find(String hql, List<Object> params, Pager pager) {
    Query q = this.getCurrentSession().createQuery(hql);
    if (params != null && !params.isEmpty()) {
      for (int i = 0; i < params.size(); i++) {
        q.setParameter(i, params.get(i));
      }
    }
    if (pager != null) {
      q.setFirstResult((pager.page - 1) * pager.pageSize).setMaxResults(pager.pageSize);
    }
    return q.list();
  }

  /**
   * <pre>
   * 查询条目数
   * </pre>
   * 
   * @param hql
   * @return
   */
  public Long count(String hql) {
    return count(hql, null);
  }

  /**
   * <pre>
   * 查询条目数
   * </pre>
   * 
   * @param hql
   * @param params
   * @return
   */
  public Long count(String hql, List<Object> params) {
    Query q = this.getCurrentSession().createQuery(hql);
    if (params != null && !params.isEmpty()) {
      for (int i = 0; i < params.size(); i++) {
        q.setParameter(i, params.get(i));
      }
    }
    return (Long) q.uniqueResult();
  }
}
