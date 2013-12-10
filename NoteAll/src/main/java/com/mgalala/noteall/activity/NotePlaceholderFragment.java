package com.mgalala.noteall.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mgalala.noteall.model.Note;
import com.mgalala.noteall.service.NoteService;
import com.mgalala.noteall.util.FilePathUtil;


/**
 * A placeholder fragment containing a simple view.
 */
public class NotePlaceholderFragment extends Fragment implements View.OnClickListener {

    private static final String FILE_URI = "FILE_URI";
    public static final String NOTE_KEY = "NOTE_KEY";
    private NoteService noteService;
    private String fileURI;
    private Note note;

    public NotePlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_open_note, container, false);
        registerListeners(rootView);
        noteService = new NoteService();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        Uri uri = (Uri) bundle.get(Intent.EXTRA_STREAM);
        Log.i(NotePlaceholderFragment.class.getName(), "URI: " + uri.toString());
        fileURI = getPathFromUri(uri);
        Log.i(NotePlaceholderFragment.class.getName(), "URI: " + uri.getPath());

        savePreferences(FILE_URI, fileURI);

        note = noteService.getNoteByKey(FilePathUtil.encodeFilePath(fileURI), getActivity().getApplicationContext());
        if (note != null) {
            EditText editText = (EditText) rootView.findViewById(R.id.note_area);
            editText.setText(note.getNoteSummary());
        }

        //savePreferences(NOTE_KEY, noteKey);

        return rootView;
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MY_SHARED_PREF", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private String loadPreferences(String key) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MY_SHARED_PREF", getActivity().MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public String getPathFromUri(Uri uri) {
        if (uri.toString().startsWith("file://"))
            return uri.getPath();
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        getActivity().startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

    public void registerListeners(View rootView) {
        Button saveButton = (Button) rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        Button cancelButton = (Button) rootView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        String file = loadPreferences(FILE_URI);
//        String noteKey = loadPreferences(NOTE_KEY);
        EditText editText = (EditText) getActivity().findViewById(R.id.note_area);
        String notes = editText.getText().toString();

        switch (view.getId()) {
            case R.id.saveButton:
                //TODO: save note.
                saveNote(notes);
                break;
            case R.id.cancelButton:
                //TODO: cancel and exit.
                break;
        }
    }

    private void saveNote(String noteSummary) {
        if (note == null) {
            //it is a new note.
            note = new Note();
            note.setNoteCategoryId(1);
            note.setNoteSummary(noteSummary);
            note.setNoteKey(FilePathUtil.encodeFilePath(fileURI));
            noteService.createNote(note, getActivity().getApplicationContext());
        } else {
            //update the note.
            note.setNoteSummary(noteSummary);
            noteService.updateNote(note, getActivity().getApplicationContext());
        }


    }
}