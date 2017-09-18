package com.intelligence.raiffeisentest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserIdModel extends BaseModel {

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("value")
    @Expose
    private String mValue;

    public String getmValue() {
        return mValue;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }
}
