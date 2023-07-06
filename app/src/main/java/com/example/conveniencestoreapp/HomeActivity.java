package com.example.conveniencestoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


    }

    public void login(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));

    }

    public void registration(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(HomeActivity.this,RegisterActivity.class));
    }

}