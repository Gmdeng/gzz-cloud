package com.gzz.cloud.product.domain;

import java.util.Date;

public class Product {
    private String no;
    private String name;
    private int level;
    private Date joinDate;
    private String desc;

    public Product() {
    }

    public Product(String no, String name) {
        this.no = no;
        this.name = name;
        this.level = 1;
        this.joinDate = new Date();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
