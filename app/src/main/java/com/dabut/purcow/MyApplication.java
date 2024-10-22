package com.dabut.purcow;
import android.app.Application;
import android.content.Intent;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // تعریف UncaughtExceptionHandler برای کنترل کرش برنامه
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                // اجرای عملیات مرتبط با کرش برنامه

                // مثلاً باز کردن دوباره اپلیکیشن
                restartApp();
            }
        });
    }

    private void restartApp() {
        // کدی برای باز کردن دوباره اپلیکیشن
        Intent intent = new Intent(this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
