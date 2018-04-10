package com.mu.example.base_recycler_adapter.entity;

import java.util.List;

public interface IExpandable<T> {
    boolean isExpanded();

    void setExpanded(boolean expanded);

    List<T> getSubItems();

}
