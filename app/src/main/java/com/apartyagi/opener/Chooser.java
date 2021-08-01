package com.apartyagi.opener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apartyagi.tictactao.BuildConfig;
import com.apartyagi.tictactao.MainActivity;
import com.apartyagi.tictactao.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Chooser extends AppCompatActivity {
Button solo,duo,review,share;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private InterstitialAd mInterstitialAa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        solo=findViewById(R.id.soloplayer);
        duo=findViewById(R.id.duoplayer);
        share=findViewById(R.id.share);
        review=findViewById(R.id.rate);


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-9590792597033705/3049351585");


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent=new Intent(Chooser.this, MainActivity.class);
                    startActivity(intent);
            }
        });


        duo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Chooser.this, com.apartyagi.duo.MainActivity.class);
                    startActivity(intent);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id="+ BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" +getPackageName())));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" +getPackageName())));
                }

/*
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri.Builder uriBuilder = Uri.parse("https://play.google.com/store/apps/details").buildUpon().appendQueryParameter("id", "com.example.android").appendQueryParameter("launch", "true");

                uriBuilder.appendQueryParameter("referrer", "exampleCampaignId");

                intent.setData(uriBuilder.build());
                intent.setPackage("com.android.vending");
                startActivity(intent);*/

            }
        });


    }
}