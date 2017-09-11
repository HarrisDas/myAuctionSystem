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
public class WinnigListFragment extends Fragment {

    RecyclerView mRecyclerView;
    WinningListAdapter mAdapter;
    ArrayList<Item> auctionList;
    FirebaseDatabase DataBase;
    String auctionKey = "";

    public WinnigListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_winnig_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.winning_recycler);
        auctionList = new ArrayList<>();
        DataBase = FirebaseDatabase.getInstance();

        mAdapter = new WinningListAdapter(getActivity(), auctionList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
        DataBase.getReference()
                .child("Winning").orderByChild("uuid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Iterable<DataSnapshot> WinningList = dataSnapshot.getChildren();
                        BidModelClass currentWinning = new BidModelClass();

                        if (dataSnapshot.exists()) {

                            for (DataSnapshot c : WinningList) {
                                currentWinning = c.getValue(BidModelClass.class);
                                if (currentWinning.getUUID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                    auctionKey = currentWinning.getAuctionKey();
                                    System.out.println(auctionKey + "hello1");
                                    FirebaseDatabase.getInstance()
                                            .getReference()
                                            .child("Auction").child(auctionKey)
                                            .addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {

                                                    if (dataSnapshot.exists()) {
                                                        Item currentAuction = new Item();

                                                        System.out.println(auctionKey + "hello2");

                                                        currentAuction = dataSnapshot.getValue(Item.class);
                                                        System.out.println(currentAuction.getItemKey() + "hello3");

                                                        //if (auctionKey.equals(currentAuction.getItemKey())) {
                                                            auctionList.add(currentAuction);
                                                            mAdapter.notifyDataSetChanged();
                                                         //   auctionKey = "";
                                                      //  }


                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });


                                }
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
