package com.example.harri.auctionsystem.UserAccountActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.harri.auctionsystem.AccountCreationFlow.SignUp;
import com.example.harri.auctionsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;


public class BiddingAcitivity extends AppCompatActivity {
ImageView image;
    TextView winner_text;
    BidModelClass maxBidWala;
    TextView BidText,ItemNameText,mCategoryText,TimerText,FinalDateText;
    String bidderKey="";
  Item  myItem;
   String myKey;
String currentUUID;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    String MembershipType;
    SignUp currentUser;
    DatabaseReference ref;
    RecyclerView recyclerView;
    BiddingListAdapter myAdapter;
LinearLayout mLayout;
DatabaseReference mRef;
    DatabaseReference mRef2;
    ArrayList<BidModelClass> mBidList ;
    Button BidButton ;
    EditText bid_editer;
    Date currentTime;
    int Max=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding_acitivity);
mLayout=(LinearLayout)findViewById(R.id.Enter_bid_layout);
recyclerView = (RecyclerView)findViewById(R.id.BidList) ;
        winner_text= (TextView) findViewById(R.id.win_person);

        mBidList = new ArrayList<>();
        myAdapter = new BiddingListAdapter(BiddingAcitivity.this,   mBidList);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(BiddingAcitivity.this);
              recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(myAdapter);
        Intent i =getIntent();
        bid_editer =(EditText)findViewById(R.id.Enter_bid_EditText);
BidButton= (Button)findViewById(R.id.Submit_Bid_button);

       myItem= new Item( i.getStringExtra("myname"), i.getStringExtra("myCat"), i.getStringExtra("mybid"), i.getStringExtra("myUri"), i.getStringExtra("myUid"), i.getStringExtra("myInitailDate"), i.getStringExtra("myEndDate"), i.getStringExtra("myKey"), i.getStringExtra("winning"));

myKey= i.getStringExtra("myKey");

ItemNameText=(TextView)findViewById(R.id.bid_name);
        mCategoryText=(TextView)findViewById(R.id.bid_category_Name);
        BidText =(TextView)findViewById(R.id.bid_myBid);


        ItemNameText.setText("Item Name : "+myItem.getItemName());
        mCategoryText.setText("Item Category : "+myItem.getItemCategory());
        BidText.setText("Minimum Bid : "+myItem.getItemBid());
        image=(ImageView)findViewById(R.id.bid_thumbnail);
        Glide.with(this).load(myItem.getItemURI()).into(image);


//currentUUID= i.getStringExtra("myUUId");


        mAuth=FirebaseAuth.getInstance();
        currentUUID= mAuth.getCurrentUser().getUid();
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference();
        mRef.child("User").child(currentUUID).addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUser= dataSnapshot.getValue(SignUp.class);
                Toast.makeText(BiddingAcitivity.this, currentUser.getMemberType(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(BiddingAcitivity.this, "do bar chala", Toast.LENGTH_SHORT).show();

                if(currentUser.getMemberType().equals("Auctioner")){
                    mLayout.setVisibility(View.INVISIBLE);}
                    mDatabase.getReference().child("Bids").child(myKey).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            mBidList.clear();
                            Iterable<DataSnapshot> bids=dataSnapshot.getChildren();
                            for (DataSnapshot c: bids){
                                BidModelClass newBids=   c.getValue(BidModelClass.class);
                                if(newBids.getAuctionKey().equals(myKey)){

                                    mBidList.add(newBids);
                                    myAdapter.notifyDataSetChanged();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


              //  }else if (currentUser.getMemberType().toString().equals("Bidder")){


             //   }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
if(currentUser!=null) {
    System.out.println("yeh errror h");
    System.out.println("current User " + currentUser.getMemberType().toString() + " " + currentUser.getUUID().toString());
}/*
        if(currentUser.getMemberType().equals("Auctioner")){


        }

*/

        BidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentBid= bid_editer.getText().toString();


                if(!currentBid.equals("")){
                    if( Integer.parseInt(currentBid)>Integer.parseInt(myItem.getItemBid()))
                    {
                        if (currentUser!=null){

                        mRef2=mDatabase.getReference();
                          bidderKey = mRef2.child("Bids").push().getKey();
                            BidModelClass current_bidder_bid=new BidModelClass(currentBid,currentUser.getUUID().toString(),myKey, bidderKey);
                            mRef2.child("Bids").child(myKey).child(bidderKey).setValue(current_bidder_bid);
                            bid_editer.setText("");
                            myAdapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(BiddingAcitivity.this, "Found Error", Toast.LENGTH_SHORT).show();

                        }
                    }else { bid_editer.setText("");
                        Toast.makeText(BiddingAcitivity.this, "Low Bid Price", Toast.LENGTH_SHORT).show();
                    }

                }else bid_editer.setError("Enter Bid");

            }
        });


    currentTime = new Date();

        TimerText= (TextView)findViewById(R.id.counter);

        long end_time=Long.parseLong(myItem.getEnd_Date().toString());
       long time=end_time-currentTime.getTime();
   new CountDownTimer(time,1000){
    @Override
    public void onTick(long millisUntilFinished) {
        int hour=0,min=0,sec=0;
        sec=(int)millisUntilFinished/1000;

        min=sec/60;
        hour=min/60;
    TimerText.setText(hour+":"+(min-hour*60) +":"+ (sec-((hour*60)+ min-hour*60)*60));
    }

    @Override
    public void onFinish() {
        TimerText.setText("Finish");
        FirebaseDatabase.getInstance().getReference().child("Bids").child(myKey).orderByChild("bid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BidModelClass maxBider= new BidModelClass();
                int max=0;
for (DataSnapshot c: dataSnapshot.getChildren())
{ BidModelClass tempBid=c.getValue(BidModelClass.class);
    System.out.println( "ERROR1" +c.toString());
    System.out.println(c.toString());
    if(Integer.parseInt(tempBid.getBid())>Max){

        maxBider=tempBid;
        Max=Integer.parseInt(tempBid.getBid());

    }
   // maxBider= c.getValue(BidModelClass.class);


}
                System.out.println("maxbider"+maxBider.getBid().toString());

              /*  Iterable<DataSnapshot> currentBidsList= dataSnapshot.getChildren();
                int max=0;

                for(DataSnapshot c: currentBidsList){
                    BidModelClass tempBid=c.getValue(BidModelClass.class);
                    System.out.println(tempBid);
                    if(Integer.parseInt(tempBid.getBid())>Max){

                        maxBider=tempBid;
                        Max=Integer.parseInt(tempBid.getBid());

                    }


                }*/

                System.out.println(" Pta nhi kia arha h" + maxBider.getUUID()+ " BID: " + maxBider.getBid()  );
              winner_text.setText(maxBider.getUUID()+" BID: "+ maxBider.getBid());
if(myItem.getWinning().equals("False")){
    FirebaseDatabase.getInstance().getReference().child("Auction").child(myKey).child("winning").setValue("True");

    System.out.println("Han bhai ek bar chala");
    FirebaseDatabase.getInstance().getReference().child("Winning").push().setValue(maxBider);

}


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}.start();






    }
    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

}
