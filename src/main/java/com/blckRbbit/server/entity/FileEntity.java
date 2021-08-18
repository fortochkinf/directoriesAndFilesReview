package com.blckRbbit.server.entity;

import javax.persistence.*;

@Entity
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long size;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "directoryAndFiles")
    private DirectoryEntity directory;

    public FileEntity() {
    }

    public FileEntity(DirectoryEntity directory) {
        this.directory = directory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public DirectoryEntity getDirectory() {
        return directory;
    }

    public void setDirectory(DirectoryEntity directory) {
        this.directory = directory;
    }
}
