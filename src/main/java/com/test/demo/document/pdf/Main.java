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
                "F:\\BaiduNetdiskDownload\\鲁班学院java架构师VIP第一期\\05.分布式专题（五）\\zookeeper\\第一节-分布式系统介绍以及zookeeper快速入门.pdf");
        System.out.println(res);
    }
}
