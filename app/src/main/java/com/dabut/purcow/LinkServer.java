package com.dabut.purcow;

public class LinkServer {

    public String name;
    public String fam;
    public String ping;
    public int color;



    public LinkServer(String name ,String fam ,String ping , int color) {
        this.name = name;
        this.fam = fam;
        this.ping = ping;
        this.color = color;


    }

    public int getColor() {
        return color;
    }

    public String getFam() {
        return fam;
    }

    public String getName() {
        return name;
    }

    public String getPing() {
        return ping;
    }
}
