package com.example.mso_lab;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button win2Btn, win3Btn, win4Btn, win5Btn, win6Btn;
    String Texthere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        win2Btn = (Button)findViewById(R.id.win2Button);
        win2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window2.class);
                startActivityForResult(intent, 1);
            }
        });

        win3Btn = (Button)findViewById(R.id.win3Button);
        win3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window3.class);
                startActivityForResult(intent, 1);
            }
        });

        win4Btn = (Button)findViewById(R.id.win4Button);
        win4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window4.class);
                startActivityForResult(intent, 1);
            }
        });

        win5Btn = (Button)findViewById(R.id.win5Button);
        win5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window5.class);
                startActivityForResult(intent, 1);
            }
        });

        win6Btn = (Button)findViewById(R.id.win6Button);
        win6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Window6.class);
                startActivityForResult(intent, 1);
            }
        });


    }

    public void onBackPressed() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Czy zakończyć?");
        adb.setPositiveButton("Tak zakończ.",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
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