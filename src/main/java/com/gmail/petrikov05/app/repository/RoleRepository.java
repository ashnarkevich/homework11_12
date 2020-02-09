package com.gmail.petrikov05.app.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.petrikov05.app.repository.model.Role;
import com.gmail.petrikov05.app.repository.model.UserRoleDB;

public interface RoleRepository extends GeneralRepository<Role> {

    UserRoleDB findUserRole(Connection connection, String userName) throws SQLException;

}
