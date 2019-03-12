package com.cxp.androidut.espresso.list;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: Adapter
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:09
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class Adapter extends RecyclerView.Adapter<Adapter.TextViewHolder> {
    private final OnItemClickListener listener;
    private List<String> labels;

    public Adapter(int count, OnItemClickListener listener) {
        labels = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            labels.add(String.valueOf(i));
        }
        this.listener = listener;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                android.R.layout.simple_list_item_1, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TextViewHolder holder, final int position) {
        final String label = labels.get(position);
        holder.textView.setText(label);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            TypedValue outValue = new TypedValue();
            holder.textView.getContext().getTheme().resolveAttribute(
                    android.R.attr.selectableItemBackground, outValue, true);
            holder.textView.setBackgroundResource(outValue.resourceId);
        }

        if (listener != null) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
