package com.protech.inc.unity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.IUnityAdsListener;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.LinearLayout;
import com.unity3d.services.banners.IUnityBannerListener;
import android.view.ViewGroup;
import android.widget.Toast;
import com.unity3d.services.banners.UnityBanners;

public class MainActivity extends Activity { 

    private Button mInter;
    private Button mReward;
    private Button mBannerButton;
    private LinearLayout mBanner;

    private String unityGameID = "4087375";
    private Boolean testMode = false;
    private String InterID = "Interstitial_Android";
    private String RewardID = "Rewarded_Android";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the SDK:
        UnityAds.initialize(this, unityGameID, testMode);

        

        // load banner view
        loadBannerAds();
        // load reward ads
        loadRewardAds();
        // load interstitial ads
        LoadInterstitialAds();

        

        //find view by their id
        mInter = findViewById(R.id.activitymainButton1);
        mReward = findViewById(R.id.activitymainButton2);
        mBannerButton = findViewById(R.id.activitymainButton3);
        mBanner = findViewById(R.id.activitymainBanner);


        // on interstitial button click
        mInter.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {

                    DisplayInterstitialAd();

                }
            });

        // on reward button click
        mReward.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {

                    DisplayRewardedVideoAd();

                }
            });

            // on banner button click show banner ads
        mBannerButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                   UnityBanners.loadBanner(MainActivity.this,"Banner_Android");
                    
                }
            });
            




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        UnityBanners.destroy();
    }
    

    // to show reward Ads
    public void DisplayRewardedVideoAd() {
        if (UnityAds.isReady(RewardID)) {
            UnityAds.show(this, RewardID);
        }else{

            Toast.makeText(getApplicationContext(),"ads not loaded",Toast.LENGTH_SHORT).show();
        }
    }

    // to show intestitial ads
    public void DisplayInterstitialAd() {
        if (UnityAds.isReady(InterID)) {
            UnityAds.show(this, InterID);
        }else{

            Toast.makeText(getApplicationContext(),"ads not loaded",Toast.LENGTH_SHORT).show();
        }
    }

    // Implement the IUnityAdsListener interface methods:
    private void loadRewardAds() {
        IUnityAdsListener RewardListener = new IUnityAdsListener() {

            @Override
            public void onUnityAdsReady(String adUnitId) {
                // Implement functionality for an ad being ready to show.
                Toast.makeText(getApplicationContext(),"Video ads loaded",Toast.LENGTH_SHORT).show();
                
            }

            @Override
            public void onUnityAdsStart(String adUnitId) {
                // Implement functionality for a user starting to watch an ad.
            }

            @Override
            public void onUnityAdsFinish(String adUnitId, UnityAds.FinishState finishState) {
                // Implement functionality for a user finishing an ad.
                loadRewardAds();
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
                // Implement functionality for a Unity Ads service error occurring.
                
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                loadRewardAds();
                
            }

        };
        UnityAds.setListener(RewardListener);
        UnityAds.load(RewardID);

    }


    private void LoadInterstitialAds() {
        IUnityAdsListener InterListener = new IUnityAdsListener() {

            public void onUnityAdsReady(String adUnitId) {
                // Implement functionality for an ad being ready to show.
                Toast.makeText(getApplicationContext(),"Interstitial ads loaded",Toast.LENGTH_SHORT).show();
                
            }

            @Override
            public void onUnityAdsStart(String adUnitId) {
                // Implement functionality for a user starting to watch an ad.
            }

            @Override
            public void onUnityAdsFinish(String adUnitId, UnityAds.FinishState finishState) {
                // Implement conditional logic for each ad completion status:
                LoadInterstitialAds();

            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
                // Implement functionality for a Unity Ads service error occurring.
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                LoadInterstitialAds();
                
            }
        };

        UnityAds.setListener(InterListener);
        UnityAds.load(InterID);
    }

    public void loadBannerAds() {
        IUnityBannerListener bannerListener = new IUnityBannerListener() {
            @Override 
            public void onUnityBannerLoaded(String s, View view) {
                ((ViewGroup) findViewById(R.id.activitymainBanner)).removeView(view);
                ((ViewGroup) findViewById(R.id.activitymainBanner)).addView(view);
                Toast.makeText(getApplicationContext(), "banner load", Toast.LENGTH_SHORT).show();
            }
            @Override 
            public void onUnityBannerUnloaded(String s) {
                Toast.makeText(getApplicationContext(), "unLoad", Toast.LENGTH_SHORT).show();
            } 
            @Override 
            public void onUnityBannerShow(String s) {
                Toast.makeText(getApplicationContext(), "banner show", Toast.LENGTH_LONG).show();
            } 
            @Override 
            public void onUnityBannerClick(String s) {
                Toast.makeText(getApplicationContext(), "banner click", Toast.LENGTH_SHORT).show();
            }
            @Override 
            public void onUnityBannerHide(String s) {
                Toast.makeText(getApplicationContext(), "banner hide", Toast.LENGTH_SHORT).show();
            } 
            @Override 
            public void onUnityBannerError(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        };
        UnityBanners.setBannerListener(bannerListener);
        UnityBanners.loadBanner(this, "Banner_Android");





    }

}
/*don't forget to subscribe my YouTube channel for more Tutorial and mod*/
/*
https://youtube.com/channel/UC_lCMHEhEOFYgJL6fg1ZzQA */
