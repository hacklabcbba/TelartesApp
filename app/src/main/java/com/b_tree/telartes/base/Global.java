package com.b_tree.telartes.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.b_tree.telartes.Entidades.DaoMaster;
import com.b_tree.telartes.Entidades.DaoSession;



public class Global extends Application {
        private int actualizacion;
        private int ingresos;
        private int posicion;
        private static Global miglobal;
        private Context context;
        private DaoMaster daomaster;
        private DaoSession daosession;
        private DaoMaster.DevOpenHelper helper;

        public DaoMaster.DevOpenHelper getHelper() {
            return helper;
        }

        public void setHelper(DaoMaster.DevOpenHelper helper) {
            this.helper = helper;
        }

        private Cursor cursor;
        private SQLiteDatabase db;

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            // TODO Auto-generated method stub
            super.onConfigurationChanged(newConfig);
        }
        @Override
        public void onCreate() {
            // TODO Auto-generated method stub
            super.onCreate();
            miglobal = this;
            helper = new DaoMaster.DevOpenHelper(this, "telartes-db", null);
            db = helper.getWritableDatabase();
            daomaster = new DaoMaster(db);
            daosession = daomaster.newSession();

        }

        public int getActualizacion() {
            return actualizacion;
        }

        public void setActualizacion(int actualizacion) {
            this.actualizacion = actualizacion;
        }

        public int getIngresos() {
            return ingresos;
        }

        public void setIngresos(int ingresos) {
            this.ingresos = ingresos;
        }



        public int getPosicion() {
            return posicion;
        }

        public void setPosicion(int posicion) {
            this.posicion = posicion;
        }


        public static Object getinstance() {

            return miglobal;
        }

        public static Global getMiglobal() {
            return miglobal;
        }

        public static void setMiglobal(Global miglobal) {
            Global.miglobal = miglobal;
        }

        public DaoMaster getDaomaster() {
            return daomaster;
        }

        public void setDaomaster(DaoMaster daomaster) {
            this.daomaster = daomaster;
        }

        public DaoSession getDaosession() {
            return daosession;
        }

        public void setDaosession(DaoSession daosession) {
            this.daosession = daosession;
        }

        public Cursor getCursor() {
            return cursor;
        }

        public void setCursor(Cursor cursor) {
            this.cursor = cursor;
        }

        public SQLiteDatabase getDb() {
            return db;
        }

        public void setDb(SQLiteDatabase db) {
            this.db = db;
        }

    }

