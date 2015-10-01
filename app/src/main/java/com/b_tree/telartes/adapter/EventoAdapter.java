package com.b_tree.telartes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Evento;
import com.b_tree.telartes.R;

import java.util.List;

/**
 * Created by Diana on 27/09/2015.
 */
public class EventoAdapter extends ArrayAdapter<Evento> {

    public EventoAdapter(Context context, List<Evento> objects) {
        super(context, 0, objects);
    }

    private class ViewHolder{
        TextView lblNombre;
        TextView lblDescripcion;
        TextView lblFecha;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_evento, null);
            holder.lblNombre = (TextView) convertView.findViewById(R.id.lbl_titulo);
            holder.lblDescripcion = (TextView) convertView.findViewById(R.id.lbl_descripcion);
            holder.lblFecha = (TextView) convertView.findViewById(R.id.lbl_fecha);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Evento item = getItem(position);
        holder.lblNombre.setText(item.getNombre());
        holder.lblDescripcion.setText(item.getDescripcion());
        holder.lblFecha.setText(item.getFecha());
        return convertView;
    }
}
