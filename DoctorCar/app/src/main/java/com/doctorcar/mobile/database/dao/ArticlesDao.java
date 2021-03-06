package com.doctorcar.mobile.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.doctorcar.mobile.database.Articles;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ARTICLES".
*/
public class ArticlesDao extends AbstractDao<Articles, Long> {

    public static final String TABLENAME = "ARTICLES";

    /**
     * Properties of entity Articles.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property Privacy = new Property(3, String.class, "privacy", false, "PRIVACY");
    }


    public ArticlesDao(DaoConfig config) {
        super(config);
    }
    
    public ArticlesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ARTICLES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TITLE\" TEXT NOT NULL ," + // 1: title
                "\"CONTENT\" TEXT NOT NULL ," + // 2: content
                "\"PRIVACY\" TEXT NOT NULL );"); // 3: privacy
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ARTICLES\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Articles entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getTitle());
        stmt.bindString(3, entity.getContent());
        stmt.bindString(4, entity.getPrivacy());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Articles entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getTitle());
        stmt.bindString(3, entity.getContent());
        stmt.bindString(4, entity.getPrivacy());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Articles readEntity(Cursor cursor, int offset) {
        Articles entity = new Articles( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // title
            cursor.getString(offset + 2), // content
            cursor.getString(offset + 3) // privacy
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Articles entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.getString(offset + 1));
        entity.setContent(cursor.getString(offset + 2));
        entity.setPrivacy(cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Articles entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Articles entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Articles entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
