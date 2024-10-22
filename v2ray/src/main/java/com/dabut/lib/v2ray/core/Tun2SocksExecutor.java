package com.dabut.lib.v2ray.core;

import android.content.Context;

import com.dabut.lib.v2ray.interfaces.Tun2SocksListener;
import com.dabut.lib.v2ray.utils.V2rayConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Tun2SocksExecutor {
    private final Tun2SocksListener tun2SocksListener;
    private Process tun2SocksProcess;

    public Tun2SocksExecutor(final Tun2SocksListener tun2SocksListener) {
        this.tun2SocksListener = tun2SocksListener;
    }

    public void stopTun2Socks() {
        try {
            if (tun2SocksProcess != null) {
                tun2SocksProcess.destroy();
                tun2SocksProcess = null;
            }
        } catch (Exception ignore) {}
        tun2SocksListener.OnTun2SocksHasMassage(V2rayConstants.CORE_STATES.STOPPED, "T2S -> Tun2Socks Stopped.");
    }

    public boolean isTun2SucksRunning() {
        return tun2SocksProcess != null;
    }

    public void run(final Context context, final int socksPort, final int localDnsPort) {
        ArrayList<String> tun2SocksCommands = new ArrayList<>(Arrays.asList(new File(context.getApplicationInfo().nativeLibraryDir, "libtun2socks.so").getAbsolutePath(),
                "--netif-ipaddr", "26.26.26.2",
                "--netif-netmask", "255.255.255.252",
                "--socks-server-addr", "127.0.0.1:" + socksPort,
                "--tunmtu", "1500",
                "--sock-path", "sock_path",
                "--enable-udprelay",
                "--loglevel", "error"));
        if (localDnsPort > 0 && localDnsPort < 65000) {
            tun2SocksCommands.add("--dnsgw");
            tun2SocksCommands.add("127.0.0.1:" + localDnsPort);
        }
        tun2SocksListener.OnTun2SocksHasMassage(V2rayConstants.CORE_STATES.IDLE, "T2S Start Commands => " + tun2SocksCommands);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(tun2SocksCommands);
            tun2SocksProcess = processBuilder.directory(context.getApplicationContext().getFilesDir()).start();
            new Thread(() -> {
                Scanner scanner = new Scanner(tun2SocksProcess.getInputStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    tun2SocksListener.OnTun2SocksHasMassage(V2rayConstants.CORE_STATES.RUNNING, "T2S -> " + line);
                }
            }, "t2s_output_thread").start();
            new Thread(() -> {
                Scanner scanner = new Scanner(tun2SocksProcess.getErrorStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    tun2SocksListener.OnTun2SocksHasMassage(V2rayConstants.CORE_STATES.RUNNING, "T2S => " + line);
                }
            }, "t2s_error_thread").start();
            new Thread(() -> {
                try {
                    tun2SocksProcess.waitFor();
                } catch (InterruptedException e) {
                    tun2SocksListener.OnTun2SocksHasMassage(V2rayConstants.CORE_STATES.STOPPED, "T2S -> Tun2socks Interrupted!" + e);
                    tun2SocksProcess.destroy();
                    tun2SocksProcess = null;
                }
            }, "t2s_main_thread").start();
        } catch (Exception e) {
            tun2SocksListener.OnTun2SocksHasMassage(V2rayConstants.CORE_STATES.IDLE, "Tun2socks Run Error =>> " + e);
            tun2SocksProcess.destroy();
            tun2SocksProcess = null;
        }
    }

}
