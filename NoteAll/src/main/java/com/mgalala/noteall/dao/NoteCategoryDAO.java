package com.mgalala.noteall.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mgalala.noteall.model.NoteCategory;
import com.mgalala.noteall.sqllite.SQLiteHelper;
import com.mgalala.noteall.sqllite.entity.NoteCategoryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by galala on 11/20/13.
 */
public class NoteCategoryDAO {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public NoteCategoryDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<NoteCategory> getAllNoteCategories() {
        List<NoteCategory> noteCategories = new ArrayList<NoteCategory>();
        Cursor cursor = database.query(NoteCategoryEntity.TABLE_NAME,
                NoteCategoryEntity.COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NoteCategory noteCategory = cursorToComment(cursor);
            noteCategories.add(noteCategory);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return noteCategories;
    }

    public List<String> getAllNoteCategoriesNames() {
        List<String> noteCategories = new ArrayList<String>();
        Cursor cursor = database.query(NoteCategoryEntity.TABLE_NAME,
                new String[]{NoteCategoryEntity.COLUMN_NAME}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            noteCategories.add(cursor.getString(0));
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return noteCategories;
    }

    private NoteCategory cursorToComment(Cursor cursor) {
        NoteCategory noteCategory = new NoteCategory();
        noteCategory.setId(cursor.getLong(0));
        noteCategory.setCategoryName(cursor.getString(1));
        return noteCategory;
    }
}
