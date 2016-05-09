package com.b_tree.telartes.principal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.CategoriaDao;
import com.b_tree.telartes.Utils.CircleCheckBox;
import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.Categoria;
import com.b_tree.telartes.base.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.EntryAdapter;
import com.b_tree.telartes.adapter.EntryItem;
import com.b_tree.telartes.adapter.Item;
import com.b_tree.telartes.adapter.SectionItem;
import com.b_tree.telartes.rest.NoticasCategoriaService;
import com.b_tree.telartes.rest.RestClientJar;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import com.b_tree.telartes.listeners.NoticiaListener;


public class NoticiasFiltroFragment extends Fragment {
    Context context= null;
    private ListView listFilter;
    private TextView btnVerNoticias;
    private LinearLayout verNoticias;
    private ActualizacionDao actualizacionDao;
    private NoticasCategoriaService NCategoriaService;
    private  List<Categoria> categoriaArrayList;
    private ArrayList<Item> arrayListItem;
    public ArrayList<String> arraySeleccion;
    private  EntryAdapter adapter;
    private CircleCheckBox checkTodas;
    private NoticiaListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sliding_fragment_layout_noticia, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listFilter = (ListView)getActivity().findViewById(R.id.list_filter);
        verNoticias = (LinearLayout)getActivity().findViewById(R.id.ver_noticias);
        arrayListItem = new ArrayList<>();
        arraySeleccion = new ArrayList<>();
        categoriaArrayList = new ArrayList<>();
        final ArrayList<String> arrayList = new ArrayList<>();
        actualizacionDao = Global.getMiglobal().getDaosession().getActualizacionDao();
        if (RestClientJar.getInternetState(getActivity().getApplicationContext())) {
            categoriaArrayList=  Global.getMiglobal().getDaosession().getCategoriaDao().queryBuilder().where(CategoriaDao.Properties.Tipo.eq("Noticia")).list();
            if (categoriaArrayList.isEmpty()){
                NCategoriaService = new NoticasCategoriaService(getActivity().getApplicationContext()) {
                    @Override
                    public void onSuccessObtenerCategoriaNoticia(List<Categoria> listaCategorias) throws JSONException {
                        Global.getMiglobal().getDaosession().getCategoriaDao().queryBuilder().where(CategoriaDao.Properties.Tipo.eq("Noticia")).buildDelete();
                        Global.getMiglobal().getDaosession().getCategoriaDao().insertOrReplaceInTx(listaCategorias);
                    }
                    @Override
                    public void onFinish() {
                        loadCategoriasBD();
                    }
                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        loadCategoriasBD();
                    }
                };
                NCategoriaService.obtenerCategoria();
            }else{
                loadCategoriasBD();
            }

        }
        else{
            loadCategoriasBD();
        }

    }

    private void loadCategoriasBD(){
        categoriaArrayList=  Global.getMiglobal().getDaosession().getCategoriaDao().queryBuilder().where(CategoriaDao.Properties.Tipo.eq("Noticia")).list();
        if (!categoriaArrayList.isEmpty()) {
            String categoria = "";
            arrayListItem.add(new EntryItem("Todas"));
            for (int i = 0; i <categoriaArrayList.size(); i++) {
                if (!categoria.equals(categoriaArrayList.get(i).getNombre())){
                    categoria = categoriaArrayList.get(i).getNombre();
                    arrayListItem.add(new SectionItem(categoriaArrayList.get(i).getNombre()));
                    arrayListItem.add(new EntryItem(categoriaArrayList.get(i).getVocabulario()));
                }else{
                    arrayListItem.add(new EntryItem(categoriaArrayList.get(i).getVocabulario()));
                }
            }
            adapter = new EntryAdapter(getActivity().getApplicationContext(),arrayListItem, "Noticia");
            listFilter.setAdapter(adapter);
        }else {
            RelativeLayout listContent = (RelativeLayout)getActivity().findViewById(R.id.noticia_filter);
            listContent.removeAllViews();
            View v = getActivity().getLayoutInflater().inflate(R.layout.error_conection_noticias, null);
            listContent.addView(v);
        }

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View headerView = ((LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header_listview, null, false);
        listFilter.addHeaderView(headerView);
        verNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arraySeleccion = adapter.getListSeleccion();
                listener.filtrodebusqueda(arraySeleccion);
                getActivity().getFragmentManager().popBackStack();
            }
        });


    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof NoticiaListener) {
            listener = (NoticiaListener) activity;
        }
    }

}