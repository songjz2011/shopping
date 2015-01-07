package shopping.system.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

import shopping.system.constant.SystemConstants;
import shopping.user.model.User;
import shopping.util.LoggerUtil;
import shopping.util.OperationResult;

/**
 * <pre>
 * 登录Aop
 * 1、登录session验证
 * 2、登录成功之后，设置用户的session
 * </pre>
 * 
 * @author songjz Jan 4, 2015
 */
@Component
@Aspect
@Order(2)
public class LoginAop {

  private String loginUrl = "/users/login";

  @Around("execution(* shopping..controller..*.*(..))")
  public Object login(ProceedingJoinPoint joinPoint) {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    Object value = null;
    if (ra != null) {
      try {
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        if (loginUrl.equals(request.getServletPath())) {

        }
      } catch (Throwable e) {
        LoggerUtil.error(e);
      }
    }
    return value;
  }

  //@Around("execution(* shopping..controller.UserController.login(..))")
  public Object doAround(ProceedingJoinPoint joinPoint) {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    Object value = null;
    // if (ra != null) {
    // try {
    // value = joinPoint.proceed();
    // if (value == null) {
    // return value;
    // }
    // OperationResult or = JSON.parseObject(value.toString(),
    // OperationResult.class);
    // if (or == null ||
    // !OperationResult.RESULT.SUCCESS.toString().equals(or.result)
    // || or.data == null) {
    // return value;
    // }
    // User user = JSON.parseObject(or.data.toString(), User.class);
    // if (user == null || user.isNew()) {
    // return value;
    // }
    // ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    // HttpServletRequest request = sra.getRequest();
    // request.getSession().setAttribute(SystemConstants.sessionUserKey(),
    // user);
    // } catch (Throwable e) {
    // LoggerUtil.error(e);
    // }
    // }
    return value;
  }

}
