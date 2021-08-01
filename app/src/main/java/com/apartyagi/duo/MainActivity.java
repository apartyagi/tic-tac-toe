package com.apartyagi.duo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apartyagi.tictactao.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    TextView status;
    private InterstitialAd mInterstitialAd;
    // x=0
    // 0=1
    boolean gameac=true;
    int activeplayer=0;
    int [] gamestate={2,2,2,2,2,2,2,2,2};
    int [][] winningposition={
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {2,4,6},{0,4,8}
    };
    public void dropIn(View view){
        ImageView img=(ImageView)view;
        int tapedimage=Integer.parseInt(img.getTag().toString());
        if(gameac){
            if(gamestate[tapedimage]==2){
                gamestate[tapedimage]=activeplayer;
                img.setTranslationY(-1000f);

                if (activeplayer==0){
                    img.setImageResource(R.drawable.cross);
                    activeplayer =1;
                    status.setText(" O "+"Turns:- Tap to play");
                } else{
                    img.setImageResource(R.drawable.zero);
                    activeplayer=0;
                    status.setText(" X "+"Turns:- Tap to play");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }

            //Check for Winning Positions

            for (int[] winning:winningposition) {

                if (gamestate[winning[0]] == gamestate[winning[1]] &&
                        gamestate[winning[1]] == gamestate[winning[2]] &&
                        gamestate[winning[0]] != 2) {
                    //who win
                    String winestr;
                    gameac = false;
                    if (gamestate[winning[0]] == 0) {
                        winestr = "player 1 " +
                                "" +
                                " win the match = 'X' ";
                    }
                    else {
                        winestr = "player 2  Win The Match= 'O' ";
                        //updat status

                    }

                    status.setText(winestr);
                    break;

                }
            }
            boolean checker =true;
            for (int i=0;i<gamestate.length;i++){
                if(gamestate[i]==2)
                {
                    checker=false;
                }
            }
            if (checker)
            {
               // status.setText("Match has Draw Please restart The game");
                Toast.makeText(this, "Fill", Toast.LENGTH_LONG).show();
            }
        }
    }

    public  void reset(){
        activeplayer=0;
        gameac=true;

        for (int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        status.setText(" X "+"Turns:- Tap to play");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        status=findViewById(R.id.status);

        Toolbar toolbar=findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9590792597033705/1459462965");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.reset){
            Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
            reset();
        }

        return super.onOptionsItemSelected(item);
    }
}
