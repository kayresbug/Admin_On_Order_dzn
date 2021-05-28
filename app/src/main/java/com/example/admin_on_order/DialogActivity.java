package com.example.admin_on_order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DialogActivity extends Activity implements View.OnClickListener {
    private TextView orderBody;
    private TextView orderTableNo;
    private TextView orderTotalPrice;
    private Button orderClose;
    private TextView orderOk, orderCancel, orderReissue, orderCancelPayment;
    private String paymentStatus = "";
    private String paymentType = "";
    private String orderTime = "";
    private String authNum = "";
    private String authDate = "";
    private String vanTr = "";
    private String cardBin = "";
    private String dptId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.order_dialog);
        setcontent();
    }
    public DialogActivity(String body, String tableNo, String totalPrice, String paymentStatus,
                       String paymentType, String orderTime, String authNum, String authDate, String vanTr, String cardBin, String dptId) {
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.orderTime = orderTime;
        this.authNum = authNum;
        this.authDate = authDate;
        this.vanTr = vanTr;
        this.cardBin = cardBin;
        this.dptId = dptId;
        this.body = body;
        this.tableNo = tableNo;
        this.totalPrice = totalPrice;
    }

    String body;
    String tableNo;
    String totalPrice;

    public void setcontent(){
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = layoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.order_dialog);

        orderBody = findViewById(R.id.order_dialog_body);
        orderBody.setText(body);

        orderTableNo = findViewById(R.id.order_dialog_table_no);
        orderTableNo.setText(tableNo + " 번 테이블");

        orderTotalPrice = findViewById(R.id.order_dialog_total_price);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        orderTotalPrice.setText(decimalFormat.format(Integer.parseInt(totalPrice)) + "원");

        orderClose = findViewById(R.id.order_dialog_close);
        orderClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dismiss();
            }
        });

        orderOk = findViewById(R.id.btn_order_dialog_ok);
        orderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dismiss();
            }
        });

        orderCancel = findViewById(R.id.btn_order_dialog_cancel);
        orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dismiss();
            }
        });

        orderReissue = findViewById(R.id.btn_order_dialog_reissue);
        orderReissue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity)getContext()).rePrint(orderTime, authDate, authNum, body, tableNo, totalPrice, vanTr);
//                ((MainActivity)getContext()).test();
//                mainActivity = new MainActivity();
//                mainActivity.rePrint(orderTime, authDate, authNum, body, tableNo, totalPrice, vanTr);
//                dismiss();

            }
        });

        orderCancelPayment = findViewById(R.id.btn_order_dialog_pay_cancel);
        orderCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity)getContext()).setPayment(orderTotalPrice.toString(), "cancelNoCard", vanTr, cardBin, authNum, authDate);
//                mainActivity = new MainActivity();
////                OrderFragment orderFragment = new OrderFragment();
//                mainActivity.setPayment(totalPrice, "cancelNoCard", vanTr, cardBin, authNum, authDate);
//                dismiss();

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}