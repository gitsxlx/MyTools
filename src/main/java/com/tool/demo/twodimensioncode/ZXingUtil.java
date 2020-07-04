package com.tool.demo.twodimensioncode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * ZXing生成二维码 
 * 需要包com.google.zxing下core-3.4.0.jar，javase-3.4.0.jar
 * @author Mr.Wang
 * @date 2019年10月31日
 */
public class ZXingUtil {

    // logo默认边框颜色
    public static final Color DEFAULT_LOGO_BORDER_COLOR = Color.WHITE;
    // logo默认边框宽度
    public static final int DEFAULT_LOGO_BORDER = 1;
    // logo大小默认为照片的1/6
    public static final int DEFAULT_LOGOPART = 4;

    // logo边框宽度
    private final int border = DEFAULT_LOGO_BORDER;
    // 自定义颜色
    private final Color borderColor;
    // 自定义logo大小
    private final int logoPart;

    // 默认字体颜色
    private static final Color DEFAULT_TEXTCOLOR = Color.BLACK;

    /**
     * 颜色上创建一个默认配置,生成正常的黑白条码。
     */
    public ZXingUtil() {
        this(DEFAULT_LOGO_BORDER_COLOR, DEFAULT_LOGOPART);
    }

    public ZXingUtil(Color borderColor, int logoPart) {
        this.borderColor = borderColor;
        this.logoPart = logoPart;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorder() {
        return border;
    }

    public int getLogoPart() {
        return logoPart;
    }

    /**
     * 给二维码图片添加Logo
     * @param qrPic 二维码文件
     * @param logoPic logo文件
     */
    public static void addLogo_QRCode(File qrPic, File logoPic, ZXingUtil creatrQrCode) {
        try {
            if (!qrPic.isFile() || !logoPic.isFile()) {
                System.err.println("找不到对应文件！" + new Object[]{"qrPic", qrPic, "logoPic", logoPic});
                return;
            }
            // 读取二维码图片，并构建绘图对象
            BufferedImage image = ImageIO.read(qrPic);
            Graphics2D g = image.createGraphics();
            // 读取Logo图片
            BufferedImage logo = ImageIO.read(logoPic);
            // 保持二维码是正方形的
            int widthLogo = image.getWidth() / creatrQrCode.getLogoPart();
            int heightLogo = image.getWidth() / creatrQrCode.getLogoPart();
            // 计算图片放置位置
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;
            // 开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
            g.setStroke(new BasicStroke(creatrQrCode.getBorder()));
            g.setColor(creatrQrCode.getBorderColor());
            g.drawRect(x, y, widthLogo, heightLogo);
            g.dispose();
            ImageIO.write(image, "jpeg", qrPic);
        } catch (Exception e) {
            System.err.println(" 为二维码添加logo错误！" + e);
        }
    }

    /**
     * 二维码图片添加文字
     *
     * @param topText
     *            顶部文字
     * @param bottomText
     *            底部文字
     * @param newImg
     *            带文字的图片
     * @param targetImg
     *            需要添加文字的图片
     * @param fontStyle
     *            文字样式
     * @param color
     *            颜色
     * @param fontSize
     *            字体大小
     * @param width
     *            图片宽度
     * @param height
     *            图片高度
     *
     */
    public static void pressText(String topText, String bottomText, String newImg, String targetImg,
            int fontStyle,
            Color color, int fontSize, int width, int height) {
        // 底部分行
        String[] arr = bottomText.split("，");
        // 计算文字开始的位置
        // x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX0 = (width - (fontSize * topText.length())) / 2;
        int startX = (width - (fontSize * bottomText.length())) / 300 + 80;
        // y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height - (height - width) / 3;
        try {
            File file = new File(targetImg);
            Image src = ImageIO.read(file);
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(topText, startX0, fontSize * 3);
            g.drawString(arr[0], startX, startY);
            g.drawString(arr[1], startX, startY + fontSize);
            g.drawString(arr[2], startX, startY + fontSize * 2);
            g.drawString(arr[3], startX, startY + fontSize * 3);
            g.dispose();
            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.err.println(" 为二维码添加logo错误！" + e);
        }
    }

    /**
     * 生成二维码（无logo，无文字）
     *
     * @param qrcPath
     *            用来存放生成的二维码图片
     * @param content
     *            二维码表示的内容
     * @param width
     *            图片完整的宽
     * @param height
     *            图片完整的高 建议：如果要在二维码下方附上文字，把图片设置为长方形（高大于宽）
     */
    public void createQrCode(String qrcPath, String content, int width, int height) {
        try {
            File qrcFile = new File(qrcPath);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            // 设置UTF-8， 防止中文乱码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // 设置二维码四周白色区域的大小
            hints.put(EncodeHintType.MARGIN, 1);
            // 设置二维码的容错性
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            // 画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
            BitMatrix bitMatrix = multiFormatWriter
                    .encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 开始画二维码
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrcFile);
        } catch (Exception e) {
            System.err.println(" 为二维码添加logo错误！" + e);
        }
    }

