package com.gmail.petrikov05.app.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.petrikov05.app.repository.model.LoginBD;
import com.gmail.petrikov05.app.repository.model.User;

public interface UserRepository extends GeneralRepository<User> {

    boolean verificationUser(Connection connection, LoginBD loginBD) throws SQLException;

}
