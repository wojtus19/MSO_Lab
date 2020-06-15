package com.example.mso_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Window6 extends AppCompatActivity {

    Button win3Btn, win4Btn, win5Btn, win2Btn, changeTextBtn, addBtn, sortBtn;
    TextView textFromC, notSortedArrayTextView, sortedArrayTextView;
    EditText addNumberText;
    EditText originalText;
    TextView textToChange;
    int[] sortedNumbers;
    ArrayList<Integer> numbersToSort = new ArrayList<Integer>();
    boolean firsttoAdd = true;

    // MSO_Lab\jni
    public native String toUpperCase(String textToChange, int n);
    public native int[] Sort(int[] arr);

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

        addBtn = (Button) findViewById(R.id.addNumberButton);
        sortBtn = (Button) findViewById(R.id.sortButton);
        addNumberText = (EditText) findViewById(R.id.addNumber);
        notSortedArrayTextView = (TextView) findViewById((R.id.notSortedArray));
        sortedArrayTextView = (TextView) findViewById((R.id.sortedArray));


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addNumberText.getText() != null && !addNumberText.getText().toString().isEmpty());
                    String val = addNumberText.getText().toString();
                    int intVal = Integer.parseInt(val);
                    numbersToSort.add(intVal);
                    if(firsttoAdd){
                        notSortedArrayTextView.setText(val);
                        firsttoAdd = false;
                    } else {
                        notSortedArrayTextView.setText(notSortedArrayTextView.getText().toString() + ", " + val);
                    }
            }
        });

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!numbersToSort.isEmpty()){
                    int[] convertedNumbers = convertIntegerToInt(numbersToSort);
                   sortedNumbers = Sort(convertedNumbers);
                   String sortedText = "";
                   for(int i = 0; i < sortedNumbers.length; i++){
                       if (i == 0){
                           sortedText += String.valueOf(sortedNumbers[i]);
                       } else {
                           sortedText += ", " + String.valueOf(sortedNumbers[i]);
                       }
                   }
                   sortedArrayTextView.setText(sortedText);
                   numbersToSort.clear();
                }
            }
        });


        //textFromC.setText(toUpperCase("SiemaToJestTest",15));

    }


    private int[] convertIntegerToInt(ArrayList<Integer> list){
            int[] ret = new int[list.size()];
            for (int i =0;i<ret.length;i++){
                ret[i] = list.get(i);
            }
            return ret;
    }

    public void onBackPressed() {
        Intent intent = new Intent(Window6.this, MainActivity.class);
        startActivityForResult(intent, 1);
    }

}