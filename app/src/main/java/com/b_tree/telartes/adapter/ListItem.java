package com.b_tree.telartes.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.b_tree.telartes.CircleCheckBox;
import com.b_tree.telartes.R;

/**
 * Created by noemi on 07-03-16.
 */
public class ListItem implements Item {
    private final String  textCategoria;
    private final int colorCheck;
    public ListItem(String textCategoria, int colorCheck) {
        this.textCategoria = textCategoria;
        this.colorCheck = colorCheck;
    }

    @Override
    public int getViewType() {
        return CategoriaArrayAdapter.RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.my_list_item, null);
            // Do some initialization
        } else {
            view = convertView;
        }

        CircleCheckBox checkFilter = (CircleCheckBox) view.findViewById(R.id.check_filter);
        checkFilter.setText(textCategoria);
        if (colorCheck==2){
            checkFilter.setCircleBorderColor(view.getContext().getResources().getColor(R.color.verde));
            checkFilter.setInnerCircleColor(view.getContext().getResources().getColor(R.color.verde));
            checkFilter.setTextColor(view.getContext().getResources().getColor(R.color.verde));
        }
        if (colorCheck==3){
            checkFilter.setCircleBorderColor(view.getContext().getResources().getColor(R.color.rojo));
            checkFilter.setInnerCircleColor(view.getContext().getResources().getColor(R.color.rojo));
            checkFilter.setTextColor(view.getContext().getResources().getColor(R.color.rojo));
        }


        return view;
    }

}