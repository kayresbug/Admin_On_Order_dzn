package com.example.admin_on_order;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.sam4s.printer.Sam4sPrint;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText editId, editPw;
    private CheckBox autoLogin;
    private Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    AdminApplication app;

    private static String BASE_URL = "http://15.164.232.164:5000";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app = new AdminApplication();

        editId = (EditText) findViewById(R.id.edit_id);
        editPw = (EditText) findViewById(R.id.edit_pw);

        editId.setText("nddz12");
        editPw.setText("1234");

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        editor = pref.edit();

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loginCheck();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void loginCheck() throws Exception {
        String strId = editId.getText().toString();
        String strPw = editPw.getText().toString();
                                editor.putString("storecode", "nddz12");
                        editor.putString("storename", "돈짬노형점");
                        editor.apply();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(new NullOnEmptyConverterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
//        interfaceAPI.login(strId, strPw).enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                if(response.isSuccessful()) {
//                    Log.d("Login Success", "onResponse: " + response.body().toString());
//                    String statusCode = response.body().get("StatusCode").toString().replace("\"","");
//                    String storeCode = response.body().get("storecode").toString();
//                    String storeName = response.body().get("storename").toString();
//
//                    if(statusCode.equals("200")) {
//                        Log.d("StoreCode", "onResponse Store Code: " + storeCode);
//                        editor.putString("storecode", storeCode.replace("\"", ""));
//                        editor.putString("storename", storeName.replace("\"", ""));
//                        editor.apply();
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Log.d("Login_Failure","Failure : "+t.getMessage() );
//            }
//        });
    }
}
