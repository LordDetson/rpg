package com.game.repository;

import com.game.entity.Field;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Date;

public final class PrimitiveSpecifications {

    public static <T> Specification<T> like(Field<? extends T> field, String text) {
        assertFieldType(field, String.class);
        if (!StringUtils.hasText(text)) {
            return null;
        }
        return (root, query, builder) -> builder.like(root.get(field.getFieldName()), contains(text));
    }

    public static <T> Specification<T> equal(Field<? extends T> field, Boolean value) {
        assertFieldType(field, Boolean.class);
        if (value == null) {
            return null;
        }
        return (root, query, builder) -> builder.equal(root.get(field.getFieldName()).as(Boolean.class), value);
    }

    public static <T> Specification<T> equal(Field<? extends T> field, Enum<?> value) {
        assertEnumFieldType(field);
        if (value == null) {
            return null;
        }
        return (root, query, builder) -> builder.equal(root.get(field.getFieldName()).as(String.class), value.name());
    }

    public static <T> Specification<T> between(Field<? extends T> field, Integer from, Integer to) {
        assertFieldType(field, Integer.class);
        if (from == null || to == null) {
            return null;
        }
        return (root, query, builder) -> builder.between(root.get(field.getFieldName()).as(Integer.class), from, to);
    }

    public static <T> Specification<T> between(Field<? extends T> field, Date startDate, Date finishDate) {
        assertFieldType(field, Date.class);
        if (startDate == null || finishDate == null) {
            return null;
        }
        return (root, query, builder) -> builder.between(root.get(field.getFieldName()).as(Date.class), startDate, finishDate);
    }

    public static <T> Specification<T> greaterThan(Field<? extends T> field, Integer value) {
        assertFieldType(field, Integer.class);
        if (value == null) {
            return null;
        }
        return (root, query, builder) -> builder.greaterThan(root.get(field.getFieldName()).as(Integer.class), value);
    }

    public static <T> Specification<T> greaterThan(Field<? extends T> field, Date value) {
        assertFieldType(field, Date.class);
        if (value == null) {
            return null;
        }
        return (root, query, builder) -> builder.greaterThan(root.get(field.getFieldName()).as(Date.class), value);
    }

    public static <T> Specification<T> lessThan(Field<? extends T> field, Integer value) {
        assertFieldType(field, Integer.class);
        if (value == null) {
            return null;
        }
        return (root, query, builder) -> builder.lessThan(root.get(field.getFieldName()).as(Integer.class), value);
    }

    public static <T> Specification<T> lessThan(Field<? extends T> field, Date value) {
        assertFieldType(field, Date.class);
        if (value == null) {
            return null;
        }
        return (root, query, builder) -> builder.lessThan(root.get(field.getFieldName()).as(Date.class), value);
    }

    private static void assertFieldType(Field<?> field, Class<?> type) {
        assert field.getType().isAssignableFrom(type) : "Not supported searching by " + field.getType().getSimpleName() + " field type";
    }

    private static void assertEnumFieldType(Field<?> field) {
        assert field.getType().isEnum() :  field.getType().getSimpleName() + " field type isn't enum";
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }
}
