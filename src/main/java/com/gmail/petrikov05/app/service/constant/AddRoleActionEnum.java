package com.gmail.petrikov05.app.service.constant;

public enum AddRoleActionEnum {
    INSERT_ROLE_USER("INSERT INTO role(name, description) " +
            "VALUES ('USER', 'description user');"),
    INSERT_ROLE_ADMIN("INSERT INTO role(name, description)" +
            "VALUES ('ADMIN', 'description admin');");

    private final String query;

    AddRoleActionEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}