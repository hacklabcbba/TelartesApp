package com.b_tree.telartes.adapter;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by noemi on 07-03-16.
 */
public interface Item {
    public int getViewType();
    public View getView(LayoutInflater inflater, View convertView);
}