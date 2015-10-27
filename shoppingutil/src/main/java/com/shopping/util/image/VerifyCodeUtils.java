package com.shopping.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Random;

/**
 * 
 * @author song
 */
public class VerifyCodeUtils {

  // 使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
  private static final String VERIFY_CODES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
  private static Random random = new Random();

  /**
   * 获取图片验证码
   * 
   * @param width：图片验证码宽
   * @param height：图片验证码高
   * @return
   */
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
   * 生成指定验证码图像文件
   * 
   * @param imageWidth
   * @param imageHeight
   * @param code
   */
  private static RenderedImage generateImage(int imageWidth, int imageHeight, String code) {
    BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    Random rand = new Random();
    Graphics2D g2 = image.createGraphics();
    //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(Color.GRAY);// 设置边框色
    g2.fillRect(0, 0, imageWidth, imageHeight);

    Color c = getRandColor(200, 250);
    g2.setColor(c);// 设置背景色
    g2.fillRect(0, 2, imageWidth, imageHeight - 4);

    // 绘制干扰线
    Random random = new Random();
    g2.setColor(getRandColor(160, 200));// 设置线条的颜色
    for (int i = 0; i < 20; i++) {
      int x = random.nextInt(imageWidth - 1);
      int y = random.nextInt(imageHeight - 1);
      int xl = random.nextInt(6) + 1;
      int yl = random.nextInt(12) + 1;
      g2.drawLine(x, y, x + xl + 40, y + yl + 20);
    }

    // 添加噪点
    float yawpRate = 0.05f;// 噪声率
    int area = (int) (yawpRate * imageWidth * imageHeight);
    for (int i = 0; i < area; i++) {
      int x = random.nextInt(imageWidth);
      int y = random.nextInt(imageHeight);
      int rgb = getRandomIntColor();
      image.setRGB(x, y, rgb);
    }

    shear(g2, imageWidth, imageHeight, c);// 使图片扭曲

    g2.setColor(getRandColor(100, 160));
    int fontSize = imageHeight - 4;
    Font font = new Font("Algerian", Font.CENTER_BASELINE, fontSize);
    g2.setFont(font);
    char[] chars = code.toCharArray();
    int verifySize = code.length();
    for (int i = 0; i < verifySize; i++) {
//      AffineTransform affine = new AffineTransform();
//      affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
//          (imageWidth / verifySize) * i + fontSize / 2, imageHeight / 2);
//      g2.setTransform(affine);
      g2.drawChars(chars, i, 1, ((imageWidth - 10) / verifySize) * i + 5, imageHeight / 2 + fontSize / 2 - 10);
    }
    // 图象生效
    g2.dispose();
    return image;
  }

  private static Color getRandColor(int fc, int bc) {
    if (fc > 255)
      fc = 255;
    if (bc > 255)
      bc = 255;
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }

  private static int getRandomIntColor() {
    int[] rgb = getRandomRgb();
    int color = 0;
    for (int c : rgb) {
      color = color << 8;
      color = color | c;
    }
    return color;
  }

  private static int[] getRandomRgb() {
    int[] rgb = new int[3];
    for (int i = 0; i < 3; i++) {
      rgb[i] = random.nextInt(255);
    }
    return rgb;
  }

  private static void shear(Graphics g, int w1, int h1, Color color) {
    shearX(g, w1, h1, color);
    shearY(g, w1, h1, color);
  }

  private static void shearX(Graphics g, int w1, int h1, Color color) {

    int period = random.nextInt(2);

    boolean borderGap = true;
    int frames = 1;
    int phase = random.nextInt(2);

    for (int i = 0; i < h1; i++) {
      double d = (double) (period >> 1)
          * Math.sin((double) i / (double) period
              + (6.2831853071795862D * (double) phase)
                  / (double) frames);
      g.copyArea(0, i, w1, 1, (int) d, 0);
      if (borderGap) {
        g.setColor(color);
        g.drawLine((int) d, i, 0, i);
        g.drawLine((int) d + w1, i, w1, i);
      }
    }

  }

  private static void shearY(Graphics g, int w1, int h1, Color color) {

    int period = random.nextInt(40) + 10; // 50;

    boolean borderGap = true;
    int frames = 20;
    int phase = 7;
    for (int i = 0; i < w1; i++) {
      double d = (double) (period >> 1)
          * Math.sin((double) i / (double) period
              + (6.2831853071795862D * (double) phase)
                  / (double) frames);
      g.copyArea(i, 0, 1, h1, 0, (int) d);
      if (borderGap) {
        g.setColor(color);
        g.drawLine(i, (int) d, i, 0);
        g.drawLine(i, (int) d + h1, i, h1);
      }

    }

  }


}
