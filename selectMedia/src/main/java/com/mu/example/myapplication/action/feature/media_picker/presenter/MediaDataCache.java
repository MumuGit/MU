package com.mu.example.myapplication.action.feature.media_picker.presenter;

import com.mu.example.myapplication.model.Folder;
import com.mu.example.myapplication.model.Media;
import com.mu.example.myapplication.util.MapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mu on 2018/3/5.
 */

public class MediaDataCache {
    private List<Folder> folders;
    private Map<String, List<Media>> selectedMediaMap = new HashMap<>();
    //    private final static String SELECT_MEDIA_DEFAULT_KEY = C.MediaPicker.DEFAULT_SELECT_MEDIA_TAG;
    private int selectedFolderIndex;
    private String currentSelectedTag;
    private int currentMaxSelectableCount;
    private List<Media> tempSelectedMedia;

    public boolean isShowCamera() {
        return showCamera;
    }

    public void setShowCamera(boolean showCamera) {
        this.showCamera = showCamera;
    }

    private boolean showCamera;

    private MediaDataCache() {
    }

    private static class Holder {
        public final static MediaDataCache INSTANCE = new MediaDataCache();
    }

    public int getCurrentMaxSelectableCount() {
        return currentMaxSelectableCount;
    }

    public void setCurrentMaxSelectableCount(int currentMaxSelectableCount) {
        this.currentMaxSelectableCount = currentMaxSelectableCount;
    }


    public String getCurrentSelectedTag() {
        return currentSelectedTag;
    }

    public void setCurrentSelectedTag(String currentSelectedTag) {
        this.currentSelectedTag = currentSelectedTag;
        if (selectedMediaMap.get(currentSelectedTag) == null) {
            List<Media> mediaList = new ArrayList<>();
            selectedMediaMap.put(currentSelectedTag, mediaList);
        }

    }

    public void add(List<Folder> folders) {
        this.folders = folders;
    }

    public List<Folder> get() {
        return folders;
    }

    public void remove() {
        folders.clear();
        folders = null;
//        selectedMediaMap.clear();
        selectedFolderIndex = 0;
        currentSelectedTag = null;
        currentMaxSelectableCount = 0;
        tempSelectedMedia.clear();
        tempSelectedMedia = null;
    }

//    public List<Media> getSelectedMedia() {
//        List<Media> selectedMedia = selectedMediaMap.get(SELECT_MEDIA_DEFAULT_KEY);
//        if (selectedMedia == null) {
//            selectedMedia = new LinkedList<>();
//            selectedMediaMap.put(SELECT_MEDIA_DEFAULT_KEY, selectedMedia);
//        }
//        return selectedMedia;
//    }
//
//    public List<Media> getSelectedMedia(String tag) {
//        List<Media> selectedMedia = selectedMediaMap.get(tag);
//        if (selectedMedia == null) {
//            selectedMedia = new LinkedList<>();
//            selectedMediaMap.put(tag, selectedMedia);
//        }
//        return selectedMedia;
//    }

    public void addMedia(Media meida) {
        if (tempSelectedMedia == null) {
            tempSelectedMedia = new ArrayList<>();
            tempSelectedMedia.addAll(selectedMediaMap.get(currentSelectedTag));
        }
        if (tempSelectedMedia == null) {
            tempSelectedMedia = new LinkedList<>();
        }

        if (!tempSelectedMedia.contains(meida)) {
            tempSelectedMedia.add(meida);
        }
    }

    public void replaceMedia(Media newMedia, Media oldMedia) {
        if (tempSelectedMedia == null) {
            tempSelectedMedia = new ArrayList<>();
            tempSelectedMedia.addAll(selectedMediaMap.get(currentSelectedTag));
        }
        if (tempSelectedMedia != null) {
            int indexOld = tempSelectedMedia.indexOf(oldMedia);
            if (indexOld != -1) {
                tempSelectedMedia.set(indexOld, newMedia);
            }
        }

    }

    public void swopMedia(Media meida1, Media meida2) {
        if (tempSelectedMedia == null) {
            tempSelectedMedia = new ArrayList<>();
            tempSelectedMedia.addAll(selectedMediaMap.get(currentSelectedTag));
        }
        if (tempSelectedMedia != null) {
            int index1 = tempSelectedMedia.indexOf(meida1);
            int index2 = tempSelectedMedia.indexOf(meida2);
            if (index1 != -1 && index2 != -1) {
                tempSelectedMedia.set(index1, meida2);
                tempSelectedMedia.set(index2, meida1);
            }
        }

    }

    public void deleteMedia(Media meida) {
        if (tempSelectedMedia == null) {
            tempSelectedMedia = new ArrayList<>();
            tempSelectedMedia.addAll(selectedMediaMap.get(currentSelectedTag));
        }
        if (tempSelectedMedia != null) {
            if (tempSelectedMedia.contains(meida)) {
                tempSelectedMedia.remove(meida);
            }
        }

    }

    /**
     * @param media
     * @param tag   tag为null或selectedMediaMap没有的key则查找是否在map中，否则只查找tag所在的list
     * @return
     */
    public boolean isSelectedMedia(Media media, boolean self) {
        boolean result = false;
        if (tempSelectedMedia == null) {
            tempSelectedMedia = new ArrayList<>();
            tempSelectedMedia.addAll(selectedMediaMap.get(currentSelectedTag));
        }
        if (self && currentSelectedTag != null && selectedMediaMap.keySet().contains(currentSelectedTag)) {
            result = result || tempSelectedMedia.contains(media);
        } else {
            Set keys = new HashSet();
            keys.addAll(selectedMediaMap.keySet());
            keys.remove(currentSelectedTag);
            result = result || MapUtil.contains(selectedMediaMap, media, keys);
        }
        return result;

    }

//    public void setSelectedMedia(List<Media> selectedMedia) {
//        selectedMediaMap.put(SELECT_MEDIA_DEFAULT_KEY, selectedMedia);
//    }
//
//    public void setSelectedMedia(String key, List<Media> selectedMedia) {
//        selectedMediaMap.put(key, selectedMedia);
//    }

    public static MediaDataCache getInstance() {
        return Holder.INSTANCE;
    }

    public void complete() {
        selectedMediaMap.get(currentSelectedTag).clear();
        selectedMediaMap.get(currentSelectedTag).addAll(tempSelectedMedia);
        tempSelectedMedia.clear();
    }

    public void cancel() {

    }

    public int getSelectedFolderIndex() {
        return selectedFolderIndex;
    }

    public void setSelectedFolderIndex(int selectedFolderIndex) {
        this.selectedFolderIndex = selectedFolderIndex;
    }


}
