package shopping.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shopping.user.dao.UserDao;
import shopping.user.model.User;
import shopping.util.OperationResult;
import shopping.util.Pager;

import com.alibaba.fastjson.JSON;

/**
 * @author songjz Dec 14, 2014
 */
@Controller
@RequestMapping(value = "/users")
@Transactional(readOnly = true)
public class UserController {

  @Resource
  private UserDao userDao;

  @InitBinder("user")
  public void initBinderUser(WebDataBinder binder) {
    binder.setFieldDefaultPrefix("user.");
  }

  @RequestMapping(value = "/list")
  @ResponseBody
  public String list(Pager pager) {
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

}
