package com.example.admin_on_order.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.example.admin_on_order.Adapter.CalculatorRecyclerAdapter;
import com.example.admin_on_order.InterfaceAPI;
import com.example.admin_on_order.NullOnEmptyConverterFactory;
import com.example.admin_on_order.R;
import com.example.admin_on_order.model.CalculatorModel;
import com.example.admin_on_order.model.CalculatorRecyclerItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
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

public class CalculateFragment extends Fragment {

    Context context;
    View view;
    SharedPreferences pref;
    String storeCode;

    private ImageView btnDatePick;
    private TextView dateResultViewStart;
    private TextView dateResultViewEnd;
    private String dateResultStart;
    private String dateResultEnd;
    Calendar calendar;

    TextView txtTotalPrice, txtTotalReturnSale, txtTotalSales, txtTotalDiscount, txtActualSales, txtTaxSale,
            txtVatSale, txtDutyFreeSale, txtNetSales, txtCash, txtCredit, txtGiftCard,
            txtAffiliateCard, txtMilelege, txtMealTicket, txtOther;

    public ArrayList<CalculatorRecyclerItem> items;
    CalculatorRecyclerAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private static String BASE_URL = "http://15.164.232.164:5000";

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calculate, container, false);
        pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        storeCode = pref.getString("storecode", "");

        // Current Time now
        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateResultViewStart = view.findViewById(R.id.calc_date_start);
        dateResultViewStart.setText(dateFormat.format(calendar.getTime()));
        dateResultStart = dateResultViewStart.getText().toString();

        dateResultViewEnd = view.findViewById(R.id.calc_date_end);
        dateResultViewEnd.setText(dateFormat.format(calendar.getTime()));
        dateResultEnd = dateResultViewEnd.getText().toString();

        Log.d("stend", "dateStart " + dateResultStart + " dateEnd " + dateResultEnd);
        initViewCalculator(storeCode, dateResultStart, dateResultEnd);
        initViewCalcRecycler(storeCode, dateResultStart, dateResultEnd);

        recyclerView = view.findViewById(R.id.calc_recyclerview);

        DatePickerDialog.OnDateSetListener dateSetStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                dateResultViewStart.setText(dateFormat.format(calendar.getTime()));
                dateResultStart = dateResultViewStart.getText().toString();
                initViewCalculator(storeCode, dateResultStart, dateResultEnd);
                initViewCalcRecycler(storeCode, dateResultStart, dateResultEnd);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                dateResultViewEnd.setText(dateFormat.format(calendar.getTime()));
                dateResultEnd = dateResultViewEnd.getText().toString();
                Log.d("DAEASD", "onDateSet: " + dateResultEnd + " " +  dateResultStart);
                initViewCalculator(storeCode, dateResultStart, dateResultEnd);
                initViewCalcRecycler(storeCode, dateResultStart, dateResultEnd);
            }
        };

        dateResultViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        dateSetStart,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        dateResultViewEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        dateSetEnd,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                Date fromDate = parseFromDate();
                if (fromDate != null) {
                    dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                }
                dialog.getDatePicker().setMinDate(fromDate.getTime());
                dialog.show();
            }
        });

//        // Date Picker From
//        dateResultViewStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog fromPicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        calendar.set(Calendar.YEAR, year);
//                        calendar.set(Calendar.MONTH, month);
//                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                        dateResultViewStart.setText(dateFormat.format(calendar.getTime()));
//                        dateResultStart = dateResultViewStart.getText().toString();
//                        Log.d("calcDateResultStart", "onClick: " + dateResultStart);
//                    }
//                }, calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH));
//                fromPicker.getDatePicker().setMaxDate(new Date().getTime());
//                fromPicker.show();
//            }
//        });

