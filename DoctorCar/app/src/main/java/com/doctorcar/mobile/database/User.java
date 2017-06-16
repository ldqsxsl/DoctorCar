package com.doctorcar.mobile.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by dd on 2016/11/8.
 */
@Entity
public class User {
    @Id
    private Long id;
    @Property(nameInDb = "USERID")
    @Index(unique = true)
    private String userid;
    @Property(nameInDb = "USERNAME")
    private String username;
    @Property(nameInDb = "PASSWORD")
    private String password;
    @Generated(hash = 1945252461)
    public User(Long id, String userid, String username, String password) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.password = password;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

}
