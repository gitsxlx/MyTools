package com.test.demo.file;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public static void main1(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:/a.txt");//创建文本文件
            int i = 0;
            while (list != null && i < list.size()) {//循环写入
                System.out.println(list.get(i));
                fileWriter.write(list.get(i) + "\r\n");//写入 \r\n换行
                i++;
            }
            fileWriter.write("共" + i + "条");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("size:" + list.size());

    }

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal(0);
        System.out.println(bd);
        bd.add(new BigDecimal(148));
        System.out.println(bd);

        bd = bd.add(new BigDecimal(100));
        System.out.println(bd);
    }

}
