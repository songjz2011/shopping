package shopping.util;

/**
 * @author songjz Dec 13, 2014
 */
public class StringUtil {

  /**
   * <pre>
   * 判断是否为空，为空则返回true
   * 为空的条件：null、""、"null"
   * </pre>
   * 
   * @param obj
   * @return
   */
  public static boolean isBlank(Object obj) {
    if (obj == null) {
      return true;
    }
    String str = obj.toString().trim();
    if ("".equals(str) || "null".equalsIgnoreCase(str)) {
      return true;
    }
    return false;
  }

  /**
   * <pre>
   * 判断是否不为空，不为空则返回true
   * 为空的条件：null、""、"null"
   * </pre>
   * 
   * @param obj
   * @return
   */
  public static boolean isNotBlank(Object obj) {
    return !isBlank(obj);
  }

  /**
   * <pre>
   * 获取换行符
   * </pre>
   * 
   * @return
   */
  public static String getNewLine() {
    return System.getProperty("line.separator");
  }

}
