package com.mgalala.noteall.activity.open;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mgalala.noteall.activity.R;

public class OpenNoteActivity extends ActionBarActivity {

    private OpenNoteFragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_note);
        Intent intent = getIntent();
        if (Intent.ACTION_SEND.equals(intent.getAction())) {
            //TODO: build a factory to process on type.
        }

        if (savedInstanceState == null) {
            fragment = new OpenNoteFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.open_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.saveButton:
                fragment.onMenuItemClick(item);
                return true;
            case R.id.cancelButton:
                fragment.onMenuItemClick(item);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
