package com.mu.example.myapplication.action.feature.media_picker.ui.holder;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class MediaHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    Context context;
    CheckBox select;
    RelativeLayout bottomLayout;
    OnSelectSelectChangeListener onSelectSelectChangeListener;
    View mask;

    public MediaHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.image);
        bottomLayout = itemView.findViewById(R.id.bottom_info);
        select = itemView.findViewById(R.id.select);
        mask = itemView.findViewById(R.id.mask);
    }

    public void bind(Media media) {
        ImageUtils.displayImageFromFile(context, new File(media.getLocalPath()), imageView);
        if (MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO == media.getMediaType()) {
            bottomLayout.setVisibility(View.VISIBLE);
        } else {
            bottomLayout.setVisibility(View.INVISIBLE);
        }
        select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onSelectSelectChangeListener.OnSelectSelectChange(isChecked);
            }
        });
        disposeAllSelectedMedia(media);
        disposeCurrentSelectedMedia(media);
    }

    private void disposeAllSelectedMedia(Media media) {

        boolean isSelected = MediaDataCache.getInstance().isSelectedMedia(media, false);
        select.setChecked(isSelected);
        if (isSelected) {
            mask.setVisibility(View.VISIBLE);
        } else {
            mask.setVisibility(View.INVISIBLE);
        }
    }

    private void disposeCurrentSelectedMedia(Media media) {

        boolean isSelected = MediaDataCache.getInstance().isSelectedMedia(media, true);
        if (isSelected) {
            mask.setVisibility(View.INVISIBLE);
        }
    }

    public void setOnSelectChangeListener(OnSelectSelectChangeListener listener) {
        onSelectSelectChangeListener = listener;
    }

    public interface OnSelectSelectChangeListener {
        void OnSelectSelectChange(boolean isChecked);
    }


}
