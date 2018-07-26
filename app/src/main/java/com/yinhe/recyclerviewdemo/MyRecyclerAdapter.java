package com.yinhe.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/7/26
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public MyRecyclerAdapter(List<String> list) {
        this.list = list;
    }

    /**
     * 设置点击事件
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 插入一条数据
     *
     * @param index 下标
     * @param s     数据
     */
    public void addItem(int index, String s) {
        list.add(index, s);
        notifyItemInserted(index);
    }

    /**
     * 删除一条数据
     *
     * @param index 下标
     */
    public void deleteItem(int index) {
        list.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * 设置长按点击事件
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder myHolder  = (MyViewHolder)holder;
            myHolder.textView.setText(list.get(position));

            int adapterPosition = holder.getAdapterPosition();
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new MyOnClickListener(position, list.get(adapterPosition)));
            }
            if (onItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener(new MyOnLongClickListener(position, list.get(adapterPosition)));
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyOnLongClickListener implements View.OnLongClickListener {
        private int position;
        private String data;

        public MyOnLongClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickListener.onItemLongClick(v, position, data);
            return true;
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int position;
        private String data;

        public MyOnClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, position, data);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, String data);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position, String data);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        MyViewHolder(View itemview){
            super(itemview);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
