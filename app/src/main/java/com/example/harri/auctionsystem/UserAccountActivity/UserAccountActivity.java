package com.example.harri.auctionsystem.UserAccountActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.harri.auctionsystem.AccountCreationFlow.AccountCreationActivity;
import com.example.harri.auctionsystem.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserAccountActivity extends AppCompatActivity {

    private static String Functionality_Code="";
    ImageView mThumbnail;
    public static String membershipType;
    public static int menuItemView;
    private static final int REQUEST_CODE_GALLERY = 2;
    private FirebaseAuth mAuth;


    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        Intent intents = getIntent();
        System.out.println("hello g"+membershipType);
        membershipType = intents.getExtras().getString("memberType");
        System.out.println("hello there"+membershipType);
        mAuth = FirebaseAuth.getInstance();
       // FirebaseDatabase.getInstance().getReference().child("Winning").removeValue();
        saveUserPref();

/*
        if(membershipType.equals("Auctioner")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.user_fragment_container,  new UserAuctionerFragment())
                    .commit();
        }
*/

       // getSupportFragmentManager().beginTransaction().add(R.id.user_fragment_container, new userCheckFragment()).commit();
    }



        public void saveUserPref(){

            sharedPreferences = getSharedPreferences(getResources().getString(R.string.prefKey),0);
            sharedPreferences.edit().putString(getResources().getString(R.string.prefType),membershipType).apply();
            System.out.println("saveuserpref"+membershipType);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (membershipType.equals(getString(R.string.Bidder_type))){

            MenuItem registerOne = menu.findItem(R.id.future_auctions);
            MenuItem registerTwo = menu.findItem(R.id.win_auctions);

            MenuItem registerThree= menu.findItem(R.id.live_auctions);
            registerOne.setVisible(true);
            registerTwo.setVisible(true);
            registerThree.setVisible(true);
        }

        if (membershipType.equals(getString(R.string.Auctioner_type))) {

            MenuItem registerOne = menu.findItem(R.id.my_auctions);
            MenuItem registerTwo = menu.findItem(R.id.post_an_item);

            registerOne.setVisible(true);
            registerTwo.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        menuItemView = item.getItemId();

        switch (item.getItemId()) {

            case R.id.future_auctions:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.user_fragment_container, new FutureAuctionFragment()).commit();
                break;
            case R.id.win_auctions:

        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.user_fragment_container, new WinnigListFragment()).commit();
                break;


            case R.id.live_auctions:
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.user_fragment_container, new BidderLiveAuctionFragment())
        .commit();
                break;
            case R.id.my_auctions:


                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.user_fragment_container,new AuctionListFragment())
                        .commit();
                break;



            case R.id.post_an_item:


getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.user_fragment_container,new selectItemForAuctionFragment())
        .commit();
                break;

            case R.id.action_sign_out:

                mAuth.signOut();
                sharedPreferences.edit().remove(getResources().getString(R.string.prefType)).apply();

                Intent intent = new Intent(this, AccountCreationActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                System.out.println("hello"+membershipType);
                startActivity(intent);

                finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
     //   finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();


        //ye destroy krna h
     //   finish();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


}
