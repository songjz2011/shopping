package shopping.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shopping.basic.controller.BasicController;
import shopping.basic.service.SessionService;
import shopping.system.constant.SystemConstants;
import shopping.user.dao.UserDao;
import shopping.user.model.User;
import shopping.user.vo.UserVO;
import shopping.util.OperationResult;
import shopping.util.Pager;
import shopping.util.QueryBuilder;
import shopping.util.StringUtil;
import shopping.util.error.StatusCode;

import com.alibaba.fastjson.JSON;

/**
 * @author songjz Dec 14, 2014
 */
@Controller
@RequestMapping(value = "/users")
@Transactional(readOnly = true)
public class UserController extends BasicController {

  @Resource
  private UserDao userDao;

  @RequestMapping(value = "/login")
  @ResponseBody
  public String login(@ModelAttribute("user") UserVO user, HttpServletRequest request) {
    QueryBuilder query = new QueryBuilder(User.class);
    query.add(null, "name", "=", user.getName());
    String password = StringUtil.hexMD5(user.getPassword());
    query.add("and", "password", "=", password);
    User u = userDao.findOne(query.getQuery(), query.getParams());
    if (u == null) {
      return OperationResult.fail(StatusCode.USER_LOGIN_ERROR);
    }
    UserVO vo = u.cloneToVO();
    SessionService.set(request.getSession(), SystemConstants.sessionUserKey(), vo);
    return OperationResult.success(vo);
  }

  /**
   * <pre>
   * 查询
   * </pre>
   * 
   * @param pager
   * @return
   */
  @RequestMapping(value = "/list")
  @ResponseBody
  public String list(UserVO user, Pager pager) {
    List<User> list = userDao.findAll();
    return JSON.toJSONString(list);
  }

  @Transactional(readOnly = false)
  @RequestMapping(value = "/save")
  @ResponseBody
  public String save(@ModelAttribute(value = "user") User user) {
    userDao.save(user);
    return OperationResult.success(user.getId());
  }

  @Transactional(readOnly = false)
  @RequestMapping(value = "/delete/{id}")
  @ResponseBody
  public String delete(@PathVariable Long id) {
    User user = userDao.findById(id);
    user.setDeleted(true);
    userDao.save(user);
    return OperationResult.success();
  }

}
