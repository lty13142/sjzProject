package com.crcm.core.utils;

import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @ClassName: QrcodeUtil
 * @Description 二维码工具类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Administrator
 * @Date 2020/1/2
 **/
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;

    /**
     * 生成图片
     * @param content 内容
     * @param imgPath 图片地址
     * @param needCompress 是否压缩
     * @param imgWidth 二维码宽度
     * @param ingHeight 二维码高度
     * @param logoWidth log宽度
     * @param logoHeight log高度
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress, Integer imgWidth,
                                             Integer ingHeight, Integer logoWidth, Integer logoHeight) throws Exception {
        if (null == imgWidth) {
            imgWidth = QRCODE_SIZE;
        }
        if (null == ingHeight) {
            ingHeight = QRCODE_SIZE;
        }
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, imgWidth, ingHeight,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress,logoWidth,logoHeight);
        return image;
    }

    /**
     * 生成图片
     * @param content 内容
     * @param imgPath 图片地址
     * @param needCompress 是否压缩
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        return QRCodeUtil.createImage(content, imgPath, needCompress,null,null,null,null);
    }

    /**
     * 在图片中插入图片
     * @param source 源图片流
     * @param imgPath 图片地址
     * @param needCompress 是否压缩
     * @param logoWidth log宽度
     * @param logoHeight log高度
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress, Integer logoWidth, Integer logoHeight) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        if (null == logoWidth) {
            logoWidth = WIDTH;
        }
        if (null == logoHeight) {
            logoHeight = HEIGHT;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > logoWidth) {
                width = logoWidth;
            }
            if (height > logoHeight) {
                height = logoHeight;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 为图片添加文字
     *
     * @param pressText   文字
     * @param targetImage 需要添加文字的图片
     * @param fontStyle   字体风格
     * @param color       字体颜色
     * @param fontSize    字体大小
     * @param width       图片宽度
     * @param height      图片高度
     */
    public static void pressText(String pressText, BufferedImage targetImage, OutputStream output, int fontStyle, Color color, int fontSize, int width, int height) {
        // 计算文字开始的位置
        // x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (width - (fontSize * pressText.length())) / 2;
        // y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height - (height - width) / 2 + fontSize;
        try {
            int imageW = targetImage.getWidth(null);
            int imageH = targetImage.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(targetImage, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(pressText, startX, startY);
            g.dispose();
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成二维码到本地地址
     * @param content 二维码内容
     * @param imgPath log地址，可以为空
     * @param destPath 输出地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }


    /**
     * 生成二维码图片流
     * @param content 二维码内容
     * @param imgPath log地址
     * @param needCompress 是否需要压缩
     * @return
     * @throws Exception
     */
    public static BufferedImage encode(String content, String imgPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        return image;
    }

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 通过输出流生成带log的二维码
     * @param content 二维码内容
     * @param imgPath log地址
     * @param output 输出流
     * @param needCompress 是否需要压缩
     * @throws Exception
     */
    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
            throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 通过输出流生成二维码
     * @param content 二维码内容
     * @param output 输出流
     * @throws Exception
     */
    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    /**
     * 通过输出流生成二维码
     * @param content 二维码内容
     * @param output 输出流
     * @param imgPath log地址,为空则不带logo
     * @param needCompress 是否压缩
     * @param imgWidth 二维码宽度
     * @param ingHeight 二维码高度
     * @param logoWidth log宽度
     * @param logoHeight log高度
     * @param output 输出流
     * @throws Exception
     */
    public static void encode(String content,String imgPath,boolean needCompress, Integer imgWidth,
                              Integer ingHeight, Integer logoWidth, Integer logoHeight,
                              OutputStream output) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress,imgWidth,ingHeight,logoWidth,logoHeight);
        ImageIO.write(image, FORMAT_NAME, output);
    }


    /**
     * 识别二维码
     * @param bufferedImage 需要识别的二维码
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public String decode(BufferedImage bufferedImage) throws NotFoundException {
        //BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filepath));
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        HashMap<DecodeHintType, Object> decodeHints = new HashMap<>();
        decodeHints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        Result result = new MultiFormatReader().decode(bitmap, decodeHints);
        return result.getText();
    }
}
