package com.lagou.domain;

/*广告模块的分页信息,当前页和每页显示的条数*/
public class PromotionAdVO {

    private Integer currentPage = 1;
    private Integer pageSize = 10;
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
