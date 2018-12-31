package com.dabinu.app.jot.models;

import java.io.Serializable;

/**
 * Created by Taslim Oseni on 12/30/18.
 */
public class NoteModel implements Serializable {

    public String note, date, time;

    public NoteModel(String note, String date, String time){
        this.note = note;
        this.date = date;
        this.time = time;
    }

    public NoteModel(){

    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}