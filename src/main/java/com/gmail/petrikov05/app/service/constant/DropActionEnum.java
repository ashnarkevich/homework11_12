package com.gmail.petrikov05.app.service.constant;

public enum DropActionEnum {
    DROP_TABLE_USER("DROP TABLE IF EXISTS user;"),
    DROP_TABLE_ROLE("DROP TABLE IF EXISTS role;");

    private final String query;

    DropActionEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
