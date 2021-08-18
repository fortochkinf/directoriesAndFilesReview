package com.blckRbbit.server.service;

import com.blckRbbit.server.entity.DirectoryEntity;
import com.blckRbbit.server.exeption.PathNotFoundException;
import com.blckRbbit.server.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class DirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    public DirectoryEntity register(DirectoryEntity directory) throws PathNotFoundException {
        String path = directory.getPath();
        File folder = null;

        try {
             folder = new File(path);
        }catch (Exception e){
            System.out.println("Error! Path not found");
            throw new PathNotFoundException("Path not found");
        }
        File[] listOfFiles = folder.listFiles();

        int folderLength = 0;
        int fileCount = 0;
        int dirCount = 0;
        long sizeCount = 0L;
        assert listOfFiles != null;

        for (File file : listOfFiles) {
            folderLength += 1;
            sizeCount += file.length();
            if (file.isFile()) {
                fileCount += 1;
            } else {
                dirCount += 1;
            }
        }

        directory.setFileCount(fileCount);
        directory.setDirCount(dirCount);
        directory.setSizeCount(sizeCount);
        return directoryRepository.save(directory);
    }
}
