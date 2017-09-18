package com.intelligence.raiffeisentest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.intelligence.raiffeisentest.R;
import com.intelligence.raiffeisentest.activities.UserDetailsActivity;
import com.intelligence.raiffeisentest.models.UserModel;
import com.intelligence.raiffeisentest.utils.MainActivityAdapterCallback;
import com.intelligence.raiffeisentest.viewHolders.UserListViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<UserModel> mUsersList;
    private Context mContext;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private MainActivityAdapterCallback mCallback;

    private String errorMsg;
    private Date mDateObj;

    public MainActivityAdapter(Context context) {
        this.mContext = context;
        this.mCallback = (MainActivityAdapterCallback) context;
        mUsersList = new ArrayList<>();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.user_item, parent, false);
                viewHolder = new UserListViewHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        UserModel result = mUsersList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel userModelDetailed = mUsersList.get(position);
                Intent openDetailsIntent = new Intent(mContext, UserDetailsActivity.class);
                openDetailsIntent.putExtra("position", userModelDetailed);
                mContext.startActivity(openDetailsIntent);
            }
        });

        switch (getItemViewType(position)) {

            case ITEM:
                final UserListViewHolder userListViewHolder = (UserListViewHolder) holder;

                String usersFullName = capitalize(result.getmNameModel().getmTitle()) + " " + capitalize(result.getmNameModel().getmFirstName()) + " " + capitalize(result.getmNameModel().getmLastName()) + " ";
                userListViewHolder.mUserName.setText(usersFullName);



                String strDateTimeBoj=result.getmRegistered();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    mDateObj = dateFormat.parse(strDateTimeBoj);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String time = DateUtils.formatDateTime(mContext, mDateObj.getTime(), DateUtils.FORMAT_SHOW_TIME);
                userListViewHolder.mUserRegisteredTime.setText(time);

                Glide.with(mContext)
                        .load(result.getmPictureModel().getmThumbUrl())
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                userListViewHolder.mProgress.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                userListViewHolder.mProgress.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(userListViewHolder.mUserPicture);
                break;

            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;

                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);

                    loadingVH.mErrorTxt.setText(
                            errorMsg != null ?
                                    errorMsg :
                                    mContext.getString(R.string.error_msg_unknown));

                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mUsersList == null ? 0 : mUsersList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM;
        } else {
            return (position == mUsersList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
        }
    }


    public void add(UserModel r) {
        mUsersList.add(r);
        notifyItemInserted(mUsersList.size() - 1);
    }

    public void addAll(List<UserModel> moveResults) {
        for (UserModel result : moveResults) {
            add(result);
        }
    }

    public void remove(UserModel r) {
        int position = mUsersList.indexOf(r);
        if (position > -1) {
            mUsersList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new UserModel());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mUsersList.size() - 1;
        UserModel result = getItem(position);

        if (result != null) {
            mUsersList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public UserModel getItem(int position) {
        return mUsersList.get(position);
    }


    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(mUsersList.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }


    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        public LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = (ImageButton) itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = (TextView) itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = (LinearLayout) itemView.findViewById(R.id.loadmore_errorlayout);

            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:

                    showRetry(false, null);
                    mCallback.retryPageLoad();

                    break;
            }
        }
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }



}
