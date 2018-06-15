package com.pickth.haeun.popcalendar.view.dialog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.model.CalendarItem;

import java.util.ArrayList;

public class CalendarListAdapter extends RecyclerView.Adapter<CalendarListAdapter.CalendarListViewHolder> {
    // 0이면 홈화면, 아니면 리스트 화면
    private int type = 0;
    ArrayList<CalendarItem> items = new ArrayList<>();

    public CalendarListAdapter(int type) {
        this.type = type;
    }

    @Override
    public CalendarListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule_list, parent, false);
        return new CalendarListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CalendarListViewHolder holder, int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(CalendarItem item) {
        items.add(item);
    }

    class CalendarListViewHolder extends RecyclerView.ViewHolder {
        CalendarListViewHolder(View view) {
            super(view);
        }

        void onBind(CalendarItem item) {
            TextView tvTitle = itemView.findViewById(R.id.tv_list_title);
            TextView tvMemo = itemView.findViewById(R.id.tv_list_memo);
            TextView tvName = itemView.findViewById(R.id.tv_list_name);
            TextView tvTel = itemView.findViewById(R.id.tv_list_tel);

            tvTitle.setText(item.title);
            tvMemo.setText(item.memo);
            try {
                tvName.setText(item.human.getName());
                tvTel.setText(item.human.getTel());
            }catch (Exception e){}

            if (item.memo.equals("")) type = 0;

            if(type == 0) {
                tvMemo.setVisibility(View.GONE);
            } else {
                tvMemo.setVisibility(View.VISIBLE);
            }
        }
    }
}
