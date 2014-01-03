package com.mgalala.noteall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgalala.noteall.model.Note;
import com.mgalala.noteall.service.NoteService;
import com.mgalala.noteall.util.CategoryUtil;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrowseNoteContentFragment extends Fragment {
    public static final String MENU_TITLE = "MENU_TITLE";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static NoteService noteService;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BrowseNoteContentFragment newInstance(int sectionNumber, String mCurrentSelectedCategory) {
        BrowseNoteContentFragment fragment = new BrowseNoteContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(MENU_TITLE, mCurrentSelectedCategory);
        fragment.setArguments(args);

        noteService = new NoteService();

        return fragment;
    }

    public BrowseNoteContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse_notes, container, false);
        String categoryName = getArguments().getString(MENU_TITLE);
        List<Note> notes = noteService.getNotesByCategoryKey(
                CategoryUtil.getCategoryKeyFromCategoryName(this.getActivity().getApplicationContext(), categoryName),
                getActivity().getApplicationContext());
        if (notes == null || notes.size() == 0) {
            ((TextView) rootView.findViewById(R.id.noNotes)).setText(R.string.no_notes_available);
        } else {
            ((TextView) rootView.findViewById(R.id.noNotes)).setText("There are some contents");
        }
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText(getArguments().getString(MENU_TITLE));
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}

