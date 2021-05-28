package com.example.admin_on_order;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin_on_order.Fragment.ServiceFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerClass {

    String dateResult;
    ImageView btnDialog;
    Calendar calendar = Calendar.getInstance();

    public void setOnDate(String dateResult, ImageView btnDialog, Activity activity) {
        this.dateResult = dateResult;
        this.btnDialog = btnDialog;

        Log.d("dateresult", "setOnDate: " + dateResult);

        // 달력 구현체
        DatePickerDialog.OnDateSetListener dateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                //String date = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth);

                Log.d("onDateSet", "onDateSet: " + dateResult);
            }
        };
        // 선택한 날짜 picker
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        activity,
                        dateSet,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }
}
