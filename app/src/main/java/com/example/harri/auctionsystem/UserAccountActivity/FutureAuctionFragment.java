package com.example.harri.auctionsystem.UserAccountActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harri.auctionsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FutureAuctionFragment extends Fragment {

    private RecyclerView recyclerView;
    AuctionCardAdapter myAdapter;
    ArrayList<Item> auctionList;
    FirebaseDatabase DataBase;

    Date Start_time;
    Date Final_time;
    Date Current_time;
    FirebaseAuth mAuth;
    public FutureAuctionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView= inflater.inflate(R.layout.fragment_future_auction, container, false);



        recyclerView=(RecyclerView)rootView.findViewById(R.id.future_auctions_recycler);
        auctionList= new ArrayList<>();
        DataBase= FirebaseDatabase.getInstance();

        myAdapter= new AuctionCardAdapter(getActivity(),auctionList);

        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(myAdapter);


        DataBase.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> auctions= dataSnapshot.child("Auction").getChildren();
                for(DataSnapshot c :auctions){

                    Item currentItem=c.getValue(Item.class);
                    Current_time = new Date();
                    System.out.println("yahan pr agya hai ");
                    Start_time= new Date(Long.parseLong(currentItem.getInitial_Date()));
                    Final_time= new Date(Long.parseLong(currentItem.getEnd_Date()));
                    System.out.println(Start_time);
                    System.out.println(Final_time);
                    System.out.println(Current_time);
                    if(Long.parseLong(currentItem.getInitial_Date())>Current_time.getTime()){

                        auctionList.add(currentItem);
                        myAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return rootView;

    }

}
