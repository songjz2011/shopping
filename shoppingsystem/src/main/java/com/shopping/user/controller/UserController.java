package com.shopping.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shopping.basic.controller.BasicController;
import com.shopping.basic.service.SessionService;
import com.shopping.system.constant.SystemConstants;
import com.shopping.user.dao.UserDao;
import com.shopping.user.model.User;
import com.shopping.user.vo.UserVO;
import com.shopping.util.OperationResult;
import com.shopping.util.Pager;
import com.shopping.util.QueryBuilder;
import com.shopping.util.StringUtil;
import com.shopping.util.error.StatusCode;

/**
 * @author songjz Dec 14, 2014
 */
@Controller
@RequestMapping(value = "/users")
@Transactional(readOnly = true)
public class UserController extends BasicController {

  private String sessionUserKey = SystemConstants.sessionUserKey();

  @Resource
  private UserDao userDao;

  /**
   * <pre>
   * 登录
   * </pre>
   * 
   * @param user
   * @param request
   * @return
   */
  @RequestMapping(value = "/login")
  @ResponseBody
  public String login(@ModelAttribute("user") UserVO user, HttpServletRequest request) {
    if (user != null
        && (StringUtil.isBlank(user.getLoginId()) || StringUtil.isBlank(user.getPassword()))) {
      return OperationResult.fail("请输入用户名|密码");
    }
    QueryBuilder query = new QueryBuilder(User.class);
    query.add(null, "loginId", "=", user.getLoginId());
    String password = StringUtil.hexMD5(user.getPassword());
    query.add("and", "password", "=", password);
    User u = userDao.findOne(query.getQuery(), query.getParams());
    if (u == null) {
      return OperationResult.fail(StatusCode.USER_LOGIN_ERROR);
    }
    UserVO vo = u.cloneToVO();
    SessionService.set(request.getSession(), sessionUserKey, vo);
    return OperationResult.success(vo);
  }

  /**
   * <pre>
   * 退出
   * </pre>
   * 
   * @param request
   * @return
   */
  @RequestMapping(value = "/logout")
  @ResponseBody
  public String logout(HttpServletRequest request) {
    SessionService.remove(request.getSession(), sessionUserKey);
    return OperationResult.success();
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
