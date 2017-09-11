package com.example.harri.auctionsystem.AccountCreationFlow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.harri.auctionsystem.R;
import com.example.harri.auctionsystem.UserAccountActivity.UserAccountActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AccountCreationActivity extends AppCompatActivity {

    private static Context context;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private FirebaseDatabase DatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        context = getApplicationContext();
        mAuth= FirebaseAuth.getInstance();
        sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.prefKey),0);
        String membershipType = sharedPreferences.getString(getResources().getString(R.string.prefType), "");
        System.out.println("hello2"+membershipType);
        if (mAuth.getCurrentUser() != null && !membershipType.equals("")){
            System.out.println("hello3"+membershipType);
            Intent intent = new Intent(this, UserAccountActivity.class);
            intent.putExtra("memberType", membershipType);
            startActivity(intent);

            finish();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new SignInFragment())
                .commit();

    }
}
