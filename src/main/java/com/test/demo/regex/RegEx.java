package com.test.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * regex
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class RegEx {

    public static void main1(String[] args) {
        //String regEx = "^(ps|jz|hy|jt|JG|gj)+$";
        String prefix = "^(ps|jz|hy|jt|JG|gj).*";
        Pattern pattern = Pattern.compile(prefix);
        Matcher matcher = pattern.matcher("jz5810000");
        // 字符串是否与正则表达式相匹配
        System.out.println(matcher.matches());
        //System.out.println("ps5810000".startsWith("(ps|jz|hy|jt|JG|gj)"));

        Pattern pattern1 = Pattern.compile("^Java.*");
        Matcher matcher1 = pattern1.matcher("Java不是人");
        boolean b = matcher1.matches();
        //当条件满足时，将返回true，否则返回false
        System.out.println(b);
    }

    public static void main2(String[] args) {
        //String prefix =  "<td>*</td>";
        Pattern pattern = Pattern.compile("<td>*</td>");
        Matcher matcher = pattern.matcher(
                "<td> 家庭保洁 </td> <td width=\"100\">13926416702<br><a href=\"/v2/index.php?telephone=13926416702&amp;user_id=389349&amp;action=userhistory&amp;do=UserHistory\">详情</a></td> <td>2015-12-21<br>08:00:00</td> \r\n <td>南京/萝岗区越秀·岭南山畔/10栋1403</td> ");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public static void main(String[] args) {
        String prefix = "/^1[3456789]\\d{9}$/";
    }

}
