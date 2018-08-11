package com.bwie.Day9_Demo1.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.Day9_Demo1.R;
import com.bwie.Day9_Demo1.bean.ProductBean;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
    private List<ProductBean.Product> list;
    private OnItemClickListener onItemClickListener;

    public ProductAdapter(Context context, List<ProductBean.Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.product_item_layout,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ProductBean.Product product=list.get(position);

        String[] imageUrls=product.images.split("\\|");

        if (imageUrls!=null&&imageUrls.length>0){

            Glide.with(context).load(imageUrls[0]).into(holder.iv);

        }

        holder.tv.setText(product.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){

                    onItemClickListener.onItenClick(position);

                }
            }
        });

    }

    public void loadData(List<ProductBean.Product> data){

        if (this.list!=null){

            this.list.addAll(data);
            notifyDataSetChanged();

        }

    }


    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);

            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{

        void onItenClick(int position);

    }
}