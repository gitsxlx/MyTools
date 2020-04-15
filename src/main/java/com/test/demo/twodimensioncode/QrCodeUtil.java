package com.test.demo.twodimensioncode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

/**
 * QRCode
 * 需要引入外部包Qrcode.jar，解析包qrcode-R.jar（原名qrcode.jar）
 * @author Mr.Wang
 * @date 2019年10月31日
 */
public class QrCodeUtil {

    /**
     * 生成二维码
     * @author LiJun
     * @Date 2019/6/20
     * @Time 15:57
     * @param content ：二维码中的内容
     * @param imgPath ：二维码生成的位置
     * @param version ：版本
     * @return void
     */
    public static void encoderQRCode(String content, String imgPath, int version) {
        try {
            Qrcode qrcodeHandler = new Qrcode();
            //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            //N代表数字,A代表字符a-Z,B代表其他字符
            qrcodeHandler.setQrcodeEncodeMode('B');
            //版本1为21*21矩阵，版本每增1，二维码的两个边长都增4；所以版本7为45*45的矩阵；最高版本为是40，是177*177的矩阵
            qrcodeHandler.setQrcodeVersion(version);
            int imgSize = 67 + 12 * (version - 1);
            System.out.println("二维码中的内容：" + content);
            byte[] contentBytes = content.getBytes("gb2312");

            BufferedImage bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();

            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);

            //设定图像颜色 > BLACK
            gs.setColor(Color.BLACK);

            //设置偏移量 不设置可能导致解析出错
            int pixoff = 2;
            //输出内容 > 二维码
            System.out.println("二维码中内容的长度：" + contentBytes.length);
            if (contentBytes.length > 0 && contentBytes.length < 130) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                System.err.println("QRCode content bytes length = "
                        + contentBytes.length + " not in [ 0,130 ]. ");
            }

            gs.dispose();
            bufImg.flush();

            File imgFile = new File(imgPath);

            // 生成二维码QRCode图片
            ImageIO.write(bufImg, "png", imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析二维码中的内容
     * @author LiJun
     * @Date 2019/6/20
     * @Time 15:56
     * @param imgPath ：二维码的位置
     * @return java.lang.String
     */
    public static String decoderQRCode(String imgPath) {
        // QRCode 二维码图片的文件
        File imageFile = new File(imgPath);

        BufferedImage bufImg = null;
        String decodedData = null;
        try {
            bufImg = ImageIO.read(imageFile);

            QRCodeDecoder decoder = new QRCodeDecoder();
            decodedData = new String(decoder.decode(new J2SEImage(bufImg)), "gb2312");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (DecodingFailedException dfe) {
            System.out.println("Error: " + dfe.getMessage());
            dfe.printStackTrace();
        }
        return decodedData;
    }

    static class J2SEImage implements QRCodeImage {

        BufferedImage bufImg;

        public J2SEImage(BufferedImage bufImg) {
            this.bufImg = bufImg;
        }

        @Override
        public int getWidth() {
            return bufImg.getWidth();
        }

        @Override
        public int getHeight() {
            return bufImg.getHeight();
        }

        @Override
        public int getPixel(int x, int y) {
            return bufImg.getRGB(x, y);
        }
    }

    public static void main(String[] args) {
        String content = "https://github.com/gitsxlx/";
        String imgPath = "D:/picture/test_qrcode.png";

        encoderQRCode(content, imgPath, 8);
        System.out.println("encoder QRcode success");

        String imgPath1 = "D:/picture/test_qrcode.png";

        System.out.println("二维码中的内容：" + decoderQRCode(imgPath1));
        System.out.println("decoderQRCode QRcode success");
    }

}
