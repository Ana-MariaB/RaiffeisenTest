package com.intelligence.raiffeisentest.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.intelligence.raiffeisentest.R;
import com.intelligence.raiffeisentest.models.UserModel;
import com.mukesh.countrypicker.Country;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserDetailsActivity extends AppCompatActivity {

    private UserModel mUserModel;
    private FloatingActionButton floatingActionButton;
    private RelativeLayout mLocationLayout;
    private RelativeLayout mCallPhone;
    private RelativeLayout mCallCell;
    private CircleImageView mUserProfilePicture;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    private Drawable mUploadedPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        mUserModel = (UserModel) getIntent().getSerializableExtra("position");

        View layoutDetails = findViewById(R.id.layoutUserDetails);
        mLocationLayout = (RelativeLayout) layoutDetails.findViewById(R.id.locationLayout);
        RelativeLayout emailLayout = (RelativeLayout) layoutDetails.findViewById(R.id.emailLayout);
        mCallPhone = (RelativeLayout) layoutDetails.findViewById(R.id.layoutCallPhone);
        mCallCell = (RelativeLayout) layoutDetails.findViewById(R.id.layoutCallCell);
        mUserProfilePicture = (CircleImageView) findViewById(R.id.userProfilePicture);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingButton);

        setActionBar();

        TextView userName = (TextView) findViewById(R.id.userName);
        TextView userPhoneNumber = (TextView) layoutDetails.findViewById(R.id.userPhoneNumber);
        TextView userCellNumber = (TextView) layoutDetails.findViewById(R.id.userCellNumber);
        TextView userEmail = (TextView) layoutDetails.findViewById(R.id.userEmail);
        TextView userStreet = (TextView) layoutDetails.findViewById(R.id.userStreet);
        TextView userCityState = (TextView) layoutDetails.findViewById(R.id.userCityState);
        TextView userPostalCode = (TextView) layoutDetails.findViewById(R.id.userPostalCode);
        TextView userId = (TextView) layoutDetails.findViewById(R.id.userId);

        String idName = mUserModel.getmUserIdModel().getmName();
        String idValue = mUserModel.getmUserIdModel().getmValue();
        if (idName != null && idValue != null) {
            userId.setText("ID:" + " " + mUserModel.getmUserIdModel().getmName() + " " + mUserModel.getmUserIdModel().getmValue());
        } else {
            userId.setVisibility(View.GONE);
        }

        userPostalCode.setText(mUserModel.getmLocationModel().getmPostCode());
        userCityState.setText(capitalize(mUserModel.getmLocationModel().getmCity()) + " " + capitalize(mUserModel.getmLocationModel().getmState()));
        userStreet.setText(mUserModel.getmLocationModel().getmStreet());
        userEmail.setText(mUserModel.getmEmail());
        userCellNumber.setText(mUserModel.getmCell());
        userPhoneNumber.setText(mUserModel.getmPhone());
        userName.setText(capitalize(mUserModel.getmNameModel().getmTitle()) + " " + capitalize(mUserModel.getmNameModel().getmFirstName() + " " + capitalize(mUserModel.getmNameModel().getmLastName())));

        String countryCode = mUserModel.getmNat();
        Country country = Country.getCountryByISO(countryCode);
        int flag = country.getFlag();
        floatingActionButton.setImageResource(flag);

        mLocationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAddress = mUserModel.getmLocationModel().getmStreet();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=" + userAddress));
                intent.setPackage("com.google.android.apps.maps");

                startActivity(intent);

            }
        });

        mCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(mUserModel.getmPhone());
            }
        });

        mCallCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(mUserModel.getmCell());
            }
        });


        setProfilePicture();

        final String picUriLarge = mUserModel.getmPictureModel().getmLargePicUrl();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    mUploadedPic = drawableFromUrl(picUriLarge);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        mUserProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(mUserProfilePicture, mUploadedPic);
            }
        });
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime
        );

        emailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });


    }

    public Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(this.getResources(), x);
    }


    public void setProfilePicture() {
        Glide.with(UserDetailsActivity.this)
                .load(mUserModel.getmPictureModel().getmMediumPicUrl())
                .fitCenter()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                }).into(mUserProfilePicture);
    }


    public void call(String number) {


        Uri callUri = Uri.parse("tel:" + number.replace("-", ""));
        Intent callIntent = new Intent(Intent.ACTION_DIAL, callUri);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        switch (item.getItemId()) {


            case R.id.action_star:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void zoomImageFromThumb(final View thumbView, Drawable imageResId) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        expandedImageView.setImageDrawable(imageResId);
        expandedImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }


        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);


        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;


        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }


                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

    public void sendEmail() {
        Intent sendEmailIntent = new Intent(UserDetailsActivity.this, SendEmailActivity.class);
        startActivity(sendEmailIntent);
    }


}
