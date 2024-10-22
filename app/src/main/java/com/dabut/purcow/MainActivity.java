package com.dabut.purcow;


import static com.dabut.lib.v2ray.V2rayController.getConnectionState;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_CONNECTION_STATE_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_DOWNLOAD_SPEED_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_DOWNLOAD_TRAFFIC_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_DURATION_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_UPLOAD_SPEED_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_UPLOAD_TRAFFIC_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_INTENT;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_STATICS_BROADCAST_INTENT;
import static com.dabut.purcow.Servers.Name;
import static com.dabut.purcow.Servers.Name2;
import static com.dabut.purcow.Servers.isLinkValid;
import static com.wireguard.android.backend.Tunnel.State.DOWN;
import static com.wireguard.android.backend.Tunnel.State.UP;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.net.Uri;
import android.net.VpnService;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dabut.lib.v2ray.V2rayController;
import com.dabut.lib.v2ray.core.V2rayCoreExecutor;
import com.dabut.lib.v2ray.interfaces.LatencyDelayListener;
import com.dabut.lib.v2ray.services.V2rayVPNService;
import com.dabut.lib.v2ray.utils.Utilities;
import com.dabut.lib.v2ray.utils.V2rayConstants;
import com.dabut.purcow.Doh.DoH_over_Fragment;
import com.dabut.purcow.Doh.HTTPS_Fragmentor;
import com.dabut.purcow.Doh.TLS_Fragmentor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.wireguard.android.backend.Backend;
import com.wireguard.android.backend.GoBackend;
import com.wireguard.android.backend.Tunnel;
import com.wireguard.config.Interface;
import com.wireguard.config.Peer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_PERMISSION_CODE = 66;
    SharedPreferences Pref,Config,wireguard,cloudsh,prefs2sh,prefs3sh;

    Backend backend = PersistentConnectionProperties.getInstance().getBackend();
    private GestureLibrary gestureLibrary;
    boolean mb = false;
    AlertDialog.Builder dialogBuilder3,dialogBuilder4;
    private static final String TAG = "MainActivity";
    private static final int NOTIFICATION_REQUEST_CODE = 1234;
    public static Activity fa;
    boolean isLightTheme = true;
    private Intent servIntent,servIntent2;
    static boolean lang = true;
    CardView card1;
    int count = 0;
    private Handler progressHandler;

    private long lastDownloadId = -1;
    private ProgressDialog progressDialog;
    AlertDialog.Builder alertDialogBuilder2;

    // FirebaseAuth firebaseAuth;
  //  String url = "aHR0cHM6Ly9wdXJuZXQuaXIvanNvbi9uZXcuanNvbg==";
    String url ="aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2RhdnVkc2VkZnQvcHVyY293L21haW4vdmVyc2lvbnB1YmxpYy5qc29u";
    String aa,bb,cc ,dd,hh,kk= "http";
    ToggleButton drawer_switch;
    ToggleButton proxyswich;
    V2rayVPNService.Builder builder;
    NavigationView navigationView;
    String utext2;
    boolean firstrun;
    ProgressDialog updial;
    Context installcontex;
    String langgg = "fa";
    String langgg2 = "en";
    static NotificationManager notifManager;
    private long downloadID;
    String offerChannelId = "DEV7DEV_AXL_CH_ID";
    PendingIntent mpIntent;
    RelativeLayout layout;
    private ImageView imgbtn,connected_country;
    String vipcon = "";
    String deviceid;
    private long backPressedTime;
    private Toast backToast;
    private DrawerLayout drawerLayout ;
    private TextView connection_speed, connection_traffic, connection_time, connected_server_delay, txtbtn ,pingtest,btnlogo,bbcon2;
    //  private EditText v2ray_json_config;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private BroadcastReceiver v2rayBroadCastReceiver;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() != Activity.RESULT_OK) {
            Toast.makeText(this, "Permission not granted.", Toast.LENGTH_SHORT).show();
        }
    });

    @SuppressLint({"SetTextI18n", "MissingInflatedId", "WrongConstant", "ResourceAsColor", "Range", "UseSwitchCompatOrMaterialCode", "UnspecifiedRegisterReceiverFlag", "WrongViewCast", "HardwareIds", "NewApi", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

        SharedPreferences preferences = getSharedPreferences("theme", MODE_PRIVATE);

        // اگر فایل preferences وجود دارد، مقدار متغیر isLightTheme را از آن بخوانید
        if (preferences.contains("isLightTheme")) {
            isLightTheme = preferences.getBoolean("isLightTheme", true);
        }

        // اگر تم فعلی روشن است، تم را روشن کنید و برعکس
        if (isLightTheme) {
            setTheme(R.style.Theme_V2rayExample);




        } else {
            setTheme(R.style.Theme_V2rayExample2);
        }

        SharedPreferences preferences2 = getSharedPreferences("language", MODE_PRIVATE);

        // اگر فایل preferences وجود دارد، مقدار متغیر isLightTheme را از آن بخوانید
        if (preferences2.contains("lang")) {
            lang = preferences2.getBoolean("lang", true);


        }

        // اگر تم فعلی روشن است، تم را روشن کنید و برعکس
        if (lang) {
            setApplicationLocale();



        } else {
            setApplicationLocale2();

        }



        installcontex = this;




      ///////////////////////////////////////////



       /////////////////////////






        setContentView(R.layout.activity_main);


        GestureOverlayView gestureOverlayView = findViewById(R.id.gestureOverlay);
        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gestureLibrary.load()) {
            finish();
        }

        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
                for (Prediction prediction : predictions) {
                    if (prediction.score > 4.0 && "vip".equals(prediction.name)) {
                        // انجام کاری که می‌خواهید
                        performAction();
                        break; // جلوگیری از بررسی سایر پیش‌بینی‌ها
                    }

                }
            }
        });
        wireguard = getSharedPreferences("adrw",MODE_PRIVATE);
        wireguard = getSharedPreferences("endw",MODE_PRIVATE);
        wireguard = getSharedPreferences("pubw",MODE_PRIVATE);
        wireguard = getSharedPreferences("priw",MODE_PRIVATE);


        btnlogo = findViewById(R.id.btnlogo);
        bbcon2 = findViewById(R.id.btn_connection2);

        try {
            backend.getRunningTunnelNames();
        }
        catch (NullPointerException e) {
            // backend cannot be created without context
            PersistentConnectionProperties.getInstance().setBackend(new GoBackend(this));
            backend = PersistentConnectionProperties.getInstance().getBackend();
        }



        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);



        //  Toast.makeText(this, vipcon, Toast.LENGTH_SHORT).show();

////////////////////////////////////////
///////////////////////////////////
        if (savedInstanceState == null) {
            V2rayController.init(this, R.drawable.ic_launcher, "V2ray Android");

        }

       // Servers.net = false;
       // videoloop();
        drawerLayout = (DrawerLayout) findViewById(R.id.dl);
           navigationView = (NavigationView) findViewById(R.id.na);

/////////////////////////خدث فهپث

//        Context context = this;
//      SharedPreferences  prefs  = context.getSharedPreferences("com.dabut.purnetvray", Context.MODE_MULTI_PROCESS);
//
//
//        SharedPreferences prefs5 = getPreferences(MODE_MULTI_PROCESS);
//        boolean isFirstRun = prefs5.getBoolean("firstRun", true);
//
//        if (isFirstRun) {
//            // Your one-time code here
//
//
//
//            SharedPreferences.Editor editor7 = prefs.edit();
//            editor7.putStringSet("selectedPackages", Collections.singleton("com.dabut.purnetvray"));
//            editor7.apply();
//
//
//            // Mark that the code has run
//            SharedPreferences.Editor editor = prefs5.edit();
//            editor.putBoolean("firstRun", false);
//            editor.apply();
//        }

