package com.example.admin_on_order;

import android.app.Application;

import com.sam4s.printer.Sam4sPrint;

public class AdminApplication extends Application {
    private static Sam4sPrint firstPrinter = new Sam4sPrint();
    private static Sam4sPrint secondPrinter = new Sam4sPrint();

    public static void setFirstPrinter(Sam4sPrint printer) {
        firstPrinter = printer;
    }

    public static void setSecondPrinter(Sam4sPrint printer) {
        secondPrinter = printer;
    }

    public static Sam4sPrint getFirstPrinter() {
        return firstPrinter;
    }

    public static Sam4sPrint getSecondPrinter() {
        return secondPrinter;
    }

}
