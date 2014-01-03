package com.mgalala.noteall.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mgalala.noteall.model.Note;
import com.mgalala.noteall.sqllite.SQLiteHelper;
import com.mgalala.noteall.sqllite.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by galala on 11/20/13.
 */
public class NoteDAO {
    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public NoteDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Note insertNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteEntity.COLUMN_NOTE_KEY, note.getNoteKey());
        values.put(NoteEntity.COLUMN_NOTE_SUMMARY, note.getNoteSummary());
        values.put(NoteEntity.COLUMN_CATEGORY_ID, note.getNoteCategoryId());
        long insertId = database.insert(NoteEntity.TABLE_NAME, null, values);
        note.setId(insertId);
////        //TODO: To be removed
        Cursor cursor = database.query(NoteEntity.TABLE_NAME,
                NoteEntity.COLUMNS, NoteEntity.COLUMN_ID + " = " + insertId, null, null, null, null);
////        cursor.moveToFirst();
////        Note newNote = cursorToNote(cursor);
//
//        cursor.close();
        return note;
    }

    public Note getNoteByKey(String key) {
        Cursor cursor = database.query(NoteEntity.TABLE_NAME,
                NoteEntity.COLUMNS, NoteEntity.COLUMN_NOTE_KEY + " = '" + key + "' ", null, null, null, null);
        cursor.moveToFirst();
        Note note = cursorToNote(cursor);
        cursor.close();
        return note;
    }

    public List<Note> getNotesByCategoryKey(Integer categoryKey) {
        String query = "SELECT * FROM " + NoteEntity.TABLE_NAME +
                " WHERE " + NoteEntity.COLUMN_CATEGORY_ID + " =?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(categoryKey)});
        List<Note> notes = new ArrayList<Note>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        return notes;
    }

    private Note cursorToNote(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        Note noteCategory = new Note();
        noteCategory.setId(cursor.getLong(0));
        noteCategory.setNoteKey(cursor.getString(1));
        noteCategory.setNoteSummary(cursor.getString(2));
        noteCategory.setNoteCategoryId(cursor.getLong(3));
        return noteCategory;
    }

    public void updateNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteEntity.COLUMN_NOTE_SUMMARY, note.getNoteSummary());

        database.update(NoteEntity.TABLE_NAME, values, NoteEntity.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }
}
