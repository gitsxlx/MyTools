package com.tool.demo.math;

import java.util.Random;

/**
 * Math
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class MathUtil {

	public static Random random = new Random(System.currentTimeMillis());

	/**
	 * randomInt
	 * @param max
	 * @return
	 */
	public static int randomInt(int max) {
		return (int) (Math.random() * (max));
	}

	/**
	 * randomNumElement
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public static int randomNumElement(int max) throws Exception {
		if (max <= 0) {
			throw new Exception("生成位数不能小于等于0");
		} else {
			return (int) ((Math.random() * 9 + 1) * Math.pow(10, max - 1));
		}

	}

	/**
	 * randomLong
	 * @return
	 */
	public static long randomLong() {
		return random.nextLong();
	}

	/**
	 * randomDouble
	 * @return
	 */
	public static double randomDouble() {
		return random.nextDouble();
	}

	public static void main(String[] args) {
		System.out.println(3 * 0.1);  //0.30000000000000004
		System.out.println(4 * 0.1);  //0.4
		System.out.println(3 * 0.1 == 0.3);  //false
		System.out.println(13 * 0.1 == 1.3);  //true
		System.out.println(9 * 0.1 == 0.9);  //true
		System.out.println(3 * 0.1 / 3);  //0.10000000000000002
	}

}
