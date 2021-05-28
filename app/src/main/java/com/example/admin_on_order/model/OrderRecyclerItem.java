package com.example.admin_on_order.model;

import android.util.Log;

public class OrderRecyclerItem {

    private String tableNo;
    private String menu;
    private String count;
    private String price;
    //결제내역
    private String paymentStatus;
    //결제관리
    private String paymentType;
    private String orderTime;
    private String authNum;
    private String authDate;
    private String vanTr;
    private String cardBin;
    private String dptId;
    private String printStatus;

    public OrderRecyclerItem() {

    }

    public OrderRecyclerItem(String tableNo, String menu, String count, String price,
                             String paymentStatus, String paymentType, String orderTime, String authNum,
                             String authDate, String vanTr, String cardBin, String dptId) {
        this.tableNo = tableNo;
        this.menu = menu;
        this.count = count;
        this.price = price;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.orderTime = orderTime;
        this.authNum = authNum;
        this.authDate = authDate;
        this.vanTr = vanTr;
        this.cardBin = cardBin;
        this.dptId = dptId;
    }

    public OrderRecyclerItem(String tableNo, String menu, String count, String price,
                             String paymentStatus, String paymentType, String orderTime, String authNum,
                             String authDate, String vanTr, String cardBin, String dptId, String printStatus) {
        this.tableNo = tableNo;
        this.menu = menu;
        this.count = count;
        this.price = price;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.orderTime = orderTime;
        this.authNum = authNum;
        this.authDate = authDate;
        this.vanTr = vanTr;
        this.cardBin = cardBin;
        this.dptId = dptId;
        this.printStatus = printStatus;
    }

    public String getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(String printStatus) {
        this.printStatus = printStatus;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getAuthNum() {
        return authNum;
    }

    public void setAuthNum(String authNum) {
        this.authNum = authNum;
    }

    public String getAuthDate() {
        return authDate;
    }

    public void setAuthDate(String authDate) {
        this.authDate = authDate;
    }

    public String getVanTr() {
        return vanTr;
    }

    public void setVanTr(String vanTr) {
        this.vanTr = vanTr;
    }

    public String getCardBin() {
        return cardBin;
    }

    public void setCardBin(String cardBin) {
        this.cardBin = cardBin;
    }

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }
}
