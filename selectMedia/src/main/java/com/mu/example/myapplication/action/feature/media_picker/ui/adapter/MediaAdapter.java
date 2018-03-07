package com.mu.example.myapplication.action.feature.media_picker.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mu.example.myapplication.C;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaDataCache;
import com.mu.example.myapplication.action.feature.media_picker.ui.holder.MediaHolder;
import com.mu.example.myapplication.model.Media;

import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class MediaAdapter extends RecyclerView.Adapter<MediaHolder> {


    List<Media> mData;
    Context context;
    String selectTag = C.MediaPicker.DEFAULT_SELECT_MEDIA_TAG;
    private boolean showCamera = C.MediaPicker.DEAULT_SHOW_CAMERA;
    private int maxSelectableCount = C.MediaPicker.DEAULT_SELECTABLE_MAX_COUNT;
    OnItemClickListener onItemClickListener;

    public MediaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MediaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_media, parent, false);
        return new MediaHolder(view);
    }


    @Override
    public void onBindViewHolder(MediaHolder holder, final int position) {
        holder.setOnSelectChangeListener(new MediaHolder.OnSelectSelectChangeListener() {
            @Override
            public void OnSelectSelectChange(boolean isChecked) {
                if (isChecked) {
                    MediaDataCache.getInstance().addMedia(mData.get(position));
                } else {
                    MediaDataCache.getInstance().deleteMedia(mData.get(position));
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(mData.get(position));
                }
            }
        });
        holder.bind(mData.get(position));


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateData() {
        mData = MediaDataCache.getInstance().get().get(MediaDataCache.getInstance().getSelectedFolderIndex()).getMedias();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Media media);
    }

    public void setShowCamera(boolean showCamera) {
        this.showCamera = showCamera;
    }

    public void setMaxSelectableCount(int maxSelectableCount) {
        this.maxSelectableCount = maxSelectableCount;
    }

    public void setSelectTag(String selectTag) {
        this.selectTag = selectTag;
    }

}
