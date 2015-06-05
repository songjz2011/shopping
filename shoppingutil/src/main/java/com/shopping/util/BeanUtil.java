package com.shopping.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author songjz Dec 17, 2014
 */
public class BeanUtil {

  /**
   * <pre>
   * 拷贝简单属性的值
   * </pre>
   * 
   * @param source
   * @param target
   */
  public static void copySimpleProperties(Object source, Object target) {
    if (source == null || target == null) {
      return;
    }
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
      PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor pd : pds) {
        Method writeMethod = pd.getWriteMethod();
        if (writeMethod == null) {
          continue;
        }
        Class<?>[] writeParameterTypes = writeMethod.getParameterTypes();
        if (writeParameterTypes == null || writeParameterTypes.length == 0) {
          continue;
        }
        boolean isFitfullProperty = true;
        for (Class<?> c : writeParameterTypes) {
          if (!isFitfullProperty(c)) {
            isFitfullProperty = false;
            break;
          }
        }
        if (!isFitfullProperty) {
          continue;
        }
        PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), pd.getName());
        if (sourcePd == null) {
          continue;
        }
        Method readMethod = sourcePd.getReadMethod();
        if (readMethod == null || !isFitfullProperty(readMethod.getReturnType())) {
          continue;
        }
        readMethod.setAccessible(true);
        Object value = readMethod.invoke(source);

        writeMethod.setAccessible(true);
        writeMethod.invoke(target, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * <pre>
   * 获取PropertyDescriptors
   * </pre>
   * 
   * @param clazz
   * @return
   * @throws IntrospectionException
   */
  private static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz)
      throws IntrospectionException {
    BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    return pds;
  }

  /**
   * <pre>
   * 获取PropertyDescriptor
   * </pre>
   * 
   * @param clazz
   * @param propertyName
   * @return
   * @throws IntrospectionException
   */
  private static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName)
      throws IntrospectionException {
    PropertyDescriptor[] pds = getPropertyDescriptors(clazz);
    for (PropertyDescriptor pd : pds) {
      if (pd.getName().equalsIgnoreCase(propertyName)) {
        return pd;
      }
    }
    return null;
  }

  /**
   * 是否是合适的简单类型, 即判断 java.lang.* 或 java.util.Date 或 java.math.BigDecimal
   * 
   * @param field
   * @return
   */
  private static boolean isFitfullProperty(Class<?> field) {
    if (field.isPrimitive()
        || field == Date.class
        || field == BigDecimal.class
        || field.getName().indexOf("java.lang.") == 0) {
      return true;
    }
    return false;
  }

}
