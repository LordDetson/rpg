package com.game.entity;

public interface Field<ENTITY> {
    String getFieldName();

    Class<?> getType();

    Object getValue(ENTITY entity);

    void setValue(ENTITY entity, Object value);
}
