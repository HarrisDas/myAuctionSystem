package com.example.harri.auctionsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.harri.auctionsystem.AccountCreationFlow.AccountCreationActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent i = new Intent(this, AccountCreationActivity.class);
        startActivity(i);
        finish();
    }
}
