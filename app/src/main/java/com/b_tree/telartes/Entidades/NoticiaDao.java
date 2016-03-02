package com.b_tree.telartes.Entidades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTICIA".
*/
public class NoticiaDao extends AbstractDao<Noticia, Long> {

    public static final String TABLENAME = "NOTICIA";

    /**
     * Properties of entity Noticia.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nid = new Property(1, int.class, "nid", false, "NID");
        public final static Property Titulo = new Property(2, String.class, "titulo", false, "TITULO");
        public final static Property Descripcion = new Property(3, String.class, "descripcion", false, "DESCRIPCION");
        public final static Property Categoria = new Property(4, String.class, "categoria", false, "CATEGORIA");
        public final static Property Fuente = new Property(5, String.class, "fuente", false, "FUENTE");
        public final static Property Imagen = new Property(6, String.class, "imagen", false, "IMAGEN");
        public final static Property Fecha = new Property(7, String.class, "fecha", false, "FECHA");
        public final static Property Enviado_por = new Property(8, String.class, "enviado_por", false, "ENVIADO_POR");
        public final static Property AutorEnlace = new Property(9, String.class, "autorEnlace", false, "AUTOR_ENLACE");
        public final static Property AutorNombre = new Property(10, String.class, "autorNombre", false, "AUTOR_NOMBRE");
    };


    public NoticiaDao(DaoConfig config) {
        super(config);
    }
    
    public NoticiaDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTICIA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NID\" INTEGER NOT NULL ," + // 1: nid
                "\"TITULO\" TEXT NOT NULL ," + // 2: titulo
                "\"DESCRIPCION\" TEXT," + // 3: descripcion
                "\"CATEGORIA\" TEXT," + // 4: categoria
                "\"FUENTE\" TEXT," + // 5: fuente
                "\"IMAGEN\" TEXT," + // 6: imagen
                "\"FECHA\" TEXT," + // 7: fecha
                "\"ENVIADO_POR\" TEXT," + // 8: enviado_por
                "\"AUTOR_ENLACE\" TEXT," + // 9: autorEnlace
                "\"AUTOR_NOMBRE\" TEXT);"); // 10: autorNombre
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTICIA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Noticia entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getNid());
        stmt.bindString(3, entity.getTitulo());
 
        String descripcion = entity.getDescripcion();
        if (descripcion != null) {
            stmt.bindString(4, descripcion);
        }
 
        String categoria = entity.getCategoria();
        if (categoria != null) {
            stmt.bindString(5, categoria);
        }
 
        String fuente = entity.getFuente();
        if (fuente != null) {
            stmt.bindString(6, fuente);
        }
 
        String imagen = entity.getImagen();
        if (imagen != null) {
            stmt.bindString(7, imagen);
        }
 
        String fecha = entity.getFecha();
        if (fecha != null) {
            stmt.bindString(8, fecha);
        }
 
        String enviado_por = entity.getEnviado_por();
        if (enviado_por != null) {
            stmt.bindString(9, enviado_por);
        }
 
        String autorEnlace = entity.getAutorEnlace();
        if (autorEnlace != null) {
            stmt.bindString(10, autorEnlace);
        }
 
        String autorNombre = entity.getAutorNombre();
        if (autorNombre != null) {
            stmt.bindString(11, autorNombre);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Noticia readEntity(Cursor cursor, int offset) {
        Noticia entity = new Noticia( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // nid
            cursor.getString(offset + 2), // titulo
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // descripcion
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // categoria
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // fuente
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // imagen
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // fecha
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // enviado_por
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // autorEnlace
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // autorNombre
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Noticia entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNid(cursor.getInt(offset + 1));
        entity.setTitulo(cursor.getString(offset + 2));
        entity.setDescripcion(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCategoria(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFuente(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setImagen(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFecha(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setEnviado_por(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAutorEnlace(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAutorNombre(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Noticia entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Noticia entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
