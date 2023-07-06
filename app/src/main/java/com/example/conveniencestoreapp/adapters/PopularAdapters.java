package com.example.conveniencestoreapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.conveniencestoreapp.Models.PopularModel;
import com.example.conveniencestoreapp.R;
import com.example.conveniencestoreapp.activity.ViewAllActivity;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelsList;

    public PopularAdapters(Context context, List<PopularModel> popularModelsList) {
        this.context = context;
        this.popularModelsList = popularModelsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(popularModelsList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popularModelsList.get(position).getName());
        holder.rating.setText(popularModelsList.get(position).getRating());
        holder.description.setText(popularModelsList.get(position).getDescription());
        holder.discount.setText(popularModelsList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",popularModelsList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,description,rating,discount;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            popImg = itemView.findViewById(R.id.popular_img);
            name = itemView.findViewById(R.id.popular_name);
            description = itemView.findViewById(R.id.popular_des);
            discount = itemView.findViewById(R.id.popular_discount);
            rating = itemView.findViewById(R.id.popular_rating);
        }
    }
}
