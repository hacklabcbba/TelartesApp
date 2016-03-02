package com.b_tree.telartes.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_tree.telartes.R;
import com.squareup.picasso.Picasso;
import java.util.List;
/**
 * Created by noemi on 21-02-16.
 */
public class AgendaAdapter  extends ArrayAdapter<AgendaCultural> {

    public AgendaAdapter(Context context, List<AgendaCultural> objects) {
        super(context, 0, objects);
    }

    private class ViewHolder{
        TextView txttitulo;
        TextView txtDescripcion;
        TextView txtFecha;
        ImageView imgAgenda;
        TextView txtLugar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.agenda_item, null);
            holder.txttitulo = (TextView) convertView.findViewById(R.id.txt_a_titulo);
            holder.txtFecha = (TextView) convertView.findViewById(R.id.txt_a_fecha);
            holder.txtDescripcion = (TextView) convertView.findViewById(R.id.txt_a_descripcion);
            holder.imgAgenda = (ImageView)convertView.findViewById(R.id.img_a_agenda);
            holder.txtLugar = (TextView)convertView.findViewById(R.id.txt_a_lugar);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AgendaCultural item = getItem(position);
        holder.txttitulo.setText(item.getTitulo());
        holder.txtFecha.setText("De "+item.getFechaInicio() + " hasta " +item.getFechaFin() );
        holder.txtDescripcion.setText(item.getDescripcion());
        Picasso.with(getContext()).load(item.getImagen()).resize(200,250).into(holder.imgAgenda);
        holder.txtLugar.setText(item.getDepartamento());
        return convertView;
    }
}
