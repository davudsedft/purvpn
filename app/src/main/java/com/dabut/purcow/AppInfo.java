package com.dabut.purcow;

import android.graphics.drawable.Drawable;
public class AppInfo {
    public Drawable icon;
    public String name;
    public  boolean check;
    public String fam;


    public AppInfo(Drawable icon, String fam ,String name, boolean check) {
        this.icon = icon;
        this.name = name;
        this.fam = fam;

        this.check = check;

    }


}
