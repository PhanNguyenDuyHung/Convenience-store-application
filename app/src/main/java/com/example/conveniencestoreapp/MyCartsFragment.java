package com.example.conveniencestoreapp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.example.conveniencestoreapp.Models.MyCartModel;
import com.example.conveniencestoreapp.activity.PlacedOrderActivity;
import com.example.conveniencestoreapp.adapters.MyCartAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MyCartsFragment extends Fragment {
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    TextView overTotalAmount;
    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> myCartModelList;
    Button buyNow;
    int totalBill;
    ProgressBar progressBar;
    public MyCartsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_my_carts, container, false);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = root.findViewById(R.id.cart_recyclerview);
        recyclerView.setVisibility(View.GONE);
        buyNow = root.findViewById(R.id.buy_now);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        overTotalAmount = root.findViewById(R.id.cart_price);



        myCartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getActivity(),myCartModelList);
        recyclerView.setAdapter(myCartAdapter);

        firestore.collection("CurrentUser").document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("AddToCart").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                            String documentId = documentSnapshot.getId();

                            MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                            cartModel.setDocumentId(documentId);

                            myCartModelList.add(cartModel);
                            myCartAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                        calculateTotalAmount(myCartModelList);
                    }
                });
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                intent.putExtra("itemList",(Serializable) myCartModelList);
                startActivity(intent);
            }
        });

        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> myCartModelList) {
        int  totalAmount = 0;
        for (MyCartModel myCartModel :  myCartModelList){
            totalAmount += myCartModel.getTotalPrice();
        }

        overTotalAmount.setText("Total Amount :" + totalAmount + "$");
    }

}