package com.dabut.purcow;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
public class Game extends AppCompatActivity {
    androidx.constraintlayout.widget.ConstraintLayout layout;
    boolean isLightTheme = true;
    SharedPreferences preferences;



    AlertDialog.Builder alertDialogBuilder;
    @SuppressLint({"MissingInflatedId", "WrongConstant", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("theme", MODE_PRIVATE);
        if (preferences.contains("isLightTheme")) {
            isLightTheme = preferences.getBoolean("isLightTheme", true);
        }
        if (isLightTheme) {
            setTheme(R.style.Theme_V2rayExample);
        } else {
            setTheme(R.style.Theme_V2rayExample2);
        }






        setContentView(R.layout.activity_game);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("PurCow");


        if (isLightTheme) {





            layout = (androidx.constraintlayout.widget.ConstraintLayout)findViewById(R.id.gameley);
            layout.setBackgroundResource(R.drawable.tttt);



        } else {



            layout = (androidx.constraintlayout.widget.ConstraintLayout )findViewById(R.id.gameley);
            layout.setBackgroundResource(R.drawable.pink);

        }




        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        WebView webView;
        webView = (WebView) findViewById(R.id.webView2);

        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        // webView.clearHistory();
        webView.setWebChromeClient(new WebChromeClient());
        //  webView.clearCache(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // webView.getSettings().setSaveFormData(boolean true );
        webView.clearCache(true);
        WebStorage.getInstance().deleteAllData();
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
        webView.clearCache(true);
        webView.clearFormData();
        webView.clearHistory();
        webView.clearSslPreferences();
        webView.loadUrl("file:///android_asset/tower/index.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/purnet.html");
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ProgressBar p = (ProgressBar) findViewById(R.id.progressBar2);
               // TextView t = (TextView) findViewById(R.id.textView);
                p.setVisibility(View.GONE);
              //  t.setText("");
            }
        });

      SwipeRefreshLayout  mySwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swiperefresh2);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                webView.loadUrl("file:///android_asset/tower/index.html");
                if (null != mySwipeRefreshLayout) {
                    mySwipeRefreshLayout.setRefreshing(false);
                }

            }

        });
    }

    public void onBackPressed() {

        if (isLightTheme) {
            alertDialogBuilder = new AlertDialog.Builder(
                    this, R.style.MyDialog);


        } else {
            alertDialogBuilder = new AlertDialog.Builder(
                    this, R.style.MyDialog3);

        }



        // set title

        // set dialog message
        alertDialogBuilder
                .setTitle("PurCow is Alive")
                .setIcon(R.drawable.yel)
                .setMessage(getString(R.string.gamexxit))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.yesss), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                     WebView   webView = (WebView) findViewById(R.id.webView2);

                        webView.loadUrl("about:blank");
                            Intent ii = new Intent(Game.this, MainActivity.class);


                            startActivity(ii);
                            finish();


                        }



                }).setNegativeButton(getString(R.string.kheyr), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity

                        dialog.cancel();


                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }
}
