package shopping.user.dao;

import org.springframework.stereotype.Repository;

import shopping.basic.dao.BaseDao;
import shopping.user.model.User;

/**
 * @author songjz Dec 17, 2014
 */
@Repository
public class UserDao extends BaseDao<User> {

  protected Class<User> getClazz() {
    return User.class;
  }

}
