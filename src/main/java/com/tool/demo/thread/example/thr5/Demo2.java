package com.tool.demo.thread.example.thr5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch对一个文本中所有数字并行求和
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo2 {

    private int[] nums;

    public Demo2(int line) {
        nums = new int[line];
    }

    public void calc(String line, int index, CountDownLatch cdl) {
        String[] nus = line.split(","); //切分出每个值
        int total = 0;
        for (String num : nus) {
            total += Integer.parseInt(num);
        }
        nums[index] = total;  //把计算结果放到数组中的指定位置
        System.out.println(Thread.currentThread().getName() + " 执行计算任务..." + line + " 结果为" + total);
        cdl.countDown();
    }

    public void sum() {
        System.out.println("汇总线程开始执行...");
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        System.out.println("最终的结果为：" + total);
    }

    public static void main(String[] args) {
        List<String> contents = readFile();
        int lineCount = contents.size();

        CountDownLatch cdl = new CountDownLatch(lineCount);

        Demo2 d = new Demo2(lineCount);

        for (int i = 0; i < lineCount; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.calc(contents.get(j), j, cdl);
                }
            }).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        d.sum();
    }

    private static List<String> readFile() {
        List<String> contents = new ArrayList<>();
        String line = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\nums.txt"));
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return contents;
    }

}
