package com.b_tree.telartes.principal;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.base.JSONParser;
import com.b_tree.telartes.base.Utils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by diana.mejia on 16/06/2015.
 */
public class RutaEntregaActivity extends BaseTelartesActivity implements MapFragment.OnMapReadyListener, GoogleMap.OnMapClickListener, GoogleMap.OnMyLocationChangeListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private FrameLayout mapContainer;


    private GoogleMap googleMap;
    private Noticia pedidoSelected;

    public static String key_lista_punto = "key_punto";

    private static HashMap<Marker, Noticia> puntoMarker = new HashMap<Marker, Noticia>();
    private static LongSparseArray<Marker> markersPuntos = new LongSparseArray<Marker>();
    private SparseArray<BitmapDescriptor> iconosCategorias = new SparseArray<BitmapDescriptor>();
    private List<Noticia> lstPuntos = new ArrayList<Noticia>();
    private int todos;
    private int cantEntregado;
    private int cantNoEntregado;

    private static final LatLng SCZ_VIEW = new LatLng(-17.783848596044916,
            -63.1809747558593);
    public static int SCZ_UBICACION = 11;





    @Override
    protected int getResLayout() {
        return R.layout.activity_ruta_entrega;
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        cargarMapa();
        lstPuntos = Utils.cargarEventos();
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {


    }

    @Override
    protected String getScreenLabel() {
        return "Eventos";
    }




    public Bitmap redimencionarIcono(Bitmap bitmap) {
        return Utils.getResizedBitmap(bitmap, 60, 40);
    }

    public BitmapDescriptor loadBitmapDescriptor() {
        BitmapDescriptor  bitmapDesc = BitmapDescriptorFactory.fromResource(R.mipmap.indicador_negro);
        return bitmapDesc;
    }

    public void actualizarMarcadores() {
        if (googleMap != null) {
            googleMap.clear();
            for (Noticia punto : lstPuntos) {
                Marker m = aderirMarcador(punto);
                if (m != null) {
                    puntoMarker.put(m, punto);
                    markersPuntos.put(punto.getNid(), m);
                }
            }
        }
    }

    public Marker aderirMarcador(Noticia punto) {
//        if (punto.getLatitud() != null    && punto.getImagen() != null) {
//            MarkerOptions markerOptions = new MarkerOptions()
//                    .position(
//                            new LatLng(punto.getLatitud(), punto.getImagen()))
//                    .icon(loadBitmapDescriptor())
//                    .title(punto.getTitulo())
//                    .snippet(punto.getCategoria());
//            return googleMap.addMarker(markerOptions);
//        } else {
//            return null;
//        }
        return  null;
    }

    public void cargarMapa() {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MapFragment tms = MapFragment.newInstance();
        tms.setListener(this);
        fragmentTransaction.add(R.id.map_container, tms).commit();
    }

    private void SituarMapa(LatLng place, int zo) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(place).zoom(zo).bearing(0).tilt(0).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (this.googleMap != null) {
            this.googleMap.setOnMapClickListener(this);
            this.googleMap.setOnMyLocationChangeListener(this);
            this.googleMap.setMyLocationEnabled(true);
            this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            this.googleMap.setOnMarkerClickListener(this);
            this.googleMap.setOnInfoWindowClickListener(this);
            SituarMapa(SCZ_VIEW, SCZ_UBICACION);
            actualizarMarcadores();
        }
    }

    private void trazarRuta(LatLng p1, LatLng p2) {
        String url = makeURL(p1.latitude, p1.longitude, p2.latitude,
                p2.longitude);
        connectAsyncTask cn = new connectAsyncTask(url);
        cn.execute();
    }

    private String makeURL(double sourcelat, double sourcelog, double destlat,
                           double destlog) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("http://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString.append(Double.toString(sourcelog));
        urlString.append("&destination=");// to
        urlString.append(Double.toString(destlat));
        urlString.append(",");
        urlString.append(Double.toString(destlog));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        return urlString.toString();
    }

    private void drawPath(String result) {

        try {
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes
                    .getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);

            for (int z = 0; z < list.size() - 1; z++) {
                LatLng src = list.get(z);
                LatLng dest = list.get(z + 1);
                googleMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude),
                                new LatLng(dest.latitude, dest.longitude))
                        .width(5).color(getResources().getColor(R.color.rojo))
                        .geodesic(true));
            }
        } catch (JSONException e) {
        }
    }

    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }

    private class connectAsyncTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog progressDialog;
        String url;

        connectAsyncTask(String urlPass) {
            url = urlPass;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(RutaEntregaActivity.this);
            progressDialog.setMessage("Obteniendo Ruta, Porfavor Espere...");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String json = JSONParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();
            if (result != null && !result.equals("")) {
                drawPath(result);
            } else {
                Toast.makeText(RutaEntregaActivity.this,
                        "Ruta no disponible para el punto seleccionado..",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onTimeExpiredMapNotInstanceYet() {
    }

    @Override
    public void onInfoWindowClick(Marker arg0) {
    }

    @Override
    public boolean onMarkerClick(Marker arg0) {
        pedidoSelected = puntoMarker.get(arg0);
        if (pedidoSelected != null) {
            arg0.showInfoWindow();
          //  SituarMapa(new LatLng(pedidoSelected.getLatitud(),pedidoSelected.getImagen()), 15);
        }

       // CargarInfo(pedidoSelected);
        return true;
    }








    public void SituarMapaPuntos(LatLng a, LatLng b) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        builder.include(a);
        builder.include(b);
        LatLngBounds bounds = builder.build();

        int padding = 175;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.animateCamera(cu);
        googleMap.moveCamera(cu);

    }

    @Override
    public void onMyLocationChange(Location arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onMapClick(LatLng arg0) {
        cleanMapa();
        actualizarMarcadores();
        SituarMapa(SCZ_VIEW, SCZ_UBICACION);

    }

    public void cleanMapa() {
        googleMap.clear();
    }
}
