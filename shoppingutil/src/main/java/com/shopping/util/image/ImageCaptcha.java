package com.shopping.util.image;

import java.awt.image.RenderedImage;
import java.io.Serializable;

/**
 * 登录图片验证码
 * 
 * @author jzsong@uworks.cc
 */
public class ImageCaptcha implements Serializable{

  private static final long serialVersionUID = -5130032017654989818L;
  /**
   * 图片
   */
  public RenderedImage image;
  /**
   * 验证码
   */
  public String captcha;
}
