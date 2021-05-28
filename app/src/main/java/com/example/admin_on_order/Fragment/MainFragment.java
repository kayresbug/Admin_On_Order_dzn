package com.example.admin_on_order.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.admin_on_order.InterfaceAPI;
import com.example.admin_on_order.NullOnEmptyConverterFactory;
import com.example.admin_on_order.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {

    LinearLayout btnService, btnOrder, btnMenu, btnCalc, btnTable;

    TextView storeName;

    TextView currentService, currentOrder;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private String storeCode;

    OrderFragment orderFragment;
    ServiceFragment serviceFragment;
    MenuFragment menuFragment;
    CalculateFragment calculateFragment;
    TableFragment tableFragment;
    SharedPreferences pref;

    Calendar calendar;

    private static String BASE_URL = "http://15.164.232.164:5000/";

    public static MainFragment newInstance(String tag) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        mainFragment.setArguments(bundle);

        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        storeCode = pref.getString("storecode", "");

        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());

//        initStatusView(storeCode, currentDate);

        currentService = view.findViewById(R.id.current_service);
        currentOrder = view.findViewById(R.id.current_order);

        storeName = view.findViewById(R.id.store_name);
        if (pref.getString("storename", "").length() > 5) {
            pref.getString("storename", "").substring(5);
            storeName.setText(pref.getString("storename", "").substring(0, 5));
        } else {
            pref.getString("storename", "");
            storeName.setText(pref.getString("storename",""));
        }

        fragmentManager = getFragmentManager();

        orderFragment = new OrderFragment();
        serviceFragment = new ServiceFragment();
        menuFragment = new MenuFragment();
        calculateFragment = new CalculateFragment();
        tableFragment = new TableFragment();

        btnService = (LinearLayout) view.findViewById(R.id.btn_service);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_frame, serviceFragment, "ServiceFragment").commit();
            }
        });

        btnOrder = (LinearLayout) view.findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_frame, orderFragment, "OrderFragment").commit();
            }
        });

        btnMenu = (LinearLayout) view.findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "메뉴 관리는 준비 중입니다.", Toast.LENGTH_SHORT).show();
//                transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.fragment_frame, menuFragment, "MenuFragment").commit();
            }
        });

        btnCalc = (LinearLayout) view.findViewById(R.id.btn_calc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_frame, calculateFragment, "CalculateFragment").commit();
            }
        });

        btnTable = (LinearLayout) view.findViewById(R.id.btn_table);
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "테이블 관리는 준비 중입니다.", Toast.LENGTH_SHORT).show();
//                transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.fragment_frame, tableFragment, "TableFragment").commit();
            }
        });

        return view;
    }

    public void initStatusView(String storeCode, String currentDate) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        interfaceAPI.service(storeCode, currentDate).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();
                    Log.d("mainFragment", "service: " + object);
                    JsonArray array = (JsonArray) object.get("result");
                    Log.d("mainFragment", "service array size: " + array + " " + array.size() + " ");

                    if (array.size() == 0) {
                        currentService.setText(String.valueOf(array.size()));
                    }

                    ArrayList<String> strArray = new ArrayList<>();

                    for (int i = 0; i < array.size(); i++) {
                        String obj = String.valueOf(array.get(i));
                        obj = obj.replace("[", "");
                        obj = obj.replace("]", "");
                        obj = obj.replace("\\", "");
                        obj = obj.substring(1, obj.length() - 1);
                        Log.d("service", "mainFragment: " + obj);

                        JSONObject strObj = null;
                        try {
                            strObj = new JSONObject(obj);
                            if (!strObj.get("body").toString().equals("") && strObj.get("status").toString().equals("접수대기")) {
                                strArray.add(strObj.getString("status"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d("toto", "MainFragment: " + strArray.size());
                    Log.d("mainFragment", "strArray.size() : " + strArray.size());
                    currentService.setText(String.valueOf(strArray.size()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

        interfaceAPI.order(storeCode, currentDate).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();
                    JsonArray array = (JsonArray) object.get("orderresult");
                    Log.d("mainFragment", "order object: " + object);
                    if (array.size() == 0) {
                        currentOrder.setText(String.valueOf(array.size()));
                    }

                    ArrayList<String> strArray = new ArrayList<>();

                    for (int i = 0; i < array.size(); i++) {
                        String obj = String.valueOf(array.get(i));
                        obj = obj.replace("[", "");
                        obj = obj.replace("]", "");
                        obj = obj.replace("\\", "");
                        obj = obj.substring(1, obj.length() - 1);

                        JSONObject strObj = null;

                        try {
                            strObj = new JSONObject(obj);
                            Log.d("order strObj", "onResponse: " + strObj.toString());
                            if (strObj.get("status").toString().equals("접수대기")) {
                                Log.d("tnt", "onResponse: " + strObj.get("status").toString());
                                strArray.add(strObj.get("status").toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    currentOrder.setText(String.valueOf(strArray.size()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
