package com.mu.example.applybaserecycleradapter.expandable;


import com.mu.example.applybaserecycleradapter.expandable.ExpandableItemAdapter;
import com.mu.example.base_recycler_adapter.entity.MultiItemEntity;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Person implements MultiItemEntity {
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String name;
    public int age;

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_PERSON;
    }
}