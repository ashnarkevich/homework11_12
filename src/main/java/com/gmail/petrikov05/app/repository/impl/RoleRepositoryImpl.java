package com.gmail.petrikov05.app.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.petrikov05.app.repository.RoleRepository;
import com.gmail.petrikov05.app.repository.model.Role;
import com.gmail.petrikov05.app.repository.model.UserRoleDB;

public class RoleRepositoryImpl extends GeneralRepositoryImpl<Role> implements RoleRepository {

    private static RoleRepository instance;

    private RoleRepositoryImpl() {
    }

    public static RoleRepository getInstance() {
        if (instance == null) {
            instance = new RoleRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Role> findAll(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(
                    "SELECT id, name, description FROM role;"
            );
            List<Role> roleDBList = new ArrayList<>();
            while (rs.next()) {
                Role role = getRole(rs);
                roleDBList.add(role);
            }
            return roleDBList;
        }
    }

    private Role getRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setName(rs.getString("name"));
        role.setDescription(rs.getString("description"));
        return role;
    }

    @Override
    public UserRoleDB findUserRole(Connection connection, String userName) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT u.username AS username, r.name AS role FROM user u LEFT JOIN role r " +
                                "ON u.role_id = r.id " +
                                "WHERE username IN (?);"
                )
        ) {
            statement.setString(1, userName);
            UserRoleDB userRoleDB = new UserRoleDB();
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    userRoleDB.setUserName(rs.getString("username"));
                    userRoleDB.setRole(rs.getString("role"));
                } else {
                    throw new SQLException("user role failed");
                }
            }
            return userRoleDB;
        }
    }

}
