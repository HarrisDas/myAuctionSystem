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

/**
 * A simple {@link Fragment} subclass.
 */
public class AuctionListFragment extends Fragment {
    private RecyclerView recyclerView;
    AuctionCardAdapter myAdapter;
ArrayList<Item> auctionList;
    FirebaseDatabase DataBase;
   FirebaseAuth mAuth;
    public AuctionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

 View rootView=       inflater.inflate(R.layout.fragment_auction_list, container, false);
        //return inflater.inflate(R.layout.fragment_auction_list, container, false);




            recyclerView=(RecyclerView)rootView.findViewById(R.id.my_recycler_view);
            auctionList= new ArrayList<>();
            DataBase= FirebaseDatabase.getInstance();

            myAdapter= new AuctionCardAdapter(getActivity(),auctionList);

            RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);

            recyclerView.setAdapter(myAdapter);



        DataBase.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                auctionList.clear();
                Iterable<DataSnapshot> auctions= dataSnapshot.child("Auction").getChildren();
for (DataSnapshot c: auctions){
    Item currentItem=c.getValue(Item.class);

    if(currentItem.getUID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())){



        System.out.println("hey my boy \n " +currentItem);
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
