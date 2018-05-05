package com.diabin.design_pattern.composite;

import java.util.Iterator;

public class Folder extends Dir {
    public Folder(String name) {
        super(name);
    }

    @Override
    public void addDir(Dir dir) {
        dirs.add(dir);
    }

    @Override
    public void print() {
        System.out.println(getName() + "(");
        Iterator<Dir> iterator = dirs.iterator();
        while (iterator.hasNext()) {
            Dir dir = iterator.next();
            dir.print();
            if (iterator.hasNext()) {
                System.out.println(", ");
            }
        }

        System.out.println(")");
    }
}
