package com.mgalala.noteall.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class OpenNote extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_note);
        Intent intent = getIntent();
        if (Intent.ACTION_SEND.equals(intent.getAction())) {
            //TODO: build a factory to process on type.
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new NotePlaceholderFragment())
                    .commit();
        }
    }
}
