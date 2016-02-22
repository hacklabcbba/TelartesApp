package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.b_tree.telartes.Entidades.AgendaCultural;
import com.b_tree.telartes.Entidades.Convocatoria;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.ConvocatoriaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.NoticiasService;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 22-02-16.
 */
public class ConvocatoriaActivity extends BaseTelartesActivity {

    private ListView lvConvocatoria;
    private ConvocatoriaAdapter convocatoriaAdapter;
    private NoticiasService noticias;
    private List<Convocatoria> convocatoriaList;

    @Override
    protected String getScreenLabel() {
        return "CONVOCATORIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        convocatoriaList = new ArrayList<>();
        try{
            JSONArray json = new JSONArray(new String("[{\"node_title\":\"<a href=\\\"/content/juntucha-convoca-bandas-y-solistas-nacionales-independientes-participar-en-la-clasificatoria\\\">JUNTUCHA convoca a bandas y solistas nacionales independientes a participar en la clasificatoria RUMBO AL JUNTUCHA 2016 </a>\",\"archivo pdf\":null,\"areas\":\"<a href=\\\"/categoria/musicales\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Musicales</a>\",\"areas (field_call_areas:delta)\":\"0\",\"campos\":\"<a href=\\\"/campos-de-interes/circulacion-cultural\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Circulaci\\u00f3n cultural</a>\",\"campos (field_call_fields:delta)\":\"0\",\"categor\\u00eda\":\"<a href=\\\"/convocatoria/movilidad\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Movilidad</a>\",\"ciudad\":\"<a href=\\\"/ciudad/nacional\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Nacional</a>\",\"convocante\":\"La Juntucha\",\"direcci\\u00f3n web\":null,\"correo electr\\u00f3nico\":\"juntucha@live.com /  juntucha@outlook.com / juntucha@hotmail.com\",\"fecha limite\":\"<span class=\\\"date-display-single\\\" property=\\\"dc:date\\\" datatype=\\\"xsd:dateTime\\\" content=\\\"2016-02-29T00:00:00-04:00\\\">Lunes, Febrero 29, 2016</span>\",\"imagen\":\"<img typeof=\\\"foaf:Image\\\" src=\\\"http://telartes.org.bo/sites/default/files/call/image/12571098_10207180788770979_368267543_n.jpg\\\" width=\\\"480\\\" height=\\\"162\\\" alt=\\\"\\\" />\",\"fecha de mensaje\":\"01/18/2016 - 13:09\",\"fecha de modificaci\\u00f3n\":\"01/18/2016 - 17:50\",\"nid\":\"1383\"},{\"node_title\":\"<a href=\\\"/content/concurso-de-novela-%E2%80%9Cdistancia-breve%E2%80%9D\\\">CONCURSO DE NOVELA \\u201cDistancia Breve\\u201d</a>\",\"archivo pdf\":null,\"areas\":\"<a href=\\\"/categoria/literarias\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Literarias</a>\",\"areas (field_call_areas:delta)\":\"0\",\"campos\":\"<a href=\\\"/campos-de-interes/circulacion-cultural\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Circulaci\\u00f3n cultural</a>\",\"campos (field_call_fields:delta)\":\"0\",\"categor\\u00eda\":\"<a href=\\\"/convocatoria/concurso\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Concurso</a>\",\"ciudad\":null,\"convocante\":\"Yerba Mala Cartonera y Centro Cultural Casa Abierta\",\"direcci\\u00f3n web\":\"<a href=\\\"http://yerbamalacartonera.blogspot.com/2016/01/concurso-de-novela-distancia-breve.html\\\">http://yerbamalacartonera.blogspot.com/2016/01/concurso-de-novela-distancia-brev...</a>\",\"correo electr\\u00f3nico\":\" yerbamalacartonera@gmail.com\",\"fecha limite\":\"<span class=\\\"date-display-single\\\" property=\\\"dc:date\\\" datatype=\\\"xsd:dateTime\\\" content=\\\"2016-02-29T00:00:00-04:00\\\">Lunes, Febrero 29, 2016</span>\",\"imagen\":\"<img typeof=\\\"foaf:Image\\\" src=\\\"http://telartes.org.bo/sites/default/files/call/image/ws_Road_at_Night_2560x1600.jpg\\\" width=\\\"640\\\" height=\\\"400\\\" alt=\\\"\\\" />\",\"fecha de mensaje\":\"01/14/2016 - 16:11\",\"fecha de modificaci\\u00f3n\":\"01/14/2016 - 16:11\",\"nid\":\"1367\"},{\"node_title\":\"<a href=\\\"/content/convocatoria-al-festival-de-performance-cimientos\\\">CONVOCATORIA AL FESTIVAL DE PERFORMANCE CIMIENTOS</a>\",\"archivo pdf\":null,\"areas\":\"<a href=\\\"/categoria/plasticas\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Pl\\u00e1sticas</a>, <a href=\\\"/categoria/audiovisuales\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Audiovisuales</a>, <a href=\\\"/categoria/visuales\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Visuales</a>\",\"areas (field_call_areas:delta)\":\"0\",\"campos\":\"<a href=\\\"/campos-de-interes/circulacion-cultural\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Circulaci\\u00f3n cultural</a>\",\"campos (field_call_fields:delta)\":\"0\",\"categor\\u00eda\":\"<a href=\\\"/convocatoria/movilidad\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Movilidad</a>\",\"ciudad\":\"<a href=\\\"/ciudad/nacional\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Nacional</a>\",\"convocante\":\"Centro Sim\\u00f3n I. Pati\\u00f1o\",\"direcci\\u00f3n web\":\"<a href=\\\"http://centropatino.fundacionpatino.org/\\\">http://centropatino.fundacionpatino.org/</a>\",\"correo electr\\u00f3nico\":\"douglasrodrigorada@gmail.com\",\"fecha limite\":\"<span class=\\\"date-display-single\\\" property=\\\"dc:date\\\" datatype=\\\"xsd:dateTime\\\" content=\\\"2016-04-15T00:00:00-04:00\\\">Viernes, Abril 15, 2016</span>\",\"imagen\":\"<img typeof=\\\"foaf:Image\\\" src=\\\"http://telartes.org.bo/sites/default/files/call/image/28d74329-a49b-438f-ba1c-e9882d40f886.jpg\\\" width=\\\"1183\\\" height=\\\"437\\\" alt=\\\"\\\" />\",\"fecha de mensaje\":\"01/12/2016 - 17:59\",\"fecha de modificaci\\u00f3n\":\"01/12/2016 - 17:59\",\"nid\":\"1356\"},{\"node_title\":\"<a href=\\\"/content/convocatoria-al-festival-de-performance-cimientos\\\">CONVOCATORIA AL FESTIVAL DE PERFORMANCE CIMIENTOS</a>\",\"archivo pdf\":null,\"areas\":\"<a href=\\\"/categoria/plasticas\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Pl\\u00e1sticas</a>, <a href=\\\"/categoria/audiovisuales\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Audiovisuales</a>, <a href=\\\"/categoria/visuales\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Visuales</a>\",\"areas (field_call_areas:delta)\":\"1\",\"campos\":\"<a href=\\\"/campos-de-interes/circulacion-cultural\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Circulaci\\u00f3n cultural</a>\",\"campos (field_call_fields:delta)\":\"0\",\"categor\\u00eda\":\"<a href=\\\"/convocatoria/movilidad\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Movilidad</a>\",\"ciudad\":\"<a href=\\\"/ciudad/nacional\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Nacional</a>\",\"convocante\":\"Centro Sim\\u00f3n I. Pati\\u00f1o\",\"direcci\\u00f3n web\":\"<a href=\\\"http://centropatino.fundacionpatino.org/\\\">http://centropatino.fundacionpatino.org/</a>\",\"correo electr\\u00f3nico\":\"douglasrodrigorada@gmail.com\",\"fecha limite\":\"<span class=\\\"date-display-single\\\" property=\\\"dc:date\\\" datatype=\\\"xsd:dateTime\\\" content=\\\"2016-04-15T00:00:00-04:00\\\">Viernes, Abril 15, 2016</span>\",\"imagen\":\"<img typeof=\\\"foaf:Image\\\" src=\\\"http://telartes.org.bo/sites/default/files/call/image/28d74329-a49b-438f-ba1c-e9882d40f886.jpg\\\" width=\\\"1183\\\" height=\\\"437\\\" alt=\\\"\\\" />\",\"fecha de mensaje\":\"01/12/2016 - 17:59\",\"fecha de modificaci\\u00f3n\":\"01/12/2016 - 17:59\",\"nid\":\"1356\"},{\"node_title\":\"<a href=\\\"/content/convocatoria-al-festival-de-performance-cimientos\\\">CONVOCATORIA AL FESTIVAL DE PERFORMANCE CIMIENTOS</a>\",\"archivo pdf\":null,\"areas\":\"<a href=\\\"/categoria/plasticas\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Pl\\u00e1sticas</a>, <a href=\\\"/categoria/audiovisuales\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Audiovisuales</a>, <a href=\\\"/categoria/visuales\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Visuales</a>\",\"areas (field_call_areas:delta)\":\"2\",\"campos\":\"<a href=\\\"/campos-de-interes/circulacion-cultural\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Circulaci\\u00f3n cultural</a>\",\"campos (field_call_fields:delta)\":\"0\",\"categor\\u00eda\":\"<a href=\\\"/convocatoria/movilidad\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Movilidad</a>\",\"ciudad\":\"<a href=\\\"/ciudad/nacional\\\" typeof=\\\"skos:Concept\\\" property=\\\"rdfs:label skos:prefLabel\\\" datatype=\\\"\\\">Nacional</a>\",\"convocante\":\"Centro Sim\\u00f3n I. Pati\\u00f1o\",\"direcci\\u00f3n web\":\"<a href=\\\"http://centropatino.fundacionpatino.org/\\\">http://centropatino.fundacionpatino.org/</a>\",\"correo electr\\u00f3nico\":\"douglasrodrigorada@gmail.com\",\"fecha limite\":\"<span class=\\\"date-display-single\\\" property=\\\"dc:date\\\" datatype=\\\"xsd:dateTime\\\" content=\\\"2016-04-15T00:00:00-04:00\\\">Viernes, Abril 15, 2016</span>\",\"imagen\":\"<img typeof=\\\"foaf:Image\\\" src=\\\"http://telartes.org.bo/sites/default/files/call/image/28d74329-a49b-438f-ba1c-e9882d40f886.jpg\\\" width=\\\"1183\\\" height=\\\"437\\\" alt=\\\"\\\" />\",\"fecha de mensaje\":\"01/12/2016 - 17:59\",\"fecha de modificaci\\u00f3n\":\"01/12/2016 - 17:59\",\"nid\":\"1356\"}]"));
            for (int c = 0; c <json.length() ; c++) {
                convocatoriaList.add(new Convocatoria(json.getJSONObject(c)));
            }
            convocatoriaAdapter = new ConvocatoriaAdapter(getBaseContext(), convocatoriaList);
        }catch (Exception e){

        }
    }

    @Override
    protected int getResLayout() {
        return R.layout.convocatoria_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        lvConvocatoria = (ListView) findViewById(R.id.lv_convocatoria);
        lvConvocatoria.setAdapter(convocatoriaAdapter);
        lvConvocatoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ConvocatoriaActivity.this, ConvocatoriaDetalleActivity.class);
                i.putExtra("convocatoria", convocatoriaList.get(position));
                startActivity(i);
            }
        });

    }}
