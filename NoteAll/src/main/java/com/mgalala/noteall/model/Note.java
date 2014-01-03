package com.mgalala.noteall.model;

import java.util.Date;

/**
 * Created by galala on 11/20/13.
 */
public class Note {

    private long id;
    private String noteKey;
    private String noteSummary;
    private long noteCategoryId;

    private Date lastUpdated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteKey() {
        return noteKey;
    }

    public void setNoteKey(String noteKey) {
        this.noteKey = noteKey;
    }

    public String getNoteSummary() {
        return noteSummary;
    }

    public void setNoteSummary(String noteSummary) {
        this.noteSummary = noteSummary;
    }

    public long getNoteCategoryId() {
        return noteCategoryId;
    }

    public void setNoteCategoryId(long noteCategoryId) {
        this.noteCategoryId = noteCategoryId;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
