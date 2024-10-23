package com.dabut.purcow;


import static com.dabut.lib.v2ray.V2rayController.getConnectionState;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_INTENT;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT;
import static com.dabut.purcow.MainActivity.isServiceRunning;
import static com.dabut.purcow.R.id.pingg;
import static com.wireguard.android.backend.Tunnel.State.DOWN;
import static com.wireguard.android.backend.Tunnel.State.UP;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dabut.lib.v2ray.V2rayController;
import com.dabut.lib.v2ray.core.V2rayCoreExecutor;
import com.dabut.lib.v2ray.interfaces.LatencyDelayListener;
import com.dabut.lib.v2ray.utils.Utilities;
import com.dabut.lib.v2ray.utils.V2rayConstants;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wireguard.android.backend.Backend;
import com.wireguard.android.backend.GoBackend;
import com.wireguard.android.backend.Tunnel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Servers extends AppCompatActivity {
    SharedPreferences  Pref, pova, linq,linq2, preferences,allping,number,allcolor,makhzan,wireguard,makhzansave,shomaresh;
    SharedPreferences Config;
    ProgressDialog updial, cancelDialog;
    ArrayList<String> vmessLinks, vmessLinks2, vmessLinks3, configarray, sslinks,vmesscount;
    ArrayList<String> person;
    boolean boo = true;


    private ArrayList<Makhzan> colorList;
    private Makhzanadapter adapter2;
    static boolean lang = true;
    private Thread myloopt;

    boolean allpingg = true;
    ProgressDialog progressDoalog;
    public static final String Name2 = "Key";
    public static final String Name = "Key";
    private DrawerLayout drawerLayout;
    private MyCustomAdapter adapter;
    TextView txtmakhzan;
    static boolean net = true;
    ImageView imageView;
    private BroadcastReceiver v2rayBroadCastReceiver;
    public static final String Linq = "Key";
    public static final String Linq2 = "Key";
    static Button button;
    String youngestName;
    static  boolean fff = true;
    public static final String Pova = "Key";

    public static boolean isRecursionEnable = true;
    ExecutorService executorService;
    private Thread myt,http,httpp;

    CountDownLatch latch;
    ArrayList<LinkServer> vmesserver,vmessgreen,vmessort,vmessort2,vmessort3,vmessort4,vmessort5,vmessort6,vmessort7;
    String wwww;
    SharedPreferences serverpref;
    List<Person> people;
    String kook;
    ExecutorService executor,executor4;
    static ExecutorService executor2;


    public String utext;
    public String utext2;
    public String utext3;
    public String connnnn;
    public String utextlink;
    public String trojane;
    static volatile boolean isConnected = false;
    public  String sss;
    public String confffff;
    boolean isLightTheme = true;
    RecyclerView recyclerView;
    public Boolean httprun = false;
    public boolean off = true;
    boolean laghv = false;
    LinearLayout layout;
    ArrayList<String> numberser;
    ArrayList<String> namemakhzan;

    String selectedText;
    Context context;
    private ScheduledExecutorService executor3;
    Backend backend = PersistentConnectionProperties.getInstance().getBackend();

    public boolean poov = false;
    boolean areEqual;
    public String lk;
    static boolean online3 = false;
    static boolean execuer4run = false;

    AlertDialog.Builder builder3;
    int numtask = 5;
    static Activity ref;
    Button  povarbtn, holmzbtn, external;

    @SuppressLint({"WrongConstant", "MissingInflatedId", "WakelockTimeout", "WrongViewCast"})
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences2 = getSharedPreferences("language", MODE_PRIVATE);

        // اگر فایل preferences وجود دارد، مقدار متغیر isLightTheme را از آن بخوانید
        if (preferences2.contains("lang")) {
            lang = preferences2.getBoolean("lang", true);


        }
        preferences = getSharedPreferences("theme", MODE_PRIVATE);
        if (preferences.contains("isLightTheme")) {
            isLightTheme = preferences.getBoolean("isLightTheme", true);
        }
        if (isLightTheme) {
            setTheme(R.style.Theme_V2rayExample);
        } else {
            setTheme(R.style.Theme_V2rayExample2);
        }

        setContentView(R.layout.activity_servers);

        V2rayController.StopV2ray(this);


        try {
            backend.getRunningTunnelNames();
        }
        catch (NullPointerException e) {
            // backend cannot be created without context
            PersistentConnectionProperties.getInstance().setBackend(new GoBackend(this));
            backend = PersistentConnectionProperties.getInstance().getBackend();
        }
        offwire2();
        Intent ii2 = new Intent(Servers.this, MyService.class);
      if(  isServiceRunning( this, MyService.class)){
          stopService(ii2);

      }


        makhzan = getSharedPreferences("makhzan", MODE_PRIVATE);

//
//        Thread internetThread = new Thread(new InternetChecker());
//        internetThread.start();
//
        net = true;
//        Mythread5 myThread5 = new Mythread5();
//        myThread5.start();
//


        executor2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        executor2.shutdownNow();


        // Start the thread



        txtmakhzan = findViewById(R.id.makhzantxt);
        txtmakhzan.setText(makhzan.getString("makhzan" , ""));


        wireguard = getSharedPreferences("adrw",MODE_PRIVATE);
        wireguard = getSharedPreferences("endw",MODE_PRIVATE);
        wireguard = getSharedPreferences("pubw",MODE_PRIVATE);
        wireguard = getSharedPreferences("priw",MODE_PRIVATE);
        makhzansave  = getSharedPreferences("namemakhzan", Context.MODE_PRIVATE);
        makhzansave  = getSharedPreferences("linkmakhzan", Context.MODE_PRIVATE);
        shomaresh  = getSharedPreferences("shomaresh", Context.MODE_PRIVATE);


        context = this;
        Button   loopbtn = findViewById(R.id.loopback);
        NavigationView navigationView = (NavigationView) findViewById(R.id.na4);

        button = findViewById(R.id.button);

        povarbtn = findViewById(R.id.powaro);
        imageView = findViewById(R.id.imageView4);

        external = (Button) findViewById(R.id.external);
        TextView txttime = (TextView) findViewById(R.id.txttime);
        //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (isLightTheme) {
            layout = (LinearLayout) findViewById(R.id.serverley);
            layout.setBackgroundResource(R.drawable.tttt);

            //  external.setBackgroundResource(R.drawable.ddd2);
            button.setBackgroundResource(R.drawable.bcg);
            loopbtn.setBackgroundResource(R.drawable.bcg);
            navigationView.setBackgroundResource(R.drawable.tttt);


        } else {
            layout = (LinearLayout) findViewById(R.id.serverley);
            layout.setBackgroundResource(R.drawable.pink);
            txttime.setTextColor(Color.RED);
            //  external.setBackgroundResource(R.drawable.ddd2);
            // povarbtn.setBackgroundResource(R.drawable.ddd);

            loopbtn.setBackgroundResource(R.drawable.bcg2);


            povarbtn.setBackgroundResource(R.drawable.bcg2);
            external.setBackgroundResource(R.drawable.bcg2);
            button.setBackgroundResource(R.drawable.bcg2);
            navigationView.setBackgroundResource(R.drawable.pink);


        }

        serverpref = getSharedPreferences("allserver", MODE_PRIVATE);



        //  selectedPackages2 = serverpref.getStringSet("allserver" , null);
        number = getSharedPreferences("number", MODE_PRIVATE);
        int nu= number.getInt("number",0);

        vmessLinks = new ArrayList<String>();
        vmessLinks2 = new ArrayList<String>();
        vmessLinks3 = new ArrayList<String>();
        numberser = new ArrayList<String>();
        vmesscount = new ArrayList<String>();
        colorList = new ArrayList<Makhzan>();
        sslinks = new ArrayList<String>();
        people = new ArrayList<>();
        configarray = new ArrayList<String>();

        recyclerView = findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        vmesserver = new ArrayList<LinkServer>();
        navigationView.setItemTextAppearance(R.style.MenuTextStyle);

        vmessgreen = new ArrayList<LinkServer>();
        vmessort = new ArrayList<LinkServer>();
        vmessort2 = new ArrayList<LinkServer>();
        vmessort3 = new ArrayList<LinkServer>();
        vmessort4 = new ArrayList<LinkServer>();
        vmessort5 = new ArrayList<LinkServer>();
        vmessort6 = new ArrayList<LinkServer>();
        vmessort7 = new ArrayList<LinkServer>();

        namemakhzan = new ArrayList<String>();
        colorList.add(new Makhzan("مخزن پورکاو","https://raw.githubusercontent.com/davudsedft/purvpn/refs/heads/main/links/purkow.txt"));

        colorList.add(new Makhzan("مخزن ایرانی","https://raw.githubusercontent.com/barry-far/V2ray-Configs/main/Sub1.txt"));
        colorList.add(new Makhzan("مخزن چینی","https://raw.githubusercontent.com/mahdibland/ShadowsocksAggregator/master/Eternity"));

        String m = shomaresh.getString("shomaresh","0");
        int mm = Integer.parseInt(m);

        for (int i = colorList.size() ; i<mm ; i++){
            colorList.add(new Makhzan(makhzansave.getString("namemakhzan"+i,"bbbb"),makhzansave.getString("linkmakhzan"+i,"bbb")));

        }


        drawerLayout = (DrawerLayout) findViewById(R.id.dl4);
        executor3 = Executors.newSingleThreadScheduledExecutor();

        // Schedule a task to check internet connectivity periodically
        executor3.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {




                if (!(isNetworkAvailable() && checkInternetConnection())){



                    isConnected = false;
                    if (executor2 != null){
                        try {

                            if (!executor2.awaitTermination(1, TimeUnit.SECONDS) && execuer4run) {
                                executor2.shutdownNow();
                             Thread.currentThread().interrupt();


                             runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                     dialogg();
                                     execuer4run = false;
                                 }
                             });

                            }


                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("وصله نیست");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setBackgroundResource(R.drawable.netof);
                            }
                        });
                    }
                }else {
                    isConnected = true;
                    System.out.println("وصلهggggg اینترنت");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setBackgroundResource(R.drawable.neton);
                        }
                    });
                }

//                if (  vmessgreen.size() >0 ){
//
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                adapter.updateList(vmessgreen);
//                                System.out.println("uuuuuuuuuuuuuuuuuuu");
//
//                            }
//                        });
//
//
//
//                }
            }
        }, 0, 2, TimeUnit.SECONDS);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
            @Override
            public boolean onNavigationItemSelected (MenuItem item){
                item.setChecked(false);

                drawerLayout.closeDrawer(Gravity.RIGHT);


//
//                Thread internetThread = new Thread(new InternetChecker());
//                internetThread.start();




                //  executor2.shutdownNow();
                int itemId = item.getItemId();
                if (itemId == pingg) {
                    if (executor2 != null) {
                        executor2.shutdownNow();
                    }


                    if (myt != null && http != null) {
                        if (!myt.isAlive() && !http.isAlive()) {
                            if (button.getText().toString().contains("حال")) {
                                Toast.makeText(context, "دریافت سرور در حال اجراست", Toast.LENGTH_SHORT).show();
                            } else {
                                if (executor2 != null) {

                                    if (executor2.isTerminated()) {


                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                numtask = vmesserver.size();

                                                runping();

                                            }
                                        }, 1000);


                                    } else {
                                        Toast.makeText(context, "در حال بستن پردازش فعلی", Toast.LENGTH_SHORT).show();
                                    }

                                }


                            }

                        } else {
                            Toast.makeText(context, "پوارو در حال اجراست", Toast.LENGTH_SHORT).show();

                        }


                    } else {
                        if (button.getText().toString().contains("حال")) {
                            Toast.makeText(context, "دریافت سرور در حال اجراست", Toast.LENGTH_SHORT).show();

                        } else {
                            if (executor2 != null) {

                                if (executor2.isTerminated()) {


                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            numtask = vmesserver.size();

                                            runping();

                                        }
                                    }, 1000);


                                } else {
                                    Toast.makeText(context, "در حال بستن پردازش فعلی", Toast.LENGTH_SHORT).show();
                                }

                            }


                        }
                    }


                    return true;
                } else if (itemId == R.id.pingg2) {
                    if (executor2 != null) {
                        executor2.shutdownNow();
                    }


                    if (myt != null && http != null) {
                        if (!myt.isAlive() && !http.isAlive()) {

                            if (button.getText().toString().contains("حال")) {
                                Toast.makeText(context, "دریافت سرور در حال اجراست", Toast.LENGTH_SHORT).show();

                            } else {


                                if (executor2 != null) {

                                    if (executor2.isTerminated()) {


                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                numtask = vmesserver.size() / 10;

                                                runping();

                                            }
                                        }, 1000);


                                    } else {
                                        Toast.makeText(context, "در حال بستن پردازش فعلی", Toast.LENGTH_SHORT).show();
                                    }


                                }


                            }


                        } else {
                            Toast.makeText(context, "پوارو در حال اجراست", Toast.LENGTH_SHORT).show();

                        }


                    } else {

                        if (button.getText().toString().contains("حال")) {
                            Toast.makeText(context, "دریافت سرور در حال اجراست", Toast.LENGTH_SHORT).show();

                        } else {


                            if (executor2 != null) {

                                if (executor2.isTerminated()) {


                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            numtask = vmesserver.size() / 10;

                                            runping();

                                        }
                                    }, 1000);


                                } else {
                                    Toast.makeText(context, "در حال بستن پردازش فعلی", Toast.LENGTH_SHORT).show();
                                }


                            }


                        }


                    }


                    return true;
                } else if (itemId == R.id.pingg3) {
                    if (executor2 != null) {
                        executor2.shutdownNow();
                    }


                    if (myt != null && http != null) {
                        if (!myt.isAlive() && !http.isAlive()) {

                            if (button.getText().toString().contains("حال")) {
                                Toast.makeText(context, "دریافت سرور در حال اجراست", Toast.LENGTH_SHORT).show();

                            } else {


                                if (executor2 != null) {

                                    if (executor2.isTerminated()) {


                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                numtask = 5;

                                                runping();

                                            }
                                        }, 1000);


                                    } else {
                                        Toast.makeText(context, "در حال بستن پردازش فعلی", Toast.LENGTH_SHORT).show();
                                    }


                                }


                            }

                        } else {
                            Toast.makeText(context, "پوارو در حال اجراست", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (button.getText().toString().contains("حال")) {
                            Toast.makeText(context, "دریافت سرور در حال اجراست", Toast.LENGTH_SHORT).show();

                        } else {


                            if (executor2 != null) {

                                if (executor2.isTerminated()) {


                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            numtask = 5;

                                            runping();

                                        }
                                    }, 1000);


                                } else {
                                    Toast.makeText(context, "در حال بستن پردازش فعلی", Toast.LENGTH_SHORT).show();
                                }


                            }


                        }

                    }


                    return true;
                } else if (itemId == R.id.pingg4) {
                    if (executor2 != null) {
                        executor2.shutdownNow();
                    }


                    if (button.getText().toString().contains("حال")) {
                        Toast.makeText(context, "دریافت سرور در حال اجراست", Toast.LENGTH_SHORT).show();

                    } else {


                        vmessgreen.clear();

                        vmessort.clear();
                        vmessort2.clear();
                        vmessort3.clear();
                        vmessort4.clear();
                        vmessort5.clear();
                        vmessort6.clear();
                        vmessort7.clear();


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                for (int i = 0; i < vmesserver.size(); i++) {
                                    LinkServer server;
                                    server = vmesserver.get(i);

                                    if (server.ping.contains("پینگ جت")) {
                                        vmessort.add(new LinkServer(server.name, server.fam, server.ping, server.color));
                                    } else if (server.ping.contains("پینگ عالی")) {
                                        vmessort2.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("پینگ خوب")) {
                                        vmessort3.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("پینگ معمولی")) {
                                        vmessort4.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("پینگ ضعیف")) {
                                        vmessort5.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("تست") || server.ping.contains("بررسی")) {
                                        vmessort7.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else {
                                        vmessort6.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    }

                                }


                                vmessort.addAll(vmessort2);
                                vmessort.addAll(vmessort3);
                                vmessort.addAll(vmessort4);
                                vmessort.addAll(vmessort5);
                                vmessort.addAll(vmessort6);
                                vmessort.addAll(vmessort7);

                                vmesserver.clear();
                                vmesserver.addAll(vmessort);


                                for (int j = 0; j < vmesserver.size(); j++) {
                                    LinkServer server;
                                    server = vmesserver.get(j);
                                    allping = getSharedPreferences("allping", MODE_PRIVATE);
                                    allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                                    SharedPreferences.Editor editor = allping.edit();
                                    editor.putString("allping" + Integer.toString(j), server.ping);
                                    editor.apply();
                                    serverpref = getSharedPreferences("allserver", MODE_PRIVATE);

                                    SharedPreferences.Editor editor2 = serverpref.edit();
                                    editor2.putString("allserver" + Integer.toString(j), server.fam);
                                    editor2.apply();


                                    SharedPreferences.Editor editor4 = allcolor.edit();

                                    editor4.putInt("allcolor" + Integer.toString(j), server.color);
                                    editor4.apply();


                                }

                                adapter.notifyDataSetChanged();
                                recyclerView.smoothScrollToPosition(0);


                            }
                        }, 2000);


                    }


                    return true;
                } else if (itemId == R.id.pingg5) {
                    if (executor2 != null) {
                        executor2.shutdownNow();
                    }


                    if (button.getText().toString().contains("حال")) {
                        Toast.makeText(context, "دریافت سرور در حال اجراست", Toast.LENGTH_SHORT).show();

                    } else {


                        vmessgreen.clear();

                        vmessort.clear();
                        vmessort2.clear();
                        vmessort3.clear();
                        vmessort4.clear();
                        vmessort5.clear();
                        vmessort6.clear();
                        vmessort7.clear();


                        int kk = vmesserver.size();
                        vmessort6.addAll(vmesserver);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                for (int i = 0; i < vmessort6.size(); i++) {
                                    LinkServer server;
                                    server = vmessort6.get(i);


                                    if (server.ping.contains("مشکل")) {

                                        SharedPreferences.Editor editor = allping.edit();
                                        editor.remove("allping" + Integer.toString(i));
                                        editor.apply();

                                        SharedPreferences.Editor editor2 = serverpref.edit();
                                        editor2.remove("allserver" + Integer.toString(i));
                                        editor2.apply();

                                        SharedPreferences.Editor editor4 = allcolor.edit();

                                        editor4.remove("allcolor" + Integer.toString(i));
                                        editor4.apply();
                                    }


                                    if (server.ping.contains("پینگ جت")) {
                                        vmessort.add(new LinkServer(server.name, server.fam, server.ping, server.color));
                                    } else if (server.ping.contains("پینگ عالی")) {
                                        vmessort2.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("پینگ خوب")) {
                                        vmessort3.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("پینگ معمولی")) {
                                        vmessort4.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("پینگ ضعیف")) {
                                        vmessort5.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("تست") || server.ping.contains("بررسی")) {
                                        vmessort7.add(new LinkServer(server.name, server.fam, "تست پینگ", server.color));

                                    }

                                }


                                vmessort.addAll(vmessort2);
                                vmessort.addAll(vmessort3);
                                vmessort.addAll(vmessort4);

                                vmessort.addAll(vmessort5);

                                vmessort.addAll(vmessort7);

                                vmesserver.clear();
                                vmesserver.addAll(vmessort);


                                for (int j = 0; j < vmesserver.size(); j++) {
                                    LinkServer server;
                                    server = vmesserver.get(j);
                                    allping = getSharedPreferences("allping", MODE_PRIVATE);
                                    allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                                    SharedPreferences.Editor editor = allping.edit();
                                    editor.putString("allping" + Integer.toString(j), server.ping);
                                    editor.apply();
                                    serverpref = getSharedPreferences("allserver", MODE_PRIVATE);

                                    SharedPreferences.Editor editor2 = serverpref.edit();
                                    editor2.putString("allserver" + Integer.toString(j), server.fam);
                                    editor2.apply();


                                    SharedPreferences.Editor editor4 = allcolor.edit();

                                    editor4.putInt("allcolor" + Integer.toString(j), server.color);
                                    editor4.apply();


                                    // vmesserver.add(new LinkServer(server.name,server.fam,server.ping,server.color));

                                }


                                number = getSharedPreferences("number", MODE_PRIVATE);
                                SharedPreferences.Editor editor3 = number.edit();
                                editor3.putInt("number", vmesserver.size());
                                editor3.apply();


                                adapter.notifyDataSetChanged();
                                recyclerView.smoothScrollToPosition(0);


                            }
                        }, 2000);


                    }


                    return true;
                } else if (itemId == R.id.pingg6) {
                    if (executor2 != null) {
                        executor2.shutdownNow();
                    }


                    vmessgreen.clear();

                    vmessort.clear();
                    vmessort2.clear();
                    vmessort3.clear();
                    vmessort4.clear();
                    vmessort5.clear();
                    vmessort6.clear();
                    vmessort7.clear();


                    int kk = vmesserver.size();
                    vmessort6.addAll(vmesserver);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            // vmesserver.add(new LinkServer(server.name,server.fam,server.ping,server.color));


                            if (vmesserver.size() == 0) {
                                Toast.makeText(Servers.this, "سروری یافت نشد", Toast.LENGTH_SHORT).show();

                            } else {
                                StringBuilder result = new StringBuilder();
                                for (int i = 0; i < vmessort6.size(); i++) {
                                    LinkServer server;
                                    server = vmessort6.get(i);


                                    if (server.ping.contains("پینگ جت")) {
                                        vmessort.add(new LinkServer(server.name, server.fam, server.ping, server.color));
                                    } else if (server.ping.contains("پینگ عالی")) {
                                        vmessort2.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("پینگ خوب")) {
                                        vmessort3.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    } else if (server.ping.contains("پینگ معمولی")) {
                                        vmessort4.add(new LinkServer(server.name, server.fam, server.ping, server.color));

                                    }

                                }


                                vmessort.addAll(vmessort2);
                                vmessort.addAll(vmessort3);
                                vmessort.addAll(vmessort4);

                                ArrayList<LinkServer> n = new ArrayList<>(vmessort);

                                for (int m = 0; m < n.size(); m++) {
                                    LinkServer server;
                                    server = vmesserver.get(m);
                                    result.append(server.fam).append("\n");


                                }
                                String finalResult = result.toString();

                                ClipboardManager _clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                _clipboard.setText(finalResult);
                                // Toast.makeText(Servers.this, "کپی در کلیپ بورد", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Servers.this, "سرورهای سالم کپی شد!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, 2000);


                    return true;
                }

                return false;
            }

        });




        if (number.contains("number")){

            MyThread4 myThread = new MyThread4();
            myThread.start();


        }



        adapter = new MyCustomAdapter( vmesserver);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);
