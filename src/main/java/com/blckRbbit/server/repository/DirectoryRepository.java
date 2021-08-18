package com.blckRbbit.server.repository;

import com.blckRbbit.server.entity.DirectoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface DirectoryRepository extends CrudRepository <DirectoryEntity, Long> {
    DirectoryEntity findByPath(String path);
}