//        Log.d("calc", "MonClickS: " + dateResultStart);
//
//        // Date Picker To
//        dateResultViewEnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog toPicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        calendar.set(Calendar.YEAR, year);
//                        calendar.set(Calendar.MONTH, month);
//                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                        dateResultViewEnd.setText(dateFormat.format(calendar.getTime()));
//                        dateResultEnd = dateResultViewEnd.getText().toString();
//                        Log.d("calcDateResultEndStart", "onDateSet: " + dateResultStart);
//                        Log.d("calcDateResultEnd", "onClick: " + dateResultEnd);
//                        initViewCalculator(storeCode, dateResultStart, dateResultEnd);
//                    }
//                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//                Date fromDate = parseFromDate();
//                if (fromDate != null) {
//                    toPicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
//                }
//                toPicker.getDatePicker().setMinDate(fromDate.getTime());
//                toPicker.show();
//            }
//        });

        //initViewCalculator(storeCode, dateResultStart, dateResultEnd);

        Log.d("calc", "MonClickE: " + dateResultEnd);

        txtTotalPrice = view.findViewById(R.id.calc_total_price);
        txtTotalReturnSale = view.findViewById(R.id.calc_total_return);
        txtTotalSales = view.findViewById(R.id.calc_total_sales);
        txtTotalDiscount = view.findViewById(R.id.calc_total_discount);
        txtActualSales = view.findViewById(R.id.calc_actual_sales);
        txtTaxSale = view.findViewById(R.id.calc_tax_sale);
        txtVatSale = view.findViewById(R.id.calc_vat_sale);
        txtDutyFreeSale = view.findViewById(R.id.calc_duty_free);
        txtNetSales = view.findViewById(R.id.calc_net_sales);
        txtCash = view.findViewById(R.id.calc_cash);
        txtCredit = view.findViewById(R.id.calc_credit);
        txtGiftCard = view.findViewById(R.id.calc_gift_card);
        txtAffiliateCard = view.findViewById(R.id.calc_affiliatecard);
        txtMilelege = view.findViewById(R.id.calc_milelege);
        txtMealTicket = view.findViewById(R.id.calc_meal_ticket);
        txtOther = view.findViewById(R.id.calc_other);

