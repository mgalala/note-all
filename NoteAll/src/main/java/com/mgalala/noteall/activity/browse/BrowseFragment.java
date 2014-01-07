package com.mgalala.noteall.activity.browse;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mgalala.noteall.activity.open.OpenNoteActivity;
import com.mgalala.noteall.util.Constants;

/**
 * Created by mgalala on 1/7/14.
 */
public abstract class BrowseFragment extends Fragment implements View.OnClickListener {

    public abstract String getIntentType();

    @Override
    public void onClick(View view) {
        Intent openNoteIntent = new Intent(this.getActivity().getApplicationContext(), OpenNoteActivity.class);
        openNoteIntent.putExtra(Constants.OPEN_NOTE_FROM_BROWSE, Constants.OPEN_NOTE_FROM_BROWSE);
        openNoteIntent.putExtra(Constants.NOTE_KEY, view.getTag().toString());
        openNoteIntent.putExtra(Constants.INTENT_TYPE, getIntentType());
        startActivity(openNoteIntent);
    }
}
