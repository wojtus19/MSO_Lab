package com.example.mso_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Window5 extends AppCompatActivity {

    Button win3Btn, win4Btn, win2Btn, win6Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window5);



        win2Btn = (Button)findViewById(R.id.win2Button);
        win2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window5.this, Window2.class);
                startActivityForResult(intent, 1);
            }
        });

        win3Btn = (Button)findViewById(R.id.win3Button);
        win3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window5.this, Window3.class);
                startActivityForResult(intent, 1);
            }
        });

        win4Btn = (Button)findViewById(R.id.win4Button);
        win4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window5.this, Window4.class);
                startActivityForResult(intent, 1);
            }
        });

        win6Btn = (Button)findViewById(R.id.win6Button);
        win6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window5.this, Window6.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(Window5.this, MainActivity.class);
        startActivityForResult(intent, 1);
    }
}