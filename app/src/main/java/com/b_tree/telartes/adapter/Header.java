package com.b_tree.telartes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.b_tree.telartes.R;

/**
 * Created by noemi on 07-03-16.
 */
public class Header implements Item {
    private final String         name;

    public Header(String name) {
        this.name = name;
    }

    @Override
    public int getViewType() {
        return CategoriaArrayAdapter.RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.header, null);
            // Do some initialization
        } else {
            view = convertView;
        }

        TextView text = (TextView) view.findViewById(R.id.txt_header);
        text.setText(name);

        return view;
    }

}