package com.example.harri.auctionsystem.UserAccountActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.harri.auctionsystem.R;

import java.util.Date;
import java.util.List;

/**
 * Created by harri on 9/3/2017.
 */

public class WinningListAdapter  extends RecyclerView.Adapter<WinningListAdapter.MyViewHolder> {


    private Context mContext;
    private List<Item> itemList;


    @Override
    public WinningListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_auction_card, parent, false);

        return new WinningListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WinningListAdapter.MyViewHolder holder, int position) {

        Item myItem =itemList.get(position);

        Date Start_time= new Date(Long.parseLong(myItem.getInitial_Date()));
        Date  Final_time= new Date(Long.parseLong(myItem.getEnd_Date()));
        holder.name.setText("Item Name  : "+myItem.getItemName());
        holder.bid.setText(" Minimum Bid  : "+myItem.getItemBid()+" Rupees");
        holder.category.setText("Category Type : "+myItem.getItemCategory());
        holder.startDate.setText("Start Time :  "+Start_time.toString());
        holder.EndDate.setText("End Time : "+Final_time.toString());
        holder.key=myItem.getItemKey();

        Glide.with(mContext).load(myItem.getItemURI()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView image;
        TextView name,category,bid,startDate,EndDate;
        private String key;
        Context ctx;
        public MyViewHolder(View itemView) {
            super(itemView);
//itemView.setOnClickListener(this);
            image=(ImageView)itemView.findViewById(R.id.card_thumbnail);
            name=(TextView)itemView.findViewById(R.id.A_name);
            category=(TextView)itemView.findViewById(R.id.A_category);
            bid=(TextView)itemView.findViewById(R.id.A_bid);
            startDate=(TextView)itemView.findViewById(R.id.A_startTime);
            EndDate=(TextView)itemView.findViewById(R.id.A_endTime);


        }


    }
    public WinningListAdapter(Context context,List<Item> AuctionList){


        this.mContext=context;
        this.itemList=AuctionList;
    }

}


