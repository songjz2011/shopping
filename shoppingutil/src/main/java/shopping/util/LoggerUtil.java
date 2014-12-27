package shopping.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 日志工具类
 * </pre>
 * 
 * @author songjz
 */
public class LoggerUtil {
  private LoggerUtil() {
  }

  public static void warn(Class<?> clazz, Throwable t) {
    warn(clazz, parseLog(t));
  }

  public static void warn(Class<?> clazz, String message) {
    getLogger(clazz).warn(message);
  }

  public static void debug(Class<?> clazz, Throwable t) {
    debug(clazz, parseLog(t));
  }

  public static void debug(Class<?> clazz, String message) {
    getLogger(clazz).debug(message);
  }

  public static void info(Class<?> clazz, Throwable t) {
    info(clazz, parseLog(t));
  }

  public static void info(Class<?> clazz, String message) {
    getLogger(clazz).info(message);
  }

  public static void error(Class<?> clazz, String message, Throwable t) {
    getLogger(clazz).error(message, t);
  }

  public static void error(Class<?> clazz, String message) {
    getLogger(clazz).error(message);
  }

  public static void error(Class<?> clazz, Throwable t) {
    getLogger(clazz).error(t.getMessage(), t);
  }

  private static Logger getLogger(Class<?> clazz) {
    return LoggerFactory.getLogger(clazz);
  }

  /**
   * 将指定的异常信息转换成字符串。
   * 
   * @param e
   * @return String
   */
  private static String parseLog(Throwable e) {
    return e.getMessage();
  }

}
