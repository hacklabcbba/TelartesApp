package com.example;
import java.net.URI;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class daogenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "");
       // addAgendaCultural(schema);
       // addNoticia(schema);
        addConvocatorias(schema);
        new DaoGenerator().generateAll(schema, "/home/noemi/martadero/telartes/app/src/main/java/com/b_tree/telartes/Entidades");
    }

//
//    private static void addAgendaCultural(Schema schema) {
//        Entity agendacultural = schema.addEntity("AgendaCultural");
//        agendacultural.addIdProperty().autoincrement().primaryKey();
//        agendacultural.addIntProperty("nid").notNull();
//        agendacultural.addStringProperty("titulo").notNull();
//        agendacultural.addStringProperty("campo");
//        agendacultural.addStringProperty("descripcion");
//        agendacultural.addStringProperty("fecha");
//        agendacultural.addStringProperty("imagen");
//        agendacultural.addStringProperty("costo");
//        agendacultural.addStringProperty("departamento");
//        agendacultural.addStringProperty("enlace");
//        agendacultural.addStringProperty("fechainicio");
//        agendacultural.addStringProperty("fechafin");
//        agendacultural.addStringProperty("lugar_direccion");
//        agendacultural.addStringProperty("archivo");
//        agendacultural.addStringProperty("enviado_por");
//    }


    private static void addConvocatorias(Schema schema) {
        Entity convocatorias = schema.addEntity("Convocatoria");
        convocatorias.addIdProperty().autoincrement().primaryKey();
        convocatorias.addIntProperty("nid").notNull();
        convocatorias.addStringProperty("titulo").notNull();
        convocatorias.addStringProperty("fechalimite");
        convocatorias.addStringProperty("descripcion");
        convocatorias.addStringProperty("lugar");
        convocatorias.addStringProperty("archivo");
        convocatorias.addStringProperty("imagen");
        convocatorias.addStringProperty("areas");
        convocatorias.addStringProperty("campo");
        convocatorias.addStringProperty("categoria");
        convocatorias.addStringProperty("convocante");
        convocatorias.addStringProperty("fechafin");
        convocatorias.addStringProperty("direccionweb");
        convocatorias.addStringProperty("correo");
        convocatorias.addStringProperty("enviado_por");


    }
//
//    private static void addNoticia(Schema schema) {
//        Entity noticia = schema.addEntity("Noticia");
//        noticia.addIdProperty().autoincrement().primaryKey();
//        noticia.addIntProperty("nid").notNull();
//        noticia.addStringProperty("titulo").notNull();
//        noticia.addStringProperty("descripcion");
//        noticia.addStringProperty("categoria");
//        noticia.addStringProperty("fuente");
//        noticia.addStringProperty("imagen");
//        noticia.addStringProperty("fecha");
//        noticia.addStringProperty("enviado_por");
//        noticia.addStringProperty("autorEnlace");
//        noticia.addStringProperty("autorNombre");
//
//    }







}