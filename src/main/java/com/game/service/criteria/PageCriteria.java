package com.game.service.criteria;

public class PageCriteria {

    private String order;
    private Integer pageNumber;
    private Integer pageSize;

    public PageCriteria() {
        order = "id";
        pageNumber = 0;
        pageSize = 3;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
