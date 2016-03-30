package com.b_tree.telartes.principal;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.b_tree.telartes.Utils.CircleCheckBox;
import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.Categoria;
import com.b_tree.telartes.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.EntryAdapter;
import com.b_tree.telartes.adapter.EntryItem;
import com.b_tree.telartes.adapter.Item;
import com.b_tree.telartes.adapter.SectionItem;
import com.b_tree.telartes.rest.NoticasCategoriaService;
import com.b_tree.telartes.rest.RestClientJar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SlidingListFragment extends Fragment {
    Context context= null;
    private ListView listFilter;
    private TextView btnVerNoticias;
    private LinearLayout verNoticias;
    private ActualizacionDao actualizacionDao;
    private NoticasCategoriaService NCategoriaService;
    private  List<Categoria> categoriaArrayList;
    private ArrayList<Item> arrayListItem;
    private ArrayList<String> arraySeleccion;
    private  EntryAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sliding_fragment_layout, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listFilter = (ListView)view.findViewById(R.id.list_filter);
        btnVerNoticias = (TextView)view.findViewById(R.id.ver_noticias);
        arrayListItem = new ArrayList<>();
        arraySeleccion = new ArrayList<>();
        categoriaArrayList = new ArrayList<>();
        final ArrayList<String> arrayList = new ArrayList<>();
        actualizacionDao = Global.getMiglobal().getDaosession().getActualizacionDao();
        if (RestClientJar.getInternetState(getActivity().getApplicationContext())) {
            NCategoriaService = new NoticasCategoriaService(getActivity().getApplicationContext()) {
                @Override
                public void onSuccessObtenerCategoriaNoticia(List<Categoria> listaCategorias, JSONArray categoriaList) throws JSONException {
                    categoriaArrayList = listaCategorias;
                    Global.getMiglobal().getDaosession().getCategoriaDao().deleteAll();
                    Global.getMiglobal().getDaosession().getCategoriaDao().insertOrReplaceInTx(listaCategorias);
                    String categoria = "";
                    for (int i = 0; i < categoriaList.length(); i++) {
                        JSONObject object = categoriaList.getJSONObject(i);
                        if (!categoria.equals(object.getString("categoria"))) {
                            categoria = object.getString("categoria");
                            arrayListItem.add(new SectionItem(categoria));
                            arrayListItem.add(new EntryItem(android.text.Html.fromHtml(object.getString("vocabulario")).toString()));
                        } else {
                            arrayListItem.add(new EntryItem(android.text.Html.fromHtml(object.getString("vocabulario")).toString()));
                        }
                    }
                }

                @Override
                public void onFinish() {
                    EntryAdapter adapter = new EntryAdapter(getActivity().getApplicationContext(), arrayListItem);
                    listFilter.setAdapter(adapter);
                    arraySeleccion = adapter.getListSeleccion();
                }
            };
            NCategoriaService.obtenerCategoria();
        }
        else{
            categoriaArrayList=  Global.getMiglobal().getDaosession().getCategoriaDao().loadAll();
            String categoria = "";
            for (int i = 0; i <categoriaArrayList.size(); i++) {
                if (!categoria.equals(categoriaArrayList.get(i).getNombre())){
                    categoria = categoriaArrayList.get(i).getNombre();
                    arrayListItem.add(new SectionItem(categoriaArrayList.get(i).getNombre()));
                    arrayListItem.add(new EntryItem(categoriaArrayList.get(i).getVocabulario()));
                }else{
                    arrayListItem.add(new EntryItem(categoriaArrayList.get(i).getVocabulario()));
                }
            }
            adapter = new EntryAdapter(getActivity().getApplicationContext(),arrayListItem);
            listFilter.setAdapter(adapter);
            CircleCheckBox check_all = (CircleCheckBox)view.findViewById(R.id.chbx);
            check_all.setOnCheckedChangeListener(new CircleCheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CircleCheckBox view, boolean isChecked) {
                    for (int i = 0; i <listFilter.getCount() ; i++) {
                        View v = listFilter.getChildAt(i);

                    }
                }
            });

        }



        btnVerNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arraySeleccion = adapter.getListSeleccion();
                for (int i = 0; i < arraySeleccion.size(); i++) {
                    Log.d("BUSCAR", arraySeleccion.get(i));
                }
            }
        });
    }


}