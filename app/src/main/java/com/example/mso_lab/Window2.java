package com.example.mso_lab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

public class Window2 extends AppCompatActivity {

    Button win3Btn, win4Btn, win5Btn, win6Btn;
    Button btnDraw;
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window2);

        btnDraw = (Button)findViewById(R.id.drawButton);
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView = new DrawView(Window2.this);
                drawView.setBackgroundColor(Color.WHITE);
                setContentView(drawView);
            }
        });

        win3Btn = (Button)findViewById(R.id.win3Button);
        win3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window2.this, Window3.class);
                startActivityForResult(intent, 1);
            }
        });

        win4Btn = (Button)findViewById(R.id.win4Button);
        win4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window2.this, Window4.class);
                startActivityForResult(intent, 1);
            }
        });

        win5Btn = (Button)findViewById(R.id.win5Button);
        win5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window2.this, Window5.class);
                startActivityForResult(intent, 1);
            }
        });

        win6Btn = (Button)findViewById(R.id.win6Button);
        win6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window2.this, Window6.class);
                startActivityForResult(intent, 1);
            }
        });



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void onBackPressed() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Powrót do okna głównego");
        adb.setPositiveButton("Tak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Window2.this, MainActivity.class);
                        startActivityForResult(intent, 1);
                    }
                });
        adb.setNegativeButton("Nie",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        adb.create().show();
    }


}