/////////////////////خدثفهپث
/////////////////////////خدث فهپث

        Set<String> selectedPackages= new HashSet<>();

        Context context = this;
        SharedPreferences  prefs  = context.getSharedPreferences("com.dabut.purnetvray", Context.MODE_MULTI_PROCESS);
        selectedPackages.add("com.dabut.purcow");

        selectedPackages.add("com.farazpardazan.enbank");
        selectedPackages.add("com.myirancell");
        selectedPackages.add("ir.divar");

        selectedPackages.add("ir.mci.ecareapp");
        selectedPackages.add("com.farsitel.bazaar");
        selectedPackages.add("ir.mservices.market");
        selectedPackages.add("com.sibche.aspardproject.app");
        selectedPackages.add("cab.snapp.passenger");
        selectedPackages.add("com.zoodfood.android");


        SharedPreferences prefs5 = getPreferences(MODE_MULTI_PROCESS);
        boolean isFirstRun = prefs5.getBoolean("firstRun", true);

        if (isFirstRun) {
            // Your one-time code here



            SharedPreferences.Editor editor7 = prefs.edit();
            editor7.putStringSet("selectedPackages", selectedPackages);
            editor7.apply();


            // Mark that the code has run
            SharedPreferences.Editor editor = prefs5.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();
        }

/////////////////////خدثفهپث


        if (isLightTheme) {
             layout = (RelativeLayout)findViewById(R.id.mainlay);
            layout.setBackgroundResource(R.drawable.tttt);

            navigationView.setBackgroundResource(R.drawable.tttt);

        } else {
             layout = (RelativeLayout)findViewById(R.id.mainlay);
            layout.setBackgroundResource(R.drawable.pink);
            navigationView.setBackgroundResource(R.drawable.pink);
            navigationView.setItemTextColor(null);
            navigationView.setItemTextAppearance(R.style.MenuTextStyle);
            pingtest = findViewById(R.id.button3);
            Button b   =(Button) findViewById(R.id.btn_connection2);
            Button gggggggg   =(Button) findViewById(R.id.btnlogo);

            connected_server_delay = findViewById(R.id.connected_server_delay);
            connection_traffic = findViewById(R.id.connection_traffic);

         TextView   connection_duration = (TextView) findViewById(R.id.connection_duration);
            connection_speed = findViewById(R.id.connection_speed);
           TextView textView4  = (TextView) findViewById(R.id.textView4);
           pingtest.setTextColor(Color.parseColor("#F86295"));
           b.setTextColor(Color.parseColor("#F86295"));
            connected_server_delay.setTextColor(Color.parseColor("#FF000000"));
            btnlogo.setTextColor(Color.parseColor("#FF000000"));
            connection_traffic.setTextColor(Color.parseColor("#FF000000"));
            connection_duration.setTextColor(Color.parseColor("#FF000000"));
            connection_speed.setTextColor(Color.parseColor("#FF000000"));
            textView4.setTextColor(Color.parseColor("#FF000000"));

        }
//        RelativeLayout layout = (RelativeLayout)findViewById(R.id.mainlay);
//        layout.setBackgroundResource(R.drawable.pink);
        connected_country = findViewById(R.id.connected_country);
        connected_country.setOnClickListener(new View.OnClickListener() {
            int l=0;

            @Override
            public void onClick(View view) {
                l++;

                if (l ==10){
                    Toast.makeText(context, " Mahdiyeh ❤️", Toast.LENGTH_SHORT).show();
                    l=0;
                }

            }
        });



        Pref = getSharedPreferences("PrefFile", Context.MODE_PRIVATE);
        if (Pref.contains(Name)) {


            Pref.getString(Name, null);

        }

        Config = getSharedPreferences("config", Context.MODE_PRIVATE);
        String nameconf = Config.getString(Name2, "");

        TextView txtconf = (TextView) findViewById(R.id.textView4) ;
        txtconf.setText(nameconf);


        Bundle resultIntent = getIntent().getExtras();

        if(resultIntent != null) {

            String nameValue = resultIntent.getString("kool");
            String textw = resultIntent.getString("copyte");

            if (Objects.equals(nameValue, "rovshan")) {


               // connected_server_delay.setText(getString(R.string.daryaft));

                V2rayController.StartV2ray(this, "Test Server", textw, null);

                String  wwww = getV2rayServerDelay(textw);

                new Handler().postDelayed(() -> {
                    // Update the TextView with the delayed text
                    connected_server_delay.setText(wwww);
                }, 2000);


            }
            if (Objects.equals(nameValue, "rovshan2")) {

wireguard();

            }



        }
                    //getConnectedV2rayServerDelay function need a text view for now

        aa=purnet();
        PackageInfo pinfo = null;
        hh=hg();
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        assert pinfo != null;
        String versionName = pinfo.versionName;


        dd=cortext();

        updial= new ProgressDialog(this,R.style.MyDialogStyle);
        bb=newurl2();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        cc= newurl3();

        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("PurCow");
        //json
        url = decodeBase64(url);

        firstrun = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getBoolean("firstrun" , true);
        if (firstrun){


            StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    parseJsonData2(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // مدیریت خطا
                }
            });



            RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
            rQueue.add(request);


        }









        TLS_Fragmentor       TLS_serv  = new TLS_Fragmentor("127.0.0.1" ,2012 ,
                "loop.iranpurnet.ir" ,443,
                true , 87 , 0.005);






        DoH_over_Fragment     DoH_service    = new DoH_over_Fragment("cloudflare",
                "loop.iranpurnet.ir", 443,
                true, 87, 0.005);


        HTTPS_Fragmentor     HTTPS_serv  = new HTTPS_Fragmentor("127.0.0.1",2015,
                null, -1,
                DoH_service,true,
                87,0.005);





       // SharedPreferences prefs8 = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        MenuItem menuItem = navigationView.getMenu().findItem(R.id.app_bar_switch); // This is the menu item that contains your switch
        MenuItem menuItem2 = navigationView.getMenu().findItem(R.id.app_bar_switch2); // This is the menu item that contains your switch

        drawer_switch= (ToggleButton) menuItem.getActionView().findViewById(R.id.ggsw);
        proxyswich= (ToggleButton) menuItem2.getActionView().findViewById(R.id.ggsw2);

        servIntent = new Intent(this, MyService.class);
        servIntent2 = new Intent(this, Myproxy.class);

     //   drawer_switch.getThumbDrawable().setTint(ContextCompat.getColor(this,R.color.red));
      //  drawer_switch.getTrackDrawable().setTint(ContextCompat.getColor(this,R.color.greeen));

        boolean savedSwitchState = false;

        drawer_switch.setChecked(savedSwitchState);
        proxyswich.setChecked(savedSwitchState);

        if (isServiceRunning(context, MyService.class)){
            savedSwitchState = true;
            drawer_switch.setChecked(true);
         //   drawer_switch.setText(getString(R.string.loopbakon));
         //   drawer_switch.setTextColor(Color.GREEN);
        }else {
            savedSwitchState = false;
            drawer_switch.setChecked(false);
            drawer_switch.setText(getString(R.string.loopbak));
            drawer_switch.setTextColor(Color.parseColor("#FFC107"));
        }


        if (isServiceRunning(context, Myproxy.class)){
            savedSwitchState = true;
            proxyswich.setChecked(true);
            //   drawer_switch.setText(getString(R.string.loopbakon));
            //   drawer_switch.setTextColor(Color.GREEN);
        }else {
            savedSwitchState = false;
            proxyswich.setChecked(false);
            proxyswich.setText(getString(R.string.proxyoff));
            proxyswich.setTextColor(Color.parseColor("#FFC107"));
        }



   //     loop();




        proxyswich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (getConnectionState() == V2rayConstants.CONNECTION_STATES.DISCONNECTED) {



                    if (isChecked) {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String nameconf = Pref.getString(Name, "");

                                if (nameconf.equals("")) {
                                    Toast.makeText(context, getString(R.string.varedkonconfig), Toast.LENGTH_SHORT).show();
                                    proxyswich.setChecked(false);

                                }else {


                                    proxyswich.setText(getString(R.string.proxyon));
                                    proxyswich.setTextColor(Color.GREEN);
                                    startService(servIntent2);


                                }

                            }
                        }, 600);



                    } else {

                        proxyswich.setChecked(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                proxyswich.setText(getString(R.string.proxyoff));
                                proxyswich.setTextColor(Color.parseColor("#FFC107"));


                                stopService(servIntent2);
                            }
                        }, 600);

