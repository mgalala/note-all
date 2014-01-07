package com.mgalala.noteall.activity.browse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mgalala.noteall.activity.R;
import com.mgalala.noteall.model.Note;
import com.mgalala.noteall.service.NoteService;
import com.mgalala.noteall.util.FilePathUtil;
import com.mgalala.noteall.util.IntentTypeEnum;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class VideoContentFragment extends BrowseFragment {
    public static final String MENU_TITLE = "MENU_TITLE";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static NoteService noteService;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static VideoContentFragment newInstance(int sectionNumber, String mCurrentSelectedCategory) {
        VideoContentFragment fragment = new VideoContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(MENU_TITLE, mCurrentSelectedCategory);
        fragment.setArguments(args);

        noteService = new NoteService();

        return fragment;
    }

    public VideoContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_videos_notes, container, false);
        List<Note> notes = noteService.getNotesByCategoryKey(R.string.video_category, getActivity().getApplicationContext());
        if (notes == null || notes.size() == 0) {
            ((TextView) rootView.findViewById(R.id.noNotes)).setText(R.string.no_notes_available);
        }
        LinearLayout myGallery = (LinearLayout) rootView.findViewById(R.id.categoryVideo);
        for (Note note : notes) {
            myGallery.addView(insertVideo(FilePathUtil.decodeFilePath(note.getNoteKey())));
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    View insertVideo(String path) {
        Bitmap imageThumbnail = decodeSampledBitmapFromUri(path, 150, 150);

        LinearLayout layout = new LinearLayout(getActivity().getApplicationContext());
        layout.setLayoutParams(new ViewGroup.LayoutParams(180, 180));
        layout.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(getActivity().getApplicationContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(imageThumbnail);
        imageView.setTag(path);
        imageView.setOnClickListener(this);

        layout.addView(imageView);
        return layout;
    }

    public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
        return ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MINI_KIND);
    }

    @Override
    public String getIntentType() {
        return IntentTypeEnum.VIDEO.getType();
    }
}

