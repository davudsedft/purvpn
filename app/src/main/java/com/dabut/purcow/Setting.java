package com.dabut.purcow;

import static com.dabut.purcow.MainActivity.isServiceRunning;
import static com.dabut.purcow.Servers.Name;
import static com.dabut.purcow.Servers.isLinkValid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Setting extends AppCompatActivity {
    boolean lang = true;
    ProgressDialog updial,updial2;
    private static final String TAG = "DownloadActivity";
    LinearLayout layout;
    String utext2;


    private long downloadID;


    Context context;
    String url ="aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2RhdnVkc2VkZnQvcHVydnBuL3JlZnMvaGVhZHMvbWFpbi9saW5rcy92ZXJzaW9ucHVibGljLmpzb24=";
    private static final int PERMISSION_REQUEST_CODE = 200;
    AlertDialog.Builder dialogBuilder3,dialogBuilder4,alertDialogBuilder2;
    SharedPreferences Pref,Config,prefssh,cloudsh,prefs2sh,prefs3sh,preferences2,preferences;
    private ProgressDialog progressDialog;

    boolean isLightTheme = true;
    ImageView btnloop,btndns,btntunel,btntheme,btnupdate,btnlang;
    @SuppressLint({"MissingInflatedId", "WrongConstant", "WakelockTimeout"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
         preferences = getSharedPreferences("theme", MODE_PRIVATE);
         preferences2 = getSharedPreferences("language", MODE_PRIVATE);
        if (preferences2.contains("lang")) {
            lang = preferences2.getBoolean("lang", true);


        }


        if (preferences.contains("isLightTheme")) {
            isLightTheme = preferences.getBoolean("isLightTheme", true);
        }
        if (isLightTheme) {
            setTheme(R.style.Theme_V2rayExample);
        } else {
            setTheme(R.style.Theme_V2rayExample2);
        }

        if (lang) {
            setApplicationLocale();



        } else {
            setApplicationLocale2();

        }


        setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("PurCow");





        url = decodeBase64(url);


        context = this;
        btnloop =findViewById(R.id.btnloop);

        btndns =findViewById(R.id.btndns);
        btntunel =findViewById(R.id.btntunel);
        btnlang =findViewById(R.id.btnlang);
        btntheme =findViewById(R.id.btntheme);
        btnupdate =findViewById(R.id.btnup);
        Pref = getSharedPreferences("PrefFile", Context.MODE_PRIVATE);



        if (isLightTheme) {
            layout = (LinearLayout) findViewById(R.id.settingley);
            layout.setBackgroundResource(R.drawable.blue);
            updial= new ProgressDialog(this,R.style.MyDialog);
            updial2= new ProgressDialog(this,R.style.MyDialog);


        } else {
            layout = (LinearLayout ) findViewById(R.id.settingley);
            layout.setBackgroundResource(R.drawable.newpinl);

            updial= new ProgressDialog(this,R.style.MyDialog3);
            updial2= new ProgressDialog(this,R.style.MyDialog3);

        }


















    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private String decodeBase64(String coded){
        byte[] valueDecoded= new byte[0];
        try {
            valueDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
        }
        return new String(valueDecoded);
    }
    public void dns() {
        if (isLightTheme) {
            dialogBuilder3 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog);


        } else {
            dialogBuilder3 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog3);

        }
        dialogBuilder3.setTitle(getString(R.string.onvan));

        final EditText editText1 = new EditText(Setting.this);
        // editText1.setGravity(Ce);
        editText1.setGravity(Gravity.CENTER_HORIZONTAL);

        dialogBuilder3.setView(editText1);

        dialogBuilder3.setPositiveButton(getString(R.string.tayid), (dialog, which) -> {
            // عملیاتی که باید در صورت کلیک بر روی دکمه تأیید انجام شود
            String text1 = editText1.getText().toString();
            SharedPreferences   prefs  = getSharedPreferences("newdns1", Context.MODE_MULTI_PROCESS);

            SharedPreferences.Editor editor6 = prefs.edit();


            editor6.apply();
            editor6.putString("dns1is", text1);
            editor6.apply();

            // ...
        });
        dialogBuilder3.setNegativeButton(getString(R.string.dnsp), (dialog, which) -> {
            SharedPreferences   prefs  = getSharedPreferences("newdns1", Context.MODE_MULTI_PROCESS);
            final EditText tt = new EditText(Setting.this);

            SharedPreferences.Editor editor6 = prefs.edit();
            String text1 = tt.getText().toString();


            editor6.apply();
            editor6.putString("dns1is", text1);
            editor6.apply();
        });
        dialogBuilder3.setNeutralButton(getString(R.string.enseraf), (dialog, which) -> {
            // عملیاتی که باید در صورت کلیک بر روی دکمه لغو انجام شود
            // ...
        });

        AlertDialog alertDialog5 = dialogBuilder3.create();
        alertDialog5.show();





    }

    public void btntunnel(View view) {
        updial2.setMessage("در حال واکشی...");
        updial2.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Setting.this,Packapp.class);
                startActivity(i);
                finish();
                updial2.dismiss();

            }
        }, 1000);


    }

    public void btnlooop(View view) {
        prefssh  = getSharedPreferences("portloop", Context.MODE_PRIVATE);
        cloudsh  = getSharedPreferences("clodfler", Context.MODE_PRIVATE);

        prefs2sh  = getSharedPreferences("fragment", Context.MODE_PRIVATE);
        prefs3sh  = getSharedPreferences("timeloop", Context.MODE_PRIVATE);
        if (isLightTheme) {
            dialogBuilder4 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog);


        } else {
            dialogBuilder4 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog3);

        }


        dialogBuilder4.setTitle(getString(R.string.fragment));