//
//                    TLS_serv.safely_stop_server();
//                    HTTPS_serv.safely_stop_server();
//                    DoH_service.safely_stop_DoH();
                    }


                }else {
                   
                        proxyswich.setText("وی پی ان خاموش کن");
                    Toast.makeText(context, "وی پی ان خاموش کن", Toast.LENGTH_SHORT).show();
                }




            }
        });




        drawer_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
              //  loop();

























                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String nameconf = Pref.getString(Name, "");

                            if (nameconf.equals("")) {
                                Toast.makeText(context, getString(R.string.varedkonconfig), Toast.LENGTH_SHORT).show();
                                drawer_switch.setChecked(false);

                            }else {


                                drawer_switch.setText(getString(R.string.loopbakon));
                                drawer_switch.setTextColor(Color.GREEN);


                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    ContextCompat.startForegroundService(MainActivity.this, servIntent);
                                } else {
                                    ContextCompat.startForegroundService(MainActivity.this, servIntent);
                                }






                            }

                        }
                    }, 500);




                } else {

                    drawer_switch.setChecked(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            drawer_switch.setText(getString(R.string.loopbak));
                            drawer_switch.setTextColor(Color.parseColor("#FFC107"));
















//                            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = prefs.edit();
//                            editor.putBoolean("switchState", false);
//                            editor.apply();

//                            Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
//                            startActivity(Intent.makeRestartActivityTask(i.getComponent()));
//                            Runtime.getRuntime().exit(0);
                            stopService(servIntent);
                        }
                    }, 500);

//
//                    TLS_serv.safely_stop_server();
//                    HTTPS_serv.safely_stop_server();
//                    DoH_service.safely_stop_DoH();
                }

// حالت سوئیچ را در یک متغیر ذخیره کنید
// مثلاً در یک فایل SharedPreferences:

            }
        });
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected (MenuItem item){
                item.setChecked(false);
                drawerLayout.closeDrawer(Gravity.RIGHT);

                int itemId = item.getItemId();
                if (itemId == R.id.mem3) {//json

                    updial.setMessage(getString(R.string.sabr));
                    updial.show();

                    StringRequest request = new StringRequest( url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            info(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // مدیریت خطا
                        }
                    });



                    RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
                    rQueue.add(request);



                    //endjson


                    return true;
                } else if (itemId == R.id.mem4) {
                    Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(decodeBase64("aHR0cDovL3QubWUvcy9wdXJjb3dib3Q=")));
                    startActivity(browserIntent2);

                    return true;

                } else if (itemId == R.id.game) {
                    Intent iit = new Intent(MainActivity.this, Game.class);
                    startActivity(iit);
                    finish();

                    return true;


                }


                else if (itemId == R.id.twitter) {
                    Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(decodeBase64("aHR0cHM6Ly94LmNvbS9wdXJjb3Y=")));
                    startActivity(browserIntent2);

                    return true;
                }

                else if (itemId == R.id.share) {
                    ApplicationInfo api = getApplicationContext().getApplicationInfo();
                    String filePath = api.sourceDir;
//
//                        Intent intent = new Intent(Intent.ACTION_SEND);
//                        intent.setType("application/vnd.android.package-archive");
//                        intent.putExtra(Intent.EXTRA_STREAM,
//                                Uri.parse(filePath));
//                        startActivity(Intent.createChooser(intent, getString(R.string.share)));
//
//

                    sendApplication(fa);

                    return true;
                } else if (itemId == R.id.mem5) {
                    Intent ii = new Intent(MainActivity.this, Video.class);
                    startActivity(ii);
                    finish();

                    return true;

                } else if (itemId == R.id.app_bar_switch) {
                    return true;
                } else if (itemId == R.id.app_bar_switch2) {
                    return true;
                } else if (itemId == R.id.Setting) {
                    Intent fds = new Intent(MainActivity.this, Setting.class);
                    startActivity(fds);
                    finish();

                    return true;
                }

                return false;
            }

        });


        //endjson



        Pref = getSharedPreferences("PrefFile", Context.MODE_PRIVATE);
        String text = Pref.getString(Name, "");

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        CardView card2 = (CardView) findViewById(R.id.card2);


        card1  = (CardView) findViewById(R.id.card1);
        CardView cardView = (CardView) findViewById(R.id.vpnBtn);
        txtbtn = (TextView) findViewById(R.id.txtbtn);
        imgbtn = (ImageView) findViewById(R.id.imgbtn);




        //    connection = findViewById(R.id.btn_connection);
        //Button bbb = (Button) findViewById(R.id.button3);

        bbcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameconf.equals("wireguard")){
                    wireguard();

                }

                //  V2rayController.StopV2ray(getApplicationContext());

                Intent i = new Intent(MainActivity.this, Servers.class);
                startActivity(i);
                finish();
            }
        });

        //   @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView card2 = (CardView) findViewById(R.id.card2);

        card1.setBackgroundResource(R.drawable.df);

        card2.setBackgroundResource(R.drawable.dsss);
        cardView.setBackgroundResource(R.drawable.df);


        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }

        }

        fa = this;

        // EditText ed = ( EditText) findViewById(R.id.editTextTextMultiLine);
        // createNotifChannel();

        //  ActionBar bar = getActionBar();
        connection_speed = findViewById(R.id.connection_speed);
        connection_time = findViewById(R.id.connection_duration);
        connection_traffic = findViewById(R.id.connection_traffic);
        //   server_delay = findViewById(R.id.server_delay);
        //     connection_mode = findViewById(R.id.connection_mode);
        connected_server_delay = findViewById(R.id.connected_server_delay);
        //    v2ray_json_config = findViewById(R.id.v2ray_json_config);
        //    core_version = findViewById(R.id.core_version);

        // Checking the previous state value each time the activity is opened
        switch (getConnectionState().toString()) {
            case "V2RAY_CONNECTED":

                if (Config.getString(Name2,"").equals("wireguard")){

                }else{
                    mb = true;
                    txtbtn.setText("ON");
                    txtbtn.setTextColor(Color.GREEN);
                }


              //  card2.setBackgroundResource(R.drawable.dsss);

                break;
            case "V2RAY_DISCONNECTED":

                if (!Config.getString(Name2,"").equals("wireguard")){
                    mb = false;

                    txtbtn.setText("OFF");
                    txtbtn.setTextColor(Color.rgb(219, 102, 200));

                }


                break;
            case "V2RAY_CONNECTING":
                txtbtn.setText("در حال اتصال");
                break;
            default:
                break;
        }
        //   connection_mode.setText("connection mode : " + V2rayController.getConnectionMode() + " (tap to toggle)");
        //  v2ray_json_config.setText(getConfigContent());
        //    core_version.setText("v" + BuildConfig.VERSION_NAME + ", " + V2rayController.getCoreVersion());
        // Checking for access to tunneling the entire device network
        Intent intent = VpnService.prepare(getApplicationContext());
        if (intent != null) {
            // we have not permission so taking it :)
            activityResultLauncher.launch(intent);
        }

        cardView.setOnClickListener(view -> {




            if (text.equals("")) {
                Toast.makeText(MainActivity.this,getString(R.string.varedkonconfig), Toast.LENGTH_SHORT).show();
            }

            if (Config.getString(Name2,"").equals("wireguard")){

                wireguard();

            }else {


                if (getConnectionState() == V2rayConstants.CONNECTION_STATES.DISCONNECTED) {
                    // in StartV2ray function we can set remark to show that on notification.
                    // StartV2ray function steel need json config of v2ray. Unfortunately, it does not accept URI or base64 type at the moment.

                    try {


                        V2rayController.startV2ray(this, "Default", text, null);


                        connected_server_delay.setText(getString(R.string.daryaft));



                        //getConnectedV2rayServerDelay function need a text view for now



                      String  wwww = getV2rayServerDelay(text);

                        new Handler().postDelayed(() -> {
                            // Update the TextView with the delayed text
                            connected_server_delay.setText(wwww);
                        }, 2000);


                        mb = true;
                    } catch (Exception ignored) {


                    }


                } else {

                    connected_server_delay.setText("خاموش");
                    V2rayController.StopV2ray(this);
                    mb = false;

                }



            }


        });












        // Another way to check the connection delay of a config without connecting to it.

        pingtest =  findViewById(R.id.button3);



