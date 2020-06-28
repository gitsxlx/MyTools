package com.test.demo.document.pdf;

/**
 * Main
 * pom中引入Spire.Doc.jar和Spire.Pdf.jar
 * jdk1.9及以上需要引入jaxb相关包
 *
 * @author WangChao
 * @date 2020/06/20
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("开始转换...");
        String res = new PdfToWord().pdftoword(
                "D:\\software\\技术\\消息中间件\\RocketMQ\\【课堂笔记】RocketMQ基本原理分析.pdf");
        System.out.println(res);
    }
}
