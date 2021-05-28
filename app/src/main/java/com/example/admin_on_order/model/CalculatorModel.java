package com.example.admin_on_order.model;

public class CalculatorModel {

    private String totalPrice;
    private String totReturnPrice;
    private String totalDiscount;
    private String taxSale;
    private String vatSale;
    private String dutyFreeSale;
    private String cash;
    private String credit;
    private String giftCard;
    private String affiliateCard;
    private String milelege;
    private String mealTicket;
    private String other;
    private String paymentTime;
    private String paymentTime2;

    public CalculatorModel(String totalPrice,
                           String totReturnPrice, String totalDiscount,
                           String taxSale, String vatSale,
                           String dutyFreeSale, String cash,
                           String credit, String giftCard,
                           String affiliateCard, String milelege,
                           String mealTicket, String other,
                           String paymentTime,
                           String paymentTime2) {
        this.totalPrice = totalPrice;
        this.totReturnPrice = totReturnPrice;
        this.totalDiscount = totalDiscount;
        this.taxSale = taxSale;
        this.vatSale = vatSale;
        this.dutyFreeSale = dutyFreeSale;
        this.cash = cash;
        this.credit = credit;
        this.giftCard = giftCard;
        this.affiliateCard = affiliateCard;
        this.milelege = milelege;
        this.mealTicket = mealTicket;
        this.other = other;
        this.paymentTime = paymentTime;
        this.paymentTime2 = paymentTime2;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotReturnPrice() {
        return totReturnPrice;
    }

    public void setTotReturnPrice(String totReturnPrice) {
        this.totReturnPrice = totReturnPrice;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getTaxSale() {
        return taxSale;
    }

    public void setTaxSale(String taxSale) {
        this.taxSale = taxSale;
    }

    public String getVatSale() {
        return vatSale;
    }

    public void setVatSale(String vatSale) {
        this.vatSale = vatSale;
    }

    public String getDutyFreeSale() {
        return dutyFreeSale;
    }

    public void setDutyFreeSale(String dutyFreeSale) {
        this.dutyFreeSale = dutyFreeSale;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(String giftCard) {
        this.giftCard = giftCard;
    }

    public String getAffiliateCard() {
        return affiliateCard;
    }

    public void setAffiliateCard(String affiliateCard) {
        this.affiliateCard = affiliateCard;
    }

    public String getMilelege() {
        return milelege;
    }

    public void setMilelege(String milelege) {
        this.milelege = milelege;
    }

    public String getMealTicket() {
        return mealTicket;
    }

    public void setMealTicket(String mealTicket) {
        this.mealTicket = mealTicket;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentTime2() {
        return paymentTime2;
    }

    public void setPaymentTime2(String paymentTime2) {
        this.paymentTime2 = paymentTime2;
    }
}
