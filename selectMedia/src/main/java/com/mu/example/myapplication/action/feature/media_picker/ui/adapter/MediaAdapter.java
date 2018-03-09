package com.mu.example.myapplication.action.feature.media_picker.ui.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mu.example.myapplication.C;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaDataCache;
import com.mu.example.myapplication.action.feature.media_picker.ui.holder.CameraHolder;
import com.mu.example.myapplication.action.feature.media_picker.ui.holder.MediaHolder;
import com.mu.example.myapplication.action.feature.media_picker.ui.holder.PickHolder;
import com.mu.example.myapplication.model.Media;

import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class MediaAdapter extends RecyclerView.Adapter<PickHolder> {


    List<Media> mData;
    Context context;
    String selectTag = C.MediaPicker.DEFAULT_SELECT_MEDIA_TAG;
    private boolean showCamera = C.MediaPicker.DEAULT_SHOW_CAMERA;
    private int maxSelectableCount = C.MediaPicker.DEAULT_SELECTABLE_MAX_COUNT;
    OnItemClickListener onItemClickListener;

    public void setOnSelectCountChangeListener(OnSelectCountChangeListener onSelectCountChangeListener) {
        this.onSelectCountChangeListener = onSelectCountChangeListener;
    }

    OnSelectCountChangeListener onSelectCountChangeListener;
    boolean isReplace = false;

    public MediaAdapter(Context context) {
        this.context = context;
    }

    public void setReplace(boolean replace) {
        isReplace = replace;
    }

    @Override
    public PickHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == C.MediaPicker.MEDIA_PICKER_TYPE_CAMERA) {
            view = LayoutInflater.from(context).inflate(R.layout.item_camera, parent, false);
            return new CameraHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_media, parent, false);
            return new MediaHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(final PickHolder holder, int position) {
        if (holder instanceof MediaHolder) {
            final MediaHolder mediaHolder = (MediaHolder) holder;
            if (showCamera) {
                position++;
            }
            final int finalPosition = position;
            mediaHolder.setOnSelectChangeListener(new MediaHolder.OnSelectStateChangeListener() {
                @Override
                public boolean OnSelectSelectChange(boolean isChecked) {

                    if (isChecked) {
                        if (isReplace) {
                            //todo 替换
                            MediaDataCache.getInstance().replaceMedia(mData.get(finalPosition), new Media());
                        } else {
                            if (mData.get(finalPosition).getMediaType() == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO) {
                                if (mData.get(finalPosition).getDuration() < 10000) {
                                    Toast.makeText(context, R.string.less_10s_tip, Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                            }
                            int result = MediaDataCache.getInstance().addMedia(mData.get(finalPosition));
                            if (result == C.MediaPicker.EXCEED_MAX_COUNT) {
                                Toast.makeText(context, R.string.exceed_tip, Toast.LENGTH_SHORT).show();
                                return false;
                            }

                        }
                    } else {
                        MediaDataCache.getInstance().deleteMedia(mData.get(finalPosition));
                    }
                    if (onSelectCountChangeListener != null) {
                        onSelectCountChangeListener.OnSelectCountChange();
                    }
                    return true;
                }
            });
            mediaHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaHolder.isUnableChangeState()) {
                        Toast.makeText(context, "不能选择", Toast.LENGTH_SHORT).show();
                    } else {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(mData.get(finalPosition));
                        }
                    }
                }
            });
            mediaHolder.bind(mData.get(position), isReplace);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int result = C.MediaPicker.MEDIA_PICKER_TYPE_MEDIA;
        if (position == 0 && showCamera) {
            result = C.MediaPicker.MEDIA_PICKER_TYPE_CAMERA;
        }
        return result;
    }


    @Override
    public int getItemCount() {

        return showCamera ? mData.size() + 1 : mData.size();
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

    public interface OnSelectCountChangeListener {
        void OnSelectCountChange();
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
