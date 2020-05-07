package com.test.demo.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * 算法
 *
 * @author WangChao
 * @date 2020/04/29
 */
public class AlgorithmTest {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{2, 5, 3};
        int[] c = new int[]{4, 3, 2};

        System.out.println(max(a, b, c));

        ArrayList<int[]> list = new ArrayList<>();
        // 前边已占位置
        //HashSet set = new HashSet();

        /*int[] a = new int[]{1,2,3};
        int[] b = new int[]{2,5,3};
        int[] c = new int[]{4,3,2};*/

        list.add(a);
        list.add(b);
        list.add(c);

        /*int max = max(list, 0, 0);
        System.out.println(max);*/

        HashSet set1 = new HashSet();
        set1.add(0);
        //set.add(1);

        /*int l = sum(list, set1, 0, 0);
        System.out.println(l);*/

        int x = arrMax(set1, b, 3);
        System.out.println(x);
    }

    /**
     * 最大彩礼
     * @param a 王子a分别对公主的彩礼
     * @param b 王子b分别对公主的彩礼
     * @param c 王子c分别对公主的彩礼
     * @return
     */
    public static int max(int[] a, int[] b, int[] c) {
        // 最大彩礼
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (i == j) {
                    continue;
                }
                // 剩余一位对应关系自动确认
                int l = 0;
                for (int k = 0; k < c.length; k++) {
                    if (k != i && k != j) {
                        l = k;
                        break;
                    }
                }
                // 三位王子对公主彩礼合计
                int sum = a[i] + b[j] + c[l];
                if (max < sum) {
                    max = sum;
                }
            }
        }
        return max;
    }

    /**
     * 递归实现有问题
     * @param list
     * @param i
     * @param max
     * @return
     */
    public static int max(ArrayList<int[]> list, int i, int max) {
        // 每次组合结果
        int sum = 0;
        if (list.get(0).length > i) {
            HashSet set = new HashSet();
            sum = sum(list, set, i, 0);
            System.out.println("s " + sum);
            if (sum > max) {
                max = sum;
            }
            int max1 = max(list, i + 1, max);
            if (max1 > max) {
                max = max1;
            }
        }
        System.out.println("m " + max);
        return max;
    }

    public static int sum(ArrayList<int[]> list, HashSet set, int i, int n) {
        int max = 0;
        if (list.size() > n) {
            if (i == n) {
                set.add(i);
                int m1 = list.get(n)[i];
                max = m1 + sum(list, set, i, n + 1);
            } else {
                int m = arrMax(set, list.get(n), list.get(n).length);
                max = m + sum(list, set, i, n + 1);
            }
        }
        return max;
    }

    public static int arrMax(HashSet set, int[] a, int n) {
        System.out.println(set + " " + a + " " + n);
        if (n == 1 && !set.contains(0)) {
            return a[0];
        } else {
            if (!set.contains(n - 1) && a[n - 1] > arrMax(set, a, n - 1)) {
                set.add(n - 1);
                return a[n - 1];
            } else {
                return arrMax(set, a, n - 1);
            }
        }
    }

}
