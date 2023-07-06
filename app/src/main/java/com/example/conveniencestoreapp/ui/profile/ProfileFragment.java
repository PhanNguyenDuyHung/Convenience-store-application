package com.example.conveniencestoreapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.conveniencestoreapp.Models.UserModel;
import com.example.conveniencestoreapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ProfileFragment extends Fragment {


    Button edit_submit;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore firestore;
    String useruid;

    EditText username, useremail,userphone, useraddress;
    Button submit;

    public  ProfileFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile,container,false);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();
        useruid = auth.getCurrentUser().getUid();
        username = root.findViewById(R.id.edit_name);
        useremail = root.findViewById(R.id.edit_email);
        userphone = root.findViewById(R.id.edit_phonenumber);
        useraddress = root.findViewById(R.id.edit_address);
        submit = root.findViewById(R.id.edit_submit);

        DocumentReference documentReference = firestore.collection("Users").document(useruid);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                username.setText(value.getString("name"));
                useremail.setText(value.getString("email"));
                userphone.setText(value.getString("phonenumber"));
                useraddress.setText(value.getString("address"));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return root;
    }
    public void updateProfile(UserModel user)
    {
        DocumentReference documentReference = firestore.collection("Users").document(useruid);

    }
}