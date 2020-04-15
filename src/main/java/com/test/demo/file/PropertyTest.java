package com.test.demo.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

/**
 * java操作properties文件
 *
 * @author Wang
 * @date 2018年8月7日
 */
public class PropertyTest {

    //获取文件的方式
    public static void getIS(String path) throws Exception {
        Properties prop = new Properties();
        InputStream is = null;
        //方式1
        is = new FileInputStream(path);
        //方式2
        //is = PropertyTest.class.getResourceAsStream(path);
        //方式3
        //is = PropertyTest.class.getClassLoader().getResourceAsStream(path);
        //方法4
        //is = ClassLoader.getSystemResourceAsStream(path);
        prop.load(is);
        is.close();
        //方法5
        //ResourceBundle rb = ResourceBundle.getBundle(path);
        //String password = rb.getString("password");
        //方法6
        //InputStream is = new FileInputStream(path);
        //ResourceBundle rb = new PropertyResourceBundle(is);
        //String nickname = rb.getString("nickname");
    }

    /**
     * 向Properties文件中添加键值对，若已存在会覆盖
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2018年8月8日
     */
    public static void add(String path, String key, String value) {
		if (StringUtils.isBlank(path)) {
			return;
		}
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("创建文件异常" + path);
                e.printStackTrace();
            }
        }
        Properties prop = getProperties(path); //获取
        prop.setProperty(key, value);  //设置
        setProperties(path, prop);  //写入
        System.out.println("添加成功！");
    }

    /**
     * 批量向Properties文件中添加键值对，若已存在会覆盖
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2018年8月8日
     */
    public static void add(String path, HashMap<String, Object> map) {
		if (StringUtils.isBlank(path)) {
			return;
		}
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("创建文件异常" + path);
                e.printStackTrace();
            }
        }
        Properties prop = getProperties(path); //获取
        //方式1
		/*Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			prop.setProperty(entry.getKey(), entry.getValue().toString());  //设置
		}*/
        //方式2
        map.forEach((key, value) -> prop.setProperty(key, value.toString()));

        setProperties(path, prop);  //写入
        System.out.println("添加成功！");
    }

    /**
     * 获取Properties文件中的value
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2018年8月8日
     */
    public static String get(String path, String key) {
        String value = null;
        Properties prop = getProperties(path); //获取
        value = prop.getProperty(key);  //获得值
        return value;
    }

    /**
     * 更新Properties文件中key对应的value,key对应数据不存在不会替换或添加
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2018年8月8日
     */
    public static void update(String path, String key, String value) {
        Properties prop = getProperties(path); //获取
        prop.replace(key, value);  //更新
        setProperties(path, prop);  //写入
        System.out.println("更新成功！");
    }

    /**
     * 移除Properties中的键值对,不存在不会做处理
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2018年8月8日
     */
    public static void remove(String path, String key) {
        Properties prop = getProperties(path); //获取
        prop.remove(key);  //移除
        setProperties(path, prop);  //写入
        System.out.println("移除成功！");
    }

    /**
     * 清除除Properties中的键值对
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2018年8月8日
     */
    public static void clear(String path) {
        Properties prop = getProperties(path); //获取
        prop.clear();  //清除
        setProperties(path, prop);  //写入
        System.out.println("清除成功！");
    }

    /**
     * 获取Properties对象
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2018年8月8日
     */
    public static Properties getProperties(String path) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            prop.load(is);
        } catch (FileNotFoundException e) {
            System.out.println("未找到" + path);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    /**
     * 写入Properties文件
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2018年8月8日
     */
    public static void setProperties(String path, Properties prop) {
        FileOutputStream fos = null;
        try {
            //fos = new FileOutputStream(path, true);
            fos = new FileOutputStream(path);
            prop.store(fos, "set");
        } catch (FileNotFoundException e) {
            System.out.println("未找到" + path);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String path = "db.properties";
        String key = "jdbc.aaa";
        String value = "bbbb";
        //add(path, key, value);
        //String val = get(path, key);
        //System.out.println(val);
        //update(path, key, value);
        //remove(path, key);
        //HashMap<String, Object> hashMap = new HashMap<>();
        //hashMap.put(key, value);
        //clear(path);
    }

    public static void main1(String[] args) {
        Properties prop = new Properties();
        try {
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream("a.properties"));
            prop.load(in);     ///加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                System.out.println(key + ":" + prop.getProperty(key));
                if ("phone".equals(key)) {
                    prop.remove(key);
                }
            }
            in.close();

            ///保存属性到b.properties文件
            //FileOutputStream oFile = new FileOutputStream("a.properties", true);//true表示追加打开
            FileOutputStream oFile = new FileOutputStream("a.properties");//true表示追加打开
            prop.setProperty("phone", "10086");
            prop.setProperty("jdbc.user", "123456");
            prop.put("jdbc.dialect", "MySQLDialect");
            prop.replace("jdbc.url", "jdbc:mysql://127.0.0.1:3306/book");
            prop.remove("jdbc.password");
            //prop.clear();
            prop.store(oFile, "The New properties file");
            oFile.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }
    }

}