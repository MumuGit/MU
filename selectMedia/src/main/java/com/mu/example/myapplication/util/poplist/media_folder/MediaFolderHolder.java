package com.mu.example.myapplication.util.poplist.media_folder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaDataCache;
import com.mu.example.myapplication.model.Folder;
import com.mu.example.myapplication.util.ImageUtils;

import java.io.File;

/**
 * Created by mu on 2018/3/5.
 */

public class MediaFolderHolder extends RecyclerView.ViewHolder {
    ImageView cover;
    ImageView indicator;
    TextView name;
    Context context;

    public MediaFolderHolder(View itemView) {
        super(itemView);
        cover = itemView.findViewById(R.id.cover);
        indicator = itemView.findViewById(R.id.indicator);
        name = itemView.findViewById(R.id.name);
        context = itemView.getContext();
    }

    public void bind(Folder folder, int position) {
        if (folder.getMedias() != null && folder.getMedias().size() > 0) {
            ImageUtils.displayImageFromFile(context, new File(folder.getMedias().get(0).getLocalPath()), cover);
        }

        if (position == MediaDataCache.getInstance().getSelectedFolderIndex()) {
            indicator.setVisibility(View.VISIBLE);
        } else {
            indicator.setVisibility(View.INVISIBLE);
        }
        name.setText(folder.getName());


    }
}
