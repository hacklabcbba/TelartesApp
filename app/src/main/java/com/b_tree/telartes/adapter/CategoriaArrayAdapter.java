package com.b_tree.telartes.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by noemi on 07-03-16.
 */
public class CategoriaArrayAdapter extends ArrayAdapter<Item> implements View.OnClickListener{
    private LayoutInflater mInflater;

    @Override
    public void onClick(View view) {
        Log.d("Hola","click");
    }

    public enum RowType {
        LIST_ITEM, HEADER_ITEM
    }

    public CategoriaArrayAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return RowType.values().length;

    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItem(position).getView(mInflater, convertView);
    }
}