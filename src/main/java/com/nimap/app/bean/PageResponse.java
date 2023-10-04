package com.nimap.app.bean;

import org.springframework.data.domain.Page;
import java.util.List;

public class PageResponse<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalPages;

    private List<T> content;

    public PageResponse(Page<T> page){
        this.pageIndex = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();

        this.content = page.getContent();
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<T> getContent() {
        return content;
    }
}
