package com.game.service.criteria;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class PageCriteria {

    String order;
    Integer pageNumber;
    Integer pageSize;

    public PageCriteria(String order, Integer pageNumber, Integer pageSize) {
        this.order = order;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        initializeDefaultValues();
    }

    private void initializeDefaultValues() {
        if (order == null) {
            order = "id";
        }
        if (pageNumber == null) {
            pageNumber = 0;
        }
        if (pageSize == null) {
            pageSize = 3;
        }
    }
}
