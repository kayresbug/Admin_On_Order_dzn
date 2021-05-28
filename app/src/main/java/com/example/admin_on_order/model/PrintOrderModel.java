package com.example.admin_on_order.model;

public class PrintOrderModel {
    String table;
    String order;
    String time;
    String printStatus;
    String type;
    String cardname;
    String cardnum;
    String authdate;
    String authnum;
    String purchasename;
    String notice;
    String vantr;
    String price;
    String ordertype;

    public PrintOrderModel(){

    }

    public PrintOrderModel(String table, String order, String time, String printStatus, String type, String cardname, String cardnum, String authdate, String authnum, String purchasename, String notice, String vantr, String price, String ordertype) {
        this.table = table;
        this.order = order;
        this.time = time;
        this.printStatus = printStatus;
        this.type = type;
        this.cardname = cardname;
        this.cardnum = cardnum;
        this.authdate = authdate;
        this.authnum = authnum;
        this.purchasename = purchasename;
        this.notice = notice;
        this.vantr = vantr;
        this.price = price;
        this.ordertype = ordertype;

    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getAuthdate() {
        return authdate;
    }

    public void setAuthdate(String authdate) {
        this.authdate = authdate;
    }

    public String getAuthnum() {
        return authnum;
    }

    public void setAuthnum(String authnum) {
        this.authnum = authnum;
    }

    public String getPurchasename() {
        return purchasename;
    }

    public void setPurchasename(String purchasename) {
        this.purchasename = purchasename;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getVantr() {
        return vantr;
    }

    public void setVantr(String vantr) {
        this.vantr = vantr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(String printStatus) {
        this.printStatus = printStatus;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

