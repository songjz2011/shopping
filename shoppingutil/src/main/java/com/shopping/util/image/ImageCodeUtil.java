package com.shopping.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 用户helper
 * 
 * @author jzsong@uworks.cc
 */
public class ImageCodeUtil {

  private static final String VERIFY_CODES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

  public static ImageCaptcha getImageCaptcha(int width, int height) {
    ImageCaptcha imageCaptcha = new ImageCaptcha();
    imageCaptcha.captcha = generateVerifyCode();
    imageCaptcha.image = generateImage(width, height, imageCaptcha.captcha);
    return imageCaptcha;
  }

  /**
   * 使用指定源生成验证码
   * 
   * @return
   */
  private static String generateVerifyCode() {
    String sources = VERIFY_CODES;
    int verifySize = 4;
    int codesLen = sources.length();
    Random rand = new Random(System.currentTimeMillis());
    StringBuilder verifyCode = new StringBuilder();
    for (int i = 0; i < verifySize; i++) {
      verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
    }
    return verifyCode.toString();
  }

  /**
   * 获取图片验证码
   * 
   * @param width：图片验证码宽
   * @param height：图片验证码高
   * @param code
   * @return
   */
  public static BufferedImage generateImage(int width, int height, String code) {
    // 生成随机类
    Random random = new Random();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // 获取图形上下文
    Graphics g = image.getGraphics();
    // 设定背景色
    g.setColor(Color.GRAY);
    g.drawRect(0, 0, width, height);
    g.setColor(Color.white);
    g.fillRect(1, 1, width - 2, height - 2);
    // 设定字体
    g.setFont(new Font("宋体", Font.BOLD, 25));
    // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
    g.setColor(new Color(125, 93, 100));
    for (int i = 0; i < 50; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(i % 20 == 0 ? 0 : x, (i % 2 - 1) == 0 ? 0 : y, x + xl + 20, y + yl + 20);
    }
    char[] chars = code.toCharArray();
    int verifySize = code.length();
    for (int i = 0; i < verifySize; i++) {
      // 将认证码显示到图象中
      g.setColor(new Color(255, 0, 0));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
      g.drawString(String.valueOf(chars[i]), 23 * i + 6, 26);
    }
    // 图象生效
    g.dispose();
    return image;
  }

}
