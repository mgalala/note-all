package com.mgalala.noteall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mgalala.noteall.model.MobileResource;
import com.mgalala.noteall.model.Note;
import com.mgalala.noteall.service.NoteService;
import com.mgalala.noteall.service.URIResolverFactory;
import com.mgalala.noteall.util.FilePathUtil;


/**
 * A placeholder fragment containing a simple view.
 */
public class NotePlaceholderFragment extends Fragment implements View.OnClickListener {

    private static final String FILE_URI = "FILE_URI";
    public static final String NOTE_KEY = "NOTE_KEY";
    private NoteService noteService;
    private URIResolverFactory uriResolverFactory = new URIResolverFactory();
    private MobileResource mobileResource;
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
        String intentType = intent.getType();

        Bundle bundle = intent.getExtras();
        if (bundle == null || bundle.size() == 0) {
            Log.e(NotePlaceholderFragment.class.getName(), "The intent contains no paramters.");
            return rootView;
        }

        mobileResource = uriResolverFactory.getResourceURI(intentType, bundle, getActivity());
        if (mobileResource == null || mobileResource.getUri() == null) {
            Log.e(NotePlaceholderFragment.class.getName(), "Can not get uri from the intent resource bundle");
            return rootView;
        }
        Log.i(NotePlaceholderFragment.class.getName(), "URI Path: " + mobileResource.getUri());
        note = noteService.getNoteByKey(FilePathUtil.encodeFilePath(mobileResource.getUri()), getActivity().getApplicationContext());
        if (note != null) {
            EditText editText = (EditText) rootView.findViewById(R.id.note_area);
            editText.setText(note.getNoteSummary());
        }

        return rootView;
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
            note.setNoteKey(FilePathUtil.encodeFilePath(mobileResource.getUri()));
            noteService.createNote(note, getActivity().getApplicationContext());
        } else {
            //update the note.
            note.setNoteSummary(noteSummary);
            noteService.updateNote(note, getActivity().getApplicationContext());
        }
    }
}