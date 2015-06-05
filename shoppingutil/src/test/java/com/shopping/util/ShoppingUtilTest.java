package com.shopping.util;

import org.junit.Test;

import com.shopping.util.ShoppingUtil;

import static org.junit.Assert.*;

/**
 * @author songjz Jan 7, 2015
 */
public class ShoppingUtilTest {

  @Test
  public void testGetPropertyValue() {
    assertNotNull(ShoppingUtil.getPropertyValue("shopping.util.properties.junit.test.data"));
  }

}
