package com.mgalala.noteall.sqllite.entity;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by galala on 11/20/13.
 */
public class NoteCategoryEntity {
    public static final String TABLE_NAME = "note_category";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "category_name";
    public static String[] COLUMNS = {COLUMN_ID, COLUMN_NAME};
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null); ";

    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void createNoteCategoryEntity(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
        insertTestCategories(sqLiteDatabase);
    }

    public static void dropNoteCategoryEntity(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_DROP);
    }

    private static void insertTestCategories(SQLiteDatabase database) {
        String insertSQL = "insert into " + TABLE_NAME + "(" + COLUMN_NAME + ") VALUES ('%s')";
        database.execSQL(String.format(insertSQL, "Photos"));
        database.execSQL(String.format(insertSQL, "Videos"));
        database.execSQL(String.format(insertSQL, "Documents"));
        database.execSQL(String.format(insertSQL, "Internet"));
    }
}
