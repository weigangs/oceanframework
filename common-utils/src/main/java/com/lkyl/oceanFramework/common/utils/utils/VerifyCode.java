package com.lkyl.oceanframework.common.utils.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wangyfbe
 * @description
 * @date 2020/1/2 14:15
 */
public class VerifyCode {
  private int width = 100;    //生成验证码图片的宽度
  private int height = 40;    //生成验证码图片的高度
  private String[] fontNames = {"宋体", "楷体", "隶书", "微软雅黑"};
  private Color bgColor = new Color(255, 255, 255); //定义验证码图片的背景色
  private static final String codes = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private String text;    //记录随机字符串

  /**
   * 获取随机一个颜色
   *
   * @return
   */
  private Color randomColor() {
    int red = ThreadLocalRandom.current().nextInt(150);
    int green = ThreadLocalRandom.current().nextInt(150);
    int blue = ThreadLocalRandom.current().nextInt(150);
    return new Color(red, green, blue);
  }

  /**
   * 获取随机一个字体
   *
   * @return
   */
  private Font randomFont() {
    String name = fontNames[ThreadLocalRandom.current().nextInt(fontNames.length)];
    int style = ThreadLocalRandom.current().nextInt(4);
    int size = ThreadLocalRandom.current().nextInt(5) + 24;
    return new Font(name, style, size);
  }

  /**
   * 获取随机一个字符
   *
   * @return
   */
  private char randomChar() {
    return codes.charAt(ThreadLocalRandom.current().nextInt(codes.length()));
  }

  /**
   * 创建一个空白的BufferedImage对象
   *
   * @return
   */
  private BufferedImage createImage() {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = (Graphics2D) image.getGraphics();
    g2.setColor(bgColor);
    g2.fillRect(0, 0, width, height);
    return image;
  }

  /**
   * 绘制干扰线
   */
  private void drawLine(BufferedImage image) {
    Graphics2D g2 = (Graphics2D) image.getGraphics();
    int num = 5;
    for (int i = 0; i < num; i++) {
      int x1 = ThreadLocalRandom.current().nextInt(width);
      int y1 = ThreadLocalRandom.current().nextInt(height);
      int x2 = ThreadLocalRandom.current().nextInt(width);
      int y2 = ThreadLocalRandom.current().nextInt(height);
      g2.setColor(randomColor());
      g2.setStroke(new BasicStroke(1.5f));
      g2.drawLine(x1, y1, x2, y2);
    }
  }

  /**
   * 绘制验证码
   *
   * @return
   */
  public BufferedImage getImage() {
    BufferedImage image = createImage();
    Graphics2D g2 = (Graphics2D) image.getGraphics();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 4; i++) {
      String s = randomChar() + "";
      sb.append(s);
      g2.setColor(randomColor());
      g2.setFont(randomFont());
      float x = i * width * 1.0f / 4;
      g2.drawString(s, x, height - 15);
    }
    this.text = sb.toString();
    drawLine(image);
    return image;
  }

  public String getText() {
    return text;
  }

  /**
   * 用流传输方法
   *
   * @return
   */
  public static void output(BufferedImage image, OutputStream out) throws IOException {
    ImageIO.write(image, "JPEG", out);
  }
}
