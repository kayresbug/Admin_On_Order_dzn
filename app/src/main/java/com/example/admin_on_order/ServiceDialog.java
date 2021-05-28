package com.example.admin_on_order;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.admin_on_order.Fragment.ServiceFragment;

public class ServiceDialog extends Dialog {

    private TextView serviceBody;
    private TextView serviceTableNo;
    private Button serviceClose;
    private TextView btnOk;
    private TextView btnCancel;

    ServiceFragment fragment;

    Context context;

    String body;
    String tableNo;
    String num;
    String paymentStatus;
    int position;

    public ServiceDialog(@NonNull Context context, int position, String body, String tableNo, String num, String paymentStatus, ServiceFragment fragment) {
        super(context);
        this.context = context;
        this.body = body;
        this.tableNo = tableNo;
        this.num = num;
        this.paymentStatus = paymentStatus;
        this.fragment = fragment;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = layoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.service_dialog);

        serviceBody = findViewById(R.id.service_dialog_body);
        serviceBody.setText(body);

        serviceTableNo = findViewById(R.id.service_dialog_table_no);
        serviceTableNo.setText(tableNo + " 번 테이블");

        serviceClose = findViewById(R.id.service_dialog_close);
        serviceClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Service Dialog", "Dialog Dismiss");
                dismiss();
            }
        });

        btnOk = findViewById(R.id.btn_service_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragment.updateStatus(num);
                Log.d("DFDF", "paymentStatus : " + paymentStatus + " / " + " num : " + num);
                Toast.makeText(context, "OK BUTTON ON SERVICE DIALOG", Toast.LENGTH_SHORT).show();
                fragment.updateStatus(num, position);
                dismiss();
            }
        });

        btnCancel = findViewById(R.id.btn_service_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "CANCEL BUTTON ON SERVICE DIALOG", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}
