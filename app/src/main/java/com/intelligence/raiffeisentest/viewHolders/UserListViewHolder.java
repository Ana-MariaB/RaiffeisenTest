package com.intelligence.raiffeisentest.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.intelligence.raiffeisentest.R;

public class UserListViewHolder extends RecyclerView.ViewHolder {
    public TextView mUserName;
    public ImageView mUserPicture;
    public ProgressBar mProgress;
    public TextView mUserRegisteredTime;

    public UserListViewHolder(View itemView) {
        super(itemView);

        mUserName = (TextView) itemView.findViewById(R.id.userName);
        mUserPicture = (ImageView) itemView.findViewById(R.id.mUserPicture);
        mProgress = (ProgressBar) itemView.findViewById(R.id.progressBar);
        mUserRegisteredTime = (TextView) itemView.findViewById(R.id.userRegisteredTime);
    }

}
