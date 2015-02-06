package shopping.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import shopping.basic.service.SessionService;
import shopping.system.constant.SystemConstants;
import shopping.user.vo.UserVO;

/**
 * 登录验证Interceptor
 * 
 * @author songjz Jan 4, 2015
 */
public class LoginValidateInterceptor extends HandlerInterceptorAdapter {

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession();
    UserVO user = SessionService.get(session, SystemConstants.sessionUserKey(), UserVO.class);
    if (session == null || user == null) {
      String contextPath = request.getContextPath();
      response.sendRedirect(contextPath);
      return false;
    }
    return true;
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    super.postHandle(request, response, handler, modelAndView);
  }

  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    response.setHeader("aaa", "我是谁");
  }

  public void afterConcurrentHandlingStarted(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
    super.afterConcurrentHandlingStarted(request, response, handler);
  }

}
