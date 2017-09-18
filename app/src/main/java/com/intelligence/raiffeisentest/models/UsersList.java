package com.intelligence.raiffeisentest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class UsersList extends BaseModel {


    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("results")
    @Expose
    private ArrayList<UserModel> usersList = new ArrayList<>();

    public ArrayList<UserModel> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<UserModel> usersList) {
        this.usersList = usersList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
