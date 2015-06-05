package com.shopping.basic.aop;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shopping.util.LoggerUtil;
import com.shopping.util.StringUtil;

/**
 * @author songjz Dec 30, 2014
 */
@Component
@Aspect
@Order(1)
public class RequestAopLog {

  @Around("execution(* shopping..controller..*.*(..))")
  public Object doAround(ProceedingJoinPoint joinPoint) {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    if (ra != null) {
      try {
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        requestLog(request);
        Object responseValue = getResponseLog(joinPoint);
        return responseValue;
      } catch (Throwable e) {
        LoggerUtil.error(e);
      }
    }
    return null;
  }

  /**
   * <pre>
   * 请求日志
   * </pre>
   * 
   * @param request
   */
  @SuppressWarnings("unchecked")
  private void requestLog(HttpServletRequest request) {
    String newLine = StringUtil.getNewLine();
    StringBuilder content = new StringBuilder();
    content.append("[Request Header]: ").append(newLine);
    content.append("请求路径为").append(request.getServletPath()).append(newLine);
    content.append("请求头为");
    Enumeration<String> headNames = request.getHeaderNames();
    while (headNames.hasMoreElements()) {
      String name = headNames.nextElement();
      content.append(name).append("=").append(request.getHeader(name)).append("&");
    }
    content.append(newLine);
    content.append("请求参数为");
    Map<String, Object> map = request.getParameterMap();
    for (Entry<String, Object> entry : map.entrySet()) {
      String value = reBuildRequestValue(entry.getValue());
      content.append(entry.getKey()).append("=").append(value).append("&");
    }
    content.append(newLine);
    LoggerUtil.info(content.toString());
  }

  /**
   * <pre>
   * 重新构造请求的参数值
   * </pre>
   * 
   * @param value
   * @return
   */
  private String reBuildRequestValue(Object value) {
    if (StringUtil.isBlank(value)) {
      return "";
    }
    StringBuilder content = new StringBuilder();
    Object[] objs = null;
    if (value instanceof Object[]) {
      objs = (Object[]) value;
    } else if (value instanceof Collection) {
      Collection<?> coll = (Collection<?>) value;
      objs = coll.toArray();
    } else {
      objs = new Object[1];
      objs[0] = value;
    }
    if (objs != null) {
      int length = objs.length;
      for (int i = 0; i < length; i++) {
        content.append(objs[i]);
        if (i != length - 1) {
          content.append(",");
        }
      }
    }
    return content.toString();
  }

  /**
   * <pre>
   * 响应Log
   * </pre>
   * 
   * @param joinPoint
   * @throws Throwable
   */
  private Object getResponseLog(ProceedingJoinPoint joinPoint) throws Throwable {
    // 返回值
    Object result = joinPoint.proceed();
    String newLine = StringUtil.getNewLine();
    StringBuilder content = new StringBuilder();
    content.append("[Response Data]: ").append(newLine);
    content.append(result);
    LoggerUtil.info(content.toString());
    return result;
  }

}
