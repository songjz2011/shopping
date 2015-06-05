package com.shopping.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author royguo@rongxintx.com
 */
public class QueryBuilder {
  private final StringBuilder builder = new StringBuilder();
  private final List<Object> params = new ArrayList<>();

  public QueryBuilder() {

  }

  public QueryBuilder(Class<?> clazz) {
    builder.append("from ").append(clazz.getName()).append(" where ");
  }

  public void add(String connector, String field, String expression, Object value) {
    if (StringUtil.isNotBlank(value)) {
      if ("in".equalsIgnoreCase(expression)) {
        in(connector, field, expression, value);
        return;
      }
      if (params.size() > 0) {
        builder.append(connector + " ");
      }
      builder.append(field + " " + expression + " ? ");
      if ("like".equalsIgnoreCase(expression)) {
        params.add("%" + value + "%");
      } else {
        params.add(value);
      }
    }
  }

  /**
   * <pre>
   * 设置in类型的查询
   * </pre>
   * 
   * @param connector
   * @param field
   * @param expression
   * @param value
   */
  private void in(String connector, String field, String expression, Object value) {
    List<Object> list = buildValueListForIn(value);
    if (list.isEmpty()) {
      return;
    }
    if (params.size() > 0) {
      builder.append(connector + " ");
    }
    StringBuilder sql = new StringBuilder();
    for (Object obj : list) {
      sql.append(",?");
      params.add(obj);
    }
    builder.append(field).append(" ").append(expression).append(" (");
    builder.append(sql.substring(1)).append(") ");
  }

  /**
   * <pre>
   * 构建in类型的查询值
   * </pre>
   * 
   * @return
   */
  private List<Object> buildValueListForIn(Object value) {
    List<Object> list = new ArrayList<Object>();
    if (value instanceof Object[]) {
      Object[] objs = (Object[]) value;
      for (Object obj : objs) {
        if (StringUtil.isNotBlank(obj)) {
          list.add(obj);
        }
      }
    } else if (value instanceof Collection) {
      Collection<?> objs = (Collection<?>) value;
      for (Object obj : objs) {
        if (StringUtil.isNotBlank(obj)) {
          list.add(obj);
        }
      }
    } else {
      list.add(value);
    }
    return list;
  }

  public List<Object> getParams() {
    return params;
  }

  public String getQuery() {
    return builder.toString();
  }
}
