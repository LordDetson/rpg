package com.game.service;

import java.util.List;
import java.util.Optional;

public interface EntityCrudService<ENTITY, ID> {

    ENTITY create(ENTITY entity);

    Optional<ENTITY> update(ENTITY entity);

    void delete(ID id);

    Optional<ENTITY> findById(ID id);

    boolean existsById(ID var1);

    List<ENTITY> getAll();

    long count();
}
