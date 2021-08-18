package com.blckRbbit.server.service;

import com.blckRbbit.server.entity.DirectoryEntity;
import com.blckRbbit.server.entity.FileEntity;
import com.blckRbbit.server.repository.DirectoryRepository;
import com.blckRbbit.server.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    private DirectoryRepository directoryRepository;

    @Autowired
    private FileRepository fileRepository;

    public Iterable<DirectoryEntity> showDirectory(){
        return directoryRepository.findAll();
    }

    public Iterable<FileEntity> showFiles () {
        return fileRepository.findAll();
    }

}
