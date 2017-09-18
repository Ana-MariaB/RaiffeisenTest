package com.intelligence.raiffeisentest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserNameModel extends BaseModel {

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("first")
    @Expose
    private String mFirstName;

    @SerializedName("last")
    @Expose
    private String mLastName;

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