//       // getVmessLinks();




        adapter.pinlisenr(new MyCustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {



                        LinkServer server;
                        server = vmesserver.get(i);


                        String    selectedText=    server.fam;


                        vmesserver.set(i,new LinkServer(server.name,server.fam,"در حال بررسی",Color.YELLOW));


                        // Toast.makeText(Servers.this, "کپی در کلیپ بورد", Toast.LENGTH_SHORT).show();
                        runOnUiThread(() -> adapter.notifyItemChanged(i));

                        if (selectedText.startsWith("ss://")){
                            selectedText = sss(selectedText);

                        }

                        if (selectedText.startsWith("wireguard://")){

                            selectedText = selectedText.replace("wireguard://" ,"");
                            String[]  url2 = selectedText.split("@");
                            String pri = url2[0];


                            String[] end = url2[1].split("\\?");

                            String[] ip =  end[0].split("]:");
                            String  port = ip[1];
                            String ipp = ip[0].replace("[","");


                            long startTime = System.currentTimeMillis();

                            try {
                                Socket socket = new Socket(ipp, Integer.parseInt(port));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            long endTime = System.currentTimeMillis();
                            long delay = endTime - startTime;
                            if (delay >= 0 && delay < 600) {
                                wwww =  "پینگ جت" + Long.toString(delay);

                            } else if (delay >= 600 && delay < 800) {
                                wwww =  "پینگ عالی" + Long.toString(delay);

                            } else if (delay >= 800 && delay < 1000) {

                                wwww =  "پینگ معمولی" + Long.toString(delay);

                            } else if (delay >= 1000 && delay < 2000) {

                                wwww =  "پینگ ضعیف" + Long.toString(delay);
                            }else  {

                                wwww =  "سرور مشکل دارد" + Long.toString(delay);
                            }



                        }else{
                            wwww =  getV2rayServerDelay(selectedText);

                        }


                        if (wwww.contains("پینگ خوب") || wwww.contains("پینگ جت")|| wwww.contains("پینگ عالی")){

                            vmesserver.set(i,new LinkServer(server.name,server.fam,wwww,Color.GREEN));




                            allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                            SharedPreferences.Editor e = allcolor.edit();
                            e.putInt("allcolor"+Integer.toString(i) , Color.GREEN);
                            e.apply();
                            allping = getSharedPreferences("allping", MODE_PRIVATE);

                            SharedPreferences.Editor el = allping.edit();
                            el.putString("allping"+Integer.toString(i) , wwww);
                            el.apply();

                        } else if (wwww.contains("معمولی")) {

                            vmesserver.set(i,new LinkServer(server.name,server.fam,wwww,Color.rgb(255,128,0)));




                            allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                            SharedPreferences.Editor e = allcolor.edit();
                            e.putInt("allcolor"+Integer.toString(i) , Color.rgb(255,128,0));
                            e.apply();
                            allping = getSharedPreferences("allping", MODE_PRIVATE);

                            SharedPreferences.Editor el = allping.edit();
                            el.putString("allping"+Integer.toString(i) , wwww);
                            el.apply();


                        }else if (wwww.contains("ضعیف")) {

                            vmesserver.set(i,new LinkServer(server.name,server.fam,wwww,Color.rgb(197,38,59)));




                            allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                            SharedPreferences.Editor e = allcolor.edit();
                            e.putInt("allcolor"+Integer.toString(i) , Color.rgb(197,38,59));
                            e.apply();
                            allping = getSharedPreferences("allping", MODE_PRIVATE);

                            SharedPreferences.Editor el = allping.edit();
                            el.putString("allping"+Integer.toString(i) , wwww);
                            el.apply();


                        }

                        else {
                            vmesserver.set(i,new LinkServer(server.name,server.fam,wwww,Color.YELLOW));
                            allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                            SharedPreferences.Editor e = allcolor.edit();
                            e.putInt("allcolor"+Integer.toString(i) , Color.YELLOW);
                            e.apply();
                            allping = getSharedPreferences("allping", MODE_PRIVATE);

                            SharedPreferences.Editor el = allping.edit();
                            el.putString("allping"+Integer.toString(i) , wwww);
                            el.apply();

                        }



                        runOnUiThread(() -> adapter.notifyItemChanged(i));

                    }
                }).start();









            }
        });
        adapter.setOnItemClickListener(new MyCustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {

                LinkServer server;
                server = vmesserver.get(i);

                String    selectedText=    server.fam;
                String    nm=    server.name;

                SharedPreferences   Pref = getSharedPreferences("PrefFile", Context.MODE_PRIVATE);
                SharedPreferences   Config = getSharedPreferences("config", Context.MODE_PRIVATE);

                if (selectedText.startsWith("wireguard")){

                    switch (V2rayController.getConnectionState()) {
                        case CONNECTED:
                           V2rayController.stopV2ray(Servers.this);
                            break;
                        case DISCONNECTED:
                            try {
                                warp(selectedText);

                            }catch (Exception e){

                            }


                            break;


                    }


                }else{

                    if (selectedText.startsWith("ss://")){
                        selectedText = sss(selectedText);

                    }


                    SharedPreferences.Editor shEdit = Pref.edit();
                    shEdit.putString(Name, selectedText);
                    shEdit.apply();
                    // V2rayController.StopV2ray(getApplicationContext());
                    SharedPreferences.Editor shEdit2 = Config.edit();
                    //  shEdit2.remove(Name2);
                    shEdit2.putString(Name2, nm);
                    shEdit2.apply();
                    Intent ii = new Intent(Servers.this, MainActivity.class);
                    ii.putExtra("kool", "rovshan");
                    ii.putExtra("copyte", selectedText);

                    startActivity(ii);
                    finish();
                    Toast.makeText(Servers.this, getString(R.string.done), Toast.LENGTH_SHORT).show();



                }



            }
        });

        adapter.imglisenner(new MyCustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {



                AlertDialog.Builder alertDialogBuilder;
                if (isLightTheme) {


                    alertDialogBuilder = new AlertDialog.Builder(
                            Servers.this, R.style.MyDialog);

                } else {

                    alertDialogBuilder = new AlertDialog.Builder(
                            Servers.this, R.style.MyDialog3);

                }


                // set title

                // set dialog message
                alertDialogBuilder
                        .setIcon(R.drawable.yel)
                        .setCancelable(true)
                        .setPositiveButton(getString(R.string.copy), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                LinkServer server;
                                server = vmesserver.get(i);



                                ClipboardManager _clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                _clipboard.setText(server.fam);
                                // Toast.makeText(Servers.this, "کپی در کلیپ بورد", Toast.LENGTH_SHORT).show();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(getBaseContext(), " کپی در کلیپ بورد", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                shareIntent.setType("text/plain");
                                shareIntent.putExtra(Intent.EXTRA_TEXT, server.fam);

                                // نمایش لیست نرم‌افزارهای اجتماعی برای اشتراک‌گذاری
                                startActivity(Intent.createChooser(shareIntent, "اشتراک‌گذاری با"));


                            }

                        }).setNeutralButton("اشتراک با qrcode", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                LinkServer server;
                                server = vmesserver.get(i);
                                Bitmap qrCodeBitmap = generateQRCode(server.fam);
                                // Show the QR Code in a dialog
                                showQRCodeDialog(qrCodeBitmap);
                            }

                        });


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

















            }
        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (button.getText().toString().contains("حال") || !executor2.isTerminated()) {
//
//               //     Toast.makeText(getApplicationContext(), "لطفاً صبر کنید...", Toast.LENGTH_SHORT).show();
//                    recyclerView.setOnTouchListener(null);
//
//                }
//            }
//        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                showColorDialog3();
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // V2rayController.StopV2ray(getApplicationContext());
                if (executor2 != null){
                    executor2.shutdownNow();

                }


                if (isLightTheme) {
                    progressDoalog = new ProgressDialog(Servers.this, R.style.MyDialog);


                } else {
                    progressDoalog = new ProgressDialog(Servers.this, R.style.MyDialog3);

                }






                    if (executor2.isTerminated()) {
                        executor4= Executors.newFixedThreadPool(1);



                        if (!button.getText().toString().contains("حال")) {


                            showColorDialog2();




                        } else {
                            Toast.makeText(context, "صبر کنید..", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(context, "در حال بستن پردازش فعلی", Toast.LENGTH_SHORT).show();
                    }














            }

        });
        // tt.setTextColor(Color.YELLOW);


////////////////////////

//        povarbtn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                if (executor != null){
//                    executor.shutdownNow();
//                    Thread.currentThread().interrupt();
//                }
//                if (isNetworkAvailable()) {
//
//               f
//
//                    link();
//
//
//                    // show();
//                } else {
//                    Toast.makeText(Servers.this, getString(R.string.barresinet), Toast.LENGTH_SHORT).show();
//                }
//
//                return true;
//            }
//        });


/////////////////////

        pova = getSharedPreferences("pova", Context.MODE_PRIVATE);

        if (pova.contains(Pova)) {


            pova.getString(Pova, null);

        }

        linq = getSharedPreferences("linq", Context.MODE_PRIVATE);

        if (linq.contains(Linq)) {


            linq.getString(Linq, null);

        }
        linq2 = getSharedPreferences("linq2", Context.MODE_PRIVATE);

        if (linq2.contains(Linq2)) {


            linq2.getString(Linq2, null);

        }


        time();
        Pref = getSharedPreferences("PrefFile", Context.MODE_PRIVATE);

        if (Pref.contains(Name)) {


            Pref.getString(Name, null);

        }
        Config = getSharedPreferences("config", Context.MODE_PRIVATE);
        if (Config.contains(Name2)) {


            Config.getString(Name2, null);

        }















    /*    updial.setMessage("  کمی صبر کنین....");
        updial.show();*/

    /*    StringRequest request = new StringRequest("http://purnet.ir/vpn//json/new.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                info(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "مشکلی رخ داد دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
               gooop.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Servers.this);
        rQueue.add(request);

*/


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("PurCow");
        Config = getSharedPreferences("config", Context.MODE_PRIVATE);

        if (Config.contains(Name2)) {


            Config.getString(Name2, null);


        }


    }

//    private void showCustomDialog() {
//
//
//
//
//
//
//        builder.setTitle(getString(R.string.bakhshberuz))
//                .setCancelable(true)
//
//                .setItems(new CharSequence[]{getString(R.string.serverp),  getString(R.string.etc) ,  getString(R.string.servert) , "مخزن وارپ وایرگارد"},
//                      new   DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // نمایش پیام Toast بر اساس گزینه انتخاب شده
//                                switch (which) {
//                                    case 0:
//
//
//                                        if (isConnected) {
//                                            button.setText("درحال دریافت سرورها...");
//                                            button.setTextColor(Color.RED);
//
//                                            Timer timer = new Timer();
//                                            //  new RetrieveFeedTask().execute("https://www.google.com");
//
//                                            // Schedule a task to run after 3 seconds
//                                            timer.schedule(new TimerTask() {
//                                                @Override
//                                                public void run() {
//
//                                                    executor4.submit(() -> {
//
//
//                                                        SharedPreferences.Editor et = linq2.edit();
//                                                        String jooo = "https://api.github.com/repos/davudsedft/purcow/contents/purkow.txt";
//
//
//
//
//
//                                                        et.putString(Linq2, jooo);
//                                                        et.apply();
//
//
//
//                                                        vmesserver.clear();
//                                                        vmessgreen.clear();
//
//                                                        number = getSharedPreferences("number", MODE_PRIVATE);
//                                                        SharedPreferences.Editor edito = number.edit();
//                                                        edito.putInt("number", vmesserver.size());
//                                                        edito.apply();
//
//                                                        runOnUiThread(() -> {
//
//                                                            adapter.notifyDataSetChanged();
//
//                                                        });
//
//
//                                                        try {
//                                                            Thread.sleep(1000);
//                                                        } catch (InterruptedException e) {
//                                                            throw new RuntimeException(e);
//                                                        }
//                                                        MyThread myThread = new MyThread();
//                                                        myThread.start();
//
//
//                                                    });
//
//                                                    executor4.shutdown();
//
//
//                                                }
//                                            }, 2000);
//
//                                        } else {
//                                            Toast.makeText(context, "اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
//                                        }
//
//
//
//
//                                        break;
//                                    case 1:
//
//
//
//                                        if (isConnected) {
//
//
//
//
//                                            button.setText("درحال دریافت سرورها...");
//                                            button.setTextColor(Color.RED);
//                                            Timer timer = new Timer();
//                                            // new RetrieveFeedTask().execute("https://www.google.com");
//
//                                            // Schedule a task to run after 3 seconds
//                                            timer.schedule(new TimerTask() {
//                                                @Override
//                                                public void run() {
//
//
//
//                                                    executor4.submit(() -> {
//
//                                                        String url = "https://api.github.com/repos/davudsedft/purcow/contents/iranian.txt";
//
//                                                        if (isLinkValid("https://github.com")) {
//                                                            System.out.println("لینک معتبر است.");
//
//
//                                                            URL textUrl = null;
//                                                            String utext5 = null;
//
//                                                            try {
//                                                                textUrl = new URL(url);
//
//                                                                HttpURLConnection connection = null;
//
//                                                                connection = (HttpURLConnection) textUrl.openConnection();
//                                                                connection.setConnectTimeout(5000);
//                                                                connection.setReadTimeout(5000);
//                                                                connection.setRequestMethod("GET");
//                                                                connection.setRequestProperty("Authorization", "token " + token);
//                                                                connection.setRequestProperty("Accept", "application/vnd.github.v3.raw");
//                                                                InputStream inputStream = connection.getInputStream();
//                                                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                                                                StringBuilder stringBuilder = new StringBuilder();
//                                                                String line;
//                                                                while ((line = reader.readLine()) != null) {
//                                                                    stringBuilder.append(line).append("\n");
//                                                                }
//                                                                reader.close();
//
//                                                                utext5 = stringBuilder.toString();
//
//
//                                                            } catch (Exception ignored) {
//
//                                                            }
//
//                                                            System.out.println("سرور چینی");
//
//
//                                                            // String[] linkstring = utext.split("https://");
//                                                            if (utext5 != null) {
//
//
//                                                                linq2 = getSharedPreferences("linq2", Context.MODE_PRIVATE);
//                                                                SharedPreferences.Editor iran2edit = linq2.edit();
//
//                                                                iran2edit.putString(Linq2, utext5);
//                                                                iran2edit.apply();
//
//
//
//
//                                                                try {
//                                                                    Thread.sleep(1000);
//                                                                } catch (InterruptedException e) {
//                                                                    throw new RuntimeException(e);
//                                                                }
//
//
//
//
//                                                                vmesserver.clear();
//                                                                vmessgreen.clear();
//
//                                                                number = getSharedPreferences("number", MODE_PRIVATE);
//                                                                SharedPreferences.Editor edito = number.edit();
//                                                                edito.putInt("number", vmesserver.size());
//                                                                edito.apply();
//                                                                runOnUiThread(() -> {
//
//                                                                    adapter.notifyDataSetChanged();
//
//                                                                });
//
//
//                                                                MyThread myThread = new MyThread();
//                                                                myThread.start();
//
//
//                                                            } else {
//
//                                                                runOnUiThread(() -> {
//
//                                                                    button.setText("اینترنت لحظه ای پرید");
//                                                                    button.setTextColor(Color.rgb(153, 153, 0));
//
//                                                                });
//
//
//                                                            }
//
//
//                                                        } else {
//                                                            System.out.println("لینک معتبر نیست یا وجود ندارد.");
//                                                            runOnUiThread(() -> {
//                                                                button.setText("خطا در دریافت");
//
//                                                            });
//
//
//                                                        }
//
//
//                                                    });
//                                                    executor4.shutdown();
//
//
//                                                }
//                                            }, 2000);
//
//
//                                        } else {
//                                            Toast.makeText(context, "اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
//                                        }
//
//
//                                        break;
//                                    case 2:
//
//
//
//                                        if (isConnected) {
//                                            button.setText("درحال دریافت سرورها...");
//                                            button.setTextColor(Color.RED);
//
//
//
//                                            Timer timer = new Timer();
//                                            //  new RetrieveFeedTask().execute("https://www.google.com");
//
//                                            // Schedule a task to run after 3 seconds
//                                            timer.schedule(new TimerTask() {
//                                                @Override
//                                                public void run() {
//
//                                                    executor4.submit(() -> {
//
//                                                        String url = "https://api.github.com/repos/davudsedft/purcow/contents/chini2.txt";
//
//
//
//                                                        if (isLinkValid("https://github.com")) {
//                                                            System.out.println("لینک معتبر است.");
//
//                                                            String utext5 = null;
//
//
//                                                            URL textUrl = null;
//                                                            try {
//                                                                textUrl = new URL(url);
//                                                                HttpURLConnection connection = null;
//
//
//                                                                connection = (HttpURLConnection) textUrl.openConnection();
//                                                                connection.setConnectTimeout(5000);
//                                                                connection.setReadTimeout(5000);
//                                                                connection.setRequestMethod("GET");
//                                                                connection.setRequestProperty("Authorization", "token " + token);
//                                                                connection.setRequestProperty("Accept", "application/vnd.github.v3.raw");
//
//                                                                InputStream inputStream = connection.getInputStream();
//                                                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                                                                StringBuilder stringBuilder = new StringBuilder();
//                                                                String line;
//                                                                while ((line = reader.readLine()) != null) {
//                                                                    stringBuilder.append(line).append("\n");
//                                                                }
//                                                                reader.close();
//                                                                utext5 = stringBuilder.toString();
//                                                            } catch (Exception ignored) {
//
//                                                            }
//
//
//                                                            if (utext5 != null) {
//
//
//                                                                System.out.println("سرور چینی");
//
//
//                                                                // String[] linkstring = utext.split("https://");
//
//
//                                                                linq2 = getSharedPreferences("linq2", Context.MODE_PRIVATE);
//                                                                SharedPreferences.Editor iran2edit = linq2.edit();
//
//                                                                iran2edit.putString(Linq2, utext5);
//                                                                iran2edit.apply();
//
//
//
//                                                                try {
//                                                                    Thread.sleep(1000);
//                                                                } catch (InterruptedException e) {
//                                                                    throw new RuntimeException(e);
//                                                                }
//
//
//
//
//
//                                                                vmesserver.clear();
//                                                                vmessgreen.clear();
//
//                                                                number = getSharedPreferences("number", MODE_PRIVATE);
//                                                                SharedPreferences.Editor edito = number.edit();
//                                                                edito.putInt("number", vmesserver.size());
//                                                                edito.apply();
//
//                                                                runOnUiThread(() -> {
//
//                                                                    adapter.notifyDataSetChanged();
//
//                                                                });
//
//                                                                try {
//                                                                    Thread.sleep(1000);
//                                                                } catch (InterruptedException e) {
//                                                                    throw new RuntimeException(e);
//                                                                }
//
//
//                                                                MyThread myThread = new MyThread();
//                                                                myThread.start();
//
//                                                            } else {
//
//
//                                                                runOnUiThread(() -> {
//
//                                                                    button.setText("اینترنت لحظه ای پرید");
//                                                                    button.setTextColor(Color.rgb(153, 153, 0));
//                                                                });
//
//
//                                                            }
//
//                                                        } else {
//                                                            System.out.println("لینک معتبر نیست یا وجود ندارد.");
//
//
//                                                            runOnUiThread(() -> {
//                                                                button.setText("خطا در دریافت");
//
//                                                            });
//
//
//                                                        }
//
//
//                                                    });
//
//
//                                                    executor4.shutdown();
//
//
//                                                }
//                                            }, 2000);
//
//                                        } else {
//                                            Toast.makeText(context, "اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
//                                        }
//
//
//                                        break;
//                                    case 3:
//
//                                        if (android.os.Build.VERSION.SDK_INT > 28){
//
//                                            if (isConnected) {
//                                                button.setText("درحال دریافت سرورها...");
//                                                button.setTextColor(Color.RED);
//
//
//
//                                                Timer timer = new Timer();
//                                                //  new RetrieveFeedTask().execute("https://www.google.com");
//
//                                                // Schedule a task to run after 3 seconds
//                                                timer.schedule(new TimerTask() {
//                                                    @Override
//                                                    public void run() {
//
//                                                        executor4.submit(() -> {
//
//                                                            String url = "https://api.github.com/repos/davudsedft/purcow/contents/warp.txt";
//
//
//
//
//
//                                                            if (isLinkValid("https://github.com")) {
//                                                                System.out.println("لینک معتبر است.");
//
//                                                                String utext5 = null;
//
//
//                                                                URL textUrl = null;
//                                                                try {
//                                                                    textUrl = new URL(url);
//                                                                    HttpURLConnection connection = null;
//
//
//                                                                    connection = (HttpURLConnection) textUrl.openConnection();
//                                                                    connection.setConnectTimeout(5000);
//                                                                    connection.setReadTimeout(5000);
//
//                                                                    connection.setRequestMethod("GET");
//                                                                    connection.setRequestProperty("Authorization", "token " + token);
//                                                                    connection.setRequestProperty("Accept", "application/vnd.github.v3.raw");
//                                                                    InputStream inputStream = connection.getInputStream();
//                                                                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                                                                    StringBuilder stringBuilder = new StringBuilder();
//                                                                    String line;
//                                                                    while ((line = reader.readLine()) != null) {
//                                                                        stringBuilder.append(line).append("\n");
//                                                                    }
//                                                                    reader.close();
//                                                                    utext5 = stringBuilder.toString();
//                                                                } catch (Exception ignored) {
//
//                                                                }
//
//
//                                                                if (utext5 != null) {
//
//
//                                                                    System.out.println("سرور چینی");
//
//
//                                                                    // String[] linkstring = utext.split("https://");
//
//
//                                                                    linq2 = getSharedPreferences("linq2", Context.MODE_PRIVATE);
//                                                                    SharedPreferences.Editor iran2edit = linq2.edit();
//
//                                                                    iran2edit.putString(Linq2, utext5);
//                                                                    iran2edit.apply();
//
//
//
//                                                                    try {
//                                                                        Thread.sleep(1000);
//                                                                    } catch (InterruptedException e) {
//                                                                        throw new RuntimeException(e);
//                                                                    }
//
//
//
//
//
//                                                                    vmesserver.clear();
//                                                                    vmessgreen.clear();
//
//                                                                    number = getSharedPreferences("number", MODE_PRIVATE);
//                                                                    SharedPreferences.Editor edito = number.edit();
//                                                                    edito.putInt("number", vmesserver.size());
//                                                                    edito.apply();
//
//                                                                    runOnUiThread(() -> {
//
//                                                                        adapter.notifyDataSetChanged();
//
//                                                                    });
//
//                                                                    try {
//                                                                        Thread.sleep(1000);
//                                                                    } catch (InterruptedException e) {
//                                                                        throw new RuntimeException(e);
//                                                                    }
//
//
//                                                                    MyThread myThread = new MyThread();
//                                                                    myThread.start();
//
//                                                                } else {
//
//
//                                                                    runOnUiThread(() -> {
//
//                                                                        button.setText("اینترنت لحظه ای پرید");
//                                                                        button.setTextColor(Color.rgb(153, 153, 0));
//                                                                    });
//
//
//                                                                }
//
//                                                            } else {
//                                                                System.out.println("لینک معتبر نیست یا وجود ندارد.");
//
//
//                                                                runOnUiThread(() -> {
//                                                                    button.setText("خطا در دریافت");
//
//                                                                });
//
//
//                                                            }
//
//
//                                                        });
//
//
//                                                        executor4.shutdown();
//
//
//                                                    }
//                                                }, 2000);
//
//                                            } else {
//                                                Toast.makeText(context, "اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
//                                            }
//
//
//                                        }else {
//                                            Toast.makeText(context, " ورژن اندروید پایین است", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                        break;
//                                    case 4:
//
//                                        break;
//                                }
//                            }
//                        });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//
//
//    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadser() {


        new Thread(() -> {
            loadser2();


        }).start();





    }
