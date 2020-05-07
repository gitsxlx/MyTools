package com.test.demo.framework.response;

import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PageResult
 *
 * @author WangChao
 * @date 2019/11/17
 */
@Setter
@Getter
@ToString
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -7491965690431868029L;

    private Integer pageNo = 1;

    private Integer pageSize = 10;

    private Integer totalCount = 0;

    private List<T> data = new ArrayList<>();

    public PageResult() {

    }

    public PageResult(Page<T> page) {
        if (!Objects.isNull(page)) {
            this.pageNo = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.totalCount = Math.toIntExact(page.getTotal());
            this.data = page.getResult();
        }
    }

    public static <O, T> PageResult<T> buildResult(Page<O> page, List<T> data) {
        PageResult<T> result = new PageResult<>();
        if (!Objects.isNull(page)) {
            result.pageNo = page.getPageNum();
            result.pageSize = page.getPageSize();
            result.totalCount = Math.toIntExact(page.getTotal());
        }
        result.setData(data);
        return result;
    }

    public static <O, T> PageResult<T> buildResult(PageResult<O> page, List<T> data) {
        PageResult<T> result = new PageResult<>();
        if (!Objects.isNull(page)) {
            result.pageNo = page.getPageNo();
            result.pageSize = page.getPageSize();
            result.totalCount = page.getTotalCount();
        }

        result.setData(data);
        return result;
    }

    public static <T> PageResult<T> buildResult(Page<T> page) {
        return new PageResult<>(page);
    }
}
