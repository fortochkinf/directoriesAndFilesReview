package com.blckRbbit.server.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class DirectoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String path;
    private Integer fileCount;
    private Integer dirCount;
    private Long sizeCount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "directory")
    private List<FileEntity> files;

    public DirectoryEntity() {
    }

    public DirectoryEntity(String path) {
        this.path = path;
        this.date = getDate();
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
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public Integer getDirCount() {
        return dirCount;
    }

    public void setDirCount(Integer dirCount) {
        this.dirCount = dirCount;
    }

    public Long getSizeCount() {
        return sizeCount;
    }

    public void setSizeCount(Long sizeCount) {
        this.sizeCount = sizeCount;
    }
}
