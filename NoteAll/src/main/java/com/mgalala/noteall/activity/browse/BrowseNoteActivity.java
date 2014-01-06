package com.mgalala.noteall.activity.browse;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

import com.mgalala.noteall.activity.R;
import com.mgalala.noteall.util.CategoryUtil;

public class BrowseNoteActivity extends ActionBarActivity
        implements BrowseNoteNavigationFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private BrowseNoteNavigationFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #//restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_notes);

        mNavigationDrawerFragment = (BrowseNoteNavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position, String mCurrentSelectedCategory) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        int category = CategoryUtil.getCategoryKeyFromCategoryName(this.getApplicationContext(), mCurrentSelectedCategory);
        if (R.string.photo_category == category) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, PhotoContentFragment.newInstance(position + 1, mCurrentSelectedCategory))
                    .commit();
        } else if (R.string.video_category == category) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, VideoContentFragment.newInstance(position + 1, mCurrentSelectedCategory))
                    .commit();
        } else {
            //TODO: place all other fragments for all other types of contents.
            Log.i(this.getClass().getName(), "Another fragment");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