pingtest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (Config.getString(Name2, "").equals("wireguard")) {
            try {
                if (mb) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String out = "در دسترس است";

                            String endpoint = wireguard.getString("endw","");

                            String[] parts = endpoint.split("]:");
                            String host = parts[0].replace("[","");
                            int port = Integer.parseInt(parts[1]);

                            long startTime = System.currentTimeMillis();
                            boolean isReachable = false;

                            try (Socket socket = new Socket(host, port)) {
                                isReachable = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            long endTime = System.currentTimeMillis();
                            long delay = endTime - startTime;



                            if (delay >= 0 && delay < 600) {
                                out =  "سرور جت" + Long.toString(delay);

                            } else if (delay >= 600 && delay < 800) {
                                out =  "سرور عالی" + Long.toString(delay);

                            } else if (delay >= 800 && delay < 1000) {

                                out =  "سرور معمولی" + Long.toString(delay);

                            } else if (delay >= 1000 && delay < 2000) {

                                out =  "سرور ضعیف" + Long.toString(delay);
                            }


                            if (isReachable){

                                String finalOut = out;
                                runOnUiThread(() -> {
                                    // کد تغییر ویوها در اینجا قرار می‌گیرد
                                    pingtest.setText(finalOut);
                                });


                            }else{
                                runOnUiThread(() -> {
                                    // کد تغییر ویوها در اینجا قرار می‌گیرد
                                    pingtest.setText("سرور مشکل دارد");                                });


                            }

                        }
                    }).start();



                }else {
                    Toast.makeText(getApplicationContext(), "ابتدا روشن کنید", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            Pref = getSharedPreferences("PrefFile", Context.MODE_PRIVATE);
            String text = Pref.getString(Name, "");
            String re = getString(R.string.daryaft);


            if (pingtest.getText().toString().equals(re)) {


                Toast.makeText(MainActivity.this, getString(R.string.sabkon), Toast.LENGTH_SHORT).show();

            } else if (text == "") {
                Toast.makeText(MainActivity.this, getString(R.string.varedkonconfig), Toast.LENGTH_SHORT).show();

            } else {
                if (!mb) {
                    Toast.makeText(MainActivity.this, getString(R.string.avvalroshan), Toast.LENGTH_SHORT).show();

                    //  pingtest.setText(re);


                }

            }


            if (mb) {
                pingtest.setText(re);

                // Don`t forget to do ui jobs in ui thread!
                getConnectedV2rayServerDelay(MainActivity.this, delayResult -> runOnUiThread(() -> pingtest.setText("پینگ: " + delayResult + "ms")));


                //  Toast.makeText(MainActivity.this, " اول خاموش کن", Toast.LENGTH_SHORT).show();

            }
        }


    }
});







        // The connection mode determines whether the entire phone is tunneled or whether an internal proxy (http , socks) is run


        //I tested several different ways to send information from the connection process side
        // to other places (such as interfaces, AIDL and singleton ,...) apparently the best way
        // to send information is broadcast.
        // So v2ray library will be broadcast information with action V2RAY_CONNECTION_INFO.
        v2rayBroadCastReceiver = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onReceive(Context context, Intent intent) {


                connection_time.setText("زمان: " + Objects.requireNonNull(intent.getExtras()).getString(SERVICE_DURATION_BROADCAST_EXTRA));
               // connection_speed.setText(intent.getExtras().getString("UPLOAD_SPEED") + "     |     " + intent.getExtras().getString("DOWNLOAD_SPEED"));
                connection_speed.setText("سرعت: " + intent.getExtras().getString(SERVICE_UPLOAD_SPEED_BROADCAST_EXTRA) + " | " + intent.getExtras().getString(SERVICE_DOWNLOAD_SPEED_BROADCAST_EXTRA));

                connection_traffic.setText("مصرف: " + intent.getExtras().getString(SERVICE_UPLOAD_TRAFFIC_BROADCAST_EXTRA) + " | " + intent.getExtras().getString(SERVICE_DOWNLOAD_TRAFFIC_BROADCAST_EXTRA));

                switch ((V2rayConstants.CONNECTION_STATES) Objects.requireNonNull(intent.getExtras().getSerializable(SERVICE_CONNECTION_STATE_BROADCAST_EXTRA))) {
                    case CONNECTED:

                        if (!Config.getString(Name2,"").equals("wireguard")){
                            txtbtn.setText("ON");
                            mb = true;
                            imgbtn.setBackgroundResource(R.drawable.kkk);
                            card1.setBackgroundResource(R.drawable.df);

                            pingtest.setBackgroundResource(R.drawable.greeeeeen);
                            bbcon2.setBackgroundResource(R.drawable.qermez);
                            txtbtn.setTextColor(Color.GREEN);
                            btnlogo.setBackgroundResource(R.drawable.sefid);


                        }




                        // simpleNotification();

                  //  pendingNotification();


                        break;
                    case DISCONNECTED:
                        mb = false;
                        if (!Config.getString(Name2,"").equals("wireguard")){
                            txtbtn.setText("OFF");
                            //  connection.setBackgroundColor("");
                            imgbtn.setBackgroundResource(R.drawable.circle);
                            txtbtn.setTextColor(Color.rgb(219, 102, 200));
                            card1.setBackgroundResource(R.drawable.df);
                            pingtest.setBackgroundResource(R.drawable.ddd);
                            bbcon2.setBackgroundResource(R.drawable.ddd);
                            btnlogo.setBackgroundResource(R.drawable.ddd);

                            //  simpleNotification();
                        }


                        //  simpleNotification();

                        break;
                    case CONNECTING:
                        txtbtn.setText("متصل شوید");
                        break;
                    default:
                        break;
                }

            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT), RECEIVER_EXPORTED);
        } else {
            registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT));
        }


        if (Config.getString(Name2,"").equals("wireguard")){
            wireh();
        }

    }








    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    @SuppressLint("NewApi")
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
    public static void sendAppItself(Activity paramActivity, String paramStringToastMsg) throws IOException {
        Toast.makeText(paramActivity, paramStringToastMsg, Toast.LENGTH_LONG).show();
        PackageManager pm = paramActivity.getPackageManager();
        ApplicationInfo appInfo;
        try {
            appInfo = pm.getApplicationInfo(paramActivity.getPackageName(),
                    PackageManager.GET_META_DATA);
            Intent sendBt = new Intent(Intent.ACTION_SEND);
            // NOT THIS! sendBt.setType("application/vnd.android.package-archive");
            sendBt.setType("*/*");
            sendBt.putExtra(Intent.EXTRA_STREAM,
                    Uri.parse("file://" + appInfo.publicSourceDir));

            Log.v("PACKAGEDIR: ", appInfo.publicSourceDir);
            paramActivity.startActivity(Intent.createChooser(sendBt, "Share image using"));
            // paramActivity.startActivity(sendBt);
        }
        catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }

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

    public void sendApplication(Activity activity) {
        ApplicationInfo app = activity.getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("*/*");

        // Append file and send Intent
        File originalApk = new File(filePath);

        try {
            //Make new directory in new location
            File tempFile = new File(activity.getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory()) {
                if (!tempFile.mkdirs()) {
                    return;
                }
            }
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/" + activity.getString(app.labelRes).replace(" ", "") + ".apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            //Open share dialog

            // Uri uri = FileProvider.getUriForFile(MainActivity.this, activity.getPackageName(), tempFile);
            Uri uri = FileProvider.getUriForFile(MainActivity.this, activity.getApplicationContext().getPackageName() + ".provider", tempFile);

            intent.putExtra(Intent.EXTRA_STREAM, uri);
            activity.grantUriPermission(activity.getPackageManager().toString(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //activity.startActivity(intent);
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String purnet() {

        String S = "ht";
        String ss = "tps";
        String Sss = "://";
        return S+ss+Sss;

    }


    public static long lastClickTime = 0;
    public static final long DOUBLE_CLICK_TIME_DELTA = 5000;

    public static boolean isDoubleClick(){
        long clickTime = System.currentTimeMillis();
        if(clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
            lastClickTime = clickTime;
            return true;
        }
        lastClickTime = clickTime;
        return false;
    }

    public static String getConfigContent() {
        return "";
    }

    ///########################

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


    public void newping(View view) {


    }

    public void config(View view) {
        Toast.makeText(this, " اینترنت برای همه یا هیچکس", Toast.LENGTH_SHORT).show();
    }



    private class CheckEndpointTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String endpoint = params[0];
            String[] parts = endpoint.split("]:");
            String host = parts[0].replace("[","");
            int port = Integer.parseInt(parts[1]);

            long startTime = System.currentTimeMillis();
            boolean isReachable = false;

            try (Socket socket = new Socket(host, port)) {
                isReachable = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            long delay = endTime - startTime;

            return endpoint + " - " + (isReachable ? "Reachable" : "Not Reachable") + " with delay: " + delay + " ms";
        }

        @Override
        protected void onPostExecute(String result) {
            pingtest.setText(result);
        }
    }
    public static String getPingResult(String a) {
        String str = "";
        String result = "";
        BufferedReader reader = null;
        char[] buffer = new char[4096];
        StringBuffer output = new StringBuffer();

        try {
            Runtime r = Runtime.getRuntime();
            Process process = r.exec("/system/bin/ping -c 3 " + a);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            int i;

            while ((i = reader.read(buffer)) > 0)
                output.append(buffer, 0, i);


            str = output.toString();

            final String[] b = str.split("---");
            final String[] c = b[2].split("rtt");

            if (b.length == 0 || c.length == 0)
                return null;

            if(b.length == 1 || c.length == 1)
                return null;

            result = b[1].substring(1, b[1].length()) + c[0] + c[1].substring(1, c[1].length());

        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        finally
        {
            if(reader != null)
            {
                try{reader.close();}catch(IOException ie){}
            }
        }

        return result;
    }


    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public static void getConnectedV2rayServerDelay(final Context context, final LatencyDelayListener latencyDelayCallback) {
        if (getConnectionState() != V2rayConstants.CONNECTION_STATES.CONNECTED) {
            latencyDelayCallback.OnResultReady(-1);
            return;
        }
        BroadcastReceiver connectionLatencyBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    int delay = Objects.requireNonNull(intent.getExtras()).getInt(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA);




                    latencyDelayCallback.OnResultReady(delay);







                } catch (Exception ignore) {
                    latencyDelayCallback.OnResultReady(-1);
                }
                context.unregisterReceiver(this);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(connectionLatencyBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT), RECEIVER_EXPORTED);
        } else {
            context.registerReceiver(connectionLatencyBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT));
        }
        Intent get_delay_intent = new Intent(V2RAY_SERVICE_COMMAND_INTENT);
        get_delay_intent.setPackage(context.getPackageName());
        get_delay_intent.putExtra(V2RAY_SERVICE_COMMAND_EXTRA, V2rayConstants.SERVICE_COMMANDS.MEASURE_DELAY);
        context.sendBroadcast(get_delay_intent);
    }



    @Override
    public  void onStart() {
        super.onStart();
    }
