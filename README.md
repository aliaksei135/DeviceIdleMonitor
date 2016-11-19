# DeviceIdleMonitor
A simple library to invoke a callback when device idle time exceeds more than a specified limit.

This is easy to implement without a library, this is just so I can play around with Maven :)

#Usage
Grab it from Maven:

    compile 'com.github.aliaksei135:deviceidlemonitor:v0.3'

Implement the DeviceIdleListener interface:

    ... extends IdleTimer.DeviceIdleListener ...
    
    
    @Override
    public void onDeviceStateIdle(){
      //Actions when device is idle
    }
    
Get an IdleTimer object and register the callback:

    IdleTimer timer = IdleTimer.getInstance();
    timer.registerCallback(this);
    
Start the Timer when needed:

    //Start timer for 5 minutes (300000millisecs)
    timer.startTimer(300000);
    
To stop the Timer completely:

    timer.nullify();
    
To restart the Timer with the most recently used duration:

    timer.restartTimer();
    
