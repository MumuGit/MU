package com.mu.example.base_recycler_adapter.entity;

import java.util.List;

public abstract class AbstractExpandableItem<T> implements IExpandable<T> {
    protected boolean mExpandable = false;
    protected List<T> mSubItems;

    @Override
    public boolean isExpanded() {
        return mExpandable;
    }

    @Override
    public void setExpanded(boolean expanded) {
        mExpandable = expanded;
    }

    @Override
    public List<T> getSubItems() {
        return mSubItems;
    }

    public boolean hasSubItem() {
        return mSubItems != null && mSubItems.size() > 0;
    }

    public void setSubItems(List<T> list) {
        mSubItems = list;
    }

    public T getSubItem(int position) {
        if (hasSubItem() && position < mSubItems.size()) {
            return mSubItems.get(position);
        } else {
            return null;
        }
    }
}