public  void ons(){
        onStart();
}



    //#########################
//json////////////////////////////

    public  String utf88(String ss){
        byte[] bytes=ss.getBytes();
        String s=new String(bytes, StandardCharsets.UTF_8);
        // String sf = new String(bytes, StandardCharsets.UTF_8);
        s = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        return s;

    }
    void parseJsonData2(String jsonString) {
        try {
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date = new Date();
//            String frmtdDate = dateFormat.format(date);

            //  String current = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());



            JSONObject object = new JSONObject(jsonString);
            JSONArray fruitsArray = object.getJSONArray("purvpn");
            //   JSONStringer gh = new JSONStringer(jsonString);
            //  ArrayList al = new ArrayList();
            List<String> list = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            List<String> list3 = new ArrayList<String>();
            List<String> list5 = new ArrayList<String>();
            list.add(fruitsArray.getString(0));
            list2.add(fruitsArray.getString(1));
            list3.add(fruitsArray.getString(2));

           // list3.add(fruitsArray.getString());


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



                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                MainActivity.this, R.style.MyDialog);

                        // set title

                        // set dialog message
                        alertDialogBuilder
                                .setTitle(getString(R.string.mojde)+System.getProperty("line.separator")+getString(R.string.noskhe))
                                .setMessage(   ggg +System.getProperty("line.separator"))
                                .setCancelable(true)
                                .setPositiveButton(getString(R.string.danlodnoskhe), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


//                                Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://davudsedft.github.io/purvpn/"));
//                                browserIntent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                startActivity(browserIntent2);
//
//
//
//



                                        if (utext2 != null){


                                            progressDialog = new ProgressDialog(MainActivity.this, R.style.MyDialog);
                                            progressDialog.setMessage("لطفا صبر کنید...");
                                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                            progressDialog.setCancelable(false);
                                            progressDialog.setMax(100);
                                            progressDialog.show();

                                            startDownload(utext2);


                                            registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));








                                        }else {
                                            Toast.makeText(installcontex, "مشکلی رخ داد", Toast.LENGTH_SHORT).show();
                                        }




                                    }
                                }).setNegativeButton(getString(R.string.badan), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, close

                                        //  firstrun = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getBoolean("firstrun" , false);
                                        getSharedPreferences("PREFERENCE" , MODE_PRIVATE).edit().putBoolean("firstrun",false).commit();

                                        dialog.cancel();

                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();

                    }
                }, 1000);


            }




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onBackPressed() {
      //  wakelock.acquire(4*60*1000L /*10 minutes*/);
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)){
            drawerLayout.closeDrawer(Gravity.RIGHT);
        }else {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                backToast.cancel();
                super.onBackPressed();
                return;
            } else {
                backToast = Toast.makeText(getBaseContext(), "برای خروج دوباره فشار دهید", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();

        }
        getSharedPreferences("PREFERENCE" , MODE_PRIVATE).edit().putBoolean("firstrun",true).commit();


    }

    void info(String jsonString) {
        try {
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date = new Date();
//            String frmtdDate = dateFormat.format(date);




            JSONObject object = new JSONObject(jsonString);
            JSONArray fruitsArray = object.getJSONArray("purvpn");
            //   JSONStringer gh = new JSONStringer(jsonString);
            //  ArrayList al = new ArrayList();
            List<String> list4 = new ArrayList<String>();

            List<String> list5 = new ArrayList<String>();


            list4.add(fruitsArray.getString(3));
            list5.add(fruitsArray.getString(5));



            String joined2 = TextUtils.join("",list4);
            String joined3 = TextUtils.join("",list5);

            String ggg ;

            if (lang){
               ggg= utf88(joined2);
            }else {
                ggg= utf88(joined3);
            }



            updial.dismiss();
            if (isLightTheme) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialog);
                builder.setIcon(R.drawable.mester);

                builder.setMessage(ggg)
                        .setCancelable(true);
                builder.setTitle(getString(R.string.qodrat));

                AlertDialog alert = builder.create();

                // alert.setIcon(R.drawable.bej);
                alert.show();

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialog3);
                builder.setIcon(R.drawable.ic_launcher);

                builder.setMessage(ggg)
                        .setCancelable(true);
                builder.setTitle(getString(R.string.qodrat));

                AlertDialog alert = builder.create();

                // alert.setIcon(R.drawable.bej);
                alert.show();
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
////////////////////////////////////dddddddddddddddddddddd


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


    private class UpdateProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            // محاسبه درصد پیشرفت و بروزرسانی آن
            for (int progress = 0; progress <= 100; progress++) {
                publishProgress(progress);
                try {
                    Thread.sleep(500); // تاخیر مصنوعی برای نمایش پیشرفت
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (progressDialog != null) {
                progressDialog.setProgress(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // پس از اتمام دانلود، اینستالر را نمایش دهید
        }}



//////////////dddddddddddddddddddddddddddddddddd
    ///jsonnn/////////////////

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(v2rayBroadCastReceiver);
        fa = this;


        /// simpleNotification();
        // simpleNotification();
        //  createNotifChannel();
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true ;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.as){
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



    private void createNotifChannel() {
        notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent pIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent mpIntent = PendingIntent.getActivity(getApplicationContext(), 0, pIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String offerChannelName = "purnet v3ray";
            String offerChannelDescription = "best free vpn";
            int offerChannelImportance = NotificationManager.IMPORTANCE_MAX;

            @SuppressLint("WrongConstant") NotificationChannel notifChannel = new NotificationChannel(offerChannelId, offerChannelName, offerChannelImportance);
            notifChannel.setDescription(offerChannelDescription);
            //notifChannel.enableVibration(true);
            notifChannel.enableLights(true);
            notifChannel.setLightColor(Color.GREEN);

            notifManager.createNotificationChannel(notifChannel);

        }

    }

    @SuppressLint("UnspecifiedImmutableFlag")
    public static void simpleNotification() {
        notifManager.cancel(3);


    }
    private String cortext() {  String  h = "pur";
        String hh = "net.i";
        String hhh = "r/js";

        return  h+hh+hhh;
    }


    @Override
    protected void onResume() {
        super.onResume();
        fa = this;
        Context context = this;

        if (isServiceRunning(context, MyService.class)){
            drawer_switch.setChecked(true);
            drawer_switch.setText(getString(R.string.loopbakon));
            drawer_switch.setTextColor(Color.GREEN);
        }else {
            drawer_switch.setChecked(false);
            drawer_switch.setText(getString(R.string.loopbak));
            drawer_switch.setTextColor(Color.parseColor("#FFC107"));
        }

        if (isServiceRunning(context, Myproxy.class)){
            proxyswich.setChecked(true);
            proxyswich.setText(getString(R.string.proxyon));
            proxyswich.setTextColor(Color.GREEN);
        }else {
            proxyswich.setChecked(false);
            proxyswich.setText(getString(R.string.proxyoff));
            proxyswich.setTextColor(Color.parseColor("#FFC107"));
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        fa = this;
        Context context = this;
        if (isServiceRunning(context, MyService.class)){
            drawer_switch.setChecked(true);
            drawer_switch.setText(getString(R.string.loopbakon));
            drawer_switch.setTextColor(Color.GREEN);
        }else {
            drawer_switch.setChecked(false);
            drawer_switch.setText(getString(R.string.loopbak));
            drawer_switch.setTextColor(Color.parseColor("#FFC107"));
        }
        if (isServiceRunning(context, Myproxy.class)){
            proxyswich.setChecked(true);
            proxyswich.setText(getString(R.string.proxyon));
            proxyswich.setTextColor(Color.GREEN);
        }else {
            proxyswich.setChecked(false);
            proxyswich.setText(getString(R.string.proxyoff));
            proxyswich.setTextColor(Color.parseColor("#FFC107"));
        }

    }


    public void runtimeEnableAutoInit() {
        // [START fcm_runtime_enable_auto_init]
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        // [END fcm_runtime_enable_auto_init]
    }
    private String hg() {
        String hhhh = "on/";
        String l = "ne";
        String ll = "w.j";
        String lll = "son";
        return hhhh+l+ll+lll;
    }
    public void deviceGroupUpstream() {
        // [START fcm_device_group_upstream]
        String to = "a_unique_key"; // the notification key
        AtomicInteger msgId = new AtomicInteger();
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder(to)
                .setMessageId(String.valueOf(msgId.get()))
                .addData("hello", "world")
                .build());
        // [END fcm_device_group_upstream]
    }
    private String newurl2() {
        String dd = "pur";
        String ddd = "net.i";
        String dddd = "r/";

        return dd+ddd+dddd;
    }
    public void sendUpstream() {
        final String SENDER_ID = "YOUR_SENDER_ID";
        final int messageId = 0; // Increment for each
        // [START fcm_send_upstream]
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        fm.send(new RemoteMessage.Builder(SENDER_ID + "@fcm.googleapis.com")
                .setMessageId(Integer.toString(messageId))
                .addData("my_message", "Hello World")
                .addData("my_action", "SAY_HELLO")
                .build());
        // [END fcm_send_upstream]
    }

    private void subscribeTopics() {
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        // [END subscribe_topics]
    }

    private void logRegToken() {
        // [START log_reg_token]
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = "FCM Registration token: " + token;
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        // [END log_reg_token]
    }
    private String newurl3() {
        String d = "vp";
        String dd = "n/purcov/Pu";
        String ddd = "rCow";

        return d+dd+ddd;
    }
    private String decodeBase64(String coded){
        byte[] valueDecoded= new byte[0];
        try {
            valueDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
        }
        return new String(valueDecoded);
    }
    private String pk() {
        String ddd = "k";

        String dd = "ap";
        String d = ".";

        return d+dd+ddd;
    }
    // [START ask_post_notifications]
    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

//    public void config(View view) {
//        Intent i = new Intent(MainActivity.this, Webv.class);
//        startActivity(i);
//        finish();
//    }









    public static void sentEmail(Context mContext, String[] addresses, String subject, String body) {

        try {
            Intent sendIntentGmail = new Intent(Intent.ACTION_VIEW);
            sendIntentGmail.setType("plain/text");
            sendIntentGmail.setData(Uri.parse(TextUtils.join(",", addresses)));
            sendIntentGmail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            sendIntentGmail.putExtra(Intent.EXTRA_EMAIL, addresses);
            if (subject != null) sendIntentGmail.putExtra(Intent.EXTRA_SUBJECT, subject);
            if (body != null) sendIntentGmail.putExtra(Intent.EXTRA_TEXT, body);
            mContext.startActivity(sendIntentGmail);
        } catch (Exception e) {
            //When Gmail App is not installed or disable
            Intent sendIntentIfGmailFail = new Intent(Intent.ACTION_SEND);
            sendIntentIfGmailFail.setType("*/*");
            sendIntentIfGmailFail.putExtra(Intent.EXTRA_EMAIL, addresses);
            if (subject != null) sendIntentIfGmailFail.putExtra(Intent.EXTRA_SUBJECT, subject);
            if (body != null) sendIntentIfGmailFail.putExtra(Intent.EXTRA_TEXT, body);
            if (sendIntentIfGmailFail.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(sendIntentIfGmailFail);
            }
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public static void getConnectedV2rayServerDelay22(final Context context, final LatencyDelayListener latencyDelayCallback) {
        if (getConnectionState() != V2rayConstants.CONNECTION_STATES.CONNECTED) {
            latencyDelayCallback.OnResultReady(-1);
            return;
        }
        BroadcastReceiver connectionLatencyBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    int delay = Objects.requireNonNull(intent.getExtras()).getInt(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA);
                    latencyDelayCallback.OnResultReady(delay);













                } catch (Exception ignore) {
                    latencyDelayCallback.OnResultReady(-1);
                }
                context.unregisterReceiver(this);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(connectionLatencyBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT), RECEIVER_EXPORTED);
        } else {
            context.registerReceiver(connectionLatencyBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT));
        }
        Intent get_delay_intent = new Intent(V2RAY_SERVICE_COMMAND_INTENT);
        get_delay_intent.setPackage(context.getPackageName());
        get_delay_intent.putExtra(V2RAY_SERVICE_COMMAND_EXTRA, V2rayConstants.SERVICE_COMMANDS.MEASURE_DELAY);
        context.sendBroadcast(get_delay_intent);
    }

//    public  void getConnectedV2rayServerDelay22(final Context context, final TextView tvDelay) {
//        Intent check_delay;
//        if (AppConfigs.V2RAY_CONNECTION_MODE == AppConfigs.V2RAY_CONNECTION_MODES.PROXY_ONLY) {
//            check_delay = new Intent(context, V2rayProxyOnlyService.class);
//        } else if (AppConfigs.V2RAY_CONNECTION_MODE == AppConfigs.V2RAY_CONNECTION_MODES.VPN_TUN) {
//            check_delay = new Intent(context, V2rayVPNService.class);
//        } else {
//            return;
//        }
//        check_delay.putExtra("COMMAND", AppConfigs.V2RAY_SERVICE_COMMANDS.MEASURE_DELAY);
//        context.startService(check_delay);
//        context.registerReceiver(new BroadcastReceiver() {
//            @SuppressLint({"SetTextI18n", "UnspecifiedRegisterReceiverFlag"})
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                String delay = arg1.getExtras().getString("DELAY");
//                int num1 = Integer.parseInt(delay);
//
//
//
//
//                if (num1 == -1) {
//                    if (lang){
//                        tvDelay.setText(" سرور مشکل دارد : " + "🤦‍");
//
//                    }else {
//                        tvDelay.setText("Not Connect : " + "🤦‍");
//
//                    }
//
//                }else{
//                    if (num1>=0 && num1<600){
//                        if (lang){
//                            tvDelay.setText("پینگ جت: " + delay);
//
//                        }else {
//                            tvDelay.setText(" ping jet: " + delay);
//
//                        }
//
//                    } else if (num1>=600 && num1<=800) {
//                        if (lang){
//                            tvDelay.setText("پینگ عالی: " + delay);
//
//                        }else {
//                            tvDelay.setText("ping very good : " + delay);
//
//                        }
//
//                    }else {
//                        if (num1>800 && num1<1000){
//                            if (lang){
//                                tvDelay.setText("پینگ خوب : " + delay);
//
//                            }else {
//                                tvDelay.setText(" ping good : " + delay);
//
//                            }
//
//                        } else if (num1>=1000 && num1<1500) {
//                            if (lang){
//                                tvDelay.setText("پینگ معمولی: " + delay);
//
//                            }else {
//                                tvDelay.setText(" ping nat bad: " + delay);
//
//                            }
//
//                        }else {
//                            if (lang){
//                                tvDelay.setText("پینگ ضعیف: " + delay);
//
//                            }else {
//                                tvDelay.setText(" ping weak: " + delay);
//
//                            }
//
//                        }
//
//                    }
//
//                }
//                context.unregisterReceiver(this);
//            }
//        }, new IntentFilter("CONNECTED_V2RAY_SERVER_DELAY"));
//    }


//public void videoloop2(){
//    String path = "android.resource://" + getPackageName() + "/" + R.raw.pppppp;
//    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//    final VideoView videoView = findViewById(R.id.videoView3); //id in your xml file
//    videoView.setVideoURI(Uri.parse(path)); //the string of the URL mentioned above
//    MediaController mc = new MediaController(videoView.getContext());
//    mc.setMediaPlayer(videoView);
//    //videoView.setMediaController(mc);
//    videoView.setMediaController(null);
//    videoView.requestFocus();
//    videoView.start();
//    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
//    {
//        @Override
//        public void onPrepared(MediaPlayer mp) {
//            mp.setLooping(true);
//            mp.start();
//
//        }
//    });
//}
private void parseJsonData5(String string) {
    try {
        JSONObject object = new JSONObject(string);
        JSONArray fruitsArray = object.getJSONArray("link");

        if (fruitsArray != null){
            List<String> list = new ArrayList<String>();
            list.add(fruitsArray.getString(0));

            // list3.add(fruitsArray.getString());


            String  server = TextUtils.join("",list);


            String url = server;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        //   JSONStringer gh = new JSONStringer(jsonString);
        //  ArrayList al = new ArrayList();


    } catch (JSONException e) {

        e.printStackTrace();
    }

}
    public static void showWebDialog(Context context, String url) {
        // Create a WebView instance
        WebView webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        TextView textView = new TextView(context);
        textView.setText("صبر کنید");

        // Load the desired URL
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/purnet.html");
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                TextView textView = new TextView(context);


               textView.setText("");

            }
        });

        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                result.confirm();

                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                String clipboardtext = item.getText().toString();

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();



                return true;

            }
        });



        // Create an AlertDialog with the WebView
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.MyDialog);
        builder.setView(webView);
        builder.setPositiveButton("بستن", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
@SuppressLint("NewApi")
public void wireguard(){

    Intent intent = new Intent(MainActivity.this , MyService2.class);


    Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();
    wireguard = getSharedPreferences("adrw",MODE_PRIVATE);
    wireguard = getSharedPreferences("endw",MODE_PRIVATE);
    wireguard = getSharedPreferences("pubw",MODE_PRIVATE);
    wireguard = getSharedPreferences("priw",MODE_PRIVATE);

    String addr = wireguard.getString("adrw","");
    String end = wireguard.getString("endw","");
    String pub = wireguard.getString("pubw","");
    String pri = wireguard.getString("priw","");


    Log.v("gggggggggggggggggggggg",addr);
    Log.v("gggggggggggggggggggggg",end);

    Log.v("gggggggggggggggggggggg",pub);

    Log.v("gggggggggggggggggggggg",pri);


    String mtu = "1280";
    String ips = "0.0.0.0/0";
    Interface.Builder interfaceBuilder = new Interface.Builder();
    Peer.Builder peerBuilder = new Peer.Builder();


    AsyncTask.execute(new Runnable() {
        @Override
        public void run() {


            try {
                if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {

                    stopService(intent);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtbtn.setText("OFF");
                            //  connection.setBackgroundColor("");
                            imgbtn.setBackgroundResource(R.drawable.circle);
                            txtbtn.setTextColor(Color.rgb(219, 102, 200));
                            card1.setBackgroundResource(R.drawable.df);
                            pingtest.setBackgroundResource(R.drawable.ddd);

                            bbcon2.setBackgroundResource(R.drawable.ddd);
                            btnlogo.setBackgroundResource(R.drawable.ddd);










                        }
                    });

                    backend.setState(tunnel, DOWN, null);
                    mb = false;

                }
else  {
                    ContextCompat.startForegroundService(MainActivity.this, intent);
mb = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtbtn.setText("ON");
                            mb = true;
                            imgbtn.setBackgroundResource(R.drawable.kkk);
                            card1.setBackgroundResource(R.drawable.df);

                            pingtest.setBackgroundResource(R.drawable.greeeeeen);
                            txtbtn.setTextColor(Color.GREEN);


                            bbcon2.setBackgroundResource(R.drawable.qermez);
                            txtbtn.setTextColor(Color.GREEN);
                            btnlogo.setBackgroundResource(R.drawable.sefid);








                        }
                    });


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    });

}




    public void wireguard3(){



        Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();
        wireguard = getSharedPreferences("adrw",MODE_PRIVATE);
        wireguard = getSharedPreferences("endw",MODE_PRIVATE);
        wireguard = getSharedPreferences("pubw",MODE_PRIVATE);
        wireguard = getSharedPreferences("priw",MODE_PRIVATE);

        String addr = wireguard.getString("adrw","");
        String end = wireguard.getString("endw","");
        String pub = wireguard.getString("pubw","");
        String pri = wireguard.getString("priw","");
        String mtu = "1280";
        String ips = "0.0.0.0/0";
        Interface.Builder interfaceBuilder = new Interface.Builder();
        Peer.Builder peerBuilder = new Peer.Builder();


    runOnUiThread(new Runnable() {
        @Override
        public void run() {
            txtbtn.setText("ON");
            imgbtn.setBackgroundResource(R.drawable.kkk);
            card1.setBackgroundResource(R.drawable.df);

            pingtest.setBackgroundResource(R.drawable.greeeeeen);
            txtbtn.setTextColor(Color.GREEN);
        }
    });



    }
    public void wireh(){

        try {
            if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtbtn.setText("ON");
                        mb = true;
                        imgbtn.setBackgroundResource(R.drawable.kkk);
                        card1.setBackgroundResource(R.drawable.df);

                        pingtest.setBackgroundResource(R.drawable.greeeeeen);
                        txtbtn.setTextColor(Color.GREEN);
                        bbcon2.setBackgroundResource(R.drawable.qermez);
                        btnlogo.setBackgroundResource(R.drawable.sefid);

                        mb = true;


                    }
                });



            } else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {







                mb=false;




                        txtbtn.setText("OFF");
                        //  connection.setBackgroundColor("");
                        imgbtn.setBackgroundResource(R.drawable.circle);
                        txtbtn.setTextColor(Color.rgb(219, 102, 200));
                        card1.setBackgroundResource(R.drawable.df);
                        pingtest.setBackgroundResource(R.drawable.ddd);
                        bbcon2.setBackgroundResource(R.drawable.ddd);
                        btnlogo.setBackgroundResource(R.drawable.ddd);

                    }
                });


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void performAction() {


        Intent intent = new Intent(MainActivity.this , vip.class);
        startActivity(intent);
        finish();

    }


    public int longToIntJavaWithMath(long number) {
        return Math.toIntExact(number);
    }
    public String getV2rayServerDelay(String config) {

        try {
            int  server_delay = longToIntJavaWithMath(V2rayCoreExecutor.getConfigDelay(Utilities.normalizeV2rayFullConfig(config)));



            if (server_delay == -1) {
                if (lang) {
                    return " سرور مشکل دارد : " + "🤦‍";

                } else {
                    return "Not Connect: " + "🤦‍";

                }



            } else {
                if (server_delay >= 0 && server_delay < 600) {
                    if (lang) {
                        return "پینگ جت: " + String.valueOf(server_delay);

                    } else {
                        return " ping jet: " + String.valueOf(server_delay);

                    }

                } else if (server_delay >= 600 && server_delay <= 800) {
                    if (lang) {
                        return "پینگ عالی: " + String.valueOf(server_delay);

                    } else {
                        return " ping very good: " + String.valueOf(server_delay);

                    }

                } else {
                    if (server_delay > 800 && server_delay < 1000) {
                        if (lang) {
                            return "پینگ خوب : " + String.valueOf(server_delay);

                        } else {
                            return " ping good : " + String.valueOf(server_delay);

                        }

                    } else if (server_delay >= 1000 && server_delay < 1500) {
                        if (lang) {
                            return "پینگ معمولی: " + String.valueOf(server_delay);

                        } else {
                            return "ping not good : " + String.valueOf(server_delay);

                        }

                    }

                    else if (server_delay==-2) {
                        if (lang) {
                            return " رییید در اینترنت: " + String.valueOf(server_delay);

                        } else {
                            return "error: " + String.valueOf(server_delay);

                        }

                    }

                    else {
                        if (lang) {
                            return "پینگ ضعیف: " + String.valueOf(server_delay);

                        } else {
                            return " ping bad: " + String.valueOf(server_delay);

                        }

                    }

                }

            }
        }catch (Exception ignored){

        }












        return "خطا";
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

