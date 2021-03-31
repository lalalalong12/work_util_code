package com.ywltest.springdemo.util.common;


import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Page
 * @Description TODO
 * @Author wuweiwei
 * @Date 2020/3/25 19:24
 * @Version 1.0
 */

public class Page<T> implements Serializable {

    private static final long serialVersionUID = -8741766802354222579L;

    private int pageSize; // 每页显示多少条记录

    private int pageIndex; //当前第几页数据

    private int recordTotal; // 一共多少条记录

    private int pageTotal; // 一共多少页记录

    private List<T> data; //要显示的数据

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Page(int pageIndex, int pageSize, List<T> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return;
        }

        // 总记录条数
        this.recordTotal = sourceList.size();

        // 每页显示多少条记录
        this.pageSize = pageSize;

        //当前页面

        this.pageIndex = pageIndex;

        //获取总页数
        this.pageTotal = this.recordTotal / this.pageSize;
        if (this.recordTotal % this.pageSize != 0) {
            this.pageTotal = this.pageTotal + 1;
        }

        // 起始索引
        int fromIndex = this.pageSize * (this.pageIndex - 1);

        // 结束索引
        int toIndex = this.pageSize * this.pageIndex > this.recordTotal ? this.recordTotal : this.pageSize * this.pageIndex;

        this.data = sourceList.subList(fromIndex, toIndex);
    }

    public Page(int currentPage, int recordTotal, int pageTotal,
                List<T> dataList) {
        super();
        this.pageIndex = currentPage;
        this.recordTotal = recordTotal;
        this.pageTotal = pageTotal;
        this.data = dataList;
    }

    public Page(int currentPage, int pageSize, int recordTotal, int pageTotal, List<T> dataList) {
        super();
        this.pageIndex = currentPage;
        this.pageSize = pageSize;
        this.recordTotal = recordTotal;
        this.pageTotal = pageTotal;
        this.data = dataList;
    }

    public static int getTotalPage(int recordTotal, int pageSize) {
        //获取总页数
        int pageTotal = recordTotal / pageSize;
        if (recordTotal % pageSize != 0) {
            pageTotal = pageTotal + 1;
        }
        return pageTotal;
    }


}

