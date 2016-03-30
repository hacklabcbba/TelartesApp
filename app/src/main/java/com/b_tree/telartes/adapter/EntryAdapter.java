package com.b_tree.telartes.adapter;

/**
 * Created by noemi on 19-03-16.
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.b_tree.telartes.Utils.CircleCheckBox;
import com.b_tree.telartes.R;


public class EntryAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;
    public ArrayList<String> listSeleccion;

    public EntryAdapter(Context context,ArrayList<Item> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        this.listSeleccion = new ArrayList<>();
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<String> getListSeleccion() {

        return listSeleccion;
    }
    public int getSize(){
       return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final Item i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                SectionItem si = (SectionItem)i;
                v = vi.inflate(R.layout.header, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.txt_header);
                sectionView.setText(si.getTitle());

            }else{
                EntryItem ei = (EntryItem)i;
                v = vi.inflate(R.layout.my_list_item, null);
                final CircleCheckBox categoria = (CircleCheckBox)v.findViewById(R.id.check_filter);
                if (categoria != null){
                    categoria.setText(ei.title);
                    categoria.setChecked(false);
                    categoria.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(categoria.isChecked()){
                                categoria.setChecked(false);
                                listSeleccion.remove(categoria.getText());
                            }else{
                                categoria.setChecked(true);
                                listSeleccion.add(categoria.getText());
                            }

                        }
                    });
                }
            }
        }
        return v;
    }

}