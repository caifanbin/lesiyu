package com.binge.lesiyu.commons;

import java.util.List;

public class Page {
    //当前页
    private Integer pageno;
    //每页数据量
    private Integer pagesize;
    //查询的数据
    private List datas;
    //总数据量
    private Integer totalsize;
    //总页数
    private Integer totalno;

    public Page(Integer pageno, Integer pagesize) {
        if (pageno <= 0) {
            this.pageno = 1;
        } else {
            this.pageno = pageno;
        }
        if (pagesize <= 0) {
            this.pageno = 10;
        } else {
            this.pagesize = pagesize;
        }
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public Integer getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(Integer totalsize) {
        this.totalsize = totalsize;
        this.totalno = (totalsize % pagesize == 0 ? (totalsize / pagesize) : (totalsize / pagesize + 1));
    }

    public Integer getTotalno() {
        return totalno;
    }

    private void setTotalno(Integer totalno) {
        this.totalno = totalno;
    }

    public Integer getStartIndex() {
        return (this.pageno - 1) * pagesize;
    }
}
