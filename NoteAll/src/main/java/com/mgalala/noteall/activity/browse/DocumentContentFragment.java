package com.mgalala.noteall.activity.browse;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mgalala.noteall.activity.R;
import com.mgalala.noteall.adapter.StableArrayAdapter;
import com.mgalala.noteall.model.Note;
import com.mgalala.noteall.service.NoteService;
import com.mgalala.noteall.util.FilePathUtil;
import com.mgalala.noteall.util.IntentTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DocumentContentFragment extends AbstractBrowseFragment {
    public static final String MENU_TITLE = "MENU_TITLE";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static NoteService noteService;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DocumentContentFragment newInstance(int sectionNumber, String mCurrentSelectedCategory) {
        DocumentContentFragment fragment = new DocumentContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(MENU_TITLE, mCurrentSelectedCategory);
        fragment.setArguments(args);

        noteService = new NoteService();

        return fragment;
    }

    public DocumentContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_document_notes, container, false);
        List<Note> notes = noteService.getNotesByCategoryKey(R.string.document_category, getActivity().getApplicationContext());
        if (notes == null || notes.size() == 0) {
            ((TextView) rootView.findViewById(R.id.noNotes)).setText(R.string.no_notes_available);
        } else {
            List<String> notesKies = new ArrayList<String>();
            for (Note note : notes) {
                notesKies.add(FilePathUtil.decodeFilePath(note.getNoteKey()));
            }
            final ListView listView = (ListView) rootView.findViewById(R.id.listview);
            final StableArrayAdapter adapter = new StableArrayAdapter(this.getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1, notesKies);
            listView.setAdapter(adapter);
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public String getIntentType() {
        return IntentTypeEnum.DOCUMENT.getType();
    }
}
