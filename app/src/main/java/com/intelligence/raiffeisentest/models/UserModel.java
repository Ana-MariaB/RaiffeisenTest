package com.intelligence.raiffeisentest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserModel extends BaseModel {

    @SerializedName("gender")
    @Expose
    private String mGender;

    @SerializedName("name")
    @Expose
    private UserNameModel mNameModel;

    @SerializedName("location")
    @Expose
    private UserLocationModel mLocationModel;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("login")
    @Expose
    private UserLoginModel mLoginModel;

    @SerializedName("dob")
    @Expose
    private String mDob;

    @SerializedName("registered")
    @Expose
    private String mRegistered;

    @SerializedName("phone")
    @Expose
    private String mPhone;


    @SerializedName("cell")
    @Expose
    private String mCell;

    @SerializedName("id")
    @Expose
    private UserIdModel mUserIdModel;

    @SerializedName("picture")
    @Expose
    private UserPictureModel mPictureModel;

    @SerializedName("nat")
    @Expose
    private String mNat;

    public String getmRegistered() {
        return mRegistered;
    }

    public String getmCell() {
        return mCell;
    }

    public String getmDob() {
        return mDob;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmGender() {
        return mGender;
    }

    public String getmNat() {
        return mNat;
    }

    public UserIdModel getmUserIdModel() {
        return mUserIdModel;
    }

    public UserLocationModel getmLocationModel() {
        return mLocationModel;
    }

    public UserLoginModel getmLoginModel() {
        return mLoginModel;
    }

    public UserNameModel getmNameModel() {
        return mNameModel;
    }

    public UserPictureModel getmPictureModel() {
        return mPictureModel;
    }

    public void setmCell(String mCell) {
        this.mCell = mCell;
    }

    public void setmDob(String mDob) {
        this.mDob = mDob;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public void setmLocationModel(UserLocationModel mLocationModel) {
        this.mLocationModel = mLocationModel;
    }

    public void setmLoginModel(UserLoginModel mLoginModel) {
        this.mLoginModel = mLoginModel;
    }

    public void setmNameModel(UserNameModel mNameModel) {
        this.mNameModel = mNameModel;
    }

    public void setmNat(String mNat) {
        this.mNat = mNat;
    }

    public void setmPictureModel(UserPictureModel mPictureModel) {
        this.mPictureModel = mPictureModel;
    }

    public void setmRegistered(String mRegistered) {
        this.mRegistered = mRegistered;
    }

    public void setmUserIdModel(UserIdModel mUserIdModel) {
        this.mUserIdModel = mUserIdModel;
    }


    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
