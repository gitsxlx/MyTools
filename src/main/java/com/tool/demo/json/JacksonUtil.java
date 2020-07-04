package com.tool.demo.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * ObjectMapper
 * @author cao
 * @date 2018年9月12日
 */
@Slf4j
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(Include.NON_NULL)
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(MapperFeature.USE_ANNOTATIONS);

    /**
     * 将JSON字符串转换为对象
     *
     * @param json      JSON字符串
     * @param valueType 对象类型
     *
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        Assert.hasText(json);
        Assert.notNull(valueType);
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            log.error("ERROR:", e);
        }
        return null;
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param value 对象
     *
     * @return JSOn字符串
     */
    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            log.error("ERROR:", e);
        }
        return null;
    }

    public static <T> List<T> doToResp(List list, Class<T> c) {

        if (null == list) {
            return null;
        }
        String json = JacksonUtil.toJson(list);

        //JavaType javaType = JsonUtils.getCollectionType(List.class, c);
        List<T> t = JacksonUtil.toObject(json, List.class);
        return t;
    }

}
