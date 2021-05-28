package com.example.admin_on_order.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_on_order.Adapter.ServiceRecyclerAdapter;
import com.example.admin_on_order.InterfaceAPI;
import com.example.admin_on_order.NullOnEmptyConverterFactory;
import com.example.admin_on_order.OnBackPressedListener;
import com.example.admin_on_order.R;
import com.example.admin_on_order.ServiceDialog;
import com.example.admin_on_order.model.ServiceRecyclerItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFragment extends Fragment implements OnBackPressedListener {

    ServiceDialog dialog;
    SharedPreferences pref;
    View view;

    private static int position;

    private TextView dateResultView;
    private ImageView btnDatePick;
    private String dateResult;
    private String storeCode;

    RecyclerView recyclerView;
    private ServiceRecyclerAdapter adapter;
    public ArrayList<ServiceRecyclerItem> items;
    RecyclerView.LayoutManager layoutManager;
    Context context;

    private static String BASE_URL = "http://15.164.232.164:5000/";

    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service, container, false);
        pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        storeCode = pref.getString("storecode", "");

        dateResultView = (TextView) view.findViewById(R.id.call_date_result);
        btnDatePick = (ImageView) view.findViewById(R.id.btn_call_date);

        recyclerView = view.findViewById(R.id.fragment_service_recyclerview);

        // Current Time now
        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateResultView.setText(dateFormat.format(calendar.getTime()));
        Log.d("Service Fragment", "onCreateView: " + dateResultView.getText().toString());

        dateResult = dateResultView.getText().toString();
        initViewService(dateResult, storeCode, position);

        Log.d("OCV", "onCreateView: " + position);
        DatePickerDialog.OnDateSetListener dateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                dateResultView.setText(dateFormat.format(calendar.getTime()));
                dateResult = dateResultView.getText().toString();
                initViewService(dateResult, storeCode, position);
                Log.d("ServiceDateResult", "onDateSet: " + dateResult);
            }
        };

        Log.d("OutOfDateResult", "onCreateView: " + dateResult);

        // Choose Date Picker Dialog
        dateResultView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        dateSet,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                Log.d("ClickDateResult", "onClick: " + dateResult);
            }
        });

        Log.d("after cch dateResult", "onCreateView: " + dateResult);

        return view;
    }

    public void initViewService(String dateResult, String storeCode, int position) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.d("dateResult", "callCheck: " + dateResult);

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        interfaceAPI.service(storeCode, dateResult).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(" Service Pass", "onResponse: " + response.isSuccessful());
                if (response.isSuccessful()) {
                    Log.d("testaaa", "onResponse: " + dateResult);
                    Log.d("response body", "onResponse: " + response.body());
                    items = new ArrayList<>();
                    JsonObject object = response.body();
                    JsonArray array = (JsonArray) object.get("result");
                    Log.d("Service array", "onResponse: " + array.size());

                    if (array.size() == 0) {
                        items.clear();
                        adapter = new ServiceRecyclerAdapter(context, items, ServiceFragment.this, position);
                        recyclerView.setAdapter(adapter);
                    }

                    for (int i = 0; i < array.size(); i++) {
                        Log.d("array", "onResponse: " + array);
                        String obj = String.valueOf(array.get(i));
                        Log.d("before arrayobj", "onResponse: " + obj);
                        obj = obj.replace("[", "");
                        obj = obj.replace("]", "");
                        obj = obj.replace("\\", "");
                        obj = obj.substring(1, obj.length() - 1);
                        Log.d("after arrayobj", "onResponse: " + obj);

                        JSONObject strObj = null;
                        try {
                            Log.d("adf", "aaaa = "+obj);
                            strObj = new JSONObject(obj);
                            Date date = new Date(Long.parseLong((String) strObj.get("inputtime")));
                            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                            String inputTime = dateFormat.format(date);

                            ServiceRecyclerItem item = new ServiceRecyclerItem(
                                    strObj.get("num").toString(),
                                    strObj.get("tableno").toString(),
                                    strObj.get("body").toString(),
                                    strObj.get("status").toString(),
                                    inputTime
                            );

                            if (!strObj.get("body").toString().equals("")) {
                                Log.d("callCheck body", "onResponse: "+ strObj.getString("body"));
                                items.add(item);
                            }

                            layoutManager = new LinearLayoutManager(getContext());
                            //LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(layoutManager);
                            layoutManager.scrollToPosition(position);
                            Log.d("LSLS", "onResponse: " + position);
                            adapter = new ServiceRecyclerAdapter(context, items, ServiceFragment.this, position);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Service_Failure", "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateStatus(String num, int position) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        storeCode = pref.getString("storecode", "");

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        interfaceAPI.serviceUpdateStatus(storeCode, num).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    initViewService(dateResult, storeCode, position);
                    Log.d("toto", "ServiceFragment: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
