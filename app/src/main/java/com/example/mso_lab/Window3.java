package com.example.mso_lab;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Window3 extends AppCompatActivity {

    TextView textView;
    String text = "";
    Date currentTime;
    Button addRecordBtn;
    String textRecordToAdd;
    EditText addText;
    DatabaseHelper db = new DatabaseHelper(this);


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window3);

        textView = (TextView) findViewById(R.id.textView);

        @SuppressLint("SimpleDateFormat") final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        currentTime = new Date(System.currentTimeMillis());


        db.addRecord(new Record("test123", formatter.format(currentTime)));

        addRecordBtn = (Button) findViewById(R.id.AddRecordBtn);
        addText = (EditText) findViewById(R.id.editText3);

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addText.setText("");

            }
        });

        addRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textRecordToAdd = addText.getText().toString();


                db.addRecord(new Record(textRecordToAdd, formatter.format(currentTime)));
                UpdateView();
            }
        });

        UpdateView();



    }

    private void UpdateView(){
        text = "";
        List<Record> records = db.getAllContacts();
        for (Record c : records){

            String log = "ID: " + c.getId() + ", TEXT: " + c.getTextRecord() + ", NUMBER: " + c.getPhone_number() + "\n";
            text = text.concat(log);
        }
        textView.setText(text);
    }

}