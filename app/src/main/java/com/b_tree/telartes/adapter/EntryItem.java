package com.b_tree.telartes.adapter;


public class EntryItem implements Item {
    public final String title;


    public EntryItem(String title) {
        this.title = title;

    }

    @Override
    public boolean isSection() {
        return false;
    }

    @Override
    public String getContent() {
        return title;
    }

}