/////
    ////
    //qrcode
private Bitmap generateQRCode(String input) {
    try {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(input, BarcodeFormat.QR_CODE, 300, 300);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
      //  Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bitmap.setPixel(x, y, bitMatrix.get(x, y) ? getResources().getColor(R.color.black) : getResources().getColor(R.color.light_blue_200));
            }
        }
        return bitmap;
    } catch (WriterException e) {
        e.printStackTrace();
        return null;
    }
}


    private void showQRCodeDialog(Bitmap qrCodeBitmap) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_qr_code);
        ImageView qrCodeImageView = dialog.findViewById(R.id.qrCodeImageView);
        qrCodeImageView.setImageBitmap(qrCodeBitmap);
        dialog.show();
    }







    //qrcode
    @SuppressLint("NewApi")
    private void loadser2() {

        allping = getSharedPreferences("allping", MODE_PRIVATE);

        allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

        serverpref = getSharedPreferences("allserver", MODE_PRIVATE);
        number = getSharedPreferences("number", MODE_PRIVATE);

        int num = number.getInt("number",0);

        int u =0;

        for (int i = 0; i<num; i++){



            String j = allping.getString("allping"+Integer.toString(i) ,"تست پینگ");
            String k = serverpref.getString("allserver"+Integer.toString(i),null);



            int col = allcolor.getInt("allcolor"+Integer.toString(i) , Color.YELLOW);

            assert k != null;
            if (k.startsWith("ss://")){
                vmesserver.add(new LinkServer("سرور شادوساکس"+shsdowipv(k)+Integer.toString(i+1),k,j,col));

            }
            if (k.startsWith("wireguard://")){
                vmesserver.add(new LinkServer("سرور وارپ"+wireguardipv(k)+Integer.toString(i+1),k,j,col));

            }
            if (k.startsWith("vless://")){
                vmesserver.add(new LinkServer("سرور ویلس"+checkIPVersion(k)+Integer.toString(i+1),k,j,col));

            }
            if (k.startsWith("vmess://")){
                vmesserver.add(new LinkServer("سرور ویمس"+checkIPVerion(k)+Integer.toString(i+1),k,j,col));

            }
            if (k.startsWith("trojan://")){
                vmesserver.add(new LinkServer("سرور تروجان"+chechtrojanipv(k)+Integer.toString(i+1),k,j,col));

            }


            //  vmesserver.add(new LinkServer("سرور تروجان"+Integer.toString(i+1),k,j,col));

            //  vmesserver.add( new LinkServer("سرورهای اسکن شده"+Integer.toString(i+1),k,j,col));


        }






    }

private  void warp(String s){
    try {

        String url = s;


        url = url.replace("wireguard://" ,"");
        String[]  url2 = url.split("@");
        String pri = url2[0];

        try {
            pri = URLDecoder.decode(pri, "UTF-8");
        } catch (UnsupportedEncodingException e22) {
            e22.printStackTrace();
        }

        String[] end = url2[1].split("\\?");

        String[] address = url.split("address=");
        String[] address2 = address[1].split("&reserved=");
        String addreslast = address2[0];


        try {
            addreslast = URLDecoder.decode(addreslast, "UTF-8");
        } catch (UnsupportedEncodingException e22) {
            e22.printStackTrace();
        }



        String[] pub = url.split("publickey=");
        String[] pub2 = pub[1].split("&mtu");

        String lastpub = pub2[0];

        try {
            lastpub = URLDecoder.decode(lastpub, "UTF-8");
        } catch (UnsupportedEncodingException e22) {
            e22.printStackTrace();
        }


        wireguard = getSharedPreferences("adrw",MODE_PRIVATE);
        wireguard = getSharedPreferences("endw",MODE_PRIVATE);
        wireguard = getSharedPreferences("pubw",MODE_PRIVATE);
        wireguard = getSharedPreferences("priw",MODE_PRIVATE);

        SharedPreferences.Editor g = wireguard.edit();
        g.putString("endw",end[0]);
        g.putString("pubw",lastpub);
        g.putString("priw",pri);
        g.putString("adrw",addreslast);
        g.apply();
        SharedPreferences.Editor shEdit = Pref.edit();
        shEdit.putString(Name, "wireguard");
        shEdit.apply();
        // V2rayController.StopV2ray(getApplicationContext());
        SharedPreferences.Editor shEdit2 = Config.edit();
        //  shEdit2.remove(Name2);
        shEdit2.putString(Name2, "wireguard");
        shEdit2.apply();
        Intent ii = new Intent(Servers.this, MainActivity.class);
        ii.putExtra("kool", "rovshan2");

        startActivity(ii);
        finish();
        Toast.makeText(Servers.this, "وارپ اعمال شد", Toast.LENGTH_SHORT).show();


    }catch (Exception e){
        Toast.makeText(context, "کانفگ ایراد دارد", Toast.LENGTH_SHORT).show();
    }







}

    public String wireguard2(String url) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);


      String m =  wireguardipv(url);



        System.out.println("3333333333333333333333333333");




            url = url.replace("wireguard://" ,"");
            String[]  url2 = url.split("@");
            String pri = url2[0];


            String[] end = url2[1].split("\\?");

            String port="1";
            String ipp = "";
        if (m.equals("(IPV6)")){
            String[] ip =  end[0].split("]:");
             port = ip[1];
             ipp = ip[0].replace("[","");
        }else {
            String[] ip =  end[0].split(":");
             port = ip[1];
             ipp = ip[0];
        }


            long startTime = System.currentTimeMillis();
            boolean isReachable = false;

        try (Socket socket = new Socket(ipp, Integer.parseInt(port))) {
            isReachable = true;
            long endTime = System.currentTimeMillis();
            long delay = endTime - startTime;
            String out = " ";
            if (delay >= 0 && delay < 600) {
                out =  "پینگ جت" + Long.toString(delay);

            } else if (delay >= 600 && delay < 800) {
                out =  "پینگ عالی" + Long.toString(delay);

            } else if (delay >= 800 && delay < 1000) {

                out =  "پینگ معمولی" + Long.toString(delay);

            } else if (delay >= 1000 && delay < 2000) {

                out =  "پینگ ضعیف" + Long.toString(delay);
            }else  {

                out =  "سرور مشکل دارد" + Long.toString(delay);
            }


            if (isReachable){
               return out;
            }else{
                return "سرور مشکل دارد";
            }
        } catch (IOException e) {



            e.printStackTrace();


            return "سرور مشکل دارد";

        }




    }
