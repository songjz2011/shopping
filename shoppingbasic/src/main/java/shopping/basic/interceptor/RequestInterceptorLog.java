package shopping.basic.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import shopping.util.StringUtil;

/**
 * 请求日志
 * 
 * @author songjz Dec 22, 2014
 */
public class RequestInterceptorLog extends HandlerInterceptorAdapter {

  //private static String[] filterFile = { ".html", ".jsp", ".js", ".css", ".jpg", ".png", ".bmp" };
  private static String[] filterFile = {".js", ".css", ".jpg", ".png", ".bmp" };

  private String mappingURL;// 利用正则映射到需要拦截的路径
  private int openingTime;
  private int closingTime;

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("--------RequestInterceptorLog");
    if (StringUtil.isNotBlank(mappingURL)) {
      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      int now = c.get(Calendar.HOUR_OF_DAY);
      if (now < openingTime || now > closingTime) {
        request.setAttribute("msg", "注册开放时间：9：00-12：00");
        request.getRequestDispatcher("/index.html").forward(request, response);
        return false;
      }
    }
    //requestLog(request);
    return true;
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
    if (isFilterRequest(request)) {
      return;
    }
    String newLine = StringUtil.getNewLine();
    StringBuilder content = new StringBuilder();
    content.append("[Request Header]: ");
    content.append("请求路径=").append(request.getServletPath()).append(newLine);
    content.append("请求头=");
    Enumeration<String> headNames = request.getHeaderNames();
    while (headNames.hasMoreElements()) {
      String name = headNames.nextElement();
      content.append(name).append("=").append(request.getHeader(name)).append("&");
    }
    content.append(newLine);
    content.append("请求参数=");
    Map<String, Object> map = request.getParameterMap();
    for (Entry<String, Object> entry : map.entrySet()) {
      content.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
    }
    content.append(newLine);
    System.out.println(content.toString());
  }

  private boolean isFilterRequest(HttpServletRequest request) {
    String url = request.getRequestURL().toString();
    String contentPath = request.getContextPath();
    if (url.endsWith(contentPath) || url.endsWith(contentPath + "/")) {
      return true;
    }
    for (String str : filterFile) {
      if (url.endsWith(str)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    // File file = null;
    // OutputStream out = new FileOutputStream(file,true);
    //
    // OutputStream os = response.getOutputStream();
    // if(os != null) {
    // BufferedOutputStream bos = new BufferedOutputStream(os);
    // }
    // if(os instanceof ByteArrayOutputStream) {
    // String encoding = response.getCharacterEncoding();
    // LoggerUtil.info(getClass(), os.toString());
    //
    // }
  }

}
