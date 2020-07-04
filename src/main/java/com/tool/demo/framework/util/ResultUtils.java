package com.tool.demo.framework.util;


import com.github.pagehelper.Page;
import com.tool.demo.framework.constant.BusinessStatus;
import com.tool.demo.framework.enums.CodeEnum;
import com.tool.demo.framework.response.BaseResponse;
import com.tool.demo.framework.response.PageResult;

/**
 * ResultUtils
 *
 * @author WangChao
 * @date 2019/11/17
 */
public class ResultUtils {

    public static BaseResponse success() {
        return new BaseResponse(BusinessStatus.SUCCESS.value(),
                BusinessStatus.SUCCESS.getReasonPhrase());
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(BusinessStatus.SUCCESS.value(),
                BusinessStatus.SUCCESS.getReasonPhrase(), data);
    }

    public static <T> BaseResponse<PageResult<T>> successPageQuery(PageResult<T> data) {
        return new BaseResponse<PageResult<T>>(BusinessStatus.SUCCESS.value(),
                BusinessStatus.SUCCESS.getReasonPhrase(), data);
    }

    public static <T> BaseResponse<PageResult<T>> successPageQuery(Page<T> data) {
        return new BaseResponse<PageResult<T>>(BusinessStatus.SUCCESS.value(),
                BusinessStatus.SUCCESS.getReasonPhrase(), PageResult.buildResult(data));
    }

    public static BaseResponse fail(CodeEnum codeEnum) {
        return new BaseResponse(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static BaseResponse fail(BusinessStatus status, String msg) {
        return new BaseResponse(status.value(), msg);
    }

    public static BaseResponse fail(String code, String msg) {
        return new BaseResponse(code, msg);
    }

}
