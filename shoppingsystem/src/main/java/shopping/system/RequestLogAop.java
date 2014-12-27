package shopping.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author songjz Dec 22, 2014
 */
@Aspect
public class RequestLogAop {

  @Pointcut("execution(public * *(..))")
  private void anyOldTransfer() {
    System.out.println("----------w我");
  }

  // @Before("execution (* shopping..controller..*.*(..))")
  @Before(value = "shopping.user.dao.UserDao.findAll()")
  public void beforeActionInvocation(JoinPoint joinPoint) {
    System.out.println("请求前");
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
  }

  // @After("execution (* shopping..controller..*.*(..))")
  @After("execution (* shopping.user.controller.*.*(..))")
  public void afterActionInvocation(JoinPoint joinPoint) {
    System.out.println("请求后");
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletResponse response = sra.getResponse();
  }
}
