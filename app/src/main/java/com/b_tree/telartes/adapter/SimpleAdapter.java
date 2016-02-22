package com.b_tree.telartes.adapter;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.b_tree.telartes.R;

public class SimpleAdapter<T> extends ArrayAdapter<T>{

	public SimpleAdapter(Context context,  List<T> objects) {
		super(context, 0, objects);
		
	}
	
	private class ViewHolder{
		TextView lblNombre;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if (convertView==null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_simple_adapter, null);
			holder.lblNombre = (TextView) convertView.findViewById(R.id.lbl_nombre);
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.lblNombre.setText(getItem(position).toString());
		
		return convertView;
	}

}
