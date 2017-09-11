package com.example.harri.auctionsystem.UserAccountActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.harri.auctionsystem.R;

import java.util.Date;
import java.util.List;

/**
 * Created by harri on 8/22/2017.
 */

public class AuctionCardAdapter extends RecyclerView.Adapter<AuctionCardAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> itemList;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_auction_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

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


    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
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

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Item myItem= itemList.get(getPosition());
            Toast.makeText(mContext, "You clicked "+ myItem.getItemKey(),Toast.LENGTH_LONG).show();

            Intent i= new Intent(mContext, BiddingAcitivity.class);
            //Bundle b= new Bundle();
          //  b.putParcelable("myObj", (Parcelable) myItem);
        i.putExtra("myKey",myItem.getItemKey());
            i.putExtra("myname",myItem.getItemName());

            i.putExtra("myInitailDate",myItem.getInitial_Date());
            i.putExtra("myEndDate",myItem.getEnd_Date());

            i.putExtra("myCat",myItem.getItemCategory());
            i.putExtra("mybid",myItem.getItemBid());

            i.putExtra("myUri",myItem.getItemURI());
            i.putExtra("myUid",myItem.getUID());
            i.putExtra("winning",myItem.getWinning());
            mContext.startActivity(i);
        }
    }
public AuctionCardAdapter(Context context,List<Item> AuctionList){


    this.mContext=context;
    this.itemList=AuctionList;
}

}
