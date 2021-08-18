package com.blckRbbit.server.model;

import com.blckRbbit.server.entity.DirectoryEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Directory {

    private Long id;
    private String path;

    public static Directory toModel (DirectoryEntity entity) {
        Directory model = new Directory();
        model.setId(entity.getId());
        model.setDate(entity.getDate());
        model.setPath(entity.getPath());
        return model;
    }

    public Directory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void setDate(String date) {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
