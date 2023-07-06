package com.example.conveniencestoreapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.conveniencestoreapp.HomeActivity;
import com.example.conveniencestoreapp.MainActivity;
import com.example.conveniencestoreapp.Models.MyCartModel;
import com.example.conveniencestoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    Button home;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        home = findViewById(R.id.home_btn);

        List<MyCartModel> list =(ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");

        if (list != null && list.size() > 0)
        {
            for (MyCartModel model : list){
                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("productName",model.getProductName());
                cartMap.put("productPrice",model.getProductPrice());
                cartMap.put("currentDate",model.getCurrentDate());
                cartMap.put("currentTime",model.getCurrentTime());
                cartMap.put("totalQuantity",model.getTotalQuantity());
                cartMap.put("totalPrice",model.getTotalPrice());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PlacedOrderActivity.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlacedOrderActivity.this, MainActivity.class));
            }
        });
    }
}