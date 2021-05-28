package com.example.admin_on_order.model;

public class ServiceRecyclerItem {

    private String tableNo;
    private String body;
    private String inputTime;
    private String paymentStatus;
    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public ServiceRecyclerItem(String num, String tableNo, String body, String paymentStatus, String inputTime) {
        this.num = num;
        this.tableNo = tableNo;
        this.body = body;
        this.paymentStatus = paymentStatus;
        this.inputTime = inputTime;
    }

    public ServiceRecyclerItem(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
