package com.tool.demo.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 加密、解密
 *
 * 可以封装成工具类
 * 可以使用工厂模式
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class EncryptTest {
    /*1、BASE64：编码方式（8位字节代码），二进制与字符串相互转换 
    2、MD5：Message-Digest Algorithm（信息摘要算法第五版），散列函数（哈希算法），不可逆，压缩性。
    3、SHA：Secure Hash Algorithm（安全散列算法），SHA家族的五个算法，分别是SHA-1、SHA-224、SHA-256、SHA-384，和SHA-512。
    4、DES：Data Encrytion Standard（数据加密标准），对应算法是DEA
        特点：1. 对称加密 2. 同一个SK
    5、AES：Advanced Encrytion Standard（高级加密标准）
        特点：1. 对称加密 2. 一个SK扩展成多个子SK，轮加密
    6、RSA（提出三人姓氏开头字母拼在一起组成的，SSL使用的就是）：
        特点： 1. 非对称加密，即：PK与SK不是同一个
           2. PK用于加密，SK用于解密
           3. PK决定SK，但是PK很难算出SK（数学原理：两个大质数相乘，积很难因式分解）
           4. 速度慢，只对少量数据加密*/

    private static final String COMMON_KEY = "emotte!@#game";

    /*
     * public static String MD5(String str) { Provider sunJce = new
     * com.sun.crypto.provider.SunJCE(); Security.addProvider(sunJce);
     *
     * try { // Generate secret key for HMAC-MD5 KeyGenerator kg =
     * KeyGenerator.getInstance("HmacMD5"); SecretKey sk = kg.generateKey();
     *
     * // Get instance of Mac object implementing HMAC-MD5, and // initialize it
     * with the above secret key Mac mac = Mac.getInstance("HmacMD5"); mac.init(sk);
     * byte[] result = mac.doFinal(str.getBytes());
     *
     * return dumpBytes(result); } catch (Exception e) { e.printStackTrace(); return
     * null; } }
     */

    /**
     * DES加密
     * @param msg 加密信息
     * @param salt 盐
     * @return
     */
    public static byte[] desEncrypt(String msg, String salt) {
        if (msg == null) {
            msg = "";
        }
        if (salt == null) {
            salt = "dudusalt";
        }
        byte[] keyBytes = new byte[8];
        int saltLen = salt.length();
        byte[] saltBytes = salt.getBytes();
        for (int i = 0; i < 8; i++) {
            keyBytes[i] = saltBytes[i % saltLen];
        }

        try {
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] text = msg.getBytes("UTF-8");
            byte[] ciphertext = desCipher.doFinal(text);

            return ciphertext;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES解密
     * @param msg 解密信息
     * @param salt 盐
     * @return
     */
    public static String desDecrypt(byte[] msg, String salt) {
        if (msg == null) {
            return null;
        }
        if (salt == null) {
            salt = "dudusalt";
        }
        byte[] keyBytes = new byte[8];
        int saltLen = salt.length();
        byte[] saltBytes = salt.getBytes();
        for (int i = 0; i < 8; i++) {
            keyBytes[i] = saltBytes[i % saltLen];
        }

        try {
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] deciphertext = desCipher.doFinal(msg);

            return new String(deciphertext, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节数组处理成字符串
     * @param bytes
     * @return
     */
    public static String dumpBytes(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            /*
             * if (i%32 == 0 && i!=0) { sb.append("\n"); }
             */
            String s = Integer.toHexString(bytes[i]);
            if (s.length() < 2) {
                s = "0" + s;
            }
            if (s.length() > 2) {
                s = s.substring(s.length() - 2);
            }
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 字符串解析成字节数组
     * @param str
     * @return
     */
    public static byte[] parseBytes(String str) {
        try {
            int len = str.length() / 2;
            if (len <= 2) {
                return new byte[]{Byte.parseByte(str)};
            }
            byte[] arr = new byte[len];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            }
            return arr;
        } catch (Exception e) {
            return new byte[0];
        }
    }

    /**
     * 加密
     * @param encrypt_value 被加密的字符串
     * @param encrypt_key 加密的密钥
     * @return
     */
    public static String encryptAsString(String encrypt_value, String encrypt_key) {
        return dumpBytes(desEncrypt(encrypt_value, encrypt_key));
    }

    /**
     * 解密
     * @param encrypt_value 要解密的字符串
     * @param encrypt_key 密钥
     * @return
     */
    public static String desEncryptAsString(String encrypt_value, String encrypt_key) {
        return desDecrypt(parseBytes(encrypt_value), encrypt_key);
    }

    /**
     * 加密
     * @param encrypt_value
     * @return
     */
    public static String desEncryptAsString(String encrypt_value) {
        return desEncryptAsString(encrypt_value, COMMON_KEY);
    }

    /**
     * 解密
     * @param encrypt_value
     * @return
     */
    public static String encryptAsString(String encrypt_value) {
        return dumpBytes(desEncrypt(encrypt_value, COMMON_KEY));
    }

    /*
     * public static Map parserToMap(String s){ Map map=new HashMap(); JSONObject
     * json=JSONObject.fromObject(s); Iterator keys=json.keys();
     * while(keys.hasNext()){ String key=(String) keys.next(); String
     * value=json.get(key).toString();
     * if(value.startsWith("{")&&value.endsWith("}")){ map.put(key,
     * parserToMap(value)); }else{ map.put(key, value); }
     *
     * } return map; }
     */
    public static String getHashPath(long parentId) {
        String id = Long.toString(parentId);
        /*
         * if(id.length()<6) { int m = id.length() ; for(int i = 0 ;i<(6-m);i++) { id
         * ="0"+id ; } } else { id = id.substring(id.length()-6,id.length()) ; }
         */
        // System.out.println("before hash::"+id) ;

        byte[] buff = id.getBytes();
        String curr = "0981276345";
        int len = curr.length();
        int[] res = new int[8];
        int iter = 0;
        for (int i = 0; i < 8; i++) {
            if (buff.length > i && i < 6) {
                res[i] = (buff[i] + buff[buff.length - 1 - i]) % 256;
            } else {
                res[i] = Integer.parseInt(curr.substring(len - iter - 3, len - iter)) % 256;
                iter++;
            }
        }
        String str = "";
        str += Integer.toHexString((int) res[0]) + Integer.toHexString((int) res[1]) + Integer
                .toHexString((int) res[2])
                + Integer.toHexString((int) res[3]) + Integer.toHexString((int) res[4])
                + Integer.toHexString((int) res[5]) + Integer.toHexString((int) res[6])
                + Integer.toHexString((int) res[7]);
        str += parentId;
        System.out.println("after hash::" + str);
        return str;
    }

    /**
     * 根据当前时间随机密码
     * @return
     */
    public static String createRandomPassword() {
        return (System.currentTimeMillis() + "").substring(5, 13);
    }

    public static void main1(String[] args) {
        String ss = desEncryptAsString("3747f8f8d0700388");
        System.out.println(ss);
        System.exit(0);
    }

    public static void main(String[] args) {
        // 解密
        String pass = EncryptTest.desEncryptAsString(String.valueOf("f4ee0fbd73ee7cb8"));
        System.out.println(pass);  //988862
    }

}
