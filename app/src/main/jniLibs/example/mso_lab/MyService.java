package com.example.mso_lab;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;


public class MyService extends Service {


    private Timer timer = new Timer();
    private int counter = 0, incrementby = 1;
    private static boolean isRunning = false;

    ArrayList<Messenger> mClients = new ArrayList<Messenger>(); // Keeps track of all current registered clients.
    int mValue = 0; // Holds last value set by a client.
    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2;
    static final int MSG_SET_INT_VALUE = 3;
    static final int MSG_SET_STRING_VALUE = 4;
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    break;
                case MSG_SET_INT_VALUE:
                    incrementby = msg.arg1;
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    private void sendMessageToUI(int intvaluetosend) {
        for (int i=mClients.size()-1; i>=0; i--) {
            try {
                mClients.get(i).send(Message.obtain(null, MSG_SET_INT_VALUE, intvaluetosend, 0));

                Bundle b = new Bundle();
                String op;
                if (incrementby > 0) {
                    op = "Zwiększanie liczby o ";
                } else {
                    op = "Zmniejszanie liczby o ";
                }
                b.putString("str1", op + Math.abs(incrementby));
                Message msg = Message.obtain(null, MSG_SET_STRING_VALUE);
                msg.setData(b);
                mClients.get(i).send(msg);

            }
            catch (RemoteException e) {
                mClients.remove(i);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyService", "Service Started.");
        timer.scheduleAtFixedRate(new TimerTask(){ public void run() {onTimerTick();}}, 0, 100L);
        isRunning = true;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "Received start id " + startId + ": " + intent);
        return START_STICKY;
    }

    public static boolean isRunning()
    {
        return isRunning;
    }


    private void onTimerTick() {
        Log.i("TimerTick", "Timer doing work." + counter);
        try {
            counter += incrementby;
            sendMessageToUI(counter);

        }
        catch (Throwable t) { //you should always ultimately catch all exceptions in timer tasks.
            Log.e("TimerTick", "Timer Tick Failed.", t);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {timer.cancel();}
        counter=0;
        Log.i("MyService", "Service Stopped.");
        isRunning = false;
    }


}
