package com.blckRbbit.server.service;

import com.blckRbbit.server.entity.DirectoryEntity;
import com.blckRbbit.server.exeption.PathNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class DirectoryServiceTest {

    @Autowired
    private DirectoryService directoryService;

    @Test
    void register() throws PathNotFoundException {

        DirectoryEntity entity = new DirectoryEntity("/");
        DirectoryEntity isEntityCreated = directoryService.register(entity);

        Assert.notNull(isEntityCreated.getId());
        Assert.notNull(isEntityCreated.getDate());
        Assert.notNull(isEntityCreated.getPath());
        Assert.notNull(isEntityCreated.getDirCount());
        Assert.notNull(isEntityCreated.getFileCount());
        Assert.notNull(isEntityCreated.getSizeCount());
        Assert.notNull(isEntityCreated);
    }
}