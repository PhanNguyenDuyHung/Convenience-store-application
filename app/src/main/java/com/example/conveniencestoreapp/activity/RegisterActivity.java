package com.example.conveniencestoreapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.conveniencestoreapp.LoginActivity;
import com.example.conveniencestoreapp.Models.UserModel;
import com.example.conveniencestoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button signUp;
    EditText name, email, password,checkpass,phonenumber;
    TextView signIn;
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();
        signUp = findViewById(R.id.btn_SignUp);
        name = findViewById(R.id.reg_username);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        checkpass = findViewById(R.id.passwordconfirm);
        phonenumber = findViewById(R.id.reg_phonenumber);
        signIn = findViewById(R.id.signIn);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility( View.GONE);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(RegisterActivity.this, com.example.conveniencestoreapp.LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                createUser();
            }
        });
    }

    private void createUser(){
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String checkPassword = checkpass.getText().toString();
        String userPhone = phonenumber.getText().toString();
        String userAddress = "";


        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Username is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Email is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Password is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6){
            Toast.makeText(this,"Password length must be greater than 6 letter!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(checkPassword)){
            Toast.makeText(this,"The password confirmation is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!TextUtils.equals(userPassword.toString(), checkPassword.toString())){
            Toast.makeText(this,"The password confirmation does not match!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Phone number is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 11){
            Toast.makeText(this,"Phone number must have 10 numbers!",Toast.LENGTH_SHORT).show();
            return;
        }

        //createUser
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            UserModel userModel = new UserModel(userName,userEmail,userPassword,userPhone,userAddress);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userModel);
                            Toast.makeText(RegisterActivity.this,"Registration Successful!!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this,"Error:"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}