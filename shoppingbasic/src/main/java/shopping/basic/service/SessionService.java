package shopping.basic.service;

import javax.servlet.http.HttpSession;

/**
 * @author songjz Jan 24, 2015
 */
public class SessionService {

  /**
   * <pre>
   * 设置值到session中
   * </pre>
   * 
   * @param session
   * @param key
   * @param value
   */
  public static void set(HttpSession session, String key, Object value) {
    if (session == null || value == null || key == null) {
      return;
    }
    session.setAttribute(key, value);
  }
  
  /**
   * <pre>
   * 从session中获取值
   * </pre>
   * 
   * @param session
   * @param key
   * @param value
   */
  @SuppressWarnings("unchecked")
  public static <T> T get(HttpSession session, String key, Class<T> clazz) {
    if (session == null || key == null) {
      return null;
    }
    return (T) session.getAttribute(key);
  }
  
  /**
   * <pre>移除对象</pre>
   * @param session
   * @param key
   */
  public static void remove(HttpSession session, String key) {
    session.removeAttribute(key);
  }

}
