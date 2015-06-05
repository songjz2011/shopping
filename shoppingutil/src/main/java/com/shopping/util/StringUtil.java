package com.shopping.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.shopping.util.constant.UtilConstants;

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

  /**
   * @return an UUID String
   */
  public static String UUID() {
    return UUID.randomUUID().toString();
  }

  /**
   * Encode a String to base64
   * 
   * @param value
   *          The plain String
   * @return The base64 encoded String
   */
  public static String encodeBASE64(String value) {
    try {
      return new String(Base64.encodeBase64(value.getBytes(UtilConstants.charset())));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Encode binary data to base64
   * 
   * @param value
   *          The binary data
   * @return The base64 encoded String
   */
  public static String encodeBASE64(byte[] value) {
    return new String(Base64.encodeBase64(value));
  }

  /**
   * Decode a base64 value
   * 
   * @param value
   *          The base64 encoded String
   * @return decoded binary data
   */
  public static byte[] decodeBASE64(String value) {
    try {
      return Base64.decodeBase64(value.getBytes(UtilConstants.charset()));
    } catch (UnsupportedEncodingException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Build an hexadecimal MD5 hash for a String
   * 
   * @param value
   *          The String to hash
   * @return An hexadecimal Hash
   */
  public static String hexMD5(String value) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.reset();
      messageDigest.update(value.getBytes(UtilConstants.charset()));
      byte[] digest = messageDigest.digest();
      return byteToHexString(digest);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * 转成16进制 String.
   */
  public static String byteToHexString(byte[] bytes) {
    return String.valueOf(Hex.encodeHex(bytes));
  }

  /**
   * 转成16进制 byte.
   */
  public static byte[] hexStringToByte(String hexString) {
    try {
      return Hex.decodeHex(hexString.toCharArray());
    } catch (DecoderException e) {
      throw new RuntimeException(e);
    }
  }

}