// افزودن دو EditText به دیالوگ
        LinearLayout eee = new LinearLayout(context);
        eee.setOrientation(LinearLayout.VERTICAL);



        final EditText cloudfler = new EditText(context);
        cloudfler.setHint(getString(R.string.cloud));
        eee.addView(cloudfler);


        final EditText et = new EditText(context);
        et.setHint(getString(R.string.port));
        eee.addView(et);



        final EditText et2 = new EditText(context);
        et2.setHint(getString(R.string.betwin));
        eee.addView(et2);




        final EditText et3 = new EditText(context);
        et3.setHint(getString(R.string.timeset));
        eee.addView(et3);

        final TextView khali = new TextView(context);
        khali.setText(getString(R.string.khaliha));
        khali.setGravity(Gravity.CENTER);
        khali.setTextColor(Color.GREEN);
        khali.setTextSize(8);

        eee.addView(khali);

        final TextView feli = new TextView(context);
        feli.setGravity(Gravity.CENTER);
        feli.setText(getString(R.string.feli));
        feli.setTextColor(Color.YELLOW);
        feli.setTextSize(8);
        eee.addView(feli);
        final TextView nowserver = new TextView(context);



        nowserver.setTextColor(Color.WHITE);
        nowserver.setGravity(Gravity.CENTER_HORIZONTAL);

        nowserver.setTextSize(9);

        eee.addView(nowserver);
        nowserver.setText("IP:"+cloudsh.getString("clodfler" , "")+";"+"Port:"+prefssh.getString("portloop","")+";"+"Size:"+prefs2sh.getString("fragment","")+";"+"Time:"+prefs3sh.getString("timeloop",""));

        cloudfler.setText(cloudsh.getString("clodfler" , ""));
        et.setText(prefssh.getString("portloop",""));
        et2.setText(prefs2sh.getString("fragment",""));

        et3.setText(prefs3sh.getString("timeloop",""));

        // editText1.setGravity(Ce);
        cloudfler.setGravity(Gravity.CENTER_HORIZONTAL);

        et.setGravity(Gravity.CENTER_HORIZONTAL);
        et2.setGravity(Gravity.CENTER_HORIZONTAL);
        et3.setGravity(Gravity.CENTER_HORIZONTAL);

        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        et2.setInputType(InputType.TYPE_CLASS_NUMBER);
        et3.setInputType(InputType.TYPE_CLASS_NUMBER);

        dialogBuilder4.setView(eee);
        dialogBuilder4.setCancelable(true);
        dialogBuilder4.setPositiveButton(getString(R.string.tayid), (dialog, which) -> {



            // عملیاتی که باید در صورت کلیک بر روی دکمه تأیید انجام شود
            String tport = et.getText().toString();
            String tfreg = et2.getText().toString();
            String ttime = et3.getText().toString();
            String tcloud = cloudfler.getText().toString();






            if (tport.equals("") ){
                tport = "443";

            }
            if (tfreg.equals("")){
                tfreg = "150";
            }
            if (ttime.equals("")){
                ttime = "150";
            }
            if (tcloud.equals("")){
                tcloud = "loop.iranpurnet.ir";
            }


            SharedPreferences.Editor cl = cloudsh.edit();

            SharedPreferences.Editor j = prefssh.edit();
            SharedPreferences.Editor r = prefs2sh.edit();
            SharedPreferences.Editor y = prefs3sh.edit();

            cl.putString("clodfler", tcloud);
            cl.apply();
            j.putString("portloop", tport);
            j.apply();
            r.putString("fragment", tfreg);
            r.apply();
            y.putString("timeloop", ttime);
            y.apply();
            String nameconf = Pref.getString(Name, "");

            if (nameconf.equals("")) {
                Toast.makeText(context, getString(R.string.varedkonconfig), Toast.LENGTH_SHORT).show();
                // drawer_switch.setChecked(false);

            } else {

                Intent servIntent = new Intent(this , MyService.class);
                    if (isServiceRunning(context, MyService.class)) {
                    stopService(servIntent);

                    startService(servIntent);

                }


            }


            // ...
        });
        dialogBuilder4.setNegativeButton(getString(R.string.dnsp), (dialog, which) -> {

            SharedPreferences.Editor cl = cloudsh.edit();

            SharedPreferences.Editor j = prefssh.edit();
            SharedPreferences.Editor r = prefs2sh.edit();
            SharedPreferences.Editor y = prefs3sh.edit();
            j.putString("portloop", "443");
            j.apply();
            r.putString("fragment", "60");
            r.apply();
            y.putString("timeloop", "50");
            y.apply();
            cl.putString("clodfler", "loop.iranpurnet.ir");
            cl.apply();
            String nameconf = Pref.getString(Name, "");

            if (nameconf.equals("")) {
                Toast.makeText(context, getString(R.string.varedkonconfig), Toast.LENGTH_SHORT).show();
                // drawer_switch.setChecked(false);

            }else {
                Intent servIntent = new Intent(this , MyService.class);

                if (isServiceRunning(context, MyService.class)){
                    stopService(servIntent);

                    startService(servIntent);

                }




            }

        });
        dialogBuilder4.setNeutralButton(getString(R.string.pishham), (dialog, which) -> {
            SharedPreferences.Editor cl = cloudsh.edit();

            SharedPreferences.Editor j = prefssh.edit();
            SharedPreferences.Editor r = prefs2sh.edit();
            SharedPreferences.Editor y = prefs3sh.edit();
            j.putString("portloop", "443");
            j.apply();
            r.putString("fragment", "180");
            r.apply();
            y.putString("timeloop", "200");
            y.apply();
            cl.putString("clodfler", "loop.iranpurnet.ir");
            cl.apply();
            String nameconf = Pref.getString(Name, "");

            if (nameconf.equals("")) {
                Toast.makeText(context, getString(R.string.varedkonconfig), Toast.LENGTH_SHORT).show();
                // drawer_switch.setChecked(false);

            }else {
                Intent servIntent = new Intent(this , MyService.class);

                if (isServiceRunning(context, MyService.class)){
                    stopService(servIntent);

                    startService(servIntent);

                }




            }

        });

        AlertDialog alertDialog8 = dialogBuilder4.create();
        alertDialog8.show();


    }

    public void lang(View view) {


        if (isLightTheme) {
            alertDialogBuilder2 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog);


        } else {
            alertDialogBuilder2 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog3);

        }

        alertDialogBuilder2
                .setCancelable(true)
                .setPositiveButton(getString(R.string.zaban), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        SharedPreferences.Editor editor6 = preferences2.edit();

                        // اگر تم فعلی روشن است، تم را تیره کنید و برعکس
                        if (lang) {
                            setApplicationLocale();

                            lang = false;
                        } else {
                            setApplicationLocale2();
                            lang = true;
                        }

                        // مقدار جدید متغیر isLightTheme را در فایل preferences ذخیره کنید
                        editor6.putBoolean("lang", lang);
                        editor6.apply();

                        // فعالیت را دوباره راه‌اندازی کنید تا تغییرات اعمال شوند
                        recreate();





                    }
                }).setNegativeButton(getString(R.string.enseraf), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close


                        dialog.cancel();

                    }
                });



        AlertDialog alertDialog6 = alertDialogBuilder2.create();

        // show it
        alertDialog6.show();
        TextView messageView6 = (TextView)alertDialog6.findViewById(android.R.id.message);
        messageView6.setGravity(Gravity.CENTER);













    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (locationAccepted && cameraAccepted) {


                }
            }
        }
    }
    private void setApplicationLocale() {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale("fa"));
        } else {
            config.setLocale(new Locale("fa"));
        }
        resources.updateConfiguration(config, dm);
    }

    private void setApplicationLocale2() {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale("en"));
        } else {
            config.setLocale(new Locale("en"));
        }
        resources.updateConfiguration(config, dm);
    }

    public void theme(View view) {

        if (isLightTheme) {
            alertDialogBuilder2 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog);


        } else {
            alertDialogBuilder2 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog3);

        }

        alertDialogBuilder2
                .setCancelable(true)
                .setPositiveButton(getString(R.string.changetheme), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        SharedPreferences.Editor editor = preferences.edit();

                        // اگر تم فعلی روشن است، تم را تیره کنید و برعکس
                        if (isLightTheme) {
                            setTheme(R.style.Theme_V2rayExample);
                            isLightTheme = false;
                        } else {
                            setTheme(R.style.Theme_V2rayExample2);
                            isLightTheme = true;
                        }

                        // مقدار جدید متغیر isLightTheme را در فایل preferences ذخیره کنید
                        editor.putBoolean("isLightTheme", isLightTheme);
                        editor.apply();

                        // فعالیت را دوباره راه‌اندازی کنید تا تغییرات اعمال شوند
                        recreate();





                    }
                }).setNegativeButton(getString(R.string.enseraf), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close


                        dialog.cancel();

                    }
                });



        AlertDialog alertDialog3 = alertDialogBuilder2.create();

        // show it
        alertDialog3.show();
        TextView messageView3 = (TextView)alertDialog3.findViewById(android.R.id.message);
        messageView3.setGravity(Gravity.CENTER);





    }
    public void update(View view) {


        if (isLightTheme) {
            alertDialogBuilder2 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog);


        } else {
            alertDialogBuilder2 = new AlertDialog.Builder(
                    Setting.this, R.style.MyDialog3);

        }

        // set title
        alertDialogBuilder2.setTitle(getString(R.string.bakhshberuz));

        // set dialog message
        alertDialogBuilder2
                .setMessage(getString(R.string.chekari))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.barresiup), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //json
                        updial.setMessage(getString(R.string.sabr));
                        updial.show();


                        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String string) {
                                parseJsonData(string);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(getApplicationContext(), getString(R.string.moshkel), Toast.LENGTH_SHORT).show();
                                updial.dismiss();
                            }
                        });
                        RequestQueue rQueue = Volley.newRequestQueue(Setting.this);
                        rQueue.add(request);



                        //endjson


                    }
                }).setNegativeButton(getString(R.string.enseraf), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close


                        dialog.cancel();

                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder2.create();

        // show it
        alertDialog.show();
        TextView messageView = (TextView)alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);



    }

    public   void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray fruitsArray = object.getJSONArray("purvpn");
            //   JSONStringer gh = new JSONStringer(jsonString);
            //  ArrayList al = new ArrayList();
            List<String> list = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            List<String> list3 = new ArrayList<String>();

            list.add(fruitsArray.getString(0));
            list2.add(fruitsArray.getString(1));
            list3.add(fruitsArray.getString(2));



            String joined = TextUtils.join("",list);
            String joined2 = TextUtils.join("",list3);
            String joined3 = TextUtils.join("",list2);

            String ggg = utf88(joined2);

            float gf=Float.parseFloat(joined3)/10;

            int number = Integer.parseInt(joined);
            float num = number+gf;

            PackageInfo pinfo = null;
            try {
                pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            }
            catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            assert pinfo != null;
            String versionName = pinfo.versionName;
            // String ff = BuildConfig.VERSION_NAME;
            float verCode=Float.parseFloat(versionName);
            if (num> verCode){

                loopthead();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        updial.dismiss();


                        // set title

                        if (isLightTheme) {
                            alertDialogBuilder2 = new AlertDialog.Builder(
                                    Setting.this, R.style.MyDialog);


                        } else {
                            alertDialogBuilder2 = new AlertDialog.Builder(
                                    Setting.this, R.style.MyDialog3);

                        }







                        // set dialog message
                        alertDialogBuilder2
                                .setTitle(getString(R.string.mojde)+System.getProperty("line.separator")+getString(R.string.noskhe))
                                .setMessage(   ggg +System.getProperty("line.separator"))
                                .setCancelable(true)
                                .setPositiveButton(getString(R.string.danlodnoskhe), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                        if (utext2 != null){


                                            progressDialog = new ProgressDialog(Setting.this, R.style.MyDialog);
                                            progressDialog.setMessage("لطفا صبر کنید...");
                                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                            progressDialog.setCancelable(false);
                                            progressDialog.setMax(100);
                                            progressDialog.show();

                                            startDownload(utext2);


                                            registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));








                                        }else {
                                            Toast.makeText(context, "مشکلی رخ داد", Toast.LENGTH_SHORT).show();
                                        }




                                    }
                                }).setNegativeButton(getString(R.string.badan), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, close


                                        dialog.cancel();

                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder2.create();

                        // show it
                        alertDialog.show();

                    }
                }, 1000);
















            }else {
                updial.dismiss();

                if (isLightTheme) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialog);

                    builder.setMessage(getString(R.string.tabrik))
                            .setCancelable(true);
                    builder.setTitle(getString(R.string.lastnoskhe));

                    AlertDialog alert = builder.create();

                    // alert.setIcon(R.drawable.bej);
                    alert.show();


                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialog3);

                    builder.setMessage(getString(R.string.tabrik))
                            .setCancelable(true);
                    builder.setTitle(getString(R.string.lastnoskhe));

                    AlertDialog alert = builder.create();

                    // alert.setIcon(R.drawable.bej);
                    alert.show();

                }

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public  String utf88(String ss){
        byte[] bytes=ss.getBytes();
        String s=new String(bytes, StandardCharsets.UTF_8);
        // String sf = new String(bytes, StandardCharsets.UTF_8);
        s = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        return s;

    }

    public void btnipv(View view) {

        showMainDialog(this);


    }