/////////////////////////////////////////////////

    private static class RetrieveFeedTask2 extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {


                    online3 = true;





                } else {

                    online3 = false;


                }
            } catch (IOException e) {
                online3 = false;

                e.printStackTrace();
                System.out.println("خطا در اتصال به سرور: " + e.getMessage());
            }

            return null;
        }
    }


    /////////////////////////
    private void getVmessLinks2() {

        new Thread(() -> {


        }).start();



    }


    private  String decodeBase64(String coded) {
        byte[] valueDecoded = new byte[0];
        try {
            valueDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException ignored) {
        }
        return new String(valueDecoded);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {


        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            net = false;

//            Mythread5 myThread5 = new Mythread5();
//            myThread5.interrupt();
//
            if (executor2 != null){
                executor2.shutdownNow();

            }
            if (executor3 != null){
                executor3.shutdownNow();

            }
            if (executor != null){
                executor.shutdownNow();

            }

            if (executor4 != null){
                executor4.shutdownNow();

            }

            if (myt != null) {
                myt.interrupt();
            }

            Intent i = new Intent(Servers.this, MainActivity.class);
            startActivity(i);
            finish();


        }

    }

    public void time() {

        String url = "https://raw.githubusercontent.com/davudsedft/purvpn/refs/heads/main/links/versionpublic.json";

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
        }) ;

        RequestQueue rQueue = Volley.newRequestQueue(Servers.this);
        rQueue.add(request);


    }

    @SuppressLint("SetTextI18n")
    void parseJsonData2(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray fruitsArray = object.getJSONArray("purvpn");


            List<String> list2 = new ArrayList<String>();
            List<String> list3 = new ArrayList<String>();
            List<String> list4 = new ArrayList<String>();
            List<String> list5 = new ArrayList<String>();

            list2.add(fruitsArray.getString(4));
            list3.add(fruitsArray.getString(6));
            list4.add(fruitsArray.getString(7));
            list5.add(fruitsArray.getString(8));

            String ggg;
            String nnn = "";
            String lll = "";
            String joined = TextUtils.join("", list2);
            String joined2 = TextUtils.join("", list3);
            String joined3 = TextUtils.join("", list4);
            String joined4 = TextUtils.join("", list5);

            if (lang) {
                ggg = utf88(joined);
                nnn = utf88(joined3);

            } else {
                ggg = utf88(joined2);
                lll = utf88(joined4);

            }
            TextView txt = (TextView) findViewById(R.id.txttime);


            if (lang) {
                txt.setText( ggg);

            } else {
                txt.setText(ggg);

            }

            //  txt.setTextColor(Color.YELLOW);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String utf88(String ss) {
        byte[] bytes = ss.getBytes();
        String s = new String(bytes, StandardCharsets.UTF_8);
        // String sf = new String(bytes, StandardCharsets.UTF_8);
        s = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        return s;

    }

    public void external(View view) {
        try {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            String clipboardtext = item.getText().toString();
            String clipboardtext2 = item.getText().toString();

            String [] k = clipboardtext.split("&");

            //  clipboardtext3 = clipboardtext3.replace("vless" ,"")  ;
            //   String v = "davud";
            //   clipboardtext3 = v+clipboardtext3;
            //  Log.v("zzzzzz",clipboardtext3);




            if ((clipboardtext2.contains("PurCow://"))) {
                clipboardtext = clipboardtext.replace("PurCow://", "");
                clipboardtext = clipboardtext.replace("#DF90#", "");

                clipboardtext = decodeBase64(clipboardtext);


                SharedPreferences.Editor shEdit = Pref.edit();
                shEdit.putString(Name, clipboardtext);
                shEdit.apply();
                // V2rayController.StopV2ray(getApplicationContext());
                SharedPreferences.Editor shEdit2 = Config.edit();
                //  shEdit2.remove(Name2);
                shEdit2.putString(Name2, getString(R.string.external));
                shEdit2.apply();
                Intent ii = new Intent(Servers.this, MainActivity.class);
                ii.putExtra("kool", "rovshan");
                ii.putExtra("copyte", clipboardtext);

                startActivity(ii);
                finish();
                Toast.makeText(Servers.this, getString(R.string.done), Toast.LENGTH_SHORT).show();
            } else if ((clipboardtext2.startsWith("vmess://"))) {
                clipboardtext = clipboardtext.replace("vmess://", "");
                //  clipboardtext = clipboardtext.replace("#DF90#", "");

                //  clipboardtext = decodeBase64(clipboardtext);

                connnnn = "کاننفیگ vmess";

                SharedPreferences.Editor shEdit = Pref.edit();
                shEdit.putString(Name, clipboardtext);
                shEdit.apply();
                // V2rayController.StopV2ray(getApplicationContext());
                SharedPreferences.Editor shEdit2 = Config.edit();
                //  shEdit2.remove(Name2);
                shEdit2.putString(Name2, connnnn);
                shEdit2.apply();
                Intent ii = new Intent(Servers.this, MainActivity.class);
                ii.putExtra("kool", "rovshan");
                ii.putExtra("copyte", clipboardtext);

                startActivity(ii);
                finish();
                Toast.makeText(Servers.this, getString(R.string.done), Toast.LENGTH_SHORT).show();


            } else if (clipboardtext2.startsWith("ss://")) {

                clipboardtext = sss(clipboardtext2);

                if (lang) {
                    connnnn = "کانفیگ شادوساکس";

                } else {
                    connnnn = "Shadowsocks";

                }



                SharedPreferences.Editor shEdit = Pref.edit();
                shEdit.putString(Name, clipboardtext);
                shEdit.apply();
                V2rayController.StopV2ray(getApplicationContext());
                SharedPreferences.Editor shEdit2 = Config.edit();
                //  shEdit2.remove(Name2);
                shEdit2.putString(Name2, connnnn);
                shEdit2.apply();
                Intent ii = new Intent(Servers.this, MainActivity.class);
                ii.putExtra("kool", "rovshan");
                ii.putExtra("copyte", clipboardtext);
                startActivity(ii);
                finish();
                Toast.makeText(Servers.this, getString(R.string.done), Toast.LENGTH_SHORT).show();


                Log.v("kkkkjjjj", clipboardtext);


            } else if (clipboardtext.startsWith("vless:")) {

                if (lang) {
                    connnnn = "کانفیگ vless";

                } else {
                    connnnn = "vless";

                }


                Log.v("ppppppppppp", clipboardtext);

                SharedPreferences.Editor shEdit = Pref.edit();
                shEdit.putString(Name, clipboardtext);
                shEdit.apply();
                V2rayController.StopV2ray(getApplicationContext());
                SharedPreferences.Editor shEdit2 = Config.edit();
                //  shEdit2.remove(Name2);
                shEdit2.putString(Name2, connnnn);
                shEdit2.apply();
                Intent ii = new Intent(Servers.this, MainActivity.class);
                ii.putExtra("kool", "rovshan");
                ii.putExtra("copyte", clipboardtext);
                startActivity(ii);
                finish();
                Toast.makeText(Servers.this, getString(R.string.done), Toast.LENGTH_SHORT).show();


            } else if (clipboardtext.contains("dns")) {

                if (lang) {
                    connnnn = "کانفیگ خارجی";

                } else {
                    connnnn = "forien";

                }
                SharedPreferences.Editor shEdit = Pref.edit();
                shEdit.putString(Name, clipboardtext);
                shEdit.apply();
                V2rayController.StopV2ray(getApplicationContext());
                SharedPreferences.Editor shEdit2 = Config.edit();
                shEdit2.putString(Name2, connnnn);
                shEdit2.apply();
                Intent ii = new Intent(Servers.this, MainActivity.class);
                ii.putExtra("kool", "rovshan");
                ii.putExtra("copyte", clipboardtext);
                startActivity(ii);
                finish();
                Toast.makeText(Servers.this, getString(R.string.done), Toast.LENGTH_SHORT).show();


            } else if (clipboardtext.startsWith("trojan://")) {

                if (lang) {
                    connnnn = "کانفیگ تروجان";

                } else {
                    connnnn = "trojan";

                }

                Log.v("ppppppppppp", clipboardtext);

                SharedPreferences.Editor shEdit = Pref.edit();
                shEdit.putString(Name, clipboardtext);
                shEdit.apply();
                V2rayController.StopV2ray(getApplicationContext());
                SharedPreferences.Editor shEdit2 = Config.edit();
                //  shEdit2.remove(Name2);
                shEdit2.putString(Name2, connnnn);
                shEdit2.apply();
                Intent ii = new Intent(Servers.this, MainActivity.class);
                ii.putExtra("kool", "rovshan");
                ii.putExtra("copyte", clipboardtext);
                startActivity(ii);
                finish();
                Toast.makeText(Servers.this, getString(R.string.done), Toast.LENGTH_SHORT).show();


            } else if (clipboardtext.startsWith("wireguard://")) {

                if (lang) {
                    connnnn = "wireguard";

                } else {
                    connnnn = "wireguard";

                }

                warp(clipboardtext);



            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.copyyyy), Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            Toast.makeText(Servers.this, getString(R.string.iradd), Toast.LENGTH_SHORT).show();

        }

    }

    private  String vll(String link) {

       String kkkk = null;

        try {
            String fp = "chrome";
            String alpn = "h2";
            String s;
            String server ;
            String security;
            int port ;
            String type;
            String sni;
            String host;
            String path;

            String encryption;









            String[] su = link.split("@");


            String[] m = su[1].split("\\?");
            // String[] serverport = m[0].split(":");


            s = su[0];
            s = s.replace("vless://", "");
            // s = decodeBase64(s);
            Log.v("llllll", s);

            String[] ipport;
            if (m[0].contains("::")){
                ipport = m[0].split("]:");
                server = ipport[0]+"]";

            }else {
                ipport = m[0].split(":");

                server = ipport[0];
            }

            String port2 = ipport[1];
            port = Integer.parseInt(port2);
            Log.v("llllll", server);
            Log.v("llllll", port2);



            if (link.contains("alpn") && link.contains("fp")){
                String[] mosa = m[1].split("&");
                ArrayList<String> h = new ArrayList<>();
                path = mosa[0].replace("path=", "");
                Log.v("path", path);


                String[] parts = link.split("path=");
                String[] mmm = parts[1].split("&");
                path = mmm[0];


                try {
                    path = URLDecoder.decode(path, "UTF-8");
                } catch (UnsupportedEncodingException e22) {
                    e22.printStackTrace();
                }
                Log.v("llllll", path);


                security = mosa[1].replace("security=", "");
                Log.v("security", security);

                encryption = mosa[2].replace("encryption=", "");
                Log.v("encrypt", encryption);

                alpn = mosa[3].replace("alpn=", "");
                Log.v("alpn", alpn);
                host = mosa[4].replace("host=", "");
                Log.v("host", host);

                fp = mosa[5].replace("fp=", "");
                Log.v("fp", fp);

                type = mosa[6].replace("type=", "");
                Log.v("type", type);


                String[] hh = mosa[7].split("#");
                sni = hh[0].replace("sni=", "");
                Log.v("sni", sni);


                //     String auth = ramzpass[0];
                //     String pass = ramzpass[1];
                String name = m[1];





            }else {

                if (link.contains("fp") && !link.contains("alpn")){
                    String[] mosa = m[1].split("&");
                    path = mosa[0].replace("path=", "");
                    Log.v("path", path);


                    String[] parts = link.split("path=");
                    String[] mmm = parts[1].split("&");
                    path = mmm[0];


                    try {
                        path = URLDecoder.decode(path, "UTF-8");
                    } catch (UnsupportedEncodingException e22) {
                        e22.printStackTrace();
                    }
                    Log.v("llllll", path);


                    security = mosa[1].replace("security=", "");
                    Log.v("security", security);

                    encryption = mosa[2].replace("encryption=", "");
                    Log.v("encrypt", encryption);


                    host = mosa[3].replace("host=", "");
                    Log.v("host", host);

                    fp = mosa[4].replace("fp=", "");
                    Log.v("fp", fp);

                    type = mosa[5].replace("type=", "");
                    Log.v("type", type);


                    String[] hh = mosa[6].split("#");
                    sni = hh[0].replace("sni=", "");
                    Log.v("sni", sni);


                    //     String auth = ramzpass[0];
                    //     String pass = ramzpass[1];
                    String name = m[1];


                }  else if (!link.contains("fp") && link.contains("alpn")) {
                    String[] mosa = m[1].split("&");
                    path = mosa[0].replace("path=", "");
                    Log.v("path", path);

                    path = path.replace("%2F" , "/");


                    security = mosa[1].replace("security=", "");
                    Log.v("security", security);

                    encryption = mosa[2].replace("encryption=", "");
                    Log.v("encrypt", encryption);
                    alpn = mosa[3].replace("alpn=", "");


                    host = mosa[4].replace("host=", "");
                    Log.v("host", host);



                    type = mosa[5].replace("type=", "");
                    Log.v("type", type);


                    String[] hh = mosa[6].split("#");
                    sni = hh[0].replace("sni=", "");
                    Log.v("sni", sni);





                }else {
                    System.out.println("1111");

                    String[] mosa = m[1].split("&");


                    String[] parts = link.split("path=");
                    String[] mmm = parts[1].split("&");
                    path = mmm[0];


                    try {
                        path = URLDecoder.decode(path, "UTF-8");
                    } catch (UnsupportedEncodingException e22) {
                        e22.printStackTrace();
                    }
                    Log.v("llllll", path);


                    security = mosa[1].replace("security=", "");
                    Log.v("security", security);

                    encryption = mosa[2].replace("encryption=", "");
                    Log.v("encrypt", encryption);


                    host = mosa[3].replace("host=", "");
                    Log.v("host", host);



                    type = mosa[4].replace("type=", "");
                    Log.v("type", type);


                    String[] hh = mosa[5].split("#");
                    sni = hh[0].replace("sni=", "");
                    Log.v("sni", sni);






                }





            }








            kkkk = "{\n" +
                    "  \"dns\": {\n" +
                    "    \"hosts\": {\n" +
                    "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                    "    },\n" +
                    "    \"servers\": [\n" +
                    "      \"1.1.1.1\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"inbounds\": [\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10808,\n" +
                    "      \"protocol\": \"socks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"auth\": \"noauth\",\n" +
                    "        \"udp\": true,\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"sniffing\": {\n" +
                    "        \"destOverride\": [\n" +
                    "          \"http\",\n" +
                    "          \"tls\"\n" +
                    "        ],\n" +
                    "        \"enabled\": true\n" +
                    "      },\n" +
                    "      \"tag\": \"socks\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10809,\n" +
                    "      \"protocol\": \"http\",\n" +
                    "      \"settings\": {\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"tag\": \"http\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"log\": {\n" +
                    "    \"loglevel\": \"warning\"\n" +
                    "  },\n" +
                    "  \"outbounds\": [\n" +
                    "    {\n" +
                    "      \"mux\": {\n" +
                    "        \"concurrency\": -1,\n" +
                    "        \"enabled\": false,\n" +
                    "        \"xudpConcurrency\": 8,\n" +
                    "        \"xudpProxyUDP443\": \"\"\n" +
                    "      },\n" +
                    "      \"protocol\": \"vless\",\n" +
                    "      \"settings\": {\n" +
                    "        \"vnext\": [\n" +
                    "          {\n" +
                    "            \"address\": \"" + server + "\",\n" +
                    "            \"port\": " + port + ",\n" +
                    "            \"users\": [\n" +
                    "              {\n" +
                    "                \"encryption\": \"" + encryption + "\",\n" +
                    "                \"flow\": \"\",\n" +
                    "                \"id\": \"" + s + "\",\n" +
                    "                \"level\": 8,\n" +
                    "                \"security\": \"auto\"\n" +
                    "              }\n" +
                    "            ]\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      },\n" +
                    "      \"streamSettings\": {\n" +
                    "        \"network\": \"" + type + "\",\n" +
                    "        \"security\": \"" + security + "\",\n" +
                    "        \"tlsSettings\": {\n" +
                    "          \"allowInsecure\": false,\n" +
                    "          \"alpn\": [\n" +
                    "            \"http/1.1\"\n" +
                    "          ],\n" +
                    "          \"fingerprint\": \"" + fp + "\",\n" +
                    "          \"publicKey\": \"\",\n" +
                    "          \"serverName\": \"" + host + "\",\n" +
                    "          \"shortId\": \"\",\n" +
                    "          \"show\": false,\n" +
                    "          \"spiderX\": \"\"\n" +
                    "        },\n" +
                    "        \"wsSettings\": {\n" +
                    "          \"headers\": {\n" +
                    "            \"Host\": \"" + sni + "\"\n" +
                    "          },\n" +
                    "          \"path\": \"" + path + "\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"proxy\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"freedom\",\n" +
                    "      \"settings\": {},\n" +
                    "      \"tag\": \"direct\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"blackhole\",\n" +
                    "      \"settings\": {\n" +
                    "        \"response\": {\n" +
                    "          \"type\": \"http\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"block\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"routing\": {\n" +
                    "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                    "    \"rules\": [\n" +
                    "      {\n" +
                    "        \"ip\": [\n" +
                    "          \"1.1.1.1\"\n" +
                    "        ],\n" +
                    "        \"outboundTag\": \"proxy\",\n" +
                    "        \"port\": \"53\",\n" +
                    "        \"type\": \"field\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";

        }catch (Exception e){


            try {
                System.out.println("2222");

                String fp = "chrome";
                String alpn = "h2";
                String s;
                String server ;
                String security;
                int port ;
                String type;
                String sni;
                String host;
                String path;

                String encryption;

                String[] su = link.split("@");


                String[] m = su[1].split("\\?");
                // String[] serverport = m[0].split(":");


                s = su[0];
                s = s.replace("vless://", "");
                // s = decodeBase64(s);
                Log.v("llllll", s);



                String[] ipport;
                if (m[0].contains("::")){
                    ipport = m[0].split("]:");
                    server = ipport[0]+"]";

                }else {
                    ipport = m[0].split(":");

                    server = ipport[0];
                }

                String port2 = ipport[1];
                port = Integer.parseInt(port2);
                Log.v("llllll", server);
                Log.v("llllll", port2);



                String[] mosa = m[1].split("&");

                String[] parts = link.split("path=");
                String[] mmm = parts[1].split("&");
                path = mmm[0];


                try {
                    path = URLDecoder.decode(path, "UTF-8");
                } catch (UnsupportedEncodingException e22) {
                    e.printStackTrace();
                }
                Log.v("llllll", path);


                security = mosa[1].replace("security=", "");
                Log.v("security", security);

                encryption = mosa[2].replace("encryption=", "");
                Log.v("encrypt", encryption);
                alpn = mosa[3].replace("alpn=", "");


                host = mosa[4].replace("host=", "");
                Log.v("host", host);



                type = mosa[5].replace("type=", "");
                Log.v("type", type);

//
//            String[] hh = mosa[6].split("#");
//            sni = hh[0].replace("sni=", "");
//            Log.v("sni", sni);
//



                kkkk = "{\n" +
                        "  \"dns\": {\n" +
                        "    \"hosts\": {\n" +
                        "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                        "    },\n" +
                        "    \"servers\": [\n" +
                        "      \"1.1.1.1\"\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"inbounds\": [\n" +
                        "    {\n" +
                        "      \"listen\": \"127.0.0.1\",\n" +
                        "      \"port\": 10808,\n" +
                        "      \"protocol\": \"socks\",\n" +
                        "      \"settings\": {\n" +
                        "        \"auth\": \"noauth\",\n" +
                        "        \"udp\": true,\n" +
                        "        \"userLevel\": 8\n" +
                        "      },\n" +
                        "      \"sniffing\": {\n" +
                        "        \"destOverride\": [\n" +
                        "          \"http\",\n" +
                        "          \"tls\"\n" +
                        "        ],\n" +
                        "        \"enabled\": true\n" +
                        "      },\n" +
                        "      \"tag\": \"socks\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"listen\": \"127.0.0.1\",\n" +
                        "      \"port\": 10809,\n" +
                        "      \"protocol\": \"http\",\n" +
                        "      \"settings\": {\n" +
                        "        \"userLevel\": 8\n" +
                        "      },\n" +
                        "      \"tag\": \"http\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"log\": {\n" +
                        "    \"loglevel\": \"warning\"\n" +
                        "  },\n" +
                        "  \"outbounds\": [\n" +
                        "    {\n" +
                        "      \"mux\": {\n" +
                        "        \"concurrency\": -1,\n" +
                        "        \"enabled\": false,\n" +
                        "        \"xudpConcurrency\": 8,\n" +
                        "        \"xudpProxyUDP443\": \"\"\n" +
                        "      },\n" +
                        "      \"protocol\": \"vless\",\n" +
                        "      \"settings\": {\n" +
                        "        \"vnext\": [\n" +
                        "          {\n" +
                        "            \"address\": \"" + server + "\",\n" +
                        "            \"port\": "+port+",\n" +
                        "            \"users\": [\n" +
                        "              {\n" +
                        "                \"encryption\": \"" + encryption + "\",\n" +
                        "                \"flow\": \"\",\n" +
                        "                \"id\": \"" + s + "\",\n" +
                        "                \"level\": 8,\n" +
                        "                \"security\": \""+security+"\"\n" +
                        "              }\n" +
                        "            ]\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      \"streamSettings\": {\n" +
                        "        \"network\": \"" + type + "\",\n" +
                        "        \"security\": \""+security+"\",\n" +
                        "        \"wsSettings\": {\n" +
                        "          \"headers\": {\n" +
                        "            \"Host\": \"" + host + "\"\n" +
                        "          },\n" +
                        "          \"path\": \""+path+"\"\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"tag\": \"proxy\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"protocol\": \"freedom\",\n" +
                        "      \"settings\": {},\n" +
                        "      \"tag\": \"direct\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"protocol\": \"blackhole\",\n" +
                        "      \"settings\": {\n" +
                        "        \"response\": {\n" +
                        "          \"type\": \"http\"\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"tag\": \"block\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"remarks\": \"\uD83D\uDD17 Vauth - 2920\",\n" +
                        "  \"routing\": {\n" +
                        "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                        "    \"rules\": [\n" +
                        "      {\n" +
                        "        \"ip\": [\n" +
                        "          \"1.1.1.1\"\n" +
                        "        ],\n" +
                        "        \"outboundTag\": \"proxy\",\n" +
                        "        \"port\": \"53\",\n" +
                        "        \"type\": \"field\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}\n";
            }catch (Exception e2){


                try {


                    System.out.println("jjjj");
                    String fp = "chrome";
                    String alpn = "h2";
                    String s;
                    String server ;
                    String security;
                    int port ;
                    String type;
                    String sni;
                    String host;
                    String path;

                    String encryption;

                    String[] su = link.split("@");


                    String[] m = su[1].split("\\?");
                    // String[] serverport = m[0].split(":");


                    s = su[0];
                    s = s.replace("vless://", "");
                    // s = decodeBase64(s);
                    Log.v("llllll", s);



                    String[] ipport;
                    if (m[0].contains("::")){
                        ipport = m[0].split("]:");
                        server = ipport[0]+"]";

                    }else {
                        ipport = m[0].split(":");

                        server = ipport[0];
                    }

                    String port2 = ipport[1];
                    port = Integer.parseInt(port2);
                    Log.v("llllll", server);
                    Log.v("llllll", port2);






                    String[] parts = link.split("path=");
                    String[] mmm = parts[1].split("&");
                    path = mmm[0];


                    try {
                        path = URLDecoder.decode(path, "UTF-8");
                    } catch (UnsupportedEncodingException e22) {
                        e.printStackTrace();
                    }
                    Log.v("llllll", path);



                    String[] parts2 = link.split("security=");
                    String[] mmm2 = parts2[1].split("&");
                    security = mmm2[0];

                    Log.v("llllll", security);



                    String[] parts3 = link.split("host=");
                    String[] mmm3 = parts3[1].split("&");
                    host = mmm3[0];







                    Log.v("host", host);




                    type = "ws";


                    Log.v("type", type);


                    String[] parts6 = link.split("encryption=");
                    String[] mmm6 = parts6[1].split("&");
                    encryption = mmm6[0];



//
//            String[] hh = mosa[6].split("#");
//            sni = hh[0].replace("sni=", "");
//            Log.v("sni", sni);
//



                    kkkk = "{\n" +
                            "  \"dns\": {\n" +
                            "    \"hosts\": {\n" +
                            "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                            "    },\n" +
                            "    \"servers\": [\n" +
                            "      \"1.1.1.1\"\n" +
                            "    ]\n" +
                            "  },\n" +
                            "  \"inbounds\": [\n" +
                            "    {\n" +
                            "      \"listen\": \"127.0.0.1\",\n" +
                            "      \"port\": 10808,\n" +
                            "      \"protocol\": \"socks\",\n" +
                            "      \"settings\": {\n" +
                            "        \"auth\": \"noauth\",\n" +
                            "        \"udp\": true,\n" +
                            "        \"userLevel\": 8\n" +
                            "      },\n" +
                            "      \"sniffing\": {\n" +
                            "        \"destOverride\": [\n" +
                            "          \"http\",\n" +
                            "          \"tls\"\n" +
                            "        ],\n" +
                            "        \"enabled\": true\n" +
                            "      },\n" +
                            "      \"tag\": \"socks\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"listen\": \"127.0.0.1\",\n" +
                            "      \"port\": 10809,\n" +
                            "      \"protocol\": \"http\",\n" +
                            "      \"settings\": {\n" +
                            "        \"userLevel\": 8\n" +
                            "      },\n" +
                            "      \"tag\": \"http\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"log\": {\n" +
                            "    \"loglevel\": \"warning\"\n" +
                            "  },\n" +
                            "  \"outbounds\": [\n" +
                            "    {\n" +
                            "      \"mux\": {\n" +
                            "        \"concurrency\": -1,\n" +
                            "        \"enabled\": false,\n" +
                            "        \"xudpConcurrency\": 8,\n" +
                            "        \"xudpProxyUDP443\": \"\"\n" +
                            "      },\n" +
                            "      \"protocol\": \"vless\",\n" +
                            "      \"settings\": {\n" +
                            "        \"vnext\": [\n" +
                            "          {\n" +
                            "            \"address\": \"" + server + "\",\n" +
                            "            \"port\": "+port+",\n" +
                            "            \"users\": [\n" +
                            "              {\n" +
                            "                \"encryption\": \"" + encryption + "\",\n" +
                            "                \"flow\": \"\",\n" +
                            "                \"id\": \"" + s + "\",\n" +
                            "                \"level\": 8,\n" +
                            "                \"security\": \""+security+"\"\n" +
                            "              }\n" +
                            "            ]\n" +
                            "          }\n" +
                            "        ]\n" +
                            "      },\n" +
                            "      \"streamSettings\": {\n" +
                            "        \"network\": \"" + type + "\",\n" +
                            "        \"security\": \""+security+"\",\n" +
                            "        \"wsSettings\": {\n" +
                            "          \"headers\": {\n" +
                            "            \"Host\": \"" + host + "\"\n" +
                            "          },\n" +
                            "          \"path\": \""+path+"\"\n" +
                            "        }\n" +
                            "      },\n" +
                            "      \"tag\": \"proxy\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"protocol\": \"freedom\",\n" +
                            "      \"settings\": {},\n" +
                            "      \"tag\": \"direct\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"protocol\": \"blackhole\",\n" +
                            "      \"settings\": {\n" +
                            "        \"response\": {\n" +
                            "          \"type\": \"http\"\n" +
                            "        }\n" +
                            "      },\n" +
                            "      \"tag\": \"block\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"remarks\": \"\uD83D\uDD17 Vauth - 2920\",\n" +
                            "  \"routing\": {\n" +
                            "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                            "    \"rules\": [\n" +
                            "      {\n" +
                            "        \"ip\": [\n" +
                            "          \"1.1.1.1\"\n" +
                            "        ],\n" +
                            "        \"outboundTag\": \"proxy\",\n" +
                            "        \"port\": \"53\",\n" +
                            "        \"type\": \"field\"\n" +
                            "      }\n" +
                            "    ]\n" +
                            "  }\n" +
                            "}\n";
                }catch (Exception h){
                     kkkk = "{\n" +
                            "  \"dns\": {\n" +
                            "    \"hosts\": {\n" +
                            "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                            "    },\n" +
                            "    \"servers\": [\n" +
                            "      \"1.1.1.1\"\n" +
                            "    ]\n" +
                            "  },\n" +
                            "  \"inbounds\": [\n" +
                            "    {\n" +
                            "      \"listen\": \"127.0.0.1\",\n" +
                            "      \"port\": 10808,\n" +
                            "      \"protocol\": \"socks\",\n" +
                            "      \"settings\": {\n" +
                            "        \"auth\": \"noauth\",\n" +
                            "        \"udp\": true,\n" +
                            "        \"userLevel\": 8\n" +
                            "      },\n" +
                            "      \"sniffing\": {\n" +
                            "        \"destOverride\": [\n" +
                            "          \"http\",\n" +
                            "          \"tls\"\n" +
                            "        ],\n" +
                            "        \"enabled\": true\n" +
                            "      },\n" +
                            "      \"tag\": \"socks\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"listen\": \"127.0.0.1\",\n" +
                            "      \"port\": 10809,\n" +
                            "      \"protocol\": \"http\",\n" +
                            "      \"settings\": {\n" +
                            "        \"userLevel\": 8\n" +
                            "      },\n" +
                            "      \"tag\": \"http\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"log\": {\n" +
                            "    \"loglevel\": \"warning\"\n" +
                            "  },\n" +
                            "  \"outbounds\": [\n" +
                            "    {\n" +
                            "      \"mux\": {\n" +
                            "        \"concurrency\": -1,\n" +
                            "        \"enabled\": false,\n" +
                            "        \"xudpConcurrency\": 8,\n" +
                            "        \"xudpProxyUDP443\": \"\"\n" +
                            "      },\n" +
                            "      \"protocol\": \"vless\",\n" +
                            "      \"settings\": {\n" +
                            "        \"vnext\": [\n" +
                            "          {\n" +
                            "            \"address\": \"zula.ir\",\n" +
                            "            \"port\": 2095,\n" +
                            "            \"users\": [\n" +
                            "              {\n" +
                            "                \"encryption\": \"none\",\n" +
                            "                \"flow\": \"\",\n" +
                            "                \"id\": \"e0e8370a-056f-410e-ac75-43872763ef41\",\n" +
                            "                \"level\": 8,\n" +
                            "                \"security\": \"auto\"\n" +
                            "              }\n" +
                            "            ]\n" +
                            "          }\n" +
                            "        ]\n" +
                            "      },\n" +
                            "      \"streamSettings\": {\n" +
                            "        \"network\": \"ws\",\n" +
                            "        \"security\": \"none\",\n" +
                            "        \"wsSettings\": {\n" +
                            "          \"headers\": {\n" +
                            "            \"Host\": \"V2ray-_-_-_-_-ShhProxy.TelegramChannelJoinBede.com.\"\n" +
                            "          },\n" +
                            "          \"path\": \"/\"\n" +
                            "        }\n" +
                            "      },\n" +
                            "      \"tag\": \"proxy\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"protocol\": \"freedom\",\n" +
                            "      \"settings\": {},\n" +
                            "      \"tag\": \"direct\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"protocol\": \"blackhole\",\n" +
                            "      \"settings\": {\n" +
                            "        \"response\": {\n" +
                            "          \"type\": \"http\"\n" +
                            "        }\n" +
                            "      },\n" +
                            "      \"tag\": \"block\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"remarks\": \"\uD83C\uDF19سرور های رایگان پرسرعت در کانال @serversiran11\",\n" +
                            "  \"routing\": {\n" +
                            "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                            "    \"rules\": [\n" +
                            "      {\n" +
                            "        \"ip\": [\n" +
                            "          \"1.1.1.1\"\n" +
                            "        ],\n" +
                            "        \"outboundTag\": \"proxy\",\n" +
                            "        \"port\": \"53\",\n" +
                            "        \"type\": \"field\"\n" +
                            "      }\n" +
                            "    ]\n" +
                            "  }\n" +
                            "}";

                }

            }


        }

        return kkkk;

    }

    private String vll2(String link) {





        String fp = "chrome";
        String alpn = "h2";
        String s;
        String server = "127.0.0.1";
        String security;
        int port = 2012;
        String type;
        String sni;
        String host;
        String path;

        String encryption;









        String[] su = link.split("@");


        String[] m = su[1].split("\\?");
        // String[] serverport = m[0].split(":");


        s = su[0];
        s = s.replace("vless://", "");
        // s = decodeBase64(s);
        Log.v("llllll", s);


        String[] ipport = m[0].split(":");

        String port2 = ipport[1];
        Log.v("llllll", port2);



        if (link.contains("alpn") && link.contains("fp")){
            String[] mosa = m[1].split("&");
            ArrayList<String> h = new ArrayList<>();
            path = mosa[0].replace("path=", "");
            Log.v("path", path);

            path = path.replace("%2F" , "/");


            security = mosa[1].replace("security=", "");
            Log.v("security", security);

            encryption = mosa[2].replace("encryption=", "");
            Log.v("encrypt", encryption);

            alpn = mosa[3].replace("alpn=", "");
            Log.v("alpn", alpn);
            host = mosa[4].replace("host=", "");
            Log.v("host", host);

            fp = mosa[5].replace("fp=", "");
            Log.v("fp", fp);

            type = mosa[6].replace("type=", "");
            Log.v("type", type);


            String[] hh = mosa[7].split("#");
            sni = hh[0].replace("sni=", "");
            Log.v("sni", sni);


            //     String auth = ramzpass[0];
            //     String pass = ramzpass[1];
            String name = m[1];





        }else {

            if (link.contains("fp") && !link.contains("alpn")){
                String[] mosa = m[1].split("&");
                path = mosa[0].replace("path=", "");
                Log.v("path", path);

                path = path.replace("%2F" , "/");


                security = mosa[1].replace("security=", "");
                Log.v("security", security);

                encryption = mosa[2].replace("encryption=", "");
                Log.v("encrypt", encryption);


                host = mosa[3].replace("host=", "");
                Log.v("host", host);

                fp = mosa[4].replace("fp=", "");
                Log.v("fp", fp);

                type = mosa[5].replace("type=", "");
                Log.v("type", type);


                String[] hh = mosa[6].split("#");
                sni = hh[0].replace("sni=", "");
                Log.v("sni", sni);


                //     String auth = ramzpass[0];
                //     String pass = ramzpass[1];
                String name = m[1];


            }  else if (!link.contains("fp") && link.contains("alpn")) {
                String[] mosa = m[1].split("&");
                path = mosa[0].replace("path=", "");
                Log.v("path", path);

                path = path.replace("%2F" , "/");


                security = mosa[1].replace("security=", "");
                Log.v("security", security);

                encryption = mosa[2].replace("encryption=", "");
                Log.v("encrypt", encryption);
                alpn = mosa[3].replace("alpn=", "");


                host = mosa[4].replace("host=", "");
                Log.v("host", host);



                type = mosa[5].replace("type=", "");
                Log.v("type", type);


                String[] hh = mosa[6].split("#");
                sni = hh[0].replace("sni=", "");
                Log.v("sni", sni);





            }else {

                String[] mosa = m[1].split("&");
                path = mosa[0].replace("path=", "");
                Log.v("path", path);

                path = path.replace("%2F" , "/");


                security = mosa[1].replace("security=", "");
                Log.v("security", security);

                encryption = mosa[2].replace("encryption=", "");
                Log.v("encrypt", encryption);


                host = mosa[3].replace("host=", "");
                Log.v("host", host);



                type = mosa[4].replace("type=", "");
                Log.v("type", type);


                String[] hh = mosa[5].split("#");
                sni = hh[0].replace("sni=", "");
                Log.v("sni", sni);






            }





        }








        String kkkk = "{\n" +
                "  \"dns\": {\n" +
                "    \"hosts\": {\n" +
                "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                "    },\n" +
                "    \"servers\": [\n" +
                "      \"1.1.1.1\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"inbounds\": [\n" +
                "    {\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"port\": 10808,\n" +
                "      \"protocol\": \"socks\",\n" +
                "      \"settings\": {\n" +
                "        \"auth\": \"noauth\",\n" +
                "        \"udp\": true,\n" +
                "        \"userLevel\": 8\n" +
                "      },\n" +
                "      \"sniffing\": {\n" +
                "        \"destOverride\": [\n" +
                "          \"http\",\n" +
                "          \"tls\"\n" +
                "        ],\n" +
                "        \"enabled\": true\n" +
                "      },\n" +
                "      \"tag\": \"socks\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"port\": 10809,\n" +
                "      \"protocol\": \"http\",\n" +
                "      \"settings\": {\n" +
                "        \"userLevel\": 8\n" +
                "      },\n" +
                "      \"tag\": \"http\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"log\": {\n" +
                "    \"loglevel\": \"warning\"\n" +
                "  },\n" +
                "  \"outbounds\": [\n" +
                "    {\n" +
                "      \"mux\": {\n" +
                "        \"concurrency\": -1,\n" +
                "        \"enabled\": false,\n" +
                "        \"xudpConcurrency\": 8,\n" +
                "        \"xudpProxyUDP443\": \"\"\n" +
                "      },\n" +
                "      \"protocol\": \"vless\",\n" +
                "      \"settings\": {\n" +
                "        \"vnext\": [\n" +
                "          {\n" +
                "            \"address\": \"" + server + "\",\n" +
                "            \"port\": " + port + ",\n" +
                "            \"users\": [\n" +
                "              {\n" +
                "                \"encryption\": \"" + encryption + "\",\n" +
                "                \"flow\": \"\",\n" +
                "                \"id\": \"" + s + "\",\n" +
                "                \"level\": 8,\n" +
                "                \"security\": \"auto\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"streamSettings\": {\n" +
                "        \"network\": \"" + type + "\",\n" +
                "        \"security\": \"" + security + "\",\n" +
                "        \"tlsSettings\": {\n" +
                "          \"allowInsecure\": false,\n" +
                "          \"alpn\": [\n" +
                "            \"http/1.1\"\n" +
                "          ],\n" +
                "          \"fingerprint\": \"" + fp + "\",\n" +
                "          \"publicKey\": \"\",\n" +
                "          \"serverName\": \"" + host + "\",\n" +
                "          \"shortId\": \"\",\n" +
                "          \"show\": false,\n" +
                "          \"spiderX\": \"\"\n" +
                "        },\n" +
                "        \"wsSettings\": {\n" +
                "          \"headers\": {\n" +
                "            \"Host\": \"" + sni + "\"\n" +
                "          },\n" +
                "          \"path\": \"" + path + "\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"tag\": \"proxy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"protocol\": \"freedom\",\n" +
                "      \"settings\": {},\n" +
                "      \"tag\": \"direct\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"protocol\": \"blackhole\",\n" +
                "      \"settings\": {\n" +
                "        \"response\": {\n" +
                "          \"type\": \"http\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"tag\": \"block\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"routing\": {\n" +
                "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                "    \"rules\": [\n" +
                "      {\n" +
                "        \"ip\": [\n" +
                "          \"1.1.1.1\"\n" +
                "        ],\n" +
                "        \"outboundTag\": \"proxy\",\n" +
                "        \"port\": \"53\",\n" +
                "        \"type\": \"field\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        return kkkk;

    }

    public  String trojan2(String linq){

        String fp = "chrome";
        String alpn = "h2";
        String s;
        String server = "127.0.0.1";
        String security;
        int port = 2012;
        String headerType;
        String type;
        String sni;

        if (linq.contains("alpn") && linq.contains("fp")) {

            String[] su = linq.split("@");


            String[] m = su[1].split("\\?");
            // String[] serverport = m[0].split(":");


            s = su[0];
            s = s.replace("trojan://", "");
            // s = decodeBase64(s);
            Log.v("llllll", s);


            String[] ipport = m[0].split(":");

            String port2 = ipport[1];
            Log.v("llllll", server);
            Log.v("llllll", port2);

            String[] mosa = m[1].split("&");
            security = mosa[0].replace("security=", "");
            Log.v("path", security);


            alpn = mosa[1].replace("alpn=", "");
            Log.v("alpn", alpn);
            headerType = mosa[2].replace("headerType=", "");
            Log.v("host", headerType);

            fp = mosa[3].replace("fp=", "");
            Log.v("fp", fp);

            type = mosa[4].replace("type=", "");
            Log.v("type", type);


            String[] hh = mosa[5].split("#");
            sni = hh[0].replace("sni=", "");
            Log.v("sni", sni);

        }else {
            String[] su = linq.split("@");
            String[] m = su[1].split("\\?");
            // String[] serverport = m[0].split(":");


            s = su[0];
            s = s.replace("trojan://", "");
            // s = decodeBase64(s);
            Log.v("llllll", s);


            String[] ipport = m[0].split(":");

            String port2 = ipport[1];
            Log.v("llllll", server);
            Log.v("llllll", port2);


            if (!linq.contains("fp") && linq.contains("alpn")){



                String[] mosa = m[1].split("&");
                security = mosa[0].replace("security=", "");
                Log.v("path", security);

                alpn = mosa[1].replace("alpn=", "");


                headerType = mosa[2].replace("headerType=", "");

                Log.v("host", headerType);



                type = mosa[3].replace("type=", "");
                Log.v("type", type);


                String[] hh = mosa[4].split("#");
                sni = hh[0].replace("sni=", "");
                Log.v("sni", sni);





            } else if (linq.contains("fp") && !linq.contains("alpn")) {


                String[] mosa = m[1].split("&");
                security = mosa[0].replace("security=", "");
                Log.v("path", security);



                headerType = mosa[1].replace("headerType=", "");
                Log.v("host", headerType);

                fp = mosa[2].replace("fp=", "");


                type = mosa[3].replace("type=", "");
                Log.v("type", type);


                String[] hh = mosa[4].split("#");
                sni = hh[0].replace("sni=", "");
                Log.v("sni", sni);

            }else {
                String[] mosa = m[1].split("&");
                security = mosa[0].replace("security=", "");
                Log.v("path", security);



                headerType = mosa[1].replace("headerType=", "");
                Log.v("host", headerType);



                type = mosa[2].replace("type=", "");
                Log.v("type", type);


                String[] hh = mosa[3].split("#");
                sni = hh[0].replace("sni=", "");
                Log.v("sni", sni);
            }






        }



        String d = "{\n" +
                "  \"dns\": {\n" +
                "    \"hosts\": {\n" +
                "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                "    },\n" +
                "    \"servers\": [\n" +
                "      \"1.1.1.1\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"inbounds\": [\n" +
                "    {\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"port\": 10808,\n" +
                "      \"protocol\": \"socks\",\n" +
                "      \"settings\": {\n" +
                "        \"auth\": \"noauth\",\n" +
                "        \"udp\": true,\n" +
                "        \"userLevel\": 8\n" +
                "      },\n" +
                "      \"sniffing\": {\n" +
                "        \"destOverride\": [\n" +
                "          \"http\",\n" +
                "          \"tls\"\n" +
                "        ],\n" +
                "        \"enabled\": true\n" +
                "      },\n" +
                "      \"tag\": \"socks\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"port\": 10809,\n" +
                "      \"protocol\": \"http\",\n" +
                "      \"settings\": {\n" +
                "        \"userLevel\": 8\n" +
                "      },\n" +
                "      \"tag\": \"http\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"log\": {\n" +
                "    \"loglevel\": \"warning\"\n" +
                "  },\n" +
                "  \"outbounds\": [\n" +
                "    {\n" +
                "      \"mux\": {\n" +
                "        \"concurrency\": -1,\n" +
                "        \"enabled\": false,\n" +
                "        \"xudpConcurrency\": 8,\n" +
                "        \"xudpProxyUDP443\": \"\"\n" +
                "      },\n" +
                "      \"protocol\": \"trojan\",\n" +
                "      \"settings\": {\n" +
                "        \"servers\": [\n" +
                "          {\n" +
                "            \"address\": \""+server+"\",\n" +
                "            \"flow\": \"\",\n" +
                "            \"level\": 8,\n" +
                "            \"method\": \"chacha20-poly1305\",\n" +
                "            \"ota\": false,\n" +
                "            \"password\": \""+s+"\",\n" +
                "            \"port\": "+port+"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"streamSettings\": {\n" +
                "        \"network\": \""+type+"\",\n" +
                "        \"security\": \""+security+"\",\n" +
                "        \"tcpSettings\": {\n" +
                "          \"header\": {\n" +
                "            \"type\": \"none\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"tlsSettings\": {\n" +
                "          \"allowInsecure\": false,\n" +
                "          \"alpn\": [\n" +
                "            \"http/1.1\"\n" +
                "          ],\n" +
                "          \"fingerprint\": \"chrome\",\n" +
                "          \"publicKey\": \"\",\n" +
                "          \"serverName\": \""+sni+"\",\n" +
                "          \"shortId\": \"\",\n" +
                "          \"show\": false,\n" +
                "          \"spiderX\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"tag\": \"proxy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"protocol\": \"freedom\",\n" +
                "      \"settings\": {},\n" +
                "      \"tag\": \"direct\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"protocol\": \"blackhole\",\n" +
                "      \"settings\": {\n" +
                "        \"response\": {\n" +
                "          \"type\": \"http\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"tag\": \"block\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"remarks\": \"daviiid\",\n" +
                "  \"routing\": {\n" +
                "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                "    \"rules\": [\n" +
                "      {\n" +
                "        \"ip\": [\n" +
                "          \"1.1.1.1\"\n" +
                "        ],\n" +
                "        \"outboundTag\": \"proxy\",\n" +
                "        \"port\": \"53\",\n" +
                "        \"type\": \"field\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        return d;
    }

    public  String trojan(String linq){

        String d = null;

        try {

                String fp = "chrome";
                String alpn = "h2";
                String s;
                String server;
                String security;
                int port;
                String headerType;
                String type;
                String sni;

                if (linq.contains("alpn") && linq.contains("fp")) {

                    String[] su = linq.split("@");


                    String[] m = su[1].split("\\?");
                    // String[] serverport = m[0].split(":");


                    s = su[0];
                    s = s.replace("trojan://", "");
                    // s = decodeBase64(s);
                    Log.v("llllll", s);


                    String[] ipport = m[0].split(":");

                    server = ipport[0];
                    String port2 = ipport[1];
                    port = Integer.parseInt(port2);
                    Log.v("llllll", server);
                    Log.v("llllll", port2);

                    String[] mosa = m[1].split("&");
                    security = mosa[0].replace("security=", "");
                    Log.v("path", security);


                    alpn = mosa[1].replace("alpn=", "");
                    Log.v("alpn", alpn);
                    headerType = mosa[2].replace("headerType=", "");
                    Log.v("host", headerType);

                    fp = mosa[3].replace("fp=", "");
                    Log.v("fp", fp);

                    type = mosa[4].replace("type=", "");
                    Log.v("type", type);


                    String[] hh = mosa[5].split("#");
                    sni = hh[0].replace("sni=", "");
                    Log.v("sni", sni);

                }else {
                    String[] su = linq.split("@");
                    String[] m = su[1].split("\\?");
                    // String[] serverport = m[0].split(":");


                    s = su[0];
                    s = s.replace("trojan://", "");
                    // s = decodeBase64(s);
                    Log.v("llllll", s);


                    String[] ipport = m[0].split(":");

                    server = ipport[0];
                    String port2 = ipport[1];
                    port = Integer.parseInt(port2);
                    Log.v("llllll", server);
                    Log.v("llllll", port2);


                    if (!linq.contains("fp") && linq.contains("alpn")){



                        String[] mosa = m[1].split("&");
                        security = mosa[0].replace("security=", "");
                        Log.v("path", security);

                        alpn = mosa[1].replace("alpn=", "");


                        headerType = mosa[2].replace("headerType=", "");

                        Log.v("host", headerType);



                        type = mosa[3].replace("type=", "");
                        Log.v("type", type);


                        String[] hh = mosa[4].split("#");
                        sni = hh[0].replace("sni=", "");
                        Log.v("sni", sni);





                    } else if (linq.contains("fp") && !linq.contains("alpn")) {


                        String[] mosa = m[1].split("&");
                        security = mosa[0].replace("security=", "");
                        Log.v("path", security);



                        headerType = mosa[1].replace("headerType=", "");
                        Log.v("host", headerType);

                        fp = mosa[2].replace("fp=", "");


                        type = mosa[3].replace("type=", "");
                        Log.v("type", type);


                        String[] hh = mosa[4].split("#");
                        sni = hh[0].replace("sni=", "");
                        Log.v("sni", sni);

                    }else {
                        String[] mosa = m[1].split("&");
                        security = mosa[0].replace("security=", "");
                        Log.v("path", security);



                        headerType = mosa[1].replace("headerType=", "");
                        Log.v("host", headerType);



                        type = mosa[2].replace("type=", "");
                        Log.v("type", type);


                        String[] hh = mosa[3].split("#");
                        sni = hh[0].replace("sni=", "");
                        Log.v("sni", sni);
                    }






                }








            d = "{\n" +
                    "  \"dns\": {\n" +
                    "    \"hosts\": {\n" +
                    "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                    "    },\n" +
                    "    \"servers\": [\n" +
                    "      \"1.1.1.1\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"inbounds\": [\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10808,\n" +
                    "      \"protocol\": \"socks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"auth\": \"noauth\",\n" +
                    "        \"udp\": true,\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"sniffing\": {\n" +
                    "        \"destOverride\": [\n" +
                    "          \"http\",\n" +
                    "          \"tls\"\n" +
                    "        ],\n" +
                    "        \"enabled\": true\n" +
                    "      },\n" +
                    "      \"tag\": \"socks\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10809,\n" +
                    "      \"protocol\": \"http\",\n" +
                    "      \"settings\": {\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"tag\": \"http\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"log\": {\n" +
                    "    \"loglevel\": \"warning\"\n" +
                    "  },\n" +
                    "  \"outbounds\": [\n" +
                    "    {\n" +
                    "      \"mux\": {\n" +
                    "        \"concurrency\": -1,\n" +
                    "        \"enabled\": false,\n" +
                    "        \"xudpConcurrency\": 8,\n" +
                    "        \"xudpProxyUDP443\": \"\"\n" +
                    "      },\n" +
                    "      \"protocol\": \"trojan\",\n" +
                    "      \"settings\": {\n" +
                    "        \"servers\": [\n" +
                    "          {\n" +
                    "            \"address\": \""+server+"\",\n" +
                    "            \"flow\": \"\",\n" +
                    "            \"level\": 8,\n" +
                    "            \"method\": \"chacha20-poly1305\",\n" +
                    "            \"ota\": false,\n" +
                    "            \"password\": \""+s+"\",\n" +
                    "            \"port\": "+port+"\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      },\n" +
                    "      \"streamSettings\": {\n" +
                    "        \"network\": \""+type+"\",\n" +
                    "        \"security\": \""+security+"\",\n" +
                    "        \"tcpSettings\": {\n" +
                    "          \"header\": {\n" +
                    "            \"type\": \"none\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        \"tlsSettings\": {\n" +
                    "          \"allowInsecure\": false,\n" +
                    "          \"alpn\": [\n" +
                    "            \"http/1.1\"\n" +
                    "          ],\n" +
                    "          \"fingerprint\": \"chrome\",\n" +
                    "          \"publicKey\": \"\",\n" +
                    "          \"serverName\": \""+sni+"\",\n" +
                    "          \"shortId\": \"\",\n" +
                    "          \"show\": false,\n" +
                    "          \"spiderX\": \"\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"proxy\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"freedom\",\n" +
                    "      \"settings\": {},\n" +
                    "      \"tag\": \"direct\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"blackhole\",\n" +
                    "      \"settings\": {\n" +
                    "        \"response\": {\n" +
                    "          \"type\": \"http\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"block\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"remarks\": \"daviiid\",\n" +
                    "  \"routing\": {\n" +
                    "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                    "    \"rules\": [\n" +
                    "      {\n" +
                    "        \"ip\": [\n" +
                    "          \"1.1.1.1\"\n" +
                    "        ],\n" +
                    "        \"outboundTag\": \"proxy\",\n" +
                    "        \"port\": \"53\",\n" +
                    "        \"type\": \"field\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";
        }catch (Exception e){
            d = "{\n" +
                    "  \"dns\": {\n" +
                    "    \"hosts\": {\n" +
                    "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                    "    },\n" +
                    "    \"servers\": [\n" +
                    "      \"1.1.1.1\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"inbounds\": [\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10808,\n" +
                    "      \"protocol\": \"socks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"auth\": \"noauth\",\n" +
                    "        \"udp\": true,\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"sniffing\": {\n" +
                    "        \"destOverride\": [\n" +
                    "          \"http\",\n" +
                    "          \"tls\"\n" +
                    "        ],\n" +
                    "        \"enabled\": true\n" +
                    "      },\n" +
                    "      \"tag\": \"socks\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10809,\n" +
                    "      \"protocol\": \"http\",\n" +
                    "      \"settings\": {\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"tag\": \"http\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"log\": {\n" +
                    "    \"loglevel\": \"warning\"\n" +
                    "  },\n" +
                    "  \"outbounds\": [\n" +
                    "    {\n" +
                    "      \"mux\": {\n" +
                    "        \"concurrency\": -1,\n" +
                    "        \"enabled\": false,\n" +
                    "        \"xudpConcurrency\": 8,\n" +
                    "        \"xudpProxyUDP443\": \"\"\n" +
                    "      },\n" +
                    "      \"protocol\": \"trojan\",\n" +
                    "      \"settings\": {\n" +
                    "        \"servers\": [\n" +
                    "          {\n" +
                    "            \"address\": \"3.11.132.113\",\n" +
                    "            \"flow\": \"\",\n" +
                    "            \"level\": 8,\n" +
                    "            \"method\": \"chacha20-poly1305\",\n" +
                    "            \"ota\": false,\n" +
                    "            \"password\": \"telegram-id-privatevpns\",\n" +
                    "            \"port\": 22222\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      },\n" +
                    "      \"streamSettings\": {\n" +
                    "        \"network\": \"tcp\",\n" +
                    "        \"security\": \"tls\",\n" +
                    "        \"tcpSettings\": {\n" +
                    "          \"header\": {\n" +
                    "            \"type\": \"none\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        \"tlsSettings\": {\n" +
                    "          \"allowInsecure\": false,\n" +
                    "          \"alpn\": [\n" +
                    "            \"http/1.1\"\n" +
                    "          ],\n" +
                    "          \"fingerprint\": \"\",\n" +
                    "          \"serverName\": \"trojan.burgerip.co.uk\",\n" +
                    "          \"show\": false\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"proxy\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"freedom\",\n" +
                    "      \"settings\": {},\n" +
                    "      \"tag\": \"direct\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"blackhole\",\n" +
                    "      \"settings\": {\n" +
                    "        \"response\": {\n" +
                    "          \"type\": \"http\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"block\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"remarks\": \"\uD83C\uDDEC\uD83C\uDDE7GB | \uD83D\uDFE2 | trojan | @PrivateVPNs | 2\",\n" +
                    "  \"routing\": {\n" +
                    "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                    "    \"rules\": [\n" +
                    "      {\n" +
                    "        \"ip\": [\n" +
                    "          \"1.1.1.1\"\n" +
                    "        ],\n" +
                    "        \"outboundTag\": \"proxy\",\n" +
                    "        \"port\": \"53\",\n" +
                    "        \"type\": \"field\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";
        }

        return d;
    }
    public void http2() throws IOException {



//        linq2 = getSharedPreferences("linq2", Context.MODE_PRIVATE);
//        SharedPreferences.Editor iran2edit2 = linq2.edit();
//
//        iran2edit2.putString(Linq2, linkstring[0]);
//        iran2edit2.apply();
//
    }


    public  String vtwo(String s) {

        String pppp= null;
        s = s.replace("vmess://", "");
        //  clipboardtext = clipboardtext.replace("#DF90#", "");

        s = decodeBase64(s);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject((s));

            int hh = jsonObject.getInt("port");
            //  int ff = jsonObject.getInt("aid");

            String aid = "0";
            String port = Integer.toString(hh);


            String add = jsonObject.getString("add");
            //     String apln = jsonObject.getString("apln");
            String path = jsonObject.getString("path");
            String id = jsonObject.getString("id");
            //   String scy = jsonObject.getString("scy");
            String net = jsonObject.getString("net");
            String tls = jsonObject.getString("tls");
            String sni = jsonObject.getString("sni");


            String host = jsonObject.getString("host");


            String ps = jsonObject.getString("ps");

//        if (sni == null || sni == ""){
//                sni = "fo.co.po";
//        }



////
            pppp = "{\n" +
                    "  \"dns\": {\n" +
                    "    \"hosts\": {\n" +
                    "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                    "    },\n" +
                    "    \"servers\": [\n" +
                    "      \"1.1.1.1\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"inbounds\": [\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10808,\n" +
                    "      \"protocol\": \"socks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"auth\": \"noauth\",\n" +
                    "        \"udp\": true,\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"sniffing\": {\n" +
                    "        \"destOverride\": [\n" +
                    "          \"http\",\n" +
                    "          \"tls\"\n" +
                    "        ],\n" +
                    "        \"enabled\": true\n" +
                    "      },\n" +
                    "      \"tag\": \"socks\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10809,\n" +
                    "      \"protocol\": \"http\",\n" +
                    "      \"settings\": {\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"tag\": \"http\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"log\": {\n" +
                    "    \"loglevel\": \"warning\"\n" +
                    "  },\n" +
                    "  \"outbounds\": [\n" +
                    "    {\n" +
                    "      \"mux\": {\n" +
                    "        \"concurrency\": 8,\n" +
                    "        \"enabled\": false\n" +
                    "      },\n" +
                    "      \"protocol\": \"vmess\",\n" +
                    "      \"settings\": {\n" +
                    "        \"vnext\": [\n" +
                    "          {\n" +
                    "            \"address\": \"" + add + "\",\n" +
                    "            \"port\": " + port + ",\n" +
                    "            \"users\": [\n" +
                    "              {\n" +
                    "                \"alterId\": " + aid + ",\n" +
                    "                \"encryption\": \"\",\n" +
                    "                \"flow\": \"\",\n" +
                    "                \"id\": \"" + id + "\",\n" +
                    "                \"level\": 8,\n" +
                    "                \"security\":autu \n" +
                    "              }\n" +
                    "            ]\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      },\n" +
                    "      \"streamSettings\": {\n" +
                    "        \"network\": \"" + net + "\",\n" +
                    "        \"security\": \"" + tls + "\",\n" +
                    "        \"tlsSettings\": {\n" +
                    "          \"allowInsecure\": false,\n" +
                    "          \"alpn\": [\n" +
                    "            \" http/1.1\"\n" +
                    "          ],\n" +
                    "          \"fingerprint\": \"chrome\",\n" +
                    "          \"publicKey\": \"\",\n" +
                    "          \"serverName\": \"" + sni + "\",\n" +
                    "          \"shortId\": \"\",\n" +
                    "          \"show\": false,\n" +
                    "          \"spiderX\": \"\"\n" +
                    "        },\n" +
                    "        \"wsSettings\": {\n" +
                    "          \"headers\": {\n" +
                    "            \"Host\": \"" + host + "\"\n" +
                    "          },\n" +
                    "          \"path\": \"" + path + "\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"proxy\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"freedom\",\n" +
                    "      \"settings\": {},\n" +
                    "      \"tag\": \"direct\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"blackhole\",\n" +
                    "      \"settings\": {\n" +
                    "        \"response\": {\n" +
                    "          \"type\": \"http\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"block\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"routing\": {\n" +
                    "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                    "    \"rules\": [\n" +
                    "      {\n" +
                    "        \"ip\": [\n" +
                    "          \"1.1.1.1\"\n" +
                    "        ],\n" +
                    "        \"outboundTag\": \"proxy\",\n" +
                    "        \"port\": \"53\",\n" +
                    "        \"type\": \"field\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";
        } catch (JSONException e) {
            pppp = "{\n" +
                    "  \"dns\": {\n" +
                    "    \"hosts\": {\n" +
                    "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                    "    },\n" +
                    "    \"servers\": [\n" +
                    "      \"1.1.1.1\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"inbounds\": [\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10808,\n" +
                    "      \"protocol\": \"socks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"auth\": \"noauth\",\n" +
                    "        \"udp\": true,\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"sniffing\": {\n" +
                    "        \"destOverride\": [\n" +
                    "          \"http\",\n" +
                    "          \"tls\"\n" +
                    "        ],\n" +
                    "        \"enabled\": true\n" +
                    "      },\n" +
                    "      \"tag\": \"socks\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10809,\n" +
                    "      \"protocol\": \"http\",\n" +
                    "      \"settings\": {\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"tag\": \"http\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"log\": {\n" +
                    "    \"loglevel\": \"warning\"\n" +
                    "  },\n" +
                    "  \"outbounds\": [\n" +
                    "    {\n" +
                    "      \"mux\": {\n" +
                    "        \"concurrency\": -1,\n" +
                    "        \"enabled\": false,\n" +
                    "        \"xudpConcurrency\": 8,\n" +
                    "        \"xudpProxyUDP443\": \"\"\n" +
                    "      },\n" +
                    "      \"protocol\": \"vmess\",\n" +
                    "      \"settings\": {\n" +
                    "        \"vnext\": [\n" +
                    "          {\n" +
                    "            \"address\": \"205.234.200.104\",\n" +
                    "            \"port\": 33164,\n" +
                    "            \"users\": [\n" +
                    "              {\n" +
                    "                \"alterId\": 0,\n" +
                    "                \"encryption\": \"\",\n" +
                    "                \"flow\": \"\",\n" +
                    "                \"id\": \"31e8f7b4-ba3e-440a-9f56-8241569c017a\",\n" +
                    "                \"level\": 8,\n" +
                    "                \"security\": \"auto\"\n" +
                    "              }\n" +
                    "            ]\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      },\n" +
                    "      \"streamSettings\": {\n" +
                    "        \"network\": \"ws\",\n" +
                    "        \"security\": \"\",\n" +
                    "        \"wsSettings\": {\n" +
                    "          \"headers\": {\n" +
                    "            \"Host\": \"205.234.200.104\"\n" +
                    "          },\n" +
                    "          \"path\": \"/sd23dss\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"proxy\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"freedom\",\n" +
                    "      \"settings\": {},\n" +
                    "      \"tag\": \"direct\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"blackhole\",\n" +
                    "      \"settings\": {\n" +
                    "        \"response\": {\n" +
                    "          \"type\": \"http\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"block\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"remarks\": \"\uD83C\uDDFA\uD83C\uDDF8US-205.234.200.104-0125\",\n" +
                    "  \"routing\": {\n" +
                    "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                    "    \"rules\": [\n" +
                    "      {\n" +
                    "        \"ip\": [\n" +
                    "          \"1.1.1.1\"\n" +
                    "        ],\n" +
                    "        \"outboundTag\": \"proxy\",\n" +
                    "        \"port\": \"53\",\n" +
                    "        \"type\": \"field\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";
        }

        return pppp;
    }    public String vtwo2(String s) throws JSONException {


        s = s.replace("vmess://", "");
        //  clipboardtext = clipboardtext.replace("#DF90#", "");

        s = decodeBase64(s);

        JSONObject jsonObject = new JSONObject((s));
        //  int ff = jsonObject.getInt("aid");

        String aid = "0";
        String port = "2012";


        String add = "127.0.0.1";
        //     String apln = jsonObject.getString("apln");
        String path = jsonObject.getString("path");
        String id = jsonObject.getString("id");
        //   String scy = jsonObject.getString("scy");
        String net = jsonObject.getString("net");
        String tls = jsonObject.getString("tls");
        String sni = jsonObject.getString("sni");
        String host = jsonObject.getString("host");
        String ps = jsonObject.getString("ps");


////
        String pppp = "{\n" +
                "  \"dns\": {\n" +
                "    \"hosts\": {\n" +
                "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                "    },\n" +
                "    \"servers\": [\n" +
                "      \"1.1.1.1\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"inbounds\": [\n" +
                "    {\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"port\": 10808,\n" +
                "      \"protocol\": \"socks\",\n" +
                "      \"settings\": {\n" +
                "        \"auth\": \"noauth\",\n" +
                "        \"udp\": true,\n" +
                "        \"userLevel\": 8\n" +
                "      },\n" +
                "      \"sniffing\": {\n" +
                "        \"destOverride\": [\n" +
                "          \"http\",\n" +
                "          \"tls\"\n" +
                "        ],\n" +
                "        \"enabled\": true\n" +
                "      },\n" +
                "      \"tag\": \"socks\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"port\": 10809,\n" +
                "      \"protocol\": \"http\",\n" +
                "      \"settings\": {\n" +
                "        \"userLevel\": 8\n" +
                "      },\n" +
                "      \"tag\": \"http\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"log\": {\n" +
                "    \"loglevel\": \"warning\"\n" +
                "  },\n" +
                "  \"outbounds\": [\n" +
                "    {\n" +
                "      \"mux\": {\n" +
                "        \"concurrency\": 8,\n" +
                "        \"enabled\": false\n" +
                "      },\n" +
                "      \"protocol\": \"vmess\",\n" +
                "      \"settings\": {\n" +
                "        \"vnext\": [\n" +
                "          {\n" +
                "            \"address\": \"" + add + "\",\n" +
                "            \"port\": " + port + ",\n" +
                "            \"users\": [\n" +
                "              {\n" +
                "                \"alterId\": " + aid + ",\n" +
                "                \"encryption\": \"\",\n" +
                "                \"flow\": \"\",\n" +
                "                \"id\": \"" + id + "\",\n" +
                "                \"level\": 8,\n" +
                "                \"security\":autu \n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"streamSettings\": {\n" +
                "        \"network\": \"" + net + "\",\n" +
                "        \"security\": \"" + tls + "\",\n" +
                "        \"tlsSettings\": {\n" +
                "          \"allowInsecure\": false,\n" +
                "          \"alpn\": [\n" +
                "            \" http/1.1\"\n" +
                "          ],\n" +
                "          \"fingerprint\": \"chrome\",\n" +
                "          \"publicKey\": \"\",\n" +
                "          \"serverName\": \"" + sni + "\",\n" +
                "          \"shortId\": \"\",\n" +
                "          \"show\": false,\n" +
                "          \"spiderX\": \"\"\n" +
                "        },\n" +
                "        \"wsSettings\": {\n" +
                "          \"headers\": {\n" +
                "            \"Host\": \"" + host + "\"\n" +
                "          },\n" +
                "          \"path\": \"" + path + "\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"tag\": \"proxy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"protocol\": \"freedom\",\n" +
                "      \"settings\": {},\n" +
                "      \"tag\": \"direct\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"protocol\": \"blackhole\",\n" +
                "      \"settings\": {\n" +
                "        \"response\": {\n" +
                "          \"type\": \"http\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"tag\": \"block\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"routing\": {\n" +
                "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                "    \"rules\": [\n" +
                "      {\n" +
                "        \"ip\": [\n" +
                "          \"1.1.1.1\"\n" +
                "        ],\n" +
                "        \"outboundTag\": \"proxy\",\n" +
                "        \"port\": \"53\",\n" +
                "        \"type\": \"field\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";


        return pppp;
    }



    @Override
    protected void onDestroy() {
        net = false;
//


        if (executor2 != null){
            executor2.shutdownNow();

        }
        if (executor3 != null){
            executor3.shutdownNow();

        }
        if (executor != null){
            executor.shutdownNow();

        }


        if (executor4 != null){
            executor4.shutdownNow();

        }


        if (myt != null) {
            myt.interrupt();
        }
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        net = true;
//
//        Mythread5 myThread5 = new Mythread5();
//        myThread5.start();

    }

    public void povaro(View view) {
        String m = pova.getString(Pova , "");
        


            Backend backend = PersistentConnectionProperties.getInstance().getBackend();

            Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

            try {
                if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {
                    backend.setState(tunnel, DOWN, null);

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }



            if (executor != null){
                executor.shutdownNow();
                // Thread.currentThread().interrupt();

            }


            if (isNetworkAvailable()  ) {
                if (http != null){
                    http.interrupt();
                }
                if (executor != null){
                    executor.shutdownNow();
                    // Thread.currentThread().interrupt();

                }


                if (executor2 != null)  {

                    if (executor2.isTerminated()){

                        cont();

                    }else {
                        dialogg2();
                    }
                }





                // show();
            } else {
                Toast.makeText(Servers.this, getString(R.string.barresinet), Toast.LENGTH_SHORT).show();
            }



    }


    public void diss() {
        //   Toast.makeText(Finder.this, "سرور پیدا شد", Toast.LENGTH_SHORT).show();

        Thread thread = new Thread("New Thread") {
            public void run() {


                cancelDialog.dismiss();
                //   updial.show();
                //
                //  executorService.shutdown();
            }
        };
        thread.start();
    }

    private void createCancelProgressDialog(String title, String message, String buttonText) {
        cancelDialog = new ProgressDialog(this, R.style.MyDialog);
        cancelDialog.setTitle(title);
        cancelDialog.setMessage(message);

        cancelDialog.setCancelable(false);

        cancelDialog.setButton(buttonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Use either finish() or return() to either close the activity or just the dialog
                return;
            }
        });
        cancelDialog.show();
    }


    public String txt;



    @SuppressLint("NotifyDataSetChanged")
    public  void fff(){

        fff = true;

        for (int j = 0; j <= vmessLinks.size(); j++) {
            //  new RetrieveFeedTask2().execute("https://www.google.com");

            executor = Executors.newFixedThreadPool(1);


            if (j % 2 == 0 && boo) {

             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     povarbtn.setTextColor(Color.YELLOW);

                     povarbtn.setText(getString(R.string.search));
                     // one.start();
                 }
             });
            }

            if (j % 2 != 0 && boo) {
             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     povarbtn.setTextColor(Color.YELLOW);

                     povarbtn.setText(getString(R.string.search2));

                 }
             });
            }


            int finalJ = j;

                executor.submit(() -> {
                    System.out.println("ثطثزعفثق۲ قعددد ");
                    try {

                        Thread.sleep(3000);


                        String   selectedText= vmessLinks.get(finalJ);

                        if (selectedText.startsWith("ss://")){
                            selectedText = sss(selectedText);

                        }

                        //   wwww = getV2rayServerDelay(selectedText);


                        System.out.println("سرور پوارو");

                      //  int delay = (int) V2rayCoreExecutor.getConfigDelay(Utilities.normalizeV2rayFullConfig(selectedText));

                         long server_delay = V2rayCoreExecutor.getConfigDelay(Utilities.normalizeV2rayFullConfig(selectedText));


                        if (server_delay>0) {
                            people.add(new Person(selectedText, longToIntJavaWithMath(server_delay)));

                            @SuppressLint({"NewApi", "LocalSuppress"})

                            Person youngestPerson = Collections.min(people, Comparator.comparing(Person::getAge));
                            youngestName = youngestPerson.getName();

                            fff = false;
                            pova = getSharedPreferences("pova", Context.MODE_PRIVATE);

                            SharedPreferences.Editor iran2edit = pova.edit();

                            iran2edit.putString(Pova, youngestName);
                            iran2edit.apply();




                            executor.shutdownNow();
                            Thread.currentThread().interrupt();


                        }

                        //  executor.execute(new MyRunnable(finalJ));







                    }catch (Exception e){

                    }

                });




        if (!fff){
            break;
        }
        }

        executor.shutdown();




    }



    public int longToIntJavaWithMath(long number) {
        return Math.toIntExact(number);
    }
public  void dialogg(){
    // ایجاد یک دیالوگ ساده با یک دکمه بستن
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this,R.style.MyDialog);
    alertDialogBuilder.setMessage("اینترنت قطع شد.مجدد پینگ بگیرید.");
    alertDialogBuilder.setCancelable(false);
    alertDialogBuilder.setPositiveButton("بستن", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss(); // لغو دیالوگ

        }
    });

    // نمایش دیالوگ
    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
}
    public  void dialogg2(){
        // ایجاد یک دیالوگ ساده با یک دکمه بستن
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this,R.style.MyDialog);
        alertDialogBuilder.setMessage("تست پینگ در حال اجراس");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("توقف تست پینگ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (executor2.isTerminated()){
                    Toast.makeText(context, "متوقف  شد", Toast.LENGTH_SHORT).show();
                    execuer4run = false;
                }else{
                    executor2.shutdownNow();
                    Thread.currentThread().interrupt();
                    execuer4run = false;

                    Toast.makeText(context, "در حال متوقف شدن", Toast.LENGTH_SHORT).show();

                }

            }
        });
        alertDialogBuilder.setNegativeButton("ادامه دادن تست پینگ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // لغو دیالوگ

            }
        });
        // نمایش دیالوگ
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onPause() {
        super.onPause();

//        if (executor2 != null){
//            executor2.shutdownNow();
//        }




/*Intent intent = new Intent(this,MainActivity.class);
startActivity(intent);
finish();*/

    }

    @Override
    public void onStop() {
        net = false;
//
//        Mythread5 myThread5 = new Mythread5();
//        myThread5.interrupt();

        super.onStop();
        //  wakeLock.release();


    }

    public void cont() {

         http = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            povarbtn.setTextColor(Color.YELLOW);
                            povarbtn.setText(getString(R.string.shory));
                        }
                    });
                    String url2 = "https://github.com";

                    String fileUrl = "https://raw.githubusercontent.com/davudsedft/purvpn/refs/heads/main/links/povarofind.txt";

                    if (isLinkValid("https://github.com")) {
                        System.out.println("لینک معتبر است.");

                        URL textUrl2 = null;
                        try {
                            textUrl2 = new URL(fileUrl);

                            HttpURLConnection connection2 = (HttpURLConnection) textUrl2.openConnection();

                            connection2.setRequestMethod("GET");
                            connection2.setConnectTimeout(5000);
                            connection2.setReadTimeout(5000);
                            InputStream inputStream2 = connection2.getInputStream();
                            BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2));
                            StringBuilder stringBuilder2 = new StringBuilder();
                            String line2;
                            while ((line2 = reader2.readLine()) != null) {
                                stringBuilder2.append(line2).append("\n");
                            }
                            reader2.close();

                            utext3 = stringBuilder2.toString();



                        } catch (Exception ignored) {
                        }

                        // String[] linkstring = utext.split("https://");

                        if (utext3 != null){

                            linq = getSharedPreferences("linq", Context.MODE_PRIVATE);
                            SharedPreferences.Editor iran2edit = linq.edit();

                            iran2edit.putString(Linq, utext3);
                            iran2edit.apply();

                            String link2 = linq.getString(Linq, "");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    povarbtn.setTextColor(Color.YELLOW);
                                    povarbtn.setText(getString(R.string.webettesal));
                                }
                            });
                            // String url = link2;

                            try {
                                URL textUrl = new URL(link2);
                                HttpURLConnection connection = null;
                                connection = (HttpURLConnection)textUrl.openConnection();
                                connection.setReadTimeout(5000);
                                connection.setReadTimeout(5000);
                                connection.setRequestMethod("GET");
                                InputStream inputStream = connection.getInputStream();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                StringBuilder stringBuilder = new StringBuilder();
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    stringBuilder.append(line).append("\n");
                                }
                                reader.close();
                                utext = stringBuilder.toString();

                            } catch (Exception e) {
                            }

                            if (utext != null){

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        povarbtn.setText(getString(R.string.doneweb));

                                    }
                                });



                                if (isBase64(utext)) {
                                    // ابتدا تبدیل به رشته معمولی
                                 utext = decodeBase64(utext);

                                    // ادامه کد...
                                }





                                // utext = utext.replace("vmess://" , " ");
                                Pattern pattern = Pattern.compile("(ss|vmess|trojan|vless)://[^\n]+");
                                Matcher matcher = pattern.matcher(utext);
                                int u=0;
                                while (matcher.find() && u<200) {
                                    u++;
                                    vmessLinks.add(matcher.group());

                                }
                                startMyThread();
                                fff();



                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        povarbtn.setText("اینترنت لحظه ای پرید");
                                        povarbtn.setTextColor(Color.rgb(153, 153, 0));
                                    }
                                });
                            }
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    povarbtn.setText("اینترنت لحظه ای پرید");
                                    povarbtn.setTextColor(Color.rgb(153, 153, 0));
                                }
                            });
                        }

//        for (int i = 0; i < vmessLinks.size(); i++) {
//            vmessLinks.set(i, vmessLinks.get(i).replace("vmess://", ""));
//        }

                        Log.v("kkkkkkkkk",vmessLinks.get(10));


                        // vmessLinks.add(substrings.replace("vmess://" , " "));
//new Thread(new Runnable() {
//    @Override
//    public void run() {
//        try {
//            Thread.sleep(200);
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}).start();




                    } else {
                        System.out.println("لینک معتبر نیست یا وجود ندارد.");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                povarbtn.setTextColor(Color.RED);
                                povarbtn.setText("خطا در اتصال");
                            }
                        });
                    }



                    //  Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        http.start();




    }


    private static boolean isBase64(String input) {
        try {
            Base64.decode(input, Base64.DEFAULT);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }



    void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(30, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
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

    public void gloop() {
    myloopt =    new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loop();
                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
            }
        });
    myloopt.start();
    }

    public void loop() throws IOException {
        String url = "https://raw.githubusercontent.com/davudsedft/newpurnet/main/newloop2.txt";
        String fileUrl = "https://raw.githubusercontent.com/davudsedft/purvpn/refs/heads/main/links/newloop2.txt";

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
            b.setText("خطا در اتصال");

        }







        //  URL textUrl = null;


    }

    public void loopback(View view) throws IOException {

        if (isNetworkAvailable()) {
            // new RetrieveFeedTask().execute("https://www.google.com");



            if (isNetworkAvailable()){

                Backend backend = PersistentConnectionProperties.getInstance().getBackend();

                Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

                try {
                    if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {
                        backend.setState(tunnel, DOWN, null);

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }



                gloop();
                Button b = (Button) findViewById(R.id.loopback);
                b.setText(getString(R.string.sabr));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Button b = (Button) findViewById(R.id.loopback);

                        if (utext2 == null) {
                            b.setText(R.string.talash);

                        } else {
                            b.setText(getString(R.string.daryaft));

                            b.setTextColor(Color.GREEN);
                            SharedPreferences.Editor shEdit = Pref.edit();
                            shEdit.putString(Name, utext2);
                            shEdit.apply();
                            // V2rayController.StopV2ray(getApplicationContext());
                            SharedPreferences.Editor shEdit2 = Config.edit();
                            //  shEdit2.remove(Name2);
                            shEdit2.putString(Name2, getString(R.string.loopser));
                            shEdit2.apply();
                            Intent ii = new Intent(Servers.this, MainActivity.class);
                            ii.putExtra("kool", "rovshan");
                            ii.putExtra("copyte", utext2);

                            Intent ii2 = new Intent(Servers.this, MyService.class);


                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                ContextCompat.startForegroundService(Servers.this, ii2);
                            } else {
                                ContextCompat.startForegroundService(Servers.this, ii2);
                            }




                            startActivity(ii);
                            finish();
                            Toast.makeText(Servers.this, getString(R.string.done), Toast.LENGTH_SHORT).show();


                        }


                    }
                }, 3000);





            }else {

                Toast.makeText(Servers.this, getString(R.string.barresinet), Toast.LENGTH_SHORT).show();


            }




            // show();
        } else {
            Toast.makeText(Servers.this, getString(R.string.barresinet), Toast.LENGTH_SHORT).show();
        }


    }




    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu3,menu);
        return true ;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.as3){
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

    @SuppressLint("NotifyDataSetChanged")
    public  void runping(){





        try {


            execuer4run = true;
            Toast.makeText(context, "تست پینگ آغاز شد!", Toast.LENGTH_SHORT).show();


            vmessgreen.clear();

            for (int j = 0; j < vmesserver.size(); j++) {

                LinkServer server2;
                server2 = vmesserver.get(j);
                vmessgreen.add(new LinkServer(server2.name, server2.fam, "در حال بررسی", Color.YELLOW));

            }
            adapter.updateList(vmessgreen);





            if (numtask == vmesserver.size()/10) {
                executor2 = Executors.newFixedThreadPool(10);

            } else if (numtask == 5) {
                executor2 = Executors.newFixedThreadPool(5);

            }else {
                executor2 = Executors.newFixedThreadPool(30);

            }



            for (int j = 0; j <= vmesserver.size(); j++) {
                //  new RetrieveFeedTask2().execute("https://www.google.com");

              //  executor2 = Executors.newFixedThreadPool(1);



                int finalJ = j;

                executor2.submit(() -> {
                    System.out.println("ثطثزعفثق۲ قعددد ");



                    try {

                        try {
                            Thread.sleep(finalJ*5L+2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        LinkServer server;
                        server = vmesserver.get(finalJ);

                        String selectedText = server.fam;

                        if (selectedText.startsWith("ss://")){
                            selectedText = sss(selectedText);

                        }


                        if (isConnected){



                            if (selectedText.startsWith("wireguard://")){
                                System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");

                                selectedText = selectedText.replace("wireguard://" ,"");
                                String[]  url2 = selectedText.split("@");
                                String pri = url2[0];


                                String[] end = url2[1].split("\\?");

                                String[] ip =  end[0].split("]:");
                              String  port = ip[1];
                               String ipp = ip[0].replace("[","");


                                long startTime = System.currentTimeMillis();

                            Socket socket = new Socket(ipp, Integer.parseInt(port));
                                    long endTime = System.currentTimeMillis();
                                    long delay = endTime - startTime;
                                    if (delay >= 0 && delay < 600) {
                                        wwww =  "پینگ جت" + Long.toString(delay);

                                    } else if (delay >= 600 && delay < 800) {
                                        wwww =  "پینگ عالی" + Long.toString(delay);

                                    } else if (delay >= 800 && delay < 1000) {

                                        wwww =  "پینگ معمولی" + Long.toString(delay);

                                    } else if (delay >= 1000 && delay < 2000) {

                                        wwww =  "پینگ ضعیف" + Long.toString(delay);
                                    }else  {

                                        wwww =  "سرور مشکل دارد" + Long.toString(delay);
                                    }




                            }else {
                                wwww = getV2rayServerDelay(selectedText);
                                System.out.println("0000000000000000000000000");

                            }


                        }else{
                            wwww = "اینترنت اختلال دارد";
                        }




                        if (wwww.contains("پینگ خوب") || wwww.contains("پینگ جت") || wwww.contains("پینگ عالی")) {

                            vmessgreen.set(finalJ, new LinkServer(server.name, server.fam, wwww, Color.GREEN));

                            allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                            SharedPreferences.Editor e = allcolor.edit();
                            e.putInt("allcolor" + Integer.toString(finalJ), Color.GREEN);
                            e.apply();
                            allping = getSharedPreferences("allping", MODE_PRIVATE);

                            SharedPreferences.Editor el = allping.edit();
                            el.putString("allping" + Integer.toString(finalJ), wwww);
                            el.apply();


                        } else if (wwww.contains("معمولی")) {

                            vmessgreen.set(finalJ, new LinkServer(server.name, server.fam, wwww, Color.rgb(255, 128, 0)));


                            allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                            SharedPreferences.Editor e = allcolor.edit();
                            e.putInt("allcolor" + Integer.toString(finalJ), Color.rgb(255, 128, 0));
                            e.apply();
                            allping = getSharedPreferences("allping", MODE_PRIVATE);

                            SharedPreferences.Editor el = allping.edit();
                            el.putString("allping" + Integer.toString(finalJ), wwww);
                            el.apply();



                        } else if (wwww.contains("ضعیف")) {

                            vmessgreen.set(finalJ, new LinkServer(server.name, server.fam, wwww, Color.rgb(197, 38, 59)));


                            allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                            SharedPreferences.Editor e = allcolor.edit();
                            e.putInt("allcolor" + Integer.toString(finalJ), Color.rgb(197, 38, 59));
                            e.apply();
                            allping = getSharedPreferences("allping", MODE_PRIVATE);

                            SharedPreferences.Editor el = allping.edit();
                            el.putString("allping" + Integer.toString(finalJ), wwww);
                            el.apply();



                        } else {
                            vmessgreen.set(finalJ, new LinkServer(server.name, server.fam, wwww, Color.YELLOW));
                            allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);

                            SharedPreferences.Editor e = allcolor.edit();
                            e.putInt("allcolor" + Integer.toString(finalJ), Color.YELLOW);
                            e.apply();
                            allping = getSharedPreferences("allping", MODE_PRIVATE);

                            SharedPreferences.Editor el = allping.edit();
                            el.putString("allping" + Integer.toString(finalJ), wwww);
                            el.apply();


                        }

                        //  executor.execute(new MyRunnable(finalJ));
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             adapter.updateList(vmessgreen);

                         }
                     });


                    }catch (Exception e){
                        Toast.makeText(Servers.this, "سرورها را دریافت کنید", Toast.LENGTH_SHORT).show();

                    }

                });




            }

            executor2.shutdown();

            // Wait for all tasks to complete


        } catch (Exception e) {
            Toast.makeText(Servers.this, "سرورها را دریافت کنید", Toast.LENGTH_SHORT).show();
        }



    }

    static class MyRunnable implements Runnable {
        private final int threadId;

        MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            // عملیات مرتبط با هر ترد
            System.out.println("Thread " + threadId + " is running.");
        }
    }

    public static boolean isPingable(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.isReachable(2000); // Timeout in milliseconds
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    private static boolean checkInternetConnection() {
        try {
            // Check if we can resolve a known host (e.g., google.com)
            InetAddress.getByName("www.google.com");

            return true;
        } catch (UnknownHostException e) {
            return false;
        } catch (Exception e) {
            // Handle other exceptions (e.g., timeout)
            return false;
        }
    }





    public class MyThread extends Thread {




        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            // کارهای خود را انجام دهید

            // پس از اتمام کار، ترد را ببندید

            //   executor2 = Executors.newFixedThreadPool(vmesserver.size()/10);


            final StringBuilder builder = new StringBuilder();




                linq2 = getSharedPreferences("linq2", Context.MODE_PRIVATE);

               String url = linq2.getString(Linq2, "");
           // String url = "https://raw.githubuserntent.com/davudsedft/newpurnet/main/scan.txt";


            if (isLinkValid("https://github.com")) {
                System.out.println("لینک معتبر است.");

                try {
                    //  String url = "https://raw.githubusercontent.com/davudsedft/newpurnet/main/jooo.txt";
                    URL textUrl = new URL(url);





                    HttpURLConnection connection = (HttpURLConnection)textUrl.openConnection();
                    connection.setConnectTimeout(6000);
                    connection.setReadTimeout(6000);
                    connection.setRequestMethod("GET");

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    reader.close();
                    kook = stringBuilder.toString();

                } catch (Exception e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
//                    vmesserver.clear();
//                    number = getSharedPreferences("number", MODE_PRIVATE);
//                    SharedPreferences.Editor editor3= number.edit();
//                    editor3.putInt("number",vmesserver.size());
//                    editor3.apply();


                }
                if (kook != null) {

                    if (isBase64(kook)) {
                        // ابتدا تبدیل به رشته معمولی
                        kook = decodeBase64(kook);

                        // ادامه کد...
                    }



                    // utext = decodeBase64(utext);
                    // utext = utext.replace("vmess://" , " ");
                    // utext = utext.replace("vmess://" , " ");

                    int numb = 0;


                    Pattern pattern = Pattern.compile("(vmess|ss|trojan|vless|wireguard)://[^\n]+");
                    Matcher matcher = pattern.matcher(kook);
                    while (matcher.find() & numb < 400) {
                        numb++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        int finalNumb = numb;
                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                button.setTextColor(Color.rgb(255, 128, 0));


                                button.setText("در حال دریافت سرورها..." + Integer.toString(finalNumb));
                            }
                        });

                        if (matcher.group().startsWith("vmess://")) {
                            vmesserver.add(new LinkServer("سرور ویمس:" +checkIPVerion(matcher.group())+ Integer.toString(numb), matcher.group(), "تست پینگ", Color.YELLOW));

                        }
                        if (matcher.group().startsWith("wireguard://")) {
                            vmesserver.add(new LinkServer("سرور وارپ:" + wireguardipv(matcher.group())+Integer.toString(numb), matcher.group(), "تست پینگ", Color.YELLOW));

                        }
                        if (matcher.group().startsWith("ss://")) {
                            vmesserver.add(new LinkServer("سرور شادوساکس:" +shsdowipv(matcher.group())+ Integer.toString(numb), matcher.group(), "تست پینگ", Color.YELLOW));

                        }

                        if (matcher.group().startsWith("trojan://")) {

                            vmesserver.add(new LinkServer("سرور تروجان:" +chechtrojanipv(matcher.group())+ Integer.toString(numb), matcher.group(), "تست پینگ", Color.YELLOW));


                        }

                        if (matcher.group().startsWith("vless://")) {

                            vmesserver.add(new LinkServer("سرور ویلس:"+checkIPVersion(matcher.group()) + Integer.toString(numb), matcher.group(), "تست پینگ", Color.YELLOW));


                        }


                        //   vmesserver.add(new LinkServer("سرور تروجان"+Integer.toString(numb),matcher.group(),"تست پینگ",Color.YELLOW));

                        allping = getSharedPreferences("allping", MODE_PRIVATE);
                        allcolor = getSharedPreferences("allcolor", MODE_PRIVATE);


                        SharedPreferences.Editor editor = allping.edit();
                        editor.putString("allping" + Integer.toString(numb - 1), "تست پینگ");
                        editor.apply();
                        serverpref = getSharedPreferences("allserver", MODE_PRIVATE);

                        SharedPreferences.Editor editor2 = serverpref.edit();
                        editor2.putString("allserver" + Integer.toString(numb - 1), matcher.group());
                        editor2.apply();


                        SharedPreferences.Editor editor4 = allcolor.edit();

                        editor4.putInt("allcolor" + Integer.toString(numb - 1), Color.YELLOW);
                        editor4.apply();


                    }
                    number = getSharedPreferences("number", MODE_PRIVATE);
                    SharedPreferences.Editor editor3 = number.edit();
                    editor3.putInt("number", vmesserver.size());
                    editor3.apply();


                    // vmessLinks.add(substrings.replace("vmess://" , " "));
                    //   Log.v("hhhhh", vmessLinks2.get(2));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();

                            button.setTextColor(Color.GREEN);
                            button.setText("بروزرسانی سرورها");


                            // استفاده از حالت‌ها در جاهای مختلف


                            txtmakhzan.setText(makhzan.getString("makhzan", ""));
                        }
                    });

                    // loadser();

                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {




                            button.setText("اینترنت لحظه ای پرید");
                            button.setTextColor(Color.rgb(153, 153, 0));
                        }
                    });
                }

                this.interrupt();




            } else {
                System.out.println("لینک معتبر نیست یا وجود ندارد.");



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setTextColor(Color.RED);
                        button.setText("خطا در دریافت");

                    }
                });

                this.interrupt();



            }
































        }
    }

    public String shsdowipv(String link){
        String[] su = link.split("@");
        String[] m = su[1].split("#");
        String[] serverport = m[0].split(":");


        String s = su[0];
        s = s.replace("ss://", "");
        s = decodeBase64(s);
        String[] ramzpass = s.split(":");

        String server = serverport[0];
        int count = 0;
        for (int i=0 ; i<server.length();i++){
            if (server.charAt(i) == ':'){
                count++;

            }

        }

        if (count>1){
            return "(IPV6)";
        }else {
            return  "(IPV4)";
        }


    }
    public  String wireguardipv(String url){
        url = url.replace("wireguard://" ,"");
        String[]  url2 = url.split("@");
        String pri = url2[0];

        try {
            pri = URLDecoder.decode(pri, "UTF-8");
        } catch (UnsupportedEncodingException e22) {
            e22.printStackTrace();
        }

        String[] end = url2[1].split("\\?");
        String[] ip =  end[0].split("]:");
        String port = ip[1];
        String ipp = ip[0].replace("[","");
        int count = 0;
        for (int i=0 ; i<ipp.length();i++){
            if (ipp.charAt(i) == ':'){
                count++;

            }

        }

        if (count>1){
            return "(IPV6)";
        }else {
            return  "(IPV4)";
        }

    }
    public  String chechtrojanipv(String trojan){
        String[] h = trojan.split("@");
        String[] hh = h[1].split(":");
        trojan = hh[0];
        int count = 0;
        for (int i=0 ; i<trojan.length();i++){
            if (trojan.charAt(i) == ':'){
                count++;

            }

        }

        if (count>1){
            return "(IPV6)";
        }else {
            return  "(IPV4)";
        }

    }
    public  String checkIPVerion(String vmess){
        String pppp= null;
        vmess = vmess.replace("vmess://", "");
        //  clipboardtext = clipboardtext.replace("#DF90#", "");

        vmess = decodeBase64(vmess);
        String add = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject((vmess));
          add  = jsonObject.getString("add");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //  int ff = jsonObject.getInt("aid");

        int count = 0;
        for (int i=0 ; i<add.length();i++){
            if (add.charAt(i) == ':'){
                count++;

            }

        }

        if (count>1){
            return "(IPV6)";
        }else {
            return  "(IPV4)";
        }

            //     String apln = jsonObject.getString("apln");









    }


    public static String checkIPVersion(String vlessLink) {
        // Regular expression for extracting IP address
       String[] vless = vlessLink.split("@");
       String[] vless2  = vless[1].split("path=");
       String vl = vless2[0];
       int count = 0;
       for (int i=0 ; i<vl.length();i++){
           if (vl.charAt(i) == ':'){
               count++;

           }

       }

        if (count>1){
            return "(IPV6)";
        }else {
            return  "(IPV4)";
        }


    }


    private void handleButtonPress2(Status status) {
        switch (status) {
            case PORKAO:
                SharedPreferences.Editor m= makhzan.edit();
                m.putString("makhzan","مخزن پورکاو");
                m.apply();
                break;
            case CHINI:


                SharedPreferences.Editor m2= makhzan.edit();
                m2.putString("makhzan","مخزن چینی");
                m2.apply();

                break;
            case MISCELLANEOUS:

                SharedPreferences.Editor m3= makhzan.edit();
                m3.putString("makhzan","مخزن ایرانی");
                m3.apply();



                break;
            case WARP:

                SharedPreferences.Editor m4= makhzan.edit();
                m4.putString("makhzan","مخزن وارپ");
                m4.apply();



                break;
            default:
                // حالت نامعتبر
        }
        return;
    }




    public static boolean isLinkValid(String url) {
        try {
            URL linkUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) linkUrl.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
//    public class MyThread2 extends Thread {
//        @Override
//        public void run() {
//
//
//                String url = "https://raw.githubusercontent.com/davudsedft/newpurnet/main/scan.txt";
//
//
//
//
//                if (isLinkValid(url)) {
//                    System.out.println("لینک معتبر است.");
//
//
//                    try {
//
//
//                        URL textUrl = null;
//
//                        textUrl = new URL(url);
//                        URLConnection connection = null;
//
//                        connection = textUrl.openConnection();
//
//                        InputStream inputStream = connection.getInputStream();
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                        StringBuilder stringBuilder = new StringBuilder();
//                        String line;
//                        while ((line = reader.readLine()) != null) {
//                            stringBuilder.append(line).append("\n");
//                        }
//                        reader.close();
//
//                        System.out.println("سرور چینی");
//
//
//
//                        String utext5 = stringBuilder.toString();
//                        // String[] linkstring = utext.split("https://");
//
//
//                        linq2 = getSharedPreferences("linq2", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor iran2edit = linq2.edit();
//
//                        iran2edit.putString(Linq2, utext5);
//                        iran2edit.apply();
//
//                        handleButtonPress(Status.CHINI);
//
//
//                        Thread.sleep(2000);
//
//                        MyThread myThread = new MyThread();
//
//                        myThread.start();
//
//                        this.interrupt();
//
//
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//
//
//                } else {
//                    System.out.println("لینک معتبر نیست یا وجود ندارد.");
//
//
//                    button.setText("خطا در دریافت");
//
//
//
//
//                    this.interrupt();
//
//
//
//
//                }
//
//
//
//
//
//
//
//
//
//
//
//
//        }
//    }









    public class MyThread4 extends Thread {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            loadser2();
            runOnUiThread(() -> adapter.notifyDataSetChanged());

            this.interrupt();
        }
    }



    public enum Status {
        PORKAO,
        CHINI,
        MISCELLANEOUS,

        WARP
    }

    private void startMyThread() {
        myt = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("10 seccccc");
                    try {
                        Thread.sleep(10000); // تاخیر ۱۰ ثانیه
                        // اجرای عملیات مورد نظر
                        if (people.size()>0){
                            if (executor.isTerminated()){

                                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      povarbtn.setTextColor(Color.GREEN);
                                      povarbtn.setText(getString(R.string.aaaali));



                                      SharedPreferences.Editor shEdit = Pref.edit();
                                      shEdit.putString(Name, people.get(0).getName());
                                      shEdit.apply();
                                      // V2rayController.StopV2ray(getApplicationContext());
                                      SharedPreferences.Editor shEdit2 = Config.edit();
                                      //  shEdit2.remove(Name2);
                                      shEdit2.putString(Name2, getString(R.string.povaro));
                                      shEdit2.apply();
                                      Intent ii = new Intent(Servers.this, MainActivity.class);
                                      ii.putExtra("kool", "rovshan");
                                      ii.putExtra("copyte", people.get(0).getName());

                                      startActivity(ii);
                                      finish();













                                  }
                              });
                                myt.interrupt();
                            }



                            //   this.interrupt();
                        }else {
                            if (executor.isTerminated()){

                                runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                     povarbtn.setTextColor(Color.RED);
                                     povarbtn.setText(getString(R.string.notfound));
                                 }
                             });
                                myt.interrupt();
                            }



                            // this.interrupt();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return; // بازگشت از ترد
                    }
                }
            }
        });
        myt.start();
    }

    private  String sss(String link) {
        sss = null;

        try {
            String[] su = link.split("@");
            String[] m = su[1].split("#");
            String[] serverport = m[0].split(":");


            String s = su[0];
            s = s.replace("ss://", "");
            s = decodeBase64(s);
            String[] ramzpass = s.split(":");

            String server = serverport[0];
            String port = serverport[1];
            String auth = ramzpass[0];
            String pass = ramzpass[1];
            String name = m[1];


            sss = "{\n" +
                    "  \"dns\": {\n" +
                    "    \"hosts\": {\n" +
                    "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                    "    },\n" +
                    "    \"servers\": [\n" +
                    "      \"1.1.1.1\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"inbounds\": [\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10808,\n" +
                    "      \"protocol\": \"socks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"auth\": \"noauth\",\n" +
                    "        \"udp\": true,\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"sniffing\": {\n" +
                    "        \"destOverride\": [\n" +
                    "          \"http\",\n" +
                    "          \"tls\"\n" +
                    "        ],\n" +
                    "        \"enabled\": true\n" +
                    "      },\n" +
                    "      \"tag\": \"socks\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10809,\n" +
                    "      \"protocol\": \"http\",\n" +
                    "      \"settings\": {\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"tag\": \"http\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"log\": {\n" +
                    "    \"loglevel\": \"warning\"\n" +
                    "  },\n" +
                    "  \"outbounds\": [\n" +
                    "    {\n" +
                    "      \"mux\": {\n" +
                    "        \"concurrency\": -1,\n" +
                    "        \"enabled\": false,\n" +
                    "        \"xudpConcurrency\": 8,\n" +
                    "        \"xudpProxyUDP443\": \"\"\n" +
                    "      },\n" +
                    "      \"protocol\": \"shadowsocks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"servers\": [\n" +
                    "          {\n" +
                    "            \"address\": \"" + server + "\",\n" +
                    "            \"level\": 8,\n" +
                    "            \"method\": \"" + auth + "\",\n" +
                    "            \"ota\": false,\n" +
                    "            \"password\": \"" + pass + "\",\n" +
                    "            \"port\": " + port + "\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      },\n" +
                    "      \"streamSettings\": {\n" +
                    "        \"network\": \"tcp\",\n" +
                    "        \"security\": \"\"\n" +
                    "      },\n" +
                    "      \"tag\": \"proxy\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"freedom\",\n" +
                    "      \"settings\": {},\n" +
                    "      \"tag\": \"direct\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"blackhole\",\n" +
                    "      \"settings\": {\n" +
                    "        \"response\": {\n" +
                    "          \"type\": \"http\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"block\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"routing\": {\n" +
                    "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                    "    \"rules\": [\n" +
                    "      {\n" +
                    "        \"ip\": [\n" +
                    "          \"1.1.1.1\"\n" +
                    "        ],\n" +
                    "        \"outboundTag\": \"proxy\",\n" +
                    "        \"port\": \"53\",\n" +
                    "        \"type\": \"field\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";

        }catch (Exception e){
            sss = "{\n" +
                    "  \"dns\": {\n" +
                    "    \"hosts\": {\n" +
                    "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                    "    },\n" +
                    "    \"servers\": [\n" +
                    "      \"1.1.1.1\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"inbounds\": [\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10808,\n" +
                    "      \"protocol\": \"socks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"auth\": \"noauth\",\n" +
                    "        \"udp\": true,\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"sniffing\": {\n" +
                    "        \"destOverride\": [\n" +
                    "          \"http\",\n" +
                    "          \"tls\"\n" +
                    "        ],\n" +
                    "        \"enabled\": true\n" +
                    "      },\n" +
                    "      \"tag\": \"socks\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"listen\": \"127.0.0.1\",\n" +
                    "      \"port\": 10809,\n" +
                    "      \"protocol\": \"http\",\n" +
                    "      \"settings\": {\n" +
                    "        \"userLevel\": 8\n" +
                    "      },\n" +
                    "      \"tag\": \"http\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"log\": {\n" +
                    "    \"loglevel\": \"warning\"\n" +
                    "  },\n" +
                    "  \"outbounds\": [\n" +
                    "    {\n" +
                    "      \"mux\": {\n" +
                    "        \"concurrency\": -1,\n" +
                    "        \"enabled\": false,\n" +
                    "        \"xudpConcurrency\": 8,\n" +
                    "        \"xudpProxyUDP443\": \"\"\n" +
                    "      },\n" +
                    "      \"protocol\": \"shadowsocks\",\n" +
                    "      \"settings\": {\n" +
                    "        \"servers\": [\n" +
                    "          {\n" +
                    "            \"address\": \"156.146.38.163\",\n" +
                    "            \"level\": 8,\n" +
                    "            \"method\": \"aes-128-cfb\",\n" +
                    "            \"ota\": false,\n" +
                    "            \"password\": \"shadowsocks\",\n" +
                    "            \"port\": 443\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      },\n" +
                    "      \"streamSettings\": {\n" +
                    "        \"network\": \"tcp\",\n" +
                    "        \"security\": \"\"\n" +
                    "      },\n" +
                    "      \"tag\": \"proxy\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"freedom\",\n" +
                    "      \"settings\": {},\n" +
                    "      \"tag\": \"direct\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"protocol\": \"blackhole\",\n" +
                    "      \"settings\": {\n" +
                    "        \"response\": {\n" +
                    "          \"type\": \"http\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"tag\": \"block\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"remarks\": \"\uD83C\uDDFA\uD83C\uDDF8US-156.146.38.163-0262\",\n" +
                    "  \"routing\": {\n" +
                    "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                    "    \"rules\": [\n" +
                    "      {\n" +
                    "        \"ip\": [\n" +
                    "          \"1.1.1.1\"\n" +
                    "        ],\n" +
                    "        \"outboundTag\": \"proxy\",\n" +
                    "        \"port\": \"53\",\n" +
                    "        \"type\": \"field\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";

        }




        return sss;

    }
    public  void offwire2(){


        if(  isServiceRunning( this, MyService2.class)){
            Intent intent = new Intent(this,MyService2.class);

            stopService(intent);

        }
        Backend backend = PersistentConnectionProperties.getInstance().getBackend();

        Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

        try {
            if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {
                backend.setState(tunnel, DOWN, null);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }



  ///



    private void showColorDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this ,R.style.MyDialog);
        builder.setTitle("لیست مخازن");

        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);
        ListView listView = dialogView.findViewById(R.id.dialog_listview);

