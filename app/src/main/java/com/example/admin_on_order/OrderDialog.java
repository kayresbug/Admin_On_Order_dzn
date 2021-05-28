package com.example.admin_on_order;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.admin_on_order.Fragment.OrderFragment;
import com.sam4s.printer.Sam4sBuilder;
import com.sam4s.printer.Sam4sPrint;

import java.text.DecimalFormat;
import java.util.HashMap;

public class OrderDialog extends Dialog {

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
    MainActivity mainActivity;
    Context context;



    public OrderDialog(@NonNull Context context, String body, String tableNo, String totalPrice, String paymentStatus,
                       String paymentType, String orderTime, String authNum, String authDate, String vanTr, String cardBin, String dptId) {
        super(context);
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
        this.context = context;
    }

    String body;
    String tableNo;
    String totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                dismiss();
            }
        });

        orderOk = findViewById(R.id.btn_order_dialog_ok);
        orderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        orderCancel = findViewById(R.id.btn_order_dialog_cancel);
        orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        orderReissue = findViewById(R.id.btn_order_dialog_reissue);
        orderReissue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity)getContext()).rePrint(orderTime, authDate, authNum, body, tableNo, totalPrice, vanTr);
//                ((MainActivity)getContext()).test();
                mainActivity = new MainActivity();
                mainActivity.rePrint(orderTime, authDate, authNum, body, tableNo, totalPrice, vanTr, cardBin);
                dismiss();

            }
        });

        orderCancelPayment = findViewById(R.id.btn_order_dialog_pay_cancel);
        orderCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity)getContext()).setPayment(orderTotalPrice.toString(), "cancelNoCard", vanTr, cardBin, authNum, authDate);
//                mainActivity = new MainActivity();
//                OrderFragment orderFragment = new OrderFragment();
//                mainActivity.setPayment(totalPrice, "cancelNoCard", vanTr, cardBin, authNum, authDate);
//                orderFragment.test();
                Intent intent = new Intent(context, testActivity.class);
                intent.putExtra("totalprice", totalPrice);
                intent.putExtra("type", "cancelNoCard");
                intent.putExtra("vantr", vanTr);
                intent.putExtra("cardbin", cardBin);
                intent.putExtra("authnum", authNum);
                intent.putExtra("authdate", authDate);
                context.startActivity(intent);
                dismiss();
            }
        });
    }
}
