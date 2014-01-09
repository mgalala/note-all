package com.mgalala.noteall.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.mgalala.noteall.util.FilePathUtil;

import java.util.HashMap;
import java.util.List;

public class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap;

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<String> notes) {
        super(context, textViewResourceId, notes);
        mIdMap = new HashMap<String, Integer>();
        for (int i = 0; i < notes.size(); ++i) {
            mIdMap.put(FilePathUtil.decodeFilePath(notes.get(i)), i);
        }
    }

//    @Override
//    public long getItemId(int position) {
//        String item = getItem(position);
//        return mIdMap.get(item);
//    }

    @Override
    public String getItem(int position) {
        String item = super.getItem(position);
        return getFileNameFromPath(item);
    }

    private String getFileNameFromPath(String path) {
        return path.substring(path.lastIndexOf("/") + 1, path.length());
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}