package shopping.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import shopping.system.constant.SystemConstants;
import shopping.util.OperationResult;
import shopping.util.constant.UtilConstants;
import shopping.util.error.StatusCode;

/**
 * 登录验证Aop
 * 
 * @author songjz Jan 4, 2015
 */
public class LoginValidateInterceptor extends HandlerInterceptorAdapter {

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession();
    if (session.getAttribute(SystemConstants.sessionUserKey()) == null) {
      if(request.getServletPath().contains("login.html")) {
        return true;
      }
      request.getRequestDispatcher("/login.html").forward(request, response);
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
    super.afterCompletion(request, response, handler, ex);
  }

  public void afterConcurrentHandlingStarted(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
    super.afterConcurrentHandlingStarted(request, response, handler);
  }

}
