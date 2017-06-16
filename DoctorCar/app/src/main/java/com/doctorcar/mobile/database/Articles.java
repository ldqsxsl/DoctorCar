package com.doctorcar.mobile.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by dd on 2017/5/8.
 */

@Entity
public class Articles {

    @Id()
    private Long id;

    @Property(nameInDb = "TITLE")
    @NotNull
    private String title;

    @Property(nameInDb = "CONTENT")
    @NotNull
    private String content;

    @Property(nameInDb = "PRIVACY")
    @NotNull
    private String privacy;//0不是私有，1是私有

    @Generated(hash = 346143720)
    public Articles(Long id, @NotNull String title, @NotNull String content,
            @NotNull String privacy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.privacy = privacy;
    }
    @Generated(hash = 2051751083)
    public Articles() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
}
