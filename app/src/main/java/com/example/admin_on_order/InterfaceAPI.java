package com.example.admin_on_order;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceAPI {

    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> login(@Field("userid") String id, @Field("password") String pw);

    @FormUrlEncoded
    @POST("service")
    Call<JsonObject> service(@Field("storecode") String storeCode, @Field("inputtime") String inputTime);

    @FormUrlEncoded
    @POST("service")
    Call<JsonObject> service2(@Field("storecode") String storeCode, @Field("inputtime") String inputTime);

    @GET("test")
    Call<JsonArray> getListTimeService(@Query("inputtime") String inputTime);

    @FormUrlEncoded
    @POST("sorder")
    Call<JsonObject> order(@Field("storecode") String storeCode, @Field("order_time") String orderTime);

    @FormUrlEncoded
    @POST("update_service")
    Call<JsonObject> serviceUpdateStatus(@Field("storecode") String storeCode, @Field("num") String num);

    @FormUrlEncoded
    @POST("nsellpayment")
    Call<JsonObject> sellPayment(@Field("storecode") String storeCode, @Field("order_time") String paymentTime, @Field("order_time2") String paymentTime2);
}
