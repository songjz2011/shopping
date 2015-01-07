package shopping.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @author songjz Jan 7, 2015
 */
public class ShoppingUtil {

  private static Map<String, String> map = new HashMap<String, String>();

  private static final String filePath = "shopping-util.properties";

  static {
    if (map.isEmpty()) {
      initData();
    }
  }

  /**
   * 获取配置项属性值
   * 
   * @param key
   * @return
   */
  public static String getPropertyValue(String key) {
    if (map.isEmpty()) {
      initData();
    }
    if (StringUtil.isBlank(key)) {
      return null;
    }
    return map.get(key);
  }

  /**
   * <pre>
   * 初始化配置项数据
   * </pre>
   */
  private static void initData() {
    InputStream is = null;
    try {
      Properties prop = new Properties();
      is = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream(filePath);
      prop.load(is);
      for (Entry<Object, Object> entry : prop.entrySet()) {
        if (entry.getKey() == null) {
          continue;
        }
        String value = null;
        if (entry.getValue() != null) {
          value = entry.getValue().toString();
        }
        map.put(entry.getKey().toString(), value);
      }
    } catch (Exception e) {
      LoggerUtil.info(e.getMessage());
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          LoggerUtil.info(e.getMessage());
        }
      }
    }
  }
}
