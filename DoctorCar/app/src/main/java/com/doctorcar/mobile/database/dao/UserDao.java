package com.doctorcar.mobile.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.doctorcar.mobile.database.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Userid = new Property(1, String.class, "userid", false, "USERID");
        public final static Property Username = new Property(2, String.class, "username", false, "USERNAME");
        public final static Property Password = new Property(3, String.class, "password", false, "PASSWORD");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USERID\" TEXT," + // 1: userid
                "\"USERNAME\" TEXT," + // 2: username
                "\"PASSWORD\" TEXT);"); // 3: password
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_USER_USERID ON USER" +
                " (\"USERID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(2, userid);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(3, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(2, userid);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(3, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // username
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // password
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUsername(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPassword(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