///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////update

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public  void downloadAndInstallApk(Context context, String url) {

        File previousFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "purvpnupdate.apk");
        if (previousFile.exists()) {
            previousFile.delete(); // حذف فایل قبلی
        }
        progressDialog = new ProgressDialog(this,R.style.MyDialog);
        progressDialog.setMessage("در حال دانلود ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("دانلود نسخه جدید");
        request.setDescription("purvpn...");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "purvpnupdate.apk");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = downloadManager.enqueue(request);

        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (downloadId == id) {
                    Intent installIntent = new Intent(Intent.ACTION_VIEW);
                    installIntent.setDataAndType(downloadManager.getUriForDownloadedFile(downloadId), "application/vnd.android.package-archive");
                    installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(installIntent);
                    progressDialog.dismiss();

                }
            }
        }, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }



////////////////////////////


    public void showMainDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context , R.style.MyDialog);
        builder.setTitle("انتخاب کنید");
        builder.setMessage("لطفاً یکی از گزینه‌های زیر را انتخاب کنید:");
        builder.setPositiveButton("ویدئوی آموزشی", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showVideoDialog(context);
            }
        });
        builder.setNegativeButton("تنظیمات APN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APN_SETTINGS);
                startActivity(intent);
            }
        });
        builder.show();
    }
    public void showVideoDialog(Context context) {
        AlertDialog.Builder videoDialogBuilder = new AlertDialog.Builder(context , R.style.MyDialog);
        videoDialogBuilder.setTitle("ویدئوی آموزشی");

        // ایجاد VideoView و تنظیم ویدئو
        VideoView videoView = new VideoView(context);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.persia));
        videoView.start();

        videoDialogBuilder.setView(videoView);
        videoDialogBuilder.setPositiveButton("بستن", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        videoDialogBuilder.show();
    }

    public void btndns(View view) {
        dns();

    }


    @Override
    protected void onPause() {
        super.onPause();

    }


    public  void loopthead(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loop();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public void loop() throws IOException {
        String url = "https://raw.githubusercontent.com/davudsedft/newpurnet/main/newloop2.txt";
        String fileUrl = "https://raw.githubusercontent.com/davudsedft/purvpn/refs/heads/main/link.txt";

        if (isLinkValid("https://github.com")) {
            System.out.println("لینک معتبر است.");
            URL textUrl = new URL(fileUrl);

            HttpURLConnection connection = (HttpURLConnection)textUrl.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
            utext2 = stringBuilder.toString();


        } else {
            System.out.println("لینک معتبر نیست یا وجود ندارد.");
            Button b = (Button) findViewById(R.id.loopback);
            Toast.makeText(this, "خطا رخ داد", Toast.LENGTH_SHORT).show();

        }







        //  URL textUrl = null;


    }
    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

            progressDialog.dismiss();
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadID == id) {
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                installIntent.setDataAndType(downloadManager.getUriForDownloadedFile(downloadID), "application/vnd.android.package-archive");
                installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(installIntent);
                progressDialog.dismiss();            }
        }
    };


    private void startDownload(String url) {

        File previousFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "purvpnupdate.apk");
        if (previousFile.exists()) {
            previousFile.delete(); // حذف فایل قبلی
        }

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("دانلود نسخه جدید");
        request.setDescription("purvpn...");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "purvpnupdate.apk");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadID = manager.enqueue(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean downloading = true;

                while (downloading) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(downloadID);
                    Cursor cursor = manager.query(query);
                    if (cursor.moveToFirst()) {
                        @SuppressLint("Range")
                        int bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                        @SuppressLint("Range")
                        int bytesTotal = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                        if (bytesTotal > 0) {
                            final int progress = (int) ((bytesDownloaded * 100L) / bytesTotal);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(progress);
                                }
                            });
                        }

                        @SuppressLint("Range")
                        int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                        if (status == DownloadManager.STATUS_SUCCESSFUL || status == DownloadManager.STATUS_FAILED) {
                            downloading = false;
                        }
                    }
                    cursor.close();
                }
            }
        }).start();
    }



}