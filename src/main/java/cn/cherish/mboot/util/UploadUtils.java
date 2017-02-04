package cn.cherish.mboot.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Calendar;

public class UploadUtils {

    public static String uploadImg(MultipartFile file, String path, int width) {
        String originalFilename = file.getOriginalFilename();
        String names[] = originalFilename.split("\\.");
        String suffix = names[names.length - 1];
        String fileName = Calendar.getInstance().getTimeInMillis() + "." + suffix;// 文件名

        try {
            InputStream is = file.getInputStream();
            File f = new File(path + "/" + fileName); // 声明File对象
            // 第2步、通过子类实例化父类对象
            OutputStream os = null; // 准备好一个输出的对象
            os = new FileOutputStream(f); // 通过对象多态性，进行实例化
            resizeImage(is, os, width, "JPEG");
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String uploadImg(MultipartFile file, String path) {
        String originalFilename = file.getOriginalFilename();
        String names[] = originalFilename.split("\\.");
        String suffix = names[names.length - 1];
        String fileName = Calendar.getInstance().getTimeInMillis() + "." + suffix;// 文件名
        File f = new File(path + "/" + fileName);
        try {
            file.transferTo(f);
            return fileName;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static boolean base64Str2Image(String imgStr, String imgFilePath, String fileName) {
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null)
            return false;
        OutputStream out = null;
        try {
            // Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    // 调整异常数据
                    b[i] += 256;
                }
            }
            File file = new File(imgFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 生成Jpeg图片
            out = new FileOutputStream(imgFilePath + fileName);
            out.write(b);
            out.flush();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void resizeImage(InputStream is, OutputStream os, int size, String format) throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        double width = prevImage.getWidth();
        double height = prevImage.getHeight();
        double percent = size / width;
        int newWidth = (int) (width * percent);
        int newHeight = (int) (height * percent);
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        is.close();
        os.close();
    }
}
