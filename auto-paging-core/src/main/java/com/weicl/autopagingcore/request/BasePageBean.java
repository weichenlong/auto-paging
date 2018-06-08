package com.weicl.autopagingcore.request;

import com.weicl.autopagingcore.result.ToString;

/**
 * Created by weicl on 2017/6/15.
 */
public class BasePageBean extends ToString {
    private static final long serialVersionUID = -1755114409316939251L;

    private int limit = 10;//每页限制数,默认为10
    private int offset = 1;//当前页偏移,默认为1
    private String order;//排序
    private String sort;//排序列名称
    private String search;//搜索内容

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 搜索内容
     * @return String
     */
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
