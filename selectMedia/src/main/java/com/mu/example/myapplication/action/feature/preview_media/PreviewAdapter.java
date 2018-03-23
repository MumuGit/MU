package com.mu.example.myapplication.action.feature.preview_media;

import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mu.example.myapplication.action.feature.image_player.PhotoView;
import com.mu.example.myapplication.model.Media;
import com.mu.example.myapplication.util.ImageUtils;

import java.io.File;
import java.util.List;

/**
 * Created by mu on 2018/3/12.
 */

public class PreviewAdapter extends PagerAdapter {
    private List<Media> mData;

    public PreviewAdapter(List<Media> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Media media = mData.get(position);
        View view = null;
        if (MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE == media.getMediaType()) {
            view = new PhotoView(container.getContext());
            ImageUtils.displayImageFromFile(container.getContext(),
                    new File(mData.get(position).getLocalPath()), (ImageView) view);
        }
        if (MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO == media.getMediaType()) {
            //TOdo
            view = new VideoView(container.getContext());
            ((VideoView) view).setUrl(mData.get(position).getLocalPath());
            ImageUtils.displayImageFromFile(container.getContext(),
                    new File(mData.get(position).getLocalPath()), ((VideoView) view).getContent());
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
