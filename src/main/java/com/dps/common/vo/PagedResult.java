package com.dps.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * </pre>
 *
 */
public class PagedResult<T> implements Serializable {
    /**
     * <pre>
     * 
     * </pre>
     */
    private static final long serialVersionUID = 5669417629082299536L;

    /**
     * <pre>
     * 总记录数
     * </pre>
     */
    private int              total;

    /**
     * <pre>
     * 每页大小写
     * </pre>
     */
    private int               pageSize;

    /**
     * <pre>
     * 当前页数
     * </pre>
     */
    private int               pageNo;

    /**
     * <pre>
     * 查询结果
     * </pre>
     */
    private List<T>           results          = new ArrayList<T>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void addResult(T result) {
        this.results.add(result);
    }

    public int currentRecord() {
        return this.pageSize * (this.pageNo - 1);
    }
}
