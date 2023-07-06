package com.example.conveniencestoreapp.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conveniencestoreapp.Models.NavCategoryModel;
import com.example.conveniencestoreapp.Models.PopularModel;
import com.example.conveniencestoreapp.R;
import com.example.conveniencestoreapp.adapters.NavCatergoryAdapter;
import com.example.conveniencestoreapp.adapters.PopularAdapters;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {


    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    FirebaseFirestore db;
    NavCatergoryAdapter navCatergoryAdapter;
    ProgressBar progressBar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category,container,false);
        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = root.findViewById(R.id.category_recyle);
        recyclerView.setVisibility(View.GONE);
        //Popular items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryModelList = new ArrayList<>();
        navCatergoryAdapter = new NavCatergoryAdapter(getActivity(),categoryModelList);
        recyclerView.setAdapter(navCatergoryAdapter);
        db = FirebaseFirestore.getInstance();
        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            NavCategoryModel navCategoryModel= document.toObject(NavCategoryModel.class);
                            categoryModelList.add(navCategoryModel);
                            navCatergoryAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                });
        return root;
    }

}