package com.example.harri.auctionsystem.UserAccountActivity;

/**
 * Created by harri on 8/24/2017.
 */

public class BidModelClass {
    private String Bid;
    private String UUID;
    private String AuctionKey;
    private String BidderKey;
    public BidModelClass(String bid, String uuid, String auctionKey, String bidderKey){
        Bid = bid;
        UUID = uuid;
        AuctionKey = auctionKey;
        this.BidderKey = bidderKey;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }
    public BidModelClass(){}

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getAuctionKey() {
        return AuctionKey;
    }

    public void setAuctionKey(String auctionKey) {
        AuctionKey = auctionKey;
    }

    public String getBidderKey() {
        return BidderKey;
    }

    public void setBidderKey(String bidderKey) {
        BidderKey = bidderKey;
    }
}
