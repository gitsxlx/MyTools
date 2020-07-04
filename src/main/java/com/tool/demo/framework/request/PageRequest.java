package com.tool.demo.framework.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页请求
 *
 * @author WangChao
 * @date 2019/11/17
 */
@Setter
@Getter
@ToString
public class PageRequest {

    /**
     * 当页数量
     */
    private int pageSize = 10;

    /**
     * 页码
     */
    private int pageNo = 1;

}
