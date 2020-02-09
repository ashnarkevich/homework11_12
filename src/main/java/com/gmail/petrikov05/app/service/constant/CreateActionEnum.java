package com.gmail.petrikov05.app.service.constant;

public enum CreateActionEnum {
    CREATE_TABLE_ROLE("CREATE TABLE IF NOT EXISTS role(" +
            "id          INT(11) PRIMARY KEY AUTO_INCREMENT, " +
            "name        VARCHAR(40) NOT NULL, " +
            "description VARCHAR(40) NOT NULL);"),

    CREATE_TABLE_USER("CREATE TABLE IF NOT EXISTS user(" +
            "id         INT(11) PRIMARY KEY AUTO_INCREMENT," +
            "username   VARCHAR(40) NOT NULL," +
            "password   VARCHAR(40) NOT NULL," +
            "created_by DATETIME    NOT NULL," +
            "role_id    INT(11)     NOT NULL," +
            "FOREIGN KEY (role_id) REFERENCES role (id)" +
            ");");

    private final String query;

    CreateActionEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
