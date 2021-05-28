package com.example.admin_on_order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_on_order.R;
import com.example.admin_on_order.model.CalculatorRecyclerItem;

import java.util.ArrayList;

public class CalculatorRecyclerAdapter extends RecyclerView.Adapter<CalculatorRecyclerAdapter.CalculatorViewHolder> {

    ArrayList<CalculatorRecyclerItem> items;
    View view;
    Context context;

    public CalculatorRecyclerAdapter(Context context, ArrayList<CalculatorRecyclerItem> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CalculatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_calculate_item, parent, false);

        CalculatorViewHolder viewHolder = new CalculatorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CalculatorViewHolder holder, int position) {
        holder.tableNo.setText(items.get(position).getTableNo());
        holder.checkApproval.setText(items.get(position).getCheckApproval());
        holder.paymentTime.setText(items.get(position).getPaymentTime());
        holder.totalPrice.setText(items.get(position).getTotalPrice());
        holder.cardNo.setText(items.get(position).getCardNo());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CalculatorViewHolder extends RecyclerView.ViewHolder {

        TextView tableNo, checkApproval, paymentTime, totalPrice, cardNo;

        public CalculatorViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tableNo = itemView.findViewById(R.id.calc_table_no);
            this.checkApproval = itemView.findViewById(R.id.calc_check_approval);
            this.paymentTime = itemView.findViewById(R.id.calc_payment_time);
            this.totalPrice = itemView.findViewById(R.id.calc_item_total_price);
            this.cardNo = itemView.findViewById(R.id.calc_card_no);
        }
    }
}
