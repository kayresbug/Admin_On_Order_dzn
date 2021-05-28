package com.example.admin_on_order.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_on_order.Fragment.ServiceFragment;
import com.example.admin_on_order.R;
import com.example.admin_on_order.ServiceDialog;
import com.example.admin_on_order.model.ServiceRecyclerItem;

import java.util.ArrayList;

public class ServiceRecyclerAdapter extends RecyclerView.Adapter<ServiceRecyclerAdapter.CallViewHolder> {

    Context context;
    private ArrayList<ServiceRecyclerItem> items;
    int position;
    View view;
    ServiceDialog serviceDialog;
    ServiceFragment serviceFragment;

    public ServiceRecyclerAdapter(Context context, ArrayList<ServiceRecyclerItem> items, ServiceFragment serviceFragment, int position) {
        this.context = context;
        this.items = items;
        this.serviceFragment = serviceFragment;
        this.position = position;
    }

    @NonNull
    @Override
    public CallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_service_item, parent, false);

        CallViewHolder viewHolder = new CallViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CallViewHolder holder, int position) {
        holder.tableNo.setText(items.get(position).getTableNo());
        holder.inputTime.setText(items.get(position).getInputTime());
//        holder.body.setText(items.get(position).getBody().replace("n", "\n"));
        if (items.get(position).getBody().contains("n")) {
            String str[] = items.get(position).getBody().split("n");
            Log.d("test", "onBindViewHolder: " + str[0]);
            holder.body.setText(str[0] + " ...");
        } else {
            holder.body.setText(items.get(position).getBody());
        }

        if (items.get(position).getPaymentStatus().equals("접수완료")) {
            holder.payComplete.setText("완료");
            holder.payComplete.setBackgroundResource(R.drawable.card_border_rectangle);
            holder.payComplete.setTextColor(Color.parseColor("#ffffff"));
        }
        else if (items.get(position).getPaymentStatus().equals("접수대기")) {
            holder.payComplete.setText("대기");
            holder.payComplete.setBackgroundResource(R.drawable.cash_border_rectangle);
            holder.payComplete.setTextColor(Color.parseColor("#8e8e8e"));
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("getNum", "onClick: " + items.get(position).getNum());
                Log.d("getPaymentStatus", "onClick: " + items.get(position).getPaymentStatus());
                //serviceFragment.updateStatus(position, items.get(position).getPaymentStatus(), items.get(position).getNum());
                serviceDialog
                        = new ServiceDialog(view.getContext(), position, items.get(position).getBody().replace("n", "\n"), items.get(position).getTableNo(),
                        items.get(position).getNum(), items.get(position).getPaymentStatus(), serviceFragment);
                serviceDialog.show();
                Log.d("SCSC", "onClick: " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class CallViewHolder extends RecyclerView.ViewHolder {
        TextView tableNo;
        TextView body;
        TextView inputTime;
        TextView payComplete;
        LinearLayout layout;

        public CallViewHolder(@NonNull View itemView) {
            super(itemView);

            this.payComplete = itemView.findViewById(R.id.pay_status_complete);
            this.tableNo = itemView.findViewById(R.id.call_table_no);
            this.body = itemView.findViewById(R.id.call_req);
            this.inputTime = itemView.findViewById(R.id.call_time);
            this.layout = itemView.findViewById(R.id.service_recycler_layout);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
