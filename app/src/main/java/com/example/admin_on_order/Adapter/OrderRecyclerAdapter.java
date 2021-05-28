package com.example.admin_on_order.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_on_order.OrderDialog;
import com.example.admin_on_order.R;
import com.example.admin_on_order.model.OrderRecyclerItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.OrderViewHolder> {

    Context context;
    private ArrayList<OrderRecyclerItem> items;
    View view;
    OrderDialog orderDialog;
    String fullMenuName;

    public OrderRecyclerAdapter(Context context, ArrayList<OrderRecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_order_item, parent, false);

        OrderViewHolder viewHolder = new OrderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.tableNo.setText(items.get(position).getTableNo());
        holder.menu.setText(items.get(position).getMenu());
        fullMenuName = items.get(position).getMenu();

        Log.d("menuText", "onBindViewHolder: " + items.get(position).getMenu().indexOf("n"));
        if (items.get(position).getMenu().contains("n")) {
            Log.d("authnum", "onBindViewHolder: " + items.get(position).getAuthNum());
            Log.d("MenuNameTNT", "onBindViewHolder: " + items.get(position).getMenu());
            Log.d("MenuNameTNT", "onBindViewHolder: " + items.get(position).getMenu().substring(0, items.get(position).getMenu().indexOf("n")));
            holder.menu.setText(items.get(position).getMenu().substring(0, items.get(position).getMenu().indexOf("n")).replace("\\", "") + " ...");
        }
//        else if (items.get(position).getMenu().contains("\\n")) {
//            // error point2
//            Log.d("error point2", "point 2 ");
//            holder.menu.setText(items.get(position).getMenu().substring(0, items.get(position).getMenu().indexOf("\\")).replace("n", "") + " ...");
//        }

        holder.paymentStatus.setText(items.get(position).getPaymentStatus());
//        if (items.get(position).getPaymentStatus().equals("결제완료")) {
//
//        }

        if (items.get(position).getPaymentType().equals("Card")) {
            holder.paymentType.setText("카드");
        }

        // total price calculator
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        int intTotalPrice = Integer.parseInt(items.get(position).getPrice());
        holder.totalPrice.setText(decimalFormat.format(intTotalPrice) + "원");
        Log.d("intTotalPrice", "onBindViewHolder: " + intTotalPrice);

        // Order Dialog
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDialog = new OrderDialog(view.getContext(),
                        items.get(position).getMenu().replace("\\n", "\n").replace("n", ""),
                        items.get(position).getTableNo(),
                        items.get(position).getPrice(),
                        items.get(position).getPaymentStatus(),
                        items.get(position).getPaymentType(),
                        items.get(position).getOrderTime(),
                        items.get(position).getAuthNum(),
                        items.get(position).getAuthDate(),
                        items.get(position).getVanTr(),
                        items.get(position).getCardBin(),
                        items.get(position).getDptId());
                orderDialog.show();
            }
        });

        holder.orderTime.setText(items.get(position).getOrderTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tableNo;
        TextView menu;
        TextView paymentStatus;
        TextView paymentType;
        TextView totalPrice;
        TextView orderTime;
        LinearLayout layout;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tableNo = itemView.findViewById(R.id.order_table_no);
            this.menu = itemView.findViewById(R.id.order_menu);
            this.paymentStatus = itemView.findViewById(R.id.order_payment_status);
            this.paymentType = itemView.findViewById(R.id.order_payment_type);
            this.totalPrice = itemView.findViewById(R.id.order_total_price);
            this.orderTime = itemView.findViewById(R.id.order_time);
            this.layout = itemView.findViewById(R.id.order_recycler_layout);
        }
    }
}
