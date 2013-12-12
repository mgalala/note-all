package com.mgalala.noteall.service;

import android.content.Context;

import com.mgalala.noteall.dao.NoteCategoryDAO;
import com.mgalala.noteall.model.NoteCategory;

import java.util.List;

/**
 * Created by galala on 11/25/13.
 */
public class NoteCategoryService {

    private NoteCategoryDAO noteCategoryDAO;

    public List<NoteCategory> getNoteCategories(Context context) {
        noteCategoryDAO = new NoteCategoryDAO(context);
        noteCategoryDAO.open();
        List<NoteCategory> noteCategories = noteCategoryDAO.getAllNoteCategories();
        noteCategoryDAO.close();

        return noteCategories;
    }

    public List<Integer> getNoteCategoriesNames(Context context) {
        noteCategoryDAO = new NoteCategoryDAO(context);
        noteCategoryDAO.open();
        List<Integer> noteCategories = noteCategoryDAO.getAllNoteCategoriesNames();
        noteCategoryDAO.close();

        return noteCategories;
    }

}
