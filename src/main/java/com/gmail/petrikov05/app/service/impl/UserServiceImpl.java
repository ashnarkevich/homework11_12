package com.gmail.petrikov05.app.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.gmail.petrikov05.app.repository.ConnectionRepository;
import com.gmail.petrikov05.app.repository.UserRepository;
import com.gmail.petrikov05.app.repository.impl.ConnectionRepositoryImpl;
import com.gmail.petrikov05.app.repository.impl.UserRepositoryImpl;
import com.gmail.petrikov05.app.repository.model.LoginBD;
import com.gmail.petrikov05.app.repository.model.User;
import com.gmail.petrikov05.app.service.UserService;
import com.gmail.petrikov05.app.service.model.UserDTO;
import com.gmail.petrikov05.app.service.model.UserLoginDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static UserService instance;
    private ConnectionRepository connectionRepository;
    private UserRepository userRepository;

    private UserServiceImpl(
            ConnectionRepository connectionRepository,
            UserRepository userRepository) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl(
                    ConnectionRepositoryImpl.getInstance(),
                    UserRepositoryImpl.getInstance());
        }
        return instance;
    }

    @Override
    public List<UserDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<User> userDBList = userRepository.findAll(connection);
                List<UserDTO> userDTOList = userDBList.stream()
                        .map(this::convertUserDBToUserDTO)
                        .collect(Collectors.toList());
                connection.commit();
                return userDTOList;
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                connection.rollback();
                throw new SQLException("find user failed");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean verification(UserLoginDTO loginDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                LoginBD loginBD = convertLoginDTOToLoginBD(loginDTO);
                boolean isVerification = userRepository.verificationUser(connection, loginBD);
                connection.commit();
                return isVerification;
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                connection.rollback();
                throw new SQLException("find user failed");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    private LoginBD convertLoginDTOToLoginBD(UserLoginDTO loginDTO) {
        LoginBD loginBD = new LoginBD();
        loginBD.setUserName(loginDTO.getUserName());
        loginBD.setPassword(loginDTO.getPassword());
        return loginBD;
    }

    UserDTO convertUserDBToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreatedBy(user.getCreateBy());
        return userDTO;
    }

}
