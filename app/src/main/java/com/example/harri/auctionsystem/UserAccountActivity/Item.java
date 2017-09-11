package com.example.harri.auctionsystem.UserAccountActivity;

/**
 * Created by harri on 8/18/2017.
 */

public class Item {
    private  String ItemName;
    private String ItemCategory;
    private  String ItemBid;
    private String ItemURI;
    private String ItemKey;
    private String Winning;
    public String getItemKey() {
        return ItemKey;
    }

    public void setItemKey(String itemKey) {
        ItemKey = itemKey;
    }



    public Item(String itemName, String itemCategory, String itemBid, String itemURI, String UID, String initial_Date, String end_Date, String key, String winning) {
        ItemName = itemName;
        ItemCategory = itemCategory;
        ItemBid = itemBid;
        ItemURI = itemURI;
        this.UID = UID;
        Initial_Date = initial_Date;
        End_Date = end_Date;
        ItemKey=key;
        Winning = winning;
    }

    private  String UID;
    private String Initial_Date;
    private String End_Date;



    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String end_Date) {
        End_Date = end_Date;
    }

    public String getInitial_Date() {
        return Initial_Date;
    }

    public void setInitial_Date(String initial_Date) {
        Initial_Date = initial_Date;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }



     public Item(){}

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemCategory() {
        return ItemCategory;
    }

    public void setItemCategory(String itemCategory) {
        ItemCategory = itemCategory;
    }

    public String getItemBid() {
        return ItemBid;
    }

    public void setItemBid(String itemBid) {
        ItemBid = itemBid;
    }

    public String getItemURI() {
        return ItemURI;
    }

    public void setItemURI(String itemURI) {
        ItemURI = itemURI;
    }

    public String getWinning() {
        return Winning;
    }

    public void setWinning(String winning) {
        Winning = winning;
    }
}
