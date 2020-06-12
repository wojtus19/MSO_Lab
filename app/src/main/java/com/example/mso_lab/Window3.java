package com.example.mso_lab;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import java.util.Map;

public class Window3 extends AppCompatActivity {

    Date currentTime;
    Button addRecordBtn;
    String textRecordToAdd;
    EditText addText;
    DatabaseHelper db = new DatabaseHelper(this);
    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window3);


        listView= (ListView) findViewById(R.id.dbListView);

        @SuppressLint("SimpleDateFormat") final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        currentTime = new Date(System.currentTimeMillis());


        db.addRecord(new Record("test123", formatter.format(currentTime)));


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

}