package com.destiny.work.model;

import java.util.List;

/**
 * Created by Destiny_hao on 2017/6/16.
 */
public class PageEntity<T> {

    private int pageCode; //当前页码 -- 前端传递得到
    //private int totalPages; //总页数 -- 计算得到
    private int totalRecord; //总记录数 -- 数据库查询得到
    private int pageSize; //每页显示条数 -- 自定义
    private List<T> entityList; //当前页记录 -- 根据pageCode和pageSize得到

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    public int getTotalPages() {
        int totalPages = totalRecord / pageSize;
        return totalRecord % pageSize == 0 ? totalPages : totalPages+1;
    }

//    public void setTotalPages(int totalPages) {
//        this.totalPages = totalPages;
//    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }
}
