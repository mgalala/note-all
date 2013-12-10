package com.mgalala.noteall.service;

import android.content.Context;

import com.mgalala.noteall.dao.NoteDAO;
import com.mgalala.noteall.model.Note;

/**
 * Created by galala on 11/26/13.
 */
public class NoteService {

    private NoteDAO noteDAO;

    public void createNote(Note note, Context context) {
        noteDAO = new NoteDAO(context);
        noteDAO.open();
        noteDAO.insertNote(note);
        noteDAO.close();
    }

    public Note getNoteByKey(String key, Context context) {
        noteDAO = new NoteDAO(context);
        noteDAO.open();
        Note note = noteDAO.getNoteByKey(key);
        noteDAO.close();
        return note;
    }

    public void updateNote(Note note, Context context) {
        noteDAO = new NoteDAO(context);
        noteDAO.open();
        noteDAO.updateNote(note);
        noteDAO.close();
    }
}