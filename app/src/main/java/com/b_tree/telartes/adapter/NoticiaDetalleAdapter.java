package com.b_tree.telartes.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.R;
import com.b_tree.telartes.Utils.Utils;

import java.util.List;

/**
 * Created by noemi on 03-05-16.
 */
public class NoticiaDetalleAdapter   extends ArrayAdapter<Noticia> {
    private  int indice;

    public NoticiaDetalleAdapter(Context context, List<Noticia> objects, int indice) {
        super(context, 0, objects);
        this.indice = indice;
        Noticia noticia =  objects.get(indice);
       objects.remove(indice);
        objects.add(0, noticia);

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Noticia getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(Noticia item) {
        return super.getPosition(item);
    }


    private class ViewHolder{
        private TextView lblTitulo;
        private  TextView lblfecha;
        private TextView txtDescripcion;
        private ImageView imgNoticias;
        private TextView txtEnlaceAutor;
        private TextView txtNombreAutor;
        private TextView txtFuente;
        private LinearLayout linear_autor;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.noticia_detalle_adapter, null);
            holder.lblTitulo = (TextView) convertView.findViewById(R.id.lbl_titulo_noticia);
            holder.lblfecha = (TextView) convertView.findViewById(R.id.lbl_fecha);
            holder.txtDescripcion = (TextView)convertView.findViewById(R.id.txt_n_noticia);
            holder.imgNoticias = (ImageView)convertView.findViewById(R.id.img_noticia);
            holder.txtEnlaceAutor = (TextView)convertView.findViewById(R.id.txt_enlace_autor);
            holder.txtNombreAutor = (TextView)convertView.findViewById(R.id.txt_nombre_autor);
            holder.txtFuente = (TextView)convertView.findViewById(R.id.txt_fuente_noticia);
            holder.linear_autor = (LinearLayout)convertView.findViewById(R.id.linear_autor);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Noticia item = getItem(position);
        holder.lblTitulo.setText(item.getTitulo());
        holder.lblfecha.setText(item.getFecha());
        holder.txtDescripcion.setText(item.getDescripcion());
        holder.txtNombreAutor.setText(item.getAutorNombre());
        if (item.getAutorEnlace().equals("http://null")) {
               holder.linear_autor.setVisibility(View.GONE);
        }else{
            holder.txtEnlaceAutor.setText(item.getAutorEnlace());
           }
        holder.imgNoticias.setImageBitmap(Utils.decodeBase64(item.getImagen()));
        holder.txtFuente.setText(item.getFuente());
        return convertView;
    }



}
