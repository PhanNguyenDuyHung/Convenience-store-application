package com.example.conveniencestoreapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.conveniencestoreapp.Models.NavCategoryModel;
import com.example.conveniencestoreapp.R;
import com.example.conveniencestoreapp.activity.NavCategoryActivity;

import java.util.List;

public class NavCatergoryAdapter extends RecyclerView.Adapter<NavCatergoryAdapter.ViewHolder> {

    Context context;
    List<NavCategoryModel> navCategoryModelList;

    public NavCatergoryAdapter(Context context, List<NavCategoryModel> navCategoryModelList) {
        this.context = context;
        this.navCategoryModelList = navCategoryModelList;
    }

    @NonNull
    @Override
    public NavCatergoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCatergoryAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(navCategoryModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(navCategoryModelList.get(position).getName());
        holder.description.setText(navCategoryModelList.get(position).getDescription());
        holder.discount.setText(navCategoryModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("type",navCategoryModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return navCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description, discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.nav_cat_name);
            description = itemView.findViewById(R.id.nav_cat_description);
            discount = itemView.findViewById(R.id.nav_cat_discount);
        }
    }
}
