package com.intelligence.raiffeisentest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserPictureModel extends BaseModel {


    @SerializedName("large")
    @Expose
    private String mLargePicUrl;

    @SerializedName("medium")
    @Expose
    private String mMediumPicUrl;

    @SerializedName("thumbnail")
    @Expose
    private String mThumbUrl;

    public String getmLargePicUrl() {
        return mLargePicUrl;
    }

    public String getmMediumPicUrl() {
        return mMediumPicUrl;
    }

    public String getmThumbUrl() {
        return mThumbUrl;
    }

    public void setmLargePicUrl(String mLargePicUrl) {
        this.mLargePicUrl = mLargePicUrl;
    }

    public void setmMediumPicUrl(String mMediumPicUrl) {
        this.mMediumPicUrl = mMediumPicUrl;
    }

    public void setmThumbUrl(String mThumbUrl) {
        this.mThumbUrl = mThumbUrl;
    }
}
