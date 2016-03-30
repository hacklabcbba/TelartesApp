package com.b_tree.telartes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.b_tree.telartes.R;

/**
 * Created by noemi on 07-03-16.
 */
public class SectionItem implements Item {
    private final String title;

    public SectionItem(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean isSection() {
        return true;
    }

    @Override
    public String getContent() {
        return title;
    }

}