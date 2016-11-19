package com.aliakseipilko.idlemonitor;

import android.os.Handler;
import android.util.Log;

public class IdleTimer {

    private static final String TAG = "IdleTimer";
    private static IdleTimer ourInstance;
    private static DeviceIdleListener callback;
    private Handler timer;
    private Runnable task;

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

    public void setTimer(long millis) {
        nullify();
        setTask();
        timer = new Handler();
        //After 5 mins inactivity
        timer.postDelayed(task, millis);
        Log.d(TAG, "Normal timer set");
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

    public void registerIdleCallback(DeviceIdleListener callback) {
        IdleTimer.callback = callback;
    }

    public interface DeviceIdleListener {
        void onDeviceStateIdle();
    }
}
