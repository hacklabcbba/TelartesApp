package com.b_tree.telartes.principal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.Categoria;
import com.b_tree.telartes.R;
import com.b_tree.telartes.Utils.CircleCheckBox;
import com.b_tree.telartes.adapter.EntryAdapter;
import com.b_tree.telartes.adapter.EntryItem;
import com.b_tree.telartes.adapter.Item;
import com.b_tree.telartes.adapter.SectionItem;
import com.b_tree.telartes.base.Global;
import com.b_tree.telartes.listeners.AgendaListener;
import com.b_tree.telartes.rest.AgendaCategoriaService;
import com.b_tree.telartes.rest.RestClientJar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 04-04-16.
 */

public class AgendaFiltroFragment extends Fragment {
    Context context= null;
    private ListView listFilter;
    private TextView btnVerAgenda;
    private LinearLayout verNoticias;
    private ActualizacionDao actualizacionDao;
    private AgendaCategoriaService NCategoriaService;
    private List<Categoria> categoriaArrayList;
    private ArrayList<Item> arrayListItem;
    private ArrayList<String> arraySeleccion;
    private EntryAdapter adapter;
    private CircleCheckBox checkTodas;
    private AgendaListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sliding_fragment_layout_agenda, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listFilter = (ListView)view.findViewById(R.id.list_filter);
        btnVerAgenda = (TextView)view.findViewById(R.id.ver_noticias);
        arrayListItem = new ArrayList<>();
        arraySeleccion = new ArrayList<>();
        categoriaArrayList = new ArrayList<>();
        final ArrayList<String> arrayList = new ArrayList<>();
        if (RestClientJar.getInternetState(getActivity().getApplicationContext())) {
            NCategoriaService = new AgendaCategoriaService(getActivity().getApplicationContext()) {
                @Override
                public void onSuccessObtenerCategoriaNoticia(List<Categoria> listaCategorias) throws JSONException {
                    categoriaArrayList = listaCategorias;
                    Global.getMiglobal().getDaosession().getCategoriaDao().deleteAll();
                    Global.getMiglobal().getDaosession().getCategoriaDao().insertOrReplaceInTx(listaCategorias);
                }

                @Override
                public void onFinish() {
                    EntryAdapter adapter = new EntryAdapter(getActivity().getApplicationContext(), arrayListItem, "Agenda");
                    listFilter.setAdapter(adapter);
                    arraySeleccion = adapter.getListSeleccion();
                }
            };
            NCategoriaService.obtenerCategoria();
        }
        else{
            categoriaArrayList=  Global.getMiglobal().getDaosession().getCategoriaDao().loadAll();
            String categoria = "";
            arrayListItem.add(new EntryItem("Todas"));
            if (categoriaArrayList != null) {
                for (int i = 0; i < categoriaArrayList.size(); i++) {
                    if (!categoria.equals(categoriaArrayList.get(i).getNombre())) {
                        categoria = categoriaArrayList.get(i).getNombre();
                        arrayListItem.add(new SectionItem(categoriaArrayList.get(i).getNombre()));
                        arrayListItem.add(new EntryItem(categoriaArrayList.get(i).getVocabulario()));
                    } else {
                        arrayListItem.add(new EntryItem(categoriaArrayList.get(i).getVocabulario()));
                    }
                }
                adapter = new EntryAdapter(getActivity().getApplicationContext(), arrayListItem, "Agenda");
                listFilter.setAdapter(adapter);
            }else{
                RelativeLayout listContent = (RelativeLayout)getActivity().findViewById(R.id.noticia_filter);
                listContent.removeAllViews();
                View v = getActivity().getLayoutInflater().inflate(R.layout.error_conection_noticias, null);
                TextView textError = (TextView) v.findViewById(R.id.txtError);
                textError.setTextColor(getResources().getColor(R.color.verde));
                TextView txtmessage = (TextView)v.findViewById(R.id.txtmessage);
                textError.setTextColor(getResources().getColor(R.color.verde));
                listContent.addView(v);
            }

        }

        btnVerAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getFragmentManager().popBackStack();

//                arraySeleccion = adapter.getListSeleccion();
//                if(!arraySeleccion.isEmpty()) {
//                    listener.filtroAgenda(arraySeleccion);
//
//
//                }else{
//                    getActivity().getFragmentManager().popBackStack();
//                }
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View headerView = ((LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header_listview, null, false);
        listFilter.addHeaderView(headerView);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof AgendaListener) {
            listener = (AgendaListener) activity;
        }
    }


}