package com.example.mso_lab;

public class Record {

    int id;
    String textRecord;
    String phone_number;


    public Record(){

    }

    public Record(String textRecord, String phone_number) {
        this.textRecord = textRecord;
        this.phone_number = phone_number;
    }

    public Record(int id, String textRecord, String phone_number) {
        this.id = id;
        this.textRecord = textRecord;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextRecord() {
        return textRecord;
    }

    public void setTextRecord(String textRecord) {
        this.textRecord = textRecord;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
