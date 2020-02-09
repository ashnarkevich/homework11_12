package com.gmail.petrikov05.app.service.constant;

public enum AddUserActionEnum {
    INSERT_ROLE_USER("INSERT INTO user(username, password, created_by, role_id)\n" +
            "VALUES ('user', 'user', NOW(), 1);"),
    INSERT_ROLE_ADMIN("INSERT INTO user(username, password, created_by, role_id)\n" +
            "VALUES ('admin', 'admin', NOW(), 2);");

    private final String query;

    AddUserActionEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
