package com.b_tree.telartes.principal;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.Categoria;
import com.b_tree.telartes.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.NoticasCategoriaService;
import com.b_tree.telartes.rest.RestClientJar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 03-03-16.
 */
public class NoticiaCategoriaActivity extends BaseTelartesActivity {
    private ListView listFilter;
    private LinearLayout verNoticias;
    private ActualizacionDao actualizacionDao;
    private NoticasCategoriaService NCategoriaService;
    private  List<Categoria> categoriaArrayList;
    @Override
    protected String getScreenLabel() {
        return null;
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        listFilter = (ListView)findViewById(R.id.list_filter);
        verNoticias = (LinearLayout)findViewById(R.id.ver_noticias);
        categoriaArrayList = new ArrayList<>();
        actualizacionDao = Global.getMiglobal().getDaosession().getActualizacionDao();
        if (RestClientJar.getInternetState(this)) {
//           NCategoriaService = new NoticasCategoriaService(this) {
//               @Override
//               public void onSuccessObtenerCategoriaNoticia(List<Categoria> categoriaList) {
//                   categoriaArrayList= categoriaList;
//                   Global.getMiglobal().getDaosession().getCategoriaDao().insertInTx(categoriaList);
//               }
//
//               @Override
//               public void onFinish() {
//                   super.onFinish();
////                   List<Item> items = new ArrayList<Item>();
////                   int color = getResources().getColor(R.color.lila);
////                   for (int i = 0; i <categoriaArrayList.size() ; i++) {
////                       if(items.isEmpty()){
////                           items.add(new SectionItem(categoriaArrayList.get(i).getNombre()));
////                           items.add(new EntryItem(categoriaArrayList.get(i).getVocabulario(), color));
////                       }else{
////
////                           for (int j = 0; j <items.size() ; j++) {
////                               if (items.get(j).content()!=categoriaArrayList.get(i).getNombre()){
////                                   items.add(new SectionItem(categoriaArrayList.get(i).getNombre()));
////                                   items.add(new EntryItem(categoriaArrayList.get(i).getVocabulario(), color));
////                               }else{
////                                   items.add(new EntryItem(categoriaArrayList.get(i).getVocabulario(), color));
////                               }
////                           }
////                       }
////                   }
////                   CategoriaArrayAdapter adapter = new CategoriaArrayAdapter(getApplicationContext(), items);
////                   listFilter.setAdapter(adapter);
//
//               }
//           };
//            NCategoriaService.obtenerCategoria();
        }




//        verNoticias.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(NoticiaCategoriaActivity.this, NoticiaActivity.class));
//            }
//        });
    }

    @Override
    protected int getResLayout() {
        return R.layout.noticia_categoria_layout;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {

    }
}
