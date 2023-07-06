package com.example.conveniencestoreapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.conveniencestoreapp.Models.NavCategoryDetailModel;
import com.example.conveniencestoreapp.R;
import com.example.conveniencestoreapp.activity.DetailActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class NavCategoryDetailAdapter extends RecyclerView.Adapter<NavCategoryDetailAdapter.ViewHolder> {

    Context context;
    List<NavCategoryDetailModel> list;

    FirebaseFirestore firestore;

    public NavCategoryDetailAdapter(Context context, List<NavCategoryDetailModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NavCategoryDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detailed_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryDetailAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice() + "$");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, price, quantity;
        FirebaseAuth auth;

        AppCompatButton button;
        int totalQuantity = 1;
        int totalPrice = 0;
        NavCategoryDetailModel navCategoryDetailModel;

        ImageView addItem,removeItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firestore =FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();
            imageView = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.nav_cat_name);
            addItem = itemView.findViewById(R.id.add_item);
            removeItem = itemView.findViewById(R.id.remove_item);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            button = itemView.findViewById(R.id.add_to_cart);

//            addItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (totalQuantity < 10){
//                        totalQuantity++;
//                        quantity.setText(String.valueOf(totalQuantity));
//                        totalPrice = navCategoryDetailModel.getPrice() * totalQuantity;
//                    }
//
//                }
//            });
//            removeItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (totalQuantity > 0){
//                        totalQuantity--;
//                        quantity.setText(String.valueOf(totalQuantity));
//                        totalPrice = navCategoryDetailModel.getPrice() * totalQuantity;
//                    }
//                }
//            });


        }
//        private void addedtoCart() {
//            String saveCurrentDate,saveCurrentTime;
//            Calendar calforDate = Calendar.getInstance();
//
//            SimpleDateFormat currentDate = new SimpleDateFormat("dd MM, yyyy");
//            saveCurrentDate = currentDate.format(calforDate.getTime());
//
//            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//            saveCurrentTime = currentTime.format(calforDate.getTime());
//
//            final HashMap<String,Object> cartMap = new HashMap<>();
//
//            cartMap.put("productName",navCategoryDetailModel.getName());
//            cartMap.put("productPrice",price.getText().toString());
//            cartMap.put("currentDate",saveCurrentDate);
//            cartMap.put("currentTime",saveCurrentTime);
//            cartMap.put("totalQuantity",quantity.getText().toString());
//            cartMap.put("totalPrice",totalPrice);
//
//            firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
//                    .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DocumentReference> task) {
//                            Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//        }
    }
}
