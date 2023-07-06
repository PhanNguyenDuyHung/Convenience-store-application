package com.example.conveniencestoreapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.conveniencestoreapp.Models.NavCategoryDetailModel;
import com.example.conveniencestoreapp.Models.NavCategoryModel;
import com.example.conveniencestoreapp.Models.PopularModel;
import com.example.conveniencestoreapp.Models.ViewAllModel;
import com.example.conveniencestoreapp.R;
import com.example.conveniencestoreapp.adapters.NavCategoryDetailAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<NavCategoryDetailModel> list;
    NavCategoryDetailAdapter adapter;
    FirebaseFirestore db;
    Toolbar toolbar;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar =findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.nav_cat_det_dec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String type = getIntent().getStringExtra("type");
        list = new ArrayList<>();
        adapter = new NavCategoryDetailAdapter(this,list);
        recyclerView.setAdapter(adapter);

        if (type != null && type.equalsIgnoreCase("drink")){
            db.collection("NavCategoryDetail").whereEqualTo("type","drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailModel navCategoryDetailModel = documentSnapshot.toObject(NavCategoryDetailModel.class);
                        list.add(navCategoryDetailModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("meat")){
            db.collection("NavCategoryDetail").whereEqualTo("type","meat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailModel navCategoryDetailModel = documentSnapshot.toObject(NavCategoryDetailModel.class);
                        list.add(navCategoryDetailModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("fruit")){
            db.collection("NavCategoryDetail").whereEqualTo("type","fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailModel navCategoryDetailModel = documentSnapshot.toObject(NavCategoryDetailModel.class);
                        list.add(navCategoryDetailModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("bakery")){
            db.collection("NavCategoryDetail").whereEqualTo("type","bakery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailModel navCategoryDetailModel = documentSnapshot.toObject(NavCategoryDetailModel.class);
                        list.add(navCategoryDetailModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("cereal")){
            db.collection("NavCategoryDetail").whereEqualTo("type","cereal").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailModel navCategoryDetailModel = documentSnapshot.toObject(NavCategoryDetailModel.class);
                        list.add(navCategoryDetailModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}