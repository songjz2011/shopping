package com.shopping.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shopping.util.StringUtil;

/**
 * @author songjz Dec 13, 2014
 */
public class StringUtilTest {

  @Test
  public void isBlankTest() {
    assertTrue(StringUtil.isBlank(null));
  }

}
