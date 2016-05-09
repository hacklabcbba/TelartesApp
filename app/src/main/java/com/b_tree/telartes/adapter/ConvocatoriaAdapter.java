package com.b_tree.telartes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Convocatoria;
import com.b_tree.telartes.R;
import com.b_tree.telartes.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by noemi on 22-02-16.
 */
public class ConvocatoriaAdapter  extends ArrayAdapter<Convocatoria> {

    public ConvocatoriaAdapter(Context context, List<Convocatoria> objects) {
        super(context, 0, objects);
    }

    private class ViewHolder{
        TextView txtTitulo;
        TextView txtDescripcion;
        TextView txtFecha;
        ImageView imgConvocatoria;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.convocatoria_item, null);
            holder.txtTitulo = (TextView) convertView.findViewById(R.id.txt_c_titulo);
            holder.txtFecha = (TextView) convertView.findViewById(R.id.txt_c_fecha);
            holder.txtDescripcion = (TextView) convertView.findViewById(R.id.txt_c_descripcion);
            holder.imgConvocatoria = (ImageView)convertView.findViewById(R.id.img_c_convocatoria);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Convocatoria item = getItem(position);
        holder.txtTitulo.setText(item.getTitulo());
        holder.txtFecha.setText(item.getFechalimite());
        holder.txtDescripcion.setText(item.getDescripcion());
        holder.imgConvocatoria.setImageBitmap(Utils.decodeBase64(item.getImagen()));
        return convertView;
    }

}
