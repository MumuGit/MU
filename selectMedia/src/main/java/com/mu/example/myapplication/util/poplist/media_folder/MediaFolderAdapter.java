package com.mu.example.myapplication.util.poplist.media_folder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaDataCache;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaLoaderListener;
import com.mu.example.myapplication.model.Folder;
import com.mu.example.myapplication.util.poplist.BaseRecyclerAdapter;
import com.mu.example.myapplication.util.poplist.PopListUtil;

/**
 * Created by mu on 2018/3/5.
 */

public class MediaFolderAdapter extends BaseRecyclerAdapter<Folder, MediaFolderHolder> {

    private MediaLoaderListener loaderListener;

    public void setLoaderListener(MediaLoaderListener loaderListener) {
        this.loaderListener = loaderListener;
    }

    public MediaFolderAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public MediaFolderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_media_folder, null);
        return new MediaFolderHolder(view);
    }

    @Override
    public void onBindViewHolder(MediaFolderHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaDataCache.getInstance().setSelectedFolderIndex(position);
                if (loaderListener != null) {
                    loaderListener.onFolderClick();
                } else {
                    PopListUtil.closePopList();
                }
            }
        });
        holder.bind(mData.get(position), position);
    }

    public void updateData() {
        mData = MediaDataCache.getInstance().get();
        notifyDataSetChanged();
    }


}
