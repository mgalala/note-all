package com.mgalala.noteall.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mgalala.noteall.sqllite.entity.NoteCategoryEntity;
import com.mgalala.noteall.sqllite.entity.NoteEntity;

/**
 * Created by galala on 11/20/13.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "noteall.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        NoteCategoryEntity.createNoteCategoryEntity(db);
        NoteEntity.createNoteEntity(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        NoteEntity.dropNoteEntity(db);
        NoteCategoryEntity.dropNoteCategoryEntity(db);
        onCreate(db);
    }
}
