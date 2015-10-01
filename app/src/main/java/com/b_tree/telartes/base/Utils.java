package com.b_tree.telartes.base;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.b_tree.telartes.Entidades.Evento;
import com.b_tree.telartes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diana on 27/09/2015.
 */
public class Utils {
    public static List<Evento> cargarEventos() {
        List<Evento> lstEvento = new ArrayList<>();

        lstEvento.add(new Evento("ACTO BLANCO","Centro Cultural Simón I. Patiño","Lunes, Septiembre 7, 2015 - 20:00",-17.767942, -63.177974, 1));
        lstEvento.add(new Evento("FESTIVAL INTERNACIONAL DANZA CONTEMPORANEA","Centro Cultural Simón I. Patiño","Lunes, Septiembre 7, 2015 - 20:00",-17.782654, -63.176944,2));
        lstEvento.add(new Evento("DOBLETE HACKER CICLO DE CINE HACKER","Centro Cultural Simón I. Patiño","Lunes, Septiembre 7, 2015 - 20:00",-17.796711, -63.196857,3));
        lstEvento.add(new Evento("III FERIA DE LOS AUTORES","Centro Cultural Simón I. Patiño","Lunes, Septiembre 7, 2015 - 20:00",-17.781510, -63.187416,4));
        lstEvento.add(new Evento("RE-ESTRENO DE \"EL BOLILLO FATAL\"","Centro Cultural Simón I. Patiño","Lunes, Septiembre 7, 2015 - 20:00",-17.786495, -63.180978,5));
        lstEvento.add(new Evento("ACTO BLANCO","Centro Cultural Simón I. Patiño","Lunes, Septiembre 7, 2015 - 20:00",-17.799163, -63.167160,6));
        lstEvento.add(new Evento("ACTO BLANCO","Centro Cultural Simón I. Patiño","Lunes, Septiembre 7, 2015 - 20:00",-17.782245, -63.176086,7));

        return lstEvento;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);
        return resizedBitmap;
    }
}
