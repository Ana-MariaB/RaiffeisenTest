package com.intelligence.raiffeisentest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserLoginModel extends BaseModel {

    @SerializedName("username")
    @Expose
    private String mUserName;

    @SerializedName("password")
    @Expose
    private String mPassword;

    @SerializedName("salt")
    @Expose
    private String mSalt;

    @SerializedName("md5")
    @Expose
    private String mMd5;

    @SerializedName("sha1")
    @Expose
    private String mSha1;

    @SerializedName("sha256")
    @Expose
    private String mSha256;

    public String getmMd5() {
        return mMd5;
    }

    public String getmPassword() {
        return mPassword;
    }

    public String getmSalt() {
        return mSalt;
    }

    public String getmSha1() {
        return mSha1;
    }

    public String getmSha256() {
        return mSha256;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmMd5(String mMd5) {
        this.mMd5 = mMd5;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public void setmSha1(String mSha1) {
        this.mSha1 = mSha1;
    }

    public void setmSalt(String mSalt) {
        this.mSalt = mSalt;
    }

    public void setmSha256(String mSha256) {
        this.mSha256 = mSha256;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }


}
