package com.test.demo.json;

import lombok.Data;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json-lib对象转json时Integer、Double等类型时Null转换为0问题处理
 * @author WangChao
 * @date 2018年10月10日
 */
public class Netsfjson {

    public static void main(String[] args) {

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Double.class, new JsonValueProcessor() {
            @Override
            public Object processObjectValue(String key, Object value, JsonConfig config) {
                //return value;
                return value == null ? "" : value;
            }

            @Override
            public Object processArrayValue(Object arg0, JsonConfig arg1) {
                return null;
            }
        });

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("aa");
        //employee.setSalary(0.0);
        JSONObject fromObject = JSONObject.fromObject(employee, jsonConfig);
        System.out.println(fromObject.toString());

    }

}

@Data
class Employee {

    private Integer id;
    private String name;
}