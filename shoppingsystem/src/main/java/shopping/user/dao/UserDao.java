package shopping.user.dao;

import org.springframework.stereotype.Repository;

import shopping.basic.dao.BasicDao;
import shopping.user.model.User;

/**
 * @author songjz Dec 17, 2014
 */
@Repository
public class UserDao extends BasicDao<User> {

  protected Class<User> getClazz() {
    return User.class;
  }

}
