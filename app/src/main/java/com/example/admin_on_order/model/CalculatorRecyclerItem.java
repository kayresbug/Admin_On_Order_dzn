package com.example.admin_on_order.model;

public class CalculatorRecyclerItem {

    private String tableNo;
    private String checkApproval;
    private String paymentTime;
    private String totalPrice;
    private String cardNo;

    public CalculatorRecyclerItem(String tableNo, String checkApproval, String paymentTime, String totalPrice, String cardNo) {
        this.tableNo = tableNo;
        this.checkApproval = checkApproval;
        this.paymentTime = paymentTime;
        this.totalPrice = totalPrice;
        this.cardNo = cardNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getCheckApproval() {
        return checkApproval;
    }

    public void setCheckApproval(String checkApproval) {
        this.checkApproval = checkApproval;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

}
