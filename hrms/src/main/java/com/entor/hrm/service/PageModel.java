package com.entor.hrm.service;

import java.util.List;

import static com.entor.hrm.uitl.common.HrmConstants.PAGE_DEFAULT_SIZE;

public class PageModel<T> {
    /**
     * 分页总记录数
     */
    private int recordCount;

    /**
     * 当前页码
     */
    private int pageIndex;

    /**
     * 每页记录数
     */
    private int pageSize = PAGE_DEFAULT_SIZE;

    /**
     * 分页记录详情
     */
    private List<T> pageList;

    public int getRecordCount() {
        this.recordCount = this.recordCount >= 0 ? this.recordCount : 0;
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageIndex() {
        this.pageIndex = (getPageIndex() - 1) * getPageSize() + 1;
        this.pageIndex = this.pageIndex >= 0 ? this.pageSize : 0;
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        this.pageSize = this.pageSize >= 0 ? this.pageSize : PAGE_DEFAULT_SIZE;
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
