package other.给图片添加文字水印;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TextWatermark {

    public static void main(String[] args) {
        Color color=new Color(52, 55, 255);
        Font font = new Font("微软雅黑", Font.BOLD, 40);
        waterMark("/Users/admin/Downloads/workspace/secondStudy/hxj/src/main/java/other/给图片添加文字水印/签章图片.png", "/Users/admin/Downloads/workspace/secondStudy/hxj/src/main/java/other/给图片添加文字水印/有水印的签章图片.png","这是一个文字水印",color,font,60,60);
    }

    /**
     * @param filePath 原图片
     * @param outFile  输出图片
     * @param text     水印文字
     * @param color    颜色
     * @param font     字体
     * @param x        水印起始X坐标
     * @param y        水印起始Y坐标
     * @return 添加水印是否成功 true-成功 false-失败
     */
    public static void waterMark(String filePath, String outFile, String text, Color color, Font font, int x, int y) {
        FileInputStream in = null;
        try {
            //将图片转化为buffer
            in = new FileInputStream(filePath);
            BufferedImage im = ImageIO.read(in);
            int width = im.getWidth();
            int height = im.getHeight();
            if (x > width || y > height) {
                return ;
            }

            //给图片数据创建画笔--增加各种操作
            Graphics g = im.getGraphics();
            g.setColor(color);
            g.setFont(font);
            g.drawString(text, x, y);
            g.dispose();

            //创建新的水印图片
            ImageIO.write(im, "png", new File(outFile));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
