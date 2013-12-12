package com.mgalala.noteall.service.resolver;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by galala on 12/12/13.
 */
public class StorageURIResolver implements URIResolver {

    @Override
    public String resolveURI(Bundle bundle, Activity activity) {
        Uri uri = (Uri) bundle.get(Intent.EXTRA_STREAM);
        if (uri == null) {
            Log.e(this.getClass().getName(), "The intent doesn't contains EXTRA_TEXT.");
            return null;
        }
        return getPathFromUri(uri, activity);
    }

    public String getPathFromUri(Uri uri, Activity activity) {
        if (uri.toString().startsWith("file://"))
            return uri.getPath();
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        activity.startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

}
