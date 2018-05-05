package com.diabin.design_pattern.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class Dir {
    protected List<Dir> dirs = new ArrayList<>();
    private String name;

    public Dir(String name) {
        this.name = name;
    }

    public abstract void addDir(Dir dir);

    public String getName() {
        return name;
    }

    public abstract void print();
}