    /**
     * 生成二维码（有logo，无文字）
     * @param qrcodePath 生成的二维码图片存放路径
     * @param content 二维码表示的内容
     * @param width 二维码图片完整的宽
     * @param height 二维码图片完整的高
     * @param logoPath 附加在二维码中的图片
     */
    public void createLogoQrCode(String qrcodePath, String content, int width, int height,
            String logoPath) {
        // 准备一张二维码
        createQrCode(qrcodePath, content, width, height);
        // 在二维码中加入图片
        ZXingUtil creatrQrCode = new ZXingUtil();
        File logoFile = new File(logoPath);
        File qrcFile = new File(qrcodePath);
        addLogo_QRCode(qrcFile, logoFile, creatrQrCode);
    }

    /**
     * 生成二维码（无logo，有文字）
     * @param newImageWithText 用来存放的带有文字的二维码图片
     * @param targetImage 原二维码图片
     * @param topText 附加在图片上的文字信息
     * @param bottomText 附加在图片上的文字信息
     * @param width 图片宽度（用来计算文字x开始位置）
     * @param height 图片高度（用来计算文字y开始位置）
     */
    public void createWordQrcode(String newImageWithText, String targetImage, String url,
            String topText,
            String bottomText, int width, int height) {
        // 准备一张二维码
        createQrCode(newImageWithText, url, width, height);
        // 字体大小
        int font = 20;
        // 字体风格
        int fontStyle = 4;
        // 在二维码下方添加文字（是否替换原图）
        if (org.apache.commons.lang.StringUtils.isNotBlank(targetImage)) {
            pressText(topText, bottomText, newImageWithText, targetImage, fontStyle, Color.red,
                    font, width, height);
        } else {
            pressText(topText, bottomText, newImageWithText, newImageWithText, fontStyle, Color.red,
                    font, width,
                    height);
        }
    }

    /**
     * 生成二维码（有logo，有文字）
     * @param newImageWithText 用来存放的带有文字的二维码图片
     * @param targetImage 原二维码图片
     * @param topText 附加在二维码上的文字信息
     * @param bottomText 附加在二维码上的文字信息
     * @param width 二维码图片宽度（用来计算文字x开始位置）
     * @param height 二维码图片高度（用来计算文字y开始位置）
     * @param logoPath 附加在二维码上的图片
     */
    public void createWordLogoQrcode(String newImageWithText, String targetImage, String url,
            String topText,
            String bottomText, int width, int height, String logoPath) {
        // 准备一张二维码
        createLogoQrCode(newImageWithText, url, width, height, logoPath);
        // 字体大小
        int font = 20;
        // 字体风格
        int fontStyle = 4;
        // 在二维码下方添加文字（是否替换原图）
        if (org.apache.commons.lang.StringUtils.isNotBlank(targetImage)) {
            pressText(topText, bottomText, newImageWithText, targetImage, fontStyle,
                    DEFAULT_TEXTCOLOR, font, width,
                    height);
        } else {
            pressText(topText, bottomText, newImageWithText, newImageWithText, fontStyle,
                    DEFAULT_TEXTCOLOR, font,
                    width, height);
        }
    }

    /**
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
     * @param path 存放目录
     */
    public static void mkdirs(String path) {
        // 得到文件对象
        File file = new File(path);
        // 判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                System.err.println("创建目标文件所在目录失败！");
                return;
            }
        }
    }

    public static void main(String args[]) {
        try {
            // 需要先有develop文件夹，否则报错
            ZXingUtil creatrQrCode = new ZXingUtil();
            // creatrQrCode.createQrCode("E:\\develop\\wu.png","单纯二维码：12345",300,300);
            // creatrQrCode.createLogoQrCode("E:\\develop\\have_logo.png","只有logo：12345",300,300,"E:\\develop\\logo.png");
            // creatrQrCode.createWordQrcode("E:\\develop\\have_word.png",
            // "","www.95081.com","管家汇支付二维码","订单号：608412968449306，订单金额：50000，订单类型：保姆，管家手机号：18888888888",
            // 400, 470);
            // creatrQrCode.createWordLogoQrcode("E:\\develop\\have_logo_word.png",
            // "","http://www.95081.com/","管家汇支付二维码","订单号：608412968449306，订单金额：50000，订单类型：保姆，管家手机号：18888888888",
            // 400, 590,"E:\\develop1\\logo.jpg");
            creatrQrCode.createWordLogoQrcode("E:\\develop\\have_logo_word.png", "",
                    "http://www.95081.com/", "", "",
                    400, 400, "E:\\develop1\\logo.jpg");
            System.out.println("Congratulations Success ！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
