package com.example.mso_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

public class Window4 extends AppCompatActivity {

    Button win3Btn, win2Btn, win5Btn, win6Btn;

    Button startBtn, stopBtn, bindBtn, unbindBtn, incrementBtn, decrementBtn;
    TextView textStatus, textIntValue, textStrValue;
    Messenger mService = null;
    boolean mIsBound;
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyService.MSG_SET_INT_VALUE:
                    textIntValue.setText("Wynik: " + msg.arg1);
                    break;
                case MyService.MSG_SET_STRING_VALUE:
                    String str1 = msg.getData().getString("str1");
                    textStrValue.setText(str1);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = new Messenger(service);
            textStatus.setText(R.string.attached);
            try {
                Message msg = Message.obtain(null, MyService.MSG_REGISTER_CLIENT);
                msg.replyTo = mMessenger;
                mService.send(msg);
            }
            catch (RemoteException e) {
                // In this case the service has crashed before we could even do anything with it
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            textStatus.setText("Disconnected.");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window4);


        startBtn = (Button)findViewById(R.id.btnStart);
        stopBtn = (Button)findViewById(R.id.btnStop);
        bindBtn = (Button)findViewById(R.id.btnBind);
        unbindBtn = (Button)findViewById(R.id.btnUnbind);
        textStatus = (TextView)findViewById(R.id.textStatus);
        textIntValue = (TextView)findViewById(R.id.textIntValue);
        textStrValue = (TextView)findViewById(R.id.textStrValue);
        incrementBtn = (Button)findViewById(R.id.incrementBtn);
        decrementBtn = (Button)findViewById(R.id.decrementBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startService(new Intent(Window4.this, MyService.class));
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                doUnbindService();
                stopService(new Intent(Window4.this, MyService.class));
            }
        });

        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBindService();
            }
        });

        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUnbindService();
            }
        });
        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToService(1);
            }
        });
        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToService(-1);
            }
        });



        win2Btn = (Button)findViewById(R.id.win2Button);
        win2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window4.this, Window2.class);
                startActivityForResult(intent, 1);
            }
        });

        win3Btn = (Button)findViewById(R.id.win3Button);
        win3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window4.this, Window3.class);
                startActivityForResult(intent, 1);
            }
        });

        win5Btn = (Button)findViewById(R.id.win5Button);
        win5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window4.this, Window5.class);
                startActivityForResult(intent, 1);
            }
        });

        win6Btn = (Button)findViewById(R.id.win6Button);
        win6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window4.this, Window6.class);
                startActivityForResult(intent, 1);
            }
        });

    }


    private void sendMessageToService(int intvaluetosend) {
        if (mIsBound) {
            if (mService != null) {
                try {
                    Message msg = Message.obtain(null, MyService.MSG_SET_INT_VALUE, intvaluetosend, 0);
                    msg.replyTo = mMessenger;
                    mService.send(msg);
                }
                catch (RemoteException e) {
                }
            }
        }
    }


    void doBindService() {
        bindService(new Intent(this, MyService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
        textStatus.setText(R.string.binding);
    }
    void doUnbindService() {
        textStatus.setText(R.string.unbound);
        if (mIsBound) {
            if (mService != null) {
                try {
                    Message msg = Message.obtain(null, MyService.MSG_UNREGISTER_CLIENT);
                    msg.replyTo = mMessenger;
                    mService.send(msg);
                }
                catch (RemoteException e) {
                }
            }
            unbindService(mConnection);
            mIsBound = false;
            textStatus.setText(R.string.unbound);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            doUnbindService();
        }
        catch (Throwable t) {
            Log.e("MainActivity", "Failed to unbind from the service", t);
        }
    }

    @Override
    public void onStop() {

        super.onStop();
        doUnbindService();
    }


    public void onBackPressed() {
        Intent intent = new Intent(Window4.this, MainActivity.class);
        startActivityForResult(intent, 1);
    }


}