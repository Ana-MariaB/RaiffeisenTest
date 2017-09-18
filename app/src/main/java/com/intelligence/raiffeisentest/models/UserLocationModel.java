package com.intelligence.raiffeisentest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserLocationModel extends BaseModel {

    @SerializedName("street")
    @Expose
    private String mStreet;

    @SerializedName("city")
    @Expose
    private String mCity;

    @SerializedName("state")
    @Expose
    private String mState;

    @SerializedName("postcode")
    @Expose
    private String mPostCode;

    public String getmCity() {
        return mCity;
    }

    public String getmPostCode() {
        return mPostCode;
    }

    public String getmState() {
        return mState;
    }

    public String getmStreet() {
        return mStreet;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public void setmPostCode(String mPostCode) {
        this.mPostCode = mPostCode;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public void setmStreet(String mStreet) {
        this.mStreet = mStreet;
    }
}
