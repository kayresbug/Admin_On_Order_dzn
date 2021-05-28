package com.example.admin_on_order.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.admin_on_order.MainActivity;
import com.example.admin_on_order.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MenuFragment extends Fragment {

    View view;
    SharedPreferences pref;
    String storeCode;

    TextView dateResultView;
    String dateResult;

    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        storeCode = pref.getString("storecode","");

        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateResultView = view.findViewById(R.id.menu_date_result);
        dateResultView.setText(dateFormat.format(calendar.getTime()));
        dateResult = dateResultView.getText().toString();

        DatePickerDialog.OnDateSetListener dateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateResultView.setText(dateFormat.format(calendar.getTime()));
                dateResult = dateResultView.getText().toString();
            }
        };

        return view;
    }
}
