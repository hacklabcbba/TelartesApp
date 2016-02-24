package com.b_tree.telartes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NoticiaAdapter extends ArrayAdapter<Noticia> {

    public NoticiaAdapter(Context context, List<Noticia> objects) {
        super(context, 0, objects);
    }

    private class ViewHolder{
        TextView lblNombre;
        TextView lblDescripcion;
        TextView lblFecha;
        ImageView img_noticias;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.noticia_item, null);
            holder.lblNombre = (TextView) convertView.findViewById(R.id.lbl_titulo);
            holder.lblDescripcion = (TextView) convertView.findViewById(R.id.lbl_descripcion);
            holder.lblFecha = (TextView) convertView.findViewById(R.id.lbl_fecha);
            holder.img_noticias = (ImageView)convertView.findViewById(R.id.img_noticia);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Noticia item = getItem(position);
        holder.lblNombre.setText(item.getTitulo());
        holder.lblDescripcion.setText(item.getDescripcion());
        holder.lblFecha.setText("Subido por "+item.getEnviadopor()+" el "+item.getFecha());
        Picasso.with(getContext()).load(item.getImagen()).resize(200, 250).into(holder.img_noticias);

        return convertView;
    }

}
