package com.example.harri.auctionsystem.UserAccountActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harri.auctionsystem.R;

import java.util.List;

/**
 * Created by harri on 8/24/2017.
 */

public class BiddingListAdapter extends RecyclerView.Adapter<BiddingListAdapter.MyViewHolder> {

    private Context mContext;
    private List<BidModelClass> itemList;

public BiddingListAdapter(Context context, List<BidModelClass> BidList){
    mContext=context;
    itemList= BidList;

}

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bid_row, parent, false);

        return new BiddingListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BidModelClass  bid = itemList.get(position);
holder.bid.setText("Bid: "+bid.getBid());
        holder.UUID.setText("By ID : "+ bid.getUUID());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
TextView bid;
        TextView UUID;

        public MyViewHolder(View itemView) {
            super(itemView);

            bid=(TextView )itemView.findViewById(R.id.Bid_of_bidder);
            UUID=(  TextView)itemView.findViewById(R.id.UUID_of_bidder);
        }
    }
}
