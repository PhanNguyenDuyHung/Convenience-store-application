package com.example.conveniencestoreapp.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conveniencestoreapp.Models.HomeCategory;
import com.example.conveniencestoreapp.Models.PopularModel;
import com.example.conveniencestoreapp.Models.RecommendedModel;
import com.example.conveniencestoreapp.Models.ViewAllModel;
import com.example.conveniencestoreapp.R;
import com.example.conveniencestoreapp.adapters.HomeAdapter;
import com.example.conveniencestoreapp.adapters.PopularAdapters;
import com.example.conveniencestoreapp.adapters.RecommendedAdapter;
import com.example.conveniencestoreapp.adapters.ViewAllAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView popularRec,homeCatRec,recommendRec;
    FirebaseFirestore db;

    //Search View
    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private RecyclerView searchRecycler;
    private ViewAllAdapter viewAllAdapter;

    //Popular Items
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    //Home Category
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    //Recommend
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;
    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container,false);
        db = FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.popular_recycle);
        homeCatRec = root.findViewById(R.id.explore_recycle);
        recommendRec = root.findViewById(R.id.recommended_recycle);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        //Popular items
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            PopularModel popularModel = document.toObject(PopularModel.class);
                            popularModelList.add(popularModel);
                            popularAdapters.notifyDataSetChanged();

                            progressBar.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                    }
                });

        //ExplorerCatergory
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            HomeCategory homeCategory = document.toObject(HomeCategory.class);
                            categoryList.add(homeCategory);
                            homeAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                    }
                });

        //Recommended
        recommendRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(),recommendedModelList);
        recommendRec.setAdapter(recommendedAdapter);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(),"Error"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Sreach View
        searchRecycler = root.findViewById(R.id.search_rec);
        search_box = root.findViewById(R.id.search_textbox);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getContext(),viewAllModelList);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        searchRecycler.setAdapter(viewAllAdapter);
        searchRecycler.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }else {
                    searchProduct(s.toString());
                }

            }
        });



        return root;
    }

    private void searchProduct(String type) {
        if(!type.isEmpty()){
            db.collection("AllProducts").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null){
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

                                }

                            }
                            else {
                                Toast.makeText(getActivity(),"Error"+task.getException(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}