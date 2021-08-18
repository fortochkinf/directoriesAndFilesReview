package com.blckRbbit.server.repository;

import com.blckRbbit.server.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileEntity, Long> {
}
