package com.mu.example.myapplication.action.feature.media_picker.ui.holder;

import android.content.Context;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaDataCache;
import com.mu.example.myapplication.model.Media;
import com.mu.example.myapplication.util.ImageUtils;

import java.io.File;

/**
 * Created by mu on 2018/1/10.
 */

public class MediaHolder extends PickHolder {
    ImageView imageView;
    Context context;
    CheckBox select;
    RelativeLayout bottomLayout;
    OnSelectStateChangeListener onSelectSelectChangeListener;
    View mask;

    public boolean isUnableChangeState() {
        return unableChangeState;
    }

    private boolean unableChangeState = false;

    public MediaHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.image);
        bottomLayout = itemView.findViewById(R.id.bottom_info);
        select = itemView.findViewById(R.id.select);
        mask = itemView.findViewById(R.id.mask);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = onSelectSelectChangeListener.OnSelectSelectChange(select.isChecked());
                if (!result) {
                    select.setChecked(!select.isChecked());
                }
            }
        });
    }

    public void bind(Media media, boolean isReplace) {
        ImageUtils.displayImageFromFile(context, new File(media.getLocalPath()), imageView);
        if (MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO == media.getMediaType()) {
            bottomLayout.setVisibility(View.VISIBLE);
        } else {
            bottomLayout.setVisibility(View.INVISIBLE);
        }


        disposeAllSelectedMedia(media);
        disposeCurrentSelectedMedia(media, isReplace);
    }

    private void disposeAllSelectedMedia(Media media) {

        boolean isSelected = MediaDataCache.getInstance().isSelectedMedia(media, false);
        select.setChecked(isSelected);
        if (isSelected) {
            mask.setVisibility(View.VISIBLE);
            setUnableChangeState(true);
        } else {
            mask.setVisibility(View.INVISIBLE);
            setUnableChangeState(false);
        }
    }

    private void setUnableChangeState(boolean unable) {
        unableChangeState = unable;
        select.setClickable(!unable);
    }

    private void disposeCurrentSelectedMedia(Media media, boolean isReplace) {

        boolean isSelected = MediaDataCache.getInstance().isSelectedMedia(media, true);
        if (isSelected) {
            select.setChecked(isSelected);
            if (isReplace) {
                mask.setVisibility(View.VISIBLE);
                setUnableChangeState(true);
            }
        }


    }

    public void setOnSelectChangeListener(OnSelectStateChangeListener listener) {
        onSelectSelectChangeListener = listener;
    }

    public interface OnSelectStateChangeListener {
        /**
         * true表示改变成功
         *
         * @param isChecked
         * @return
         */
        boolean OnSelectSelectChange(boolean isChecked);
    }


}
