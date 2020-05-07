package com.test.demo.framework.constant;

import com.test.demo.framework.enums.CodeEnum;

/**
 * 全局业务状态码
 * 格式：10xxxx
 *
 * @author WangChao
 * @date 2019/11/17
 */
public enum BusinessStatus implements CodeEnum {

    /**
     * 状态枚举
     */
    SUCCESS("00000", "操作成功"),
    FAIL("-00001", "系统繁忙，请稍候再试"),
    PARAM_ILLEGAL("00106", "不合法的参数"),
    LOGIN_FAILED("00003", "登录失败"),
    PASSWORD_ERROR("00005", "密码错误"),
    VERIFICATION_CODE_ERROR("00006", "验证码错误"),
    HAS_NO_AUTHORITY("00007", "没有权限"),
    INVALID_TOKEN("00008", "token无效"),
    TOKEN_NOT_EXIST("00009", "token信息不存在,请检查"),
    VERIFICATION_CODE_LIMIT("00010", "验证码已达上限"),
    FORBIDDEN("00011", "您已被禁用"),
    URL_ILLEGAL("00113", "url不合法"),
    URL_EMPTY("00114", "URL为空");

    /**
     * 内部变量和方法
     */
    private final String value;

    private final String reasonPhrase;

    BusinessStatus(String value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public String value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }


    @Override
    public String getCode() {
        return value();
    }

    @Override
    public String getMsg() {
        return getReasonPhrase();
    }
}
