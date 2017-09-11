package com.example.harri.auctionsystem.UserAccountActivity;

/**
 * Created by harri on 8/25/2017.
 */

public class Winnnig {
public Winnnig(){}

    public Winnnig(String bidderUUID, String bidderKey, String auctionKey) {
        BidderUUID = bidderUUID;
        BidderKey = bidderKey;
        AuctionKey = auctionKey;
    }


    public String getBidderUUID() {
        return BidderUUID;
    }

    public void setBidderUUID(String bidderUUID) {
        BidderUUID = bidderUUID;
    }

    public String getBidderKey() {
        return BidderKey;
    }

    public void setBidderKey(String bidderKey) {
        BidderKey = bidderKey;
    }

    public String getAuctionKey() {
        return AuctionKey;
    }

    public void setAuctionKey(String auctionKey) {
        AuctionKey = auctionKey;
    }

    private String BidderUUID;
    private String BidderKey;
    private String AuctionKey;


}

