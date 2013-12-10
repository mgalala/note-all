package com.mgalala.noteall.sqllite.entity;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by galala on 11/20/13.
 */
public class NoteEntity {
    public static final String TABLE_NAME = "note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTE_KEY = "note_key";
    public static final String COLUMN_NOTE_SUMMARY = "note_summary";
    public static final String COLUMN_CATEGORY_ID = "note_cat";
    public static String[] COLUMNS = {COLUMN_ID, COLUMN_NOTE_KEY, COLUMN_NOTE_SUMMARY, COLUMN_CATEGORY_ID};
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NOTE_KEY + " text not null, "
            + COLUMN_NOTE_SUMMARY + " text not null, "
            + COLUMN_CATEGORY_ID + " INTEGER " +
            ");";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void createNoteEntity(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    public static void dropNoteEntity(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_DROP);
    }
}
