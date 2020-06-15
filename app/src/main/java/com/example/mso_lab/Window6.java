package com.example.mso_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Window6 extends AppCompatActivity {

    Button win3Btn, win4Btn, win5Btn, win2Btn, changeTextBtn;
    TextView textFromC;
    EditText originalText;
    TextView textToChange;

    public native String toUpperCase(String textToChange, int n);

    static {
        System.loadLibrary("lab6");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window6);

        win2Btn = (Button)findViewById(R.id.win2Button);
        win2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window6.this, Window2.class);
                startActivityForResult(intent, 1);
            }
        });

        win3Btn = (Button)findViewById(R.id.win3Button);
        win3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window6.this, Window3.class);
                startActivityForResult(intent, 1);
            }
        });

        win5Btn = (Button)findViewById(R.id.win5Button);
        win5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window6.this, Window5.class);
                startActivityForResult(intent, 1);
            }
        });

        win4Btn = (Button)findViewById(R.id.win4Button);
        win4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window6.this, Window4.class);
                startActivityForResult(intent, 1);
            }
        });

        originalText = (EditText) findViewById(R.id.original_text);
        textFromC = (TextView) findViewById(R.id.text_from_c);
        textToChange = (TextView) findViewById(R.id.text_to_change);

        changeTextBtn = (Button)findViewById(R.id.changed_text_button);
        changeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (originalText.getText() != null && !originalText.getText().toString().isEmpty());
                        textToChange.setText(originalText.getText().toString());
                        textFromC.setText(toUpperCase(originalText.getText().toString(), originalText.getText().toString().length()));
            }
        });


        //textFromC.setText(toUpperCase("SiemaToJestTest",15));

    }

    public void onBackPressed() {
        Intent intent = new Intent(Window6.this, MainActivity.class);
        startActivityForResult(intent, 1);
    }

}