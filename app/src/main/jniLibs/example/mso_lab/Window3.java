package com.example.mso_lab;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Window3 extends AppCompatActivity {

    Button win2Btn, win4Btn, win5Btn, win6Btn;
    Date currentTime;
    Button addRecordBtn, listViewBtn;
    String textRecordToAdd;
    EditText addText;
    DatabaseHelper db = new DatabaseHelper(this);
    ListView listView;
    boolean showList = false;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window3);

        win2Btn = (Button)findViewById(R.id.win2Button);
        win2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window3.this, Window2.class);
                startActivityForResult(intent, 1);
            }
        });

        win4Btn = (Button)findViewById(R.id.win4Button);
        win4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window3.this, Window4.class);
                startActivityForResult(intent, 1);
            }
        });

        win5Btn = (Button)findViewById(R.id.win5Button);
        win5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window3.this, Window5.class);
                startActivityForResult(intent, 1);
            }
        });

        win6Btn = (Button)findViewById(R.id.win6Button);
        win6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Window3.this, Window6.class);
                startActivityForResult(intent, 1);
            }
        });




        listView= (ListView) findViewById(R.id.dbListView);
        listViewBtn = (Button) findViewById(R.id.listViewBtn);
        listView.setVisibility(View.GONE);

        @SuppressLint("SimpleDateFormat") final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        currentTime = new Date(System.currentTimeMillis());


        addRecordBtn = (Button) findViewById(R.id.AddRecordBtn);
        addText = (EditText) findViewById(R.id.editText3);

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addText.setText("");
                addText.setOnClickListener(null);
            }
        });

        addRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textRecordToAdd = addText.getText().toString();
                currentTime = new Date(System.currentTimeMillis());
                db.addRecord(new Record(textRecordToAdd, formatter.format(currentTime)));
                UpdateView();
            }
        });

        listViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showList){
                    listView.setVisibility(View.VISIBLE);
                    listViewBtn.setText("Ukryj tabelę");
                    showList = true;
                }
                else {
                    listView.setVisibility(View.GONE);
                    listViewBtn.setText("Wyświetl Tabelę");
                    showList = false;
                }
            }
        });

        UpdateView();

    }

    private void UpdateView(){
        List<Record> records = db.getAllContacts();

       //HashMap<String, String> recordsMap = new HashMap<>();

        List<String> texts = new ArrayList<>();
        List<String> times = new ArrayList<>();

        Cursor cursor;
        for (Record c : records){

            //recordsMap.put( c.getTextRecord(), c.getRecordTime());

            texts.add(c.getTextRecord());
            times.add(c.getRecordTime());

        }

        List<HashMap<String, String>> listRecords = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listRecords, R.layout.list_item,
                new String[] {"Text", "Time"},
                new int[] {R.id.text1, R.id.text2});
        Iterator it = texts.iterator();
        Iterator it2 = times.iterator();
        while (it.hasNext()){
            HashMap<String, String> resultMap = new HashMap<>();
            //Map.Entry pair = (Map.Entry)it.next();
            resultMap.put("Text", it.next().toString());
            resultMap.put("Time", it2.next().toString());
            listRecords.add(resultMap);
        }
        listView.setAdapter(adapter);

        //@SuppressWarnings("rawtypes") ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, arrayList);
        //listView.setAdapter(arrayAdapter);
    }


    public void onBackPressed() {
           Intent intent = new Intent(Window3.this, MainActivity.class);
           startActivityForResult(intent, 1);
    }


}