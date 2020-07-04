package com.tool.demo.framework.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 返回客户端数据基类
 *
 * @author WangChao
 * @date 2019/11/17
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -5516071135506630139L;
    /**
     * 状态
     */
    private String code;

    /**
     * 描述
     */
    private String msg;

    private T data;

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
