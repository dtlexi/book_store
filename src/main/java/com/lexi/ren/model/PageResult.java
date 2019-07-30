package com.lexi.ren.model;

import java.util.List;

public class PageResult<T> {
    private long count;
    private int pagesize;
    private int pageindex;
    private int pagecount;
    private List<T> data;
    public int getPagecount() {
        if (count > 0 && pagesize > 0) {
            return count % pagesize == 0 ? (int) count / pagesize : (int) (count / pagesize + 1);
        }
        return 0;
    }
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "count=" + count +
                ", pagesize=" + pagesize +
                ", pageindex=" + pageindex +
                ", data=" + data +
                '}';
    }
}
