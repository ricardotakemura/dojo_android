package br.com.ciandt.blocodenotas.app.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wbatista on 6/1/16.
 */
public class Note {

    @SerializedName("note")
    private String note;

    @SerializedName("objectId")
    private String serverObjectId;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getServerObjectId() {
        return serverObjectId;
    }

    public void setServerObjectId(String serverObjectId) {
        this.serverObjectId = serverObjectId;
    }

    @Override
    public String toString() {
        return this.note;
    }
}