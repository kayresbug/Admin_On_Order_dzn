package com.example.admin_on_order;

import android.util.Log;

import com.sam4s.printer.Sam4sPrint;

public class isPrinter {
    public Sam4sPrint setPrinter1(){
        Sam4sPrint sam4sPrint = new Sam4sPrint();
        try {
            Thread.sleep(300);
            sam4sPrint.openPrinter(Sam4sPrint.DEVTYPE_ETHERNET, "192.168.20.130", 9100);
            sam4sPrint.resetPrinter();
        } catch (Exception exception) {
            try {
                Log.d("daon_test", "aaa = "+sam4sPrint.getPrinterStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
        }
        return sam4sPrint;
    }
    public Sam4sPrint setPrinter2(){
        Sam4sPrint sam4sPrint = new Sam4sPrint();
        try {
            Thread.sleep(300);
            sam4sPrint.openPrinter(Sam4sPrint.DEVTYPE_ETHERNET, "192.168.20.131", 9100);
            sam4sPrint.resetPrinter();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return sam4sPrint;
    }

    public Sam4sPrint setPrinter3(){
        Sam4sPrint sam4sPrint = new Sam4sPrint();
        try {
            Thread.sleep(300);
            sam4sPrint.openPrinter(Sam4sPrint.DEVTYPE_ETHERNET, "192.168.20.132", 9100);
            sam4sPrint.resetPrinter();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return sam4sPrint;
    }

    public void closePrint1(Sam4sPrint sam4sPrint){
        try {
            Thread.sleep(600);
            sam4sPrint.closePrinter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void closePrint2(Sam4sPrint sam4sPrint){
        try {
            Thread.sleep(600);
            sam4sPrint.closePrinter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void closePrint3(Sam4sPrint sam4sPrint){
        try {
            Thread.sleep(600);
            sam4sPrint.closePrinter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
