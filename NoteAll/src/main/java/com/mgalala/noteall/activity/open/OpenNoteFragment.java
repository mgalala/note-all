package com.mgalala.noteall.activity.open;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mgalala.noteall.activity.R;
import com.mgalala.noteall.model.MobileResource;
import com.mgalala.noteall.model.Note;
import com.mgalala.noteall.service.NoteService;
import com.mgalala.noteall.service.URIResolverFactory;
import com.mgalala.noteall.util.Constants;
import com.mgalala.noteall.util.FilePathUtil;


/**
 * A placeholder fragment containing a simple view.
 */
public class OpenNoteFragment extends Fragment {

    private static final String FILE_URI = "FILE_URI";
    public static final String NOTE_KEY = "NOTE_KEY";
    private NoteService noteService;
    private URIResolverFactory uriResolverFactory = new URIResolverFactory();
    private MobileResource mobileResource;
    private Note note;

    public OpenNoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_open_note, container, false);
        noteService = new NoteService();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle == null || bundle.size() == 0) {
            Log.e(OpenNoteFragment.class.getName(), "The intent contains no paramters.");
            return rootView;
        }

        if (bundle.get(Constants.OPEN_NOTE_FROM_BROWSE) == null ||
                Constants.OPEN_NOTE_FROM_SHARE.equals(bundle.getString(Constants.OPEN_NOTE_FROM_SHARE))) {
            String intentType = intent.getType();
            mobileResource = uriResolverFactory.getResourceURIByBundle(intentType, bundle, getActivity());
        } else if (bundle.get(Constants.OPEN_NOTE_FROM_BROWSE) != null &&
                Constants.OPEN_NOTE_FROM_BROWSE.equals(bundle.getString(Constants.OPEN_NOTE_FROM_BROWSE))) {
            String noteKey = bundle.getString(Constants.NOTE_KEY);
            mobileResource = uriResolverFactory.getResourceByKey(bundle.getString(Constants.INTENT_TYPE), bundle.getString(Constants.NOTE_KEY));
        }

        if (mobileResource == null || mobileResource.getUri() == null) {
            Log.e(OpenNoteFragment.class.getName(), "Can not get uri from the intent resource bundle");
            return rootView;
        }
        Log.i(OpenNoteFragment.class.getName(), "URI Path: " + mobileResource.getUri());
        note = noteService.getNoteByKey(FilePathUtil.encodeFilePath(mobileResource.getUri()), getActivity().getApplicationContext());
        if (note != null) {
            EditText editText = (EditText) rootView.findViewById(R.id.note_area);
            editText.setText(note.getNoteSummary());
        }
        return rootView;
    }

    public void onMenuItemClick(MenuItem menuItem) {
        EditText editText = (EditText) getActivity().findViewById(R.id.note_area);
        String notes = editText.getText().toString();
        switch (menuItem.getItemId()) {
            case R.id.saveButton:
                saveNote(notes);
                break;
            case R.id.cancelButton:
                this.getActivity().finish();
                break;
        }
    }

    private void saveNote(String noteSummary) {
        if (note == null) {
            //it is a new note.
            note = new Note();
            note.setNoteCategoryId(mobileResource.getType());
            note.setNoteSummary(noteSummary);
            note.setNoteKey(FilePathUtil.encodeFilePath(mobileResource.getUri()));
            noteService.createNote(note, getActivity().getApplicationContext());
        } else {
            //update the note.
            note.setNoteSummary(noteSummary);
            noteService.updateNote(note, getActivity().getApplicationContext());
        }
        this.getActivity().finish();
    }
}