final AlertDialog dialog = builder.create();

        adapter2 = new Makhzanadapter(this, R.layout.show_name_makhzan, colorList);
        listView.setAdapter(adapter2);
        builder.setNeutralButton("اضافه کردن", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showColorDialog3();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Makhzan makhzan1;
                makhzan1 = colorList.get(position);


                if (position == 1){
                    if (isConnected) {
                        button.setText("درحال دریافت سرورها...");
                        button.setTextColor(Color.RED);

                        Timer timer = new Timer();
                        //  new RetrieveFeedTask().execute("https://www.google.com");

                        // Schedule a task to run after 3 seconds
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {

                                executor4.submit(() -> {


                                    SharedPreferences.Editor et = linq2.edit();
                                    String jooo = makhzan1.link;



                                    SharedPreferences.Editor editor = makhzan.edit();
                                    editor.putString("makhzan" ,"مخزن:"+makhzan1.name);
                                    editor.apply();


                                    et.putString(Linq2, jooo);
                                    et.apply();



                                    vmesserver.clear();
                                    vmessgreen.clear();

                                    number = getSharedPreferences("number", MODE_PRIVATE);
                                    SharedPreferences.Editor edito = number.edit();
                                    edito.putInt("number", vmesserver.size());
                                    edito.apply();

                                    runOnUiThread(() -> {

                                        adapter.notifyDataSetChanged();

                                    });


                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    MyThread myThread = new MyThread();
                                    myThread.start();


                                });

                                executor4.shutdown();


                            }
                        }, 2000);

                    } else {
                        Toast.makeText(context, "اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    if (isConnected) {




                        button.setText("درحال دریافت سرورها...");
                        button.setTextColor(Color.RED);
                        Timer timer = new Timer();
                        // new RetrieveFeedTask().execute("https://www.google.com");

                        // Schedule a task to run after 3 seconds
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {



                                executor4.submit(() -> {

                                    String url = makhzan1.link;

                                    if (isLinkValid("https://github.com")) {
                                        System.out.println("لینک معتبر است.");


                                        URL textUrl = null;
                                        String utext5 = null;

                                        System.out.println("سرور چینی");

                                        SharedPreferences.Editor editor = makhzan.edit();
                                        editor.putString("makhzan" ,"مخزن:"+makhzan1.name);

                                        editor.apply();




                                        // String[] linkstring = utext.split("https://");


                                            linq2 = getSharedPreferences("linq2", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor iran2edit = linq2.edit();

                                            iran2edit.putString(Linq2, makhzan1.link);
                                            iran2edit.apply();




                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }




                                            vmesserver.clear();
                                            vmessgreen.clear();

                                            number = getSharedPreferences("number", MODE_PRIVATE);
                                            SharedPreferences.Editor edito = number.edit();
                                            edito.putInt("number", vmesserver.size());
                                            edito.apply();
                                            runOnUiThread(() -> {

                                                adapter.notifyDataSetChanged();

                                            });


                                            MyThread myThread = new MyThread();
                                            myThread.start();





                                    } else {
                                        System.out.println("لینک معتبر نیست یا وجود ندارد.");
                                        runOnUiThread(() -> {
                                            button.setText("خطا در دریافت");

                                        });


                                    }


                                });
                                executor4.shutdown();


                            }
                        }, 2000);


                    } else {
                        Toast.makeText(context, "اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
                    }

                }


                Toast.makeText(context, "در حال دریافت", Toast.LENGTH_SHORT).show();

                dialog.dismiss();






            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==0 || position == 1 || position == 2){
                    Toast.makeText(context, "غیر قابل حذف", Toast.LENGTH_SHORT).show();

                }else {
                    colorList.remove(position);
                    Toast.makeText(context, "حذف شد", Toast.LENGTH_SHORT).show();
                    adapter2.notifyDataSetChanged();




                    int u = Integer.parseInt(shomaresh.getString("shomaresh" ,"0"))-1;





                    makhzansave.edit().clear().apply();


                    for (int i=0;i<colorList.size()-3;i++){

                        SharedPreferences.Editor  edit =  makhzansave.edit();
                        edit.putString("namemakhzan"+Integer.toString(3+i) , colorList.get(3+i).name);
                        edit.putString("linkmakhzan"+Integer.toString(3+i) , colorList.get(3+i).link);

                        edit.apply();

                    }
                    SharedPreferences.Editor  edit2 =  shomaresh.edit();
                    edit2.putString("shomaresh",Integer.toString(colorList.size()));
                    edit2.apply();







                }


                return true;
            }
        });






       dialog.show();





        ///









}


    private void showColorDialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this ,R.style.MyDialog);
        builder.setTitle("اضافه کردن مخزن");
        builder.setCancelable(true);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_color, null);
        builder.setView(dialogView);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        EditText nametxt = dialogView.findViewById(R.id.makhzanname);
        EditText linktxt = dialogView.findViewById(R.id.linkmmmm);



        builder.setPositiveButton("افزودن", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String namemkj = nametxt.getText().toString();
                String linkmh = linktxt.getText().toString();

                if (!namemkj.isEmpty() && !linkmh.isEmpty()) {



                    colorList.add(new Makhzan(namemkj ,linkmh));


                    for (int i=0;i<colorList.size()-3;i++){

                        SharedPreferences.Editor  edit =  makhzansave.edit();
                        edit.putString("namemakhzan"+Integer.toString(3+i) , colorList.get(3+i).name);
                        edit.putString("linkmakhzan"+Integer.toString(3+i) , colorList.get(3+i).link);

                        edit.apply();

                    }
                    SharedPreferences.Editor  edit2 =  shomaresh.edit();
                    edit2.putString("shomaresh",Integer.toString(colorList.size()));
                    edit2.apply();


                    //   adapter2.notifyDataSetChanged();
                    Toast.makeText(Servers.this, "اضافه شد", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Servers.this, "فیلدها خالی نباشد", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();




        ///









    }



}






