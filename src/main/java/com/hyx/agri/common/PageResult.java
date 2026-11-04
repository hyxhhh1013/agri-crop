package com.hyx.agri.common;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private long total;
    private long pages;
    private long current;
    private List<T> records;

    public static <T> PageResult<T> of(long total, long pages, long current, List<T> records) {
        PageResult<T> page = new PageResult<>();
        page.setTotal(total);
        page.setPages(pages);
        page.setCurrent(current);
        page.setRecords(records);
        return page;
    }
}
