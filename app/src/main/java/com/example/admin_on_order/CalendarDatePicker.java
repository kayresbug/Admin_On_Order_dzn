package com.example.admin_on_order;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarDatePicker {

    Calendar calendar = Calendar.getInstance();
    TextView dateResult;
    Context context;

    DatePickerDialog.OnDateSetListener setDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year,
                    Calendar.MONTH, month,
                    Calendar.DAY_OF_MONTH, dayOfMonth);

            String format = "YYYY-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateResult.setText(dateFormat.format(calendar.getTime()));

            Log.d("TEST", "adfa = "+ calendar.getTime());
        }
    };

    public DatePickerDialog.OnDateSetListener getSetDate(TextView dateResult) {
        this.dateResult = dateResult;
        return setDate;
    }

    public void getDateResult(TextView dateResult) {
        this.dateResult = dateResult;
    }

}
