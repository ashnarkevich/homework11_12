package com.gmail.petrikov05.app.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gmail.petrikov05.app.repository.UserRepository;
import com.gmail.petrikov05.app.repository.model.LoginBD;
import com.gmail.petrikov05.app.repository.model.User;

public class UserRepositoryImpl extends GeneralRepositoryImpl<User> implements UserRepository {

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<User> findAll(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(
                    "SELECT id, username, password, created_by FROM user;"
            );
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = getUser(rs);
                userList.add(user);
            }
            return userList;
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        Date dateStr = rs.getDate("created_by");
        user.setCreateBy(dateStr);
        return user;
    }

    @Override
    public boolean verificationUser(Connection connection, LoginBD loginBD) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT username FROM user WHERE username IN (?) AND password IN (?);"
                )
        ) {
            statement.setString(1, loginBD.getUserName());
            statement.setString(2, loginBD.getPassword());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }
    }

}
