package com.gmail.petrikov05.app.repository.model;

import java.util.Date;

public class User {

    private Integer id;
    private String userName;
    private String password;
    private Date createBy;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public Date getCreateBy() {
        return createBy;
    }

}
