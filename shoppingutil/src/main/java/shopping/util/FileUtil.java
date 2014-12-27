package shopping.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 *
 * @author jzsong@rongxintx.com
 */
public class FileUtil {

  /**
   * 默认编码格式(utf-8)
   */
  public static Charset defalutChartset = Charset.forName("utf-8");
  
  /**
   * 关闭输出流
   * 
   * @param streams
   */
  public static void closeOutputStream(OutputStream... streams) {
    for (OutputStream stream : streams) {
      try {
        if (stream != null) {
          stream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 关闭输入流
   * 
   * @param streams
   */
  public static void closeInputStream(InputStream... streams) {
    for (InputStream stream : streams) {
      try {
        if (stream != null) {
          stream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 关闭Reader流
   * 
   * @param streams
   */
  public static void closeReader(Reader... readers) {
    for (Reader reader : readers) {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 关闭Writer流
   * 
   * @param streams
   */
  public static void closeWriter(Writer... writers) {
    for (Writer writer : writers) {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 读取文件内容(编码格式为utf-8)
   * 
   * @param file
   * @return
   */
  public static String readContent(File file) {
    try {
      return readContent(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 读取文件内容(编码格式为utf-8)，并且关闭参数InputStream流
   * 
   * @param file
   * @return
   */
  public static String readContent(InputStream is) {
    StringBuilder content = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, defalutChartset));
      String line = null;
      while ((line = reader.readLine()) != null) {
        content.append(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeInputStream(is);
    }
    return content.toString();
  }

}
