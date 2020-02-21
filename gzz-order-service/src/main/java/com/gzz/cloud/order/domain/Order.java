package com.gzz.cloud.order.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String no;
    private Date createDate;
    private BigDecimal amount;
    private String memberNo;

    public Order() {
    }

    public Order(String no, Date createDate, BigDecimal amount, String memberNo) {
        this.no = no;
        this.createDate = createDate;
        this.amount = amount;
        this.memberNo = memberNo;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}
