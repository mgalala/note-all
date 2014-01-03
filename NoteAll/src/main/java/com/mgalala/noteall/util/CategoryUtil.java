package com.mgalala.noteall.util;

import android.content.Context;

import com.mgalala.noteall.activity.R;

/**
 * Created by mgalala on 1/3/14.
 */
public class CategoryUtil {

    public static Integer getCategoryKeyFromCategoryName(Context context, String categoryName) {
        if (context.getString(R.string.photo_category).equals(categoryName)) {
            return R.string.photo_category;
        } else if (context.getString(R.string.video_category).equals(categoryName)) {
            return R.string.video_category;
        } else if (context.getString(R.string.document_category).equals(categoryName)) {
            return R.string.document_category;
        } else if (context.getString(R.string.web_category).equals(categoryName)) {
            return R.string.web_category;
        } else {
            return null;
        }
    }
}
