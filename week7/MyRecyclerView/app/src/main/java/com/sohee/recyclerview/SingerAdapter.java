package com.sohee.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {
    Context context;

    ArrayList<SingerItem> items = new ArrayList<>();

    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void onItemClick(ViewHolder holder, View view, int position);
    }

    public SingerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.singer_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SingerItem item = items.get(position);
        holder.setItem(item);

        holder.setOnItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(SingerItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<SingerItem> items) {
        this.items = items;
    }

    public SingerItem getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener!= null){
                        listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            });

        }

        public void setItem(SingerItem item) {
            textView1.setText(item.getName());
            textView2.setText(item.getMobile());
        }


        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

}
