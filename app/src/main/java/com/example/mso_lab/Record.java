package com.example.mso_lab;

public class Record {

    int id;
    String textRecord;
    String recordTime;


    public Record(){

    }

    public Record(String textRecord, String recordTime) {
        this.textRecord = textRecord;
        this.recordTime = recordTime;
    }

    public Record(int id, String textRecord, String recordTime) {
        this.id = id;
        this.textRecord = textRecord;
        this.recordTime = recordTime;
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

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String phone_number) {
        this.recordTime = phone_number;
    }
}
