package com.mgalala.noteall.service;

import android.content.Context;

import com.mgalala.noteall.dao.NoteDAO;
import com.mgalala.noteall.model.Note;

import java.util.List;

/**
 * Created by mgalala on 11/26/13.
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

    public List<Note> getNotesByCategoryKey(Integer categoryKey, Context context) {
        if (categoryKey == null) {
            return null;
        }
        noteDAO = new NoteDAO(context);
        noteDAO.open();
        List<Note> notes = noteDAO.getNotesByCategoryKey(categoryKey);
        noteDAO.close();
        return notes;
    }

    public void updateNote(Note note, Context context) {
        noteDAO = new NoteDAO(context);
        noteDAO.open();
        noteDAO.updateNote(note);
        noteDAO.close();
    }
}