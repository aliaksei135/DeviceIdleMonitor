package com.aliakseipilko.idlemonitor;

import android.os.Handler;
import android.util.Log;

public class IdleTimer {

    private static final String TAG = "IdleTimer";
    private static IdleTimer ourInstance;
    private static DeviceIdleListener callback;
    private Handler timer;
    private Runnable task;
    private long duration;

    private IdleTimer() {
        //Nothing needed here
    }

    public static IdleTimer getInstance() {
        if (ourInstance == null) {
            ourInstance = new IdleTimer();
        }

        return ourInstance;
    }

    private static void setDeviceStateIdle() {
        Log.d(TAG, "Device state idle");
        callback.onDeviceStateIdle();
    }

    public void restart() {
        start(duration);
    }

    public void start(long millis) {
        nullify();
        setTask();
        duration = millis;
        timer = new Handler();
        timer.postDelayed(task, millis);
        Log.d(TAG, "Idle timer set for " + millis + " millis");
    }

    private void setTask() {
        task = new Runnable() {
            @Override
            public void run() {
                setDeviceStateIdle();
            }
        };
    }

    private void nullify() {
        if (task != null) {
            task = null;
        }
        if (timer != null) {
            //Remove all callbacks and messages
            timer.removeCallbacksAndMessages(null);
            timer = null;
            Log.d(TAG, "Timer nullified");
        }
    }

    public void registerCallback(DeviceIdleListener callback) {
        IdleTimer.callback = callback;
    }

    public interface DeviceIdleListener {
        void onDeviceStateIdle();
    }
}