//        initViewCalculator(storeCode, dateResultStart, dateResultEnd);

        return view;
    }

    public void initViewCalculator(String storeCode, String paymentTimeFrom, String paymentTimeTo) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        interfaceAPI.sellPayment(storeCode, paymentTimeFrom, paymentTimeTo).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();

                    JsonArray array = (JsonArray) response.body().get("paymentresult");
                    DecimalFormat decimalFormat = new DecimalFormat("###,###");

                    String totalPrice = decimalFormat.format(Integer.parseInt(object.get("totalprice").toString()));
                    String totReturnSale = decimalFormat.format(Integer.parseInt(object.get("totreturnsale").toString()));
                    String totalDiscount = decimalFormat.format(Integer.parseInt(object.get("totaldiscount").toString()));
                    String taxSale = decimalFormat.format(Integer.parseInt(object.get("taxsale").toString()));
                    String vatSale = decimalFormat.format(Integer.parseInt(object.get("vatsale").toString()));
                    String dutyFreeSale = decimalFormat.format(Integer.parseInt(object.get("duty-freesale").toString()));

                    String cash = decimalFormat.format(Integer.parseInt(object.get("cash").toString()));
                    String credit = decimalFormat.format(Integer.parseInt(object.get("credit").toString()));
                    String giftCard = decimalFormat.format(Integer.parseInt(object.get("giftcard").toString()));
                    String affiliateCard = decimalFormat.format(Integer.parseInt(object.get("affiliatecard").toString()));
                    String milelege = decimalFormat.format(Integer.parseInt(object.get("milelege").toString()));
                    String mealTicket = decimalFormat.format(Integer.parseInt(object.get("mealticket").toString()));
                    String other = decimalFormat.format(Integer.parseInt(object.get("other").toString()));

                    String paymentTime = object.get("paymenttime").toString().replace("\"", "");
                    String paymentTime2 = object.get("paymenttime2").toString().replace("\"", "");


                    CalculatorModel calculatorModel = new CalculatorModel(
                            totalPrice, totReturnSale, totalDiscount, taxSale, vatSale, dutyFreeSale, cash, credit, giftCard,
                            affiliateCard, milelege, mealTicket, other, paymentTime, paymentTime2
                    );

                    // Calculator Left Sales Status
                    txtTotalPrice.setText(calculatorModel.getTotalPrice());
                    txtTotalReturnSale.setText(calculatorModel.getTotReturnPrice());
                    txtTotalSales.setText(String.valueOf(decimalFormat.format(Integer.parseInt(object.get("totalprice").toString()) - Integer.parseInt(object.get("totreturnsale").toString()))));
                    txtTotalDiscount.setText(calculatorModel.getTotalDiscount());
                    String strTotalSales = txtTotalSales.getText().toString().replace(",", "");

                    txtActualSales.setText(decimalFormat.format(Integer.parseInt(strTotalSales) - Integer.parseInt(object.get("totaldiscount").toString())));
                    txtTaxSale.setText(calculatorModel.getTaxSale());
                    txtVatSale.setText(calculatorModel.getVatSale());
                    txtDutyFreeSale.setText(calculatorModel.getDutyFreeSale());
                    String strActualSales = txtActualSales.getText().toString().replace(",", "");

                    txtNetSales.setText(decimalFormat.format(Integer.parseInt(strActualSales) - Integer.parseInt(object.get("vatsale").toString())));

                    // Calculator Right Sales Detail by Payment Method
                    txtCash.setText(calculatorModel.getCash());
                    txtCredit.setText(calculatorModel.getCredit());
                    txtGiftCard.setText(calculatorModel.getGiftCard());
                    txtAffiliateCard.setText(calculatorModel.getAffiliateCard());
                    txtMilelege.setText(calculatorModel.getMilelege());
                    txtMealTicket.setText(calculatorModel.getMealTicket());
                    txtOther.setText(calculatorModel.getOther());

                    dateResultViewStart.setText(calculatorModel.getPaymentTime());
                    dateResultViewEnd.setText(calculatorModel.getPaymentTime2());

                } else {

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void initViewCalcRecycler(String storeCode, String paymentTimeFrom, String paymentTimeTo) {
        Log.d("RecyclerView START", "START POINT");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        interfaceAPI.sellPayment(storeCode, paymentTimeFrom, paymentTimeTo).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();
                    JsonArray array = (JsonArray) object.get("paymentresult");
                    items = new ArrayList<>();

                    Log.d("Calculate response", object.toString());

                    if (array.size() == 0) {
                        items.clear();
                        adapter = new CalculatorRecyclerAdapter(context, items);
                        recyclerView.setAdapter(adapter);
                    }

                    Log.d("TESTARRAY", "onResponse: " + array.toString());

//                    String test = String.valueOf(array).replace("[", "").replace("]", "").replace("\\", "");
//                    Log.d("TESTARRAY", "onResponse: " + test);
//                    String subPrice = test[0];
//                    Log.d("TESTPRICE", "onResponse: " + subPrice);

                    for (int i = 0; i < array.size(); i++) {
                        String obj = String.valueOf(array.get(i));
//                        Log.d("OBJSTR", "onResponse: " + obj);
                        obj = obj.replace("[", "");
                        obj = obj.replace("]", "");
                        obj = obj.replace("\\", "");
//                        Log.d("TESTOBJ", "onResponse: " + obj);

                        String[] splitString = obj.split(",",2);
//                        Log.d("SPLIT", "onResponse: " + splitString[0]);
//                        Log.d("222222", "onResponse: " + splitString[1]);
                        String subPrice = splitString[0].replace("\"", "");
                        Log.d("subprice", "onResponse: " + subPrice);

//                        obj = obj.substring(obj.indexOf("\""));
                        obj = splitString[1];
                        obj = obj.substring(1, obj.length() - 1);
//                        Log.d("Calc OBJ", "onResponse: " + obj);

                        JSONObject strObj = null;
                        try {

                            strObj = new JSONObject(obj);
                            DecimalFormat decimalFormat = new DecimalFormat("###,###");
//                            String strPrice = decimalFormat.format(Integer.parseInt(subPrice));

                            CalculatorRecyclerItem item = new CalculatorRecyclerItem(
                                    strObj.get("tableno").toString(),
                                    strObj.get("status").toString(),
                                    strObj.get("authdate").toString(),
                                    strObj.get("price").toString(),
                                    strObj.get("cardno").toString()
                            );

                            if (!object.get("paymentresult").toString().equals("")) {
                                items.add(item);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new CalculatorRecyclerAdapter(context, items);
                    recyclerView.setAdapter(adapter);
                }

                Log.d("RecyclerView END", "End point");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        Log.d("RecyclerView Logic END", "Logic End point");
    }

    private Date parseFromDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return dateFormat.parse(dateResultViewStart.getText().toString());
        } catch (Exception e){
            return  null;
        }
    }

    class DateFromThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                DatePickerDialog.OnDateSetListener dateSetStart = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        dateResultViewStart.setText(dateFormat.format(calendar.getTime()));
                        dateResultStart = dateResultViewStart.getText().toString();
                        initViewCalculator(storeCode, dateResultStart, dateResultEnd);
                        initViewCalcRecycler(storeCode, dateResultStart, dateResultEnd);
                    }
                };
            }
        }
    }
}
