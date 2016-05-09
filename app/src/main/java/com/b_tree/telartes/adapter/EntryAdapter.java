package com.b_tree.telartes.adapter;

/**
 * Created by noemi on 19-03-16.
 */
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.b_tree.telartes.Utils.CircleCheckBox;
import com.b_tree.telartes.R;
import com.bluejamesbond.text.Console;


public class EntryAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;
    private  String tipo;
    public ArrayList<String> listSeleccion;
    private boolean[] categoriaEstado;

    public EntryAdapter(Context context,ArrayList<Item> items, String Tipo) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        this.tipo = Tipo;
        this.listSeleccion = new ArrayList<>();
        this.categoriaEstado = new boolean[items.size()];
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<String> getListSeleccion() {
        return listSeleccion;
    }
    public int getSize(){
       return items.size();
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
                if (tipo.equals("Noticia")){
                    v = vi.inflate(R.layout.my_list_item_noticias, null);
                    final RadioButton categoria = (RadioButton)v.findViewById(R.id.check_filter);
                    categoria.setText(ei.title);
                    categoria.setChecked(categoriaEstado[position]);

                    categoria.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (categoriaEstado[position] == false) {
                                Log.d("TELARTES true", String.valueOf(categoria.isChecked()));
                                categoriaEstado[position] = true;
                                categoria.setChecked(true);
                                if (categoria.getText().equals("Todas")) {
                                    for (int i = 1; i < categoriaEstado.length; i++) {
                                        categoriaEstado[i] = true;
                                    }
                                    listSeleccion.add((String) categoria.getText());
                                    notifyDataSetChanged();
                                } else {
                                    listSeleccion.add((String) categoria.getText());
                                }

                            } else {
                                categoriaEstado[position] = false;
                                categoria.setChecked(false);
                                if (categoria.getText().equals("Todas")) {
                                    for (int i = 1; i < categoriaEstado.length; i++) {
                                        categoriaEstado[i] = false;
                                    }
                                    listSeleccion = new ArrayList<String>();
                                    notifyDataSetChanged();
                                } else {
                                    listSeleccion.remove((String) categoria.getText());
                                }

                            }
                        }
                    });

                    this.notifyDataSetChanged();
                }
                if (tipo.equals("Agenda")){
                    v = vi.inflate(R.layout.my_list_item_agenda, null);
                    final RadioButton categoria = (RadioButton)v.findViewById(R.id.check_filter);
                    categoria.setText(ei.title);
                    categoria.setChecked(categoriaEstado[position]);
                    categoria.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (categoriaEstado[position]==false) {
                                Log.d("TELARTES true", String.valueOf(categoria.isChecked()));
                                categoriaEstado[position] = true;
                                categoria.setChecked(true);
                                if (categoria.getText().equals("Todas")) {
                                    for (int i = 1; i < categoriaEstado.length; i++) {
                                        categoriaEstado[i] = true;
                                    }
                                    listSeleccion.add((String) categoria.getText());
                                    notifyDataSetChanged();
                                } else {
                                    listSeleccion.add((String) categoria.getText());
                                }

                            } else {
                                categoriaEstado[position] = false;
                                categoria.setChecked(false);
                                if (categoria.getText().equals("Todas")) {
                                    for (int i = 1; i < categoriaEstado.length; i++) {
                                        categoriaEstado[i] = false;
                                    }
                                    listSeleccion = new ArrayList<String>();
                                    notifyDataSetChanged();
                                } else {
                                    listSeleccion.remove((String) categoria.getText());
                                }

                            }
                        }
                    });
                }
                if (tipo.equals("Convocatoria")){
                    v = vi.inflate(R.layout.my_list_item_convocatoria, null);
                    final RadioButton categoria = (RadioButton)v.findViewById(R.id.check_filter);
                    categoria.setText(ei.title);
                    categoria.setChecked(categoriaEstado[position]);
                    categoria.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (categoriaEstado[position]==false) {
                                Log.d("TELARTES true", String.valueOf(categoria.isChecked()));
                                categoriaEstado[position] = true;
                                categoria.setChecked(true);
                                if (categoria.getText().equals("Todas")) {
                                    for (int i = 1; i < categoriaEstado.length; i++) {
                                        categoriaEstado[i] = true;
                                    }
                                    listSeleccion.add((String) categoria.getText());
                                    notifyDataSetChanged();
                                } else {
                                    listSeleccion.add((String) categoria.getText());
                                }

                            } else {
                                categoriaEstado[position] = false;
                                categoria.setChecked(false);
                                if (categoria.getText().equals("Todas")) {
                                    for (int i = 1; i < categoriaEstado.length; i++) {
                                        categoriaEstado[i] = false;
                                    }
                                    listSeleccion = new ArrayList<String>();
                                    notifyDataSetChanged();
                                } else {
                                    listSeleccion.remove((String) categoria.getText());
                                }

                            }
                        }
                    });
                }
            }
        }
        return v;
    }


}