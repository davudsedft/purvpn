package com.dabut.purcow;


import static com.dabut.purcow.Servers.Name;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dabut.lib.v2ray.V2rayController;
import com.google.android.material.navigation.NavigationView;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class vip extends AppCompatActivity {
    public static Activity webv;
    LinearLayout layout;



    String gg = "aHR0cHM6Ly9wdXJuZXQuaXIvcmFtenB1cmNvdzc3Nzg4NjY1NTQ0NDQ0NTc3ODg=";
    public static final String Name2 = "Key";
    public static final String Name3 = "Key";

    public static final String Save1 = "Key";


    public static final String Save2 = "Key";


    public static final String Save3 = "Key";

    public static final String Save4 = "Key";
    public static final String Save5 = "Key";


    public static final String Save6 = "Key";


    public static final String Save7 = "Key";

    public static final String Save8 = "Key";
    boolean isLightTheme = true;
    static  final    String token = "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";

    boolean g = true;
    private DrawerLayout drawerLayout ;

    SharedPreferences Pref,Config,Ramz,save1,save2,save3,save4,save5,save6,save7,save8,  preferences;

    SwipeRefreshLayout mySwipeRefreshLayout;
    @SuppressLint({"SetJavaScriptEnabled", "WrongConstant", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gg = decodeBase64(gg);
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



        setContentView(R.layout.activity_vip);
        NavigationView navigationView = (NavigationView) findViewById(R.id.na3);

        if (isLightTheme) {
            layout = (LinearLayout)findViewById(R.id.vipley);
            layout.setBackgroundResource(R.drawable.tttt);
            navigationView.setBackgroundResource(R.drawable.tttt);


        } else {
            layout = (LinearLayout)findViewById(R.id.vipley);
            layout.setBackgroundResource(R.drawable.pink);
            navigationView.setBackgroundResource(R.drawable.pink);

        }

        drawerLayout = (DrawerLayout) findViewById(R.id.dl3);


        //nav

        Ramz = getSharedPreferences("ramz", Context.MODE_PRIVATE);
        if (Ramz.contains(Name3)) {


            Ramz.getString(Name3, null);

        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected (MenuItem item){
                item.setChecked(false);
                drawerLayout.closeDrawer(Gravity.RIGHT);
                if (item.getItemId() == R.id.ramz2) {
                    final EditText txtUrl2 = new EditText(vip.this);

// Set the default text to a link of the Queen
                    //  txtUrl.setHint("http://www.librarising.com/astrology/celebs/images2/QR/queenelizabethii.jpg");
                    if (isLightTheme) {
                        new AlertDialog.Builder(vip.this, R.style.MyDialog)
                                .setTitle(getString(R.string.zakhireramz))
                                .setMessage(getString(R.string.hamishesave))
                                .setView(txtUrl2)
                                .setPositiveButton(getString(R.string.zakhire), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String uu = txtUrl2.getText().toString();
                                        //   moustachify(null, url);
                                        SharedPreferences.Editor shEdit = Ramz.edit();
                                        shEdit.putString(Name3, uu);
                                        shEdit.apply();
                                        Ramz = getSharedPreferences("ramz", Context.MODE_PRIVATE);
                                        String ramz = Ramz.getString(Name3, "");
                                        WebView webView = (WebView) findViewById(R.id.webView);

                                        webView.loadUrl("javascript:getFromAndroid('" + ramz + "')");


                                    }
                                })
                                .setNegativeButton(getString(R.string.enseraf), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    }
                                })
                                .show();


                    } else {

                        new AlertDialog.Builder(vip.this, R.style.MyDialog3)
                                .setTitle(getString(R.string.zakhireramz))
                                .setMessage(getString(R.string.hamishesave))
                                .setView(txtUrl2)
                                .setPositiveButton(getString(R.string.zakhire), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String uu = txtUrl2.getText().toString();
                                        //   moustachify(null, url);
                                        SharedPreferences.Editor shEdit = Ramz.edit();
                                        shEdit.putString(Name3, uu);
                                        shEdit.apply();
                                        Ramz = getSharedPreferences("ramz", Context.MODE_PRIVATE);
                                        String ramz = Ramz.getString(Name3, "");
                                        WebView webView = (WebView) findViewById(R.id.webView);

                                        webView.loadUrl("javascript:getFromAndroid('" + ramz + "')");


                                    }
                                })
                                .setNegativeButton(getString(R.string.enseraf), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    }
                                })
                                .show();


                    }


                    return true;
                }

                return false;
            }

        });






        //endnav




        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("PurCow");
        Pref = getSharedPreferences("PrefFile", Context.MODE_PRIVATE);
        Config = getSharedPreferences("config", Context.MODE_PRIVATE);
        save1 = getSharedPreferences("save1", Context.MODE_PRIVATE);
        save2 = getSharedPreferences("save2", Context.MODE_PRIVATE);
        save3 = getSharedPreferences("save3", Context.MODE_PRIVATE);
        save4 = getSharedPreferences("save4", Context.MODE_PRIVATE);



        save5 = getSharedPreferences("save5", Context.MODE_PRIVATE);
        save6 = getSharedPreferences("save6", Context.MODE_PRIVATE);
        save7 = getSharedPreferences("save7", Context.MODE_PRIVATE);
        save8 = getSharedPreferences("save8", Context.MODE_PRIVATE);

        webv = this;

        if (Config.contains(Name2)) {


            Config.getString(Name2, null);


        }
        if (Pref.contains(Name)) {


            Pref.getString(Name, null);

        }

        if (save1.contains(Save1)) {


            save1.getString(Save1, null);

        }
        if (save2.contains(Save2)) {


            save2.getString(Save2, null);

        }
        if (save3.contains(Save3)) {


            save3.getString(Save3, null);

        }
        if (save4.contains(Save4)) {


            save4.getString(Save4, null);

        }
        if (save5.contains(Save5)) {


            save5.getString(Save5, null);

        }
        if (save6.contains(Save6)) {


            save6.getString(Save6, null);

        }
        if (save7.contains(Save7)) {


            save7.getString(Save7, null);

        }
        if (save8.contains(Save8)) {


            save8.getString(Save8, null);

        }




        WebView webView = (WebView) findViewById(R.id.webView);


        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        // webView.clearHistory();
        webView.setWebChromeClient(new WebChromeClient());
        //   webView.clearCache(true);
        //   webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //  webView.getSettings().setSaveFormData(boolean true );
        //  webView.clearCache(true);
        //  WebStorage.getInstance().deleteAllData();
        CookieManager.getInstance().getCookie(gg);
        //   CookieManager.getInstance().flush();
        //  webView.clearCache(true);
        //  webView.clearFormData();
        // webView.clearHistory();
        // webView.clearSslPreferences();
        webView.loadUrl(gg);

        webView.setWebChromeClient(new WebChromeClient(){

            @SuppressLint("NewApi")
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();



                if (Objects.equals(message, "خرید")){
                    showCustomDialog();
                }else {


                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                    String clipboardtext = item.getText().toString();
                    System.out.println("ttttttttttttttttttttttttttttttttttt");
                    clipboardtext = clipboardtext.trim();

System.out.println(clipboardtext);
                     clipboardtext = clipboardtext.trim();

                    SharedPreferences.Editor shEdit = Pref.edit();
                    shEdit.putString(Name, clipboardtext);
                    shEdit.apply();
//                try {
//                    StartV2ray(getApplicationContext(), "Default", clipboardtext, null);
//
//                } catch (Exception ignored) {
//
//
//
//
//
//
//                }



                    Toast.makeText(vip.this, message, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor shEdit2 = Config.edit();
                    shEdit2.remove(Name2);
                    shEdit2.putString(Name2, message);
                    shEdit2.apply();
                    if (Objects.equals(message, "ایرانسل گیمینگ vip")){
                        SharedPreferences.Editor dd = save1.edit();
                        dd.remove(Save1);
                        dd.putString(Save1, clipboardtext);
                        dd.apply();
                    }

                    if (Objects.equals(message,"ایرانسل2vip")){

                        SharedPreferences.Editor dd = save2.edit();
                        dd.remove(Save2);
                        dd.putString(Save2, clipboardtext);
                        dd.apply();


                    }

                    if (Objects.equals(message,"ایرانسل3vip")){

                        SharedPreferences.Editor dd = save3.edit();
                        dd.remove(Save3);
                        dd.putString(Save3, clipboardtext);
                        dd.apply();


                    }
                    if (Objects.equals(message,"سایر اپراتورها vip")){

                        SharedPreferences.Editor dd = save4.edit();
                        dd.remove(Save4);
                        dd.putString(Save4, clipboardtext);
                        dd.apply();


                    }


                    if (Objects.equals(message,"vip wifi1")){

                        SharedPreferences.Editor dd = save5.edit();
                        dd.remove(Save5);
                        dd.putString(Save5, clipboardtext);
                        dd.apply();


                    }
                    if (Objects.equals(message,"vip wifi2")){

                        SharedPreferences.Editor dd = save6.edit();
                        dd.remove(Save6);
                        dd.putString(Save6, clipboardtext);
                        dd.apply();


                    }



                    if (Objects.equals(message,"همراه۱ vip")){

                        SharedPreferences.Editor dd = save7.edit();
                        dd.remove(Save7);
                        dd.putString(Save7, clipboardtext);
                        dd.apply();


                    }
                    if (Objects.equals(message,"همراه گیمینگ vip")){

                        SharedPreferences.Editor dd = save8.edit();
                        dd.remove(Save8);
                        dd.putString(Save8, clipboardtext);
                        dd.apply();


                    }



                    for (int i=0;i<50;i++ ) {



                        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        mClipboardManager.setText(String.valueOf(i));              }



                    Intent ii = new Intent(vip.this, MainActivity.class);
                    V2rayController.StopV2ray(getApplicationContext());
                    ii.putExtra("kool","rovshan");
                    ii.putExtra("copyte",clipboardtext);
                    startActivity(ii);
                    finish();


                }


                return true;
            }
//            @Override
//            public boolean onJsConfirm(WebView view, String url, String message, JsResult result){
//                new AlertDialog.Builder(view.getContext())
//                        .setMessage(message)
//                        .setOnDismissListener((DialogInterface dialog) -> result.cancel())
//                        .create();
//                        //.show();
//
//                return true;
//            }
//
//            @Override
//            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result){
//                final EditText input = new EditText(view.getContext());
//                input.setInputType(InputType.TYPE_CLASS_TEXT);
//                input.setText(defaultValue);
//                new AlertDialog.Builder(view.getContext())
//                        .setMessage(message)
//                        .setView(input)
//                        .setOnDismissListener((DialogInterface dialog) -> result.cancel())
//                        .create();
//
//                //.show();
//                return true;
//            }
        });




        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/purnet.html");
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String js = "javascript:(function() { " +
                        "document.getElementsByClassName('next_button')[0].click();" +
                        "})()";




                @SuppressLint("HardwareIds")

                String device = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                Ramz = getSharedPreferences("ramz", Context.MODE_PRIVATE);
                String ramz = Ramz.getString(Name3, "");
                webView.loadUrl("javascript:getFromAndroid('"+ramz+"')");
                webView.loadUrl("javascript:getFromAndroid2('"+device+"')");

                ProgressBar p = (ProgressBar) findViewById(R.id.progressBar);
                TextView t = (TextView) findViewById(R.id.textView);
                p.setVisibility(View.GONE);
                t.setText("");
                webView.loadUrl(js);
            }
        });
        mySwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                webView.loadUrl(gg);
                if (null != mySwipeRefreshLayout) {
                    mySwipeRefreshLayout.setRefreshing(false);
                }

            }

        });

    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main2,menu);
        return true ;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.as2){
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }else {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }else {
            Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
    private String decodeBase64(String coded){
        byte[] valueDecoded= new byte[0];
        try {
            valueDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
        }
        return new String(valueDecoded);
    }



    public void endTask() {
        // Is the user running Lollipop or above?
        // If yes, run the fancy new function to end the app and
        //  remove it from the task list.
        finishAndRemoveTask();
        finish();
    }
    @Override
    public void onBackPressed() {

       // super.onBackPressed();
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            // super.onBackPressed();
            AlertDialog.Builder alertDialogBuilder;
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
                    .setMessage(getString(R.string.chekari))
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.homee), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
//                        WebView webView = (WebView) findViewById(R.id.webView);
//
//                        webView.loadUrl("about:blank");

                            Intent iiii = new Intent(vip.this, MainActivity.class);

                            startActivity(iiii);
                            finish();


                        }
                    }).setNegativeButton(getString(R.string.mandanhere), new DialogInterface.OnClickListener() {
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

    private void showCustomDialog() {


        AlertDialog.Builder builder;
        if (isLightTheme) {


            builder = new AlertDialog.Builder(
                    this, R.style.MyDialog);

        } else {

            builder = new AlertDialog.Builder(
                    this, R.style.MyDialog3);

        }



        builder.setTitle("بخش پشتیبانی")
                .setItems(new CharSequence[]{"تلگرام پشتیبانی", "رفتن به ربات"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/purtell"));
                            startActivity(browserIntent2);
                        } else if (which == 1) {
                            Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/purcowbot"));
                            startActivity(browserIntent2);
                        }
                    }
                })
                .setNegativeButton("بستن", null)
                .create()
                .show();
    }
}