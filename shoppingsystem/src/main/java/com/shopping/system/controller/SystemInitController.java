package com.shopping.system.controller;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

import com.shopping.user.dao.UserDao;
import com.shopping.user.model.User;
import com.shopping.util.LoggerUtil;
import com.shopping.util.StringUtil;

/**
 * 系统初始化
 * 
 * @author songjz Dec 22, 2014
 */
@Controller
public class SystemInitController implements ApplicationListener<ContextRefreshedEvent> {

  private static boolean isStart = false;

  @Resource
  private UserDao userDao;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (!isStart) {
      isStart = true;
      initUser();
    }
  }

  /**
   * <pre>
   * 初始化User
   * </pre>
   */
  private void initUser() {
    if (userDao.count() > 0) {
      LoggerUtil.info("用户已经初始化，跳过此步骤");
      return;
    }
    User user = new User();
    user.setName("root");
    user.setPassword(StringUtil.hexMD5("rootroot"));
    user.setSystemResource(true);
    userDao.save(user);
  }

}
