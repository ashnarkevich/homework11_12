package com.gmail.petrikov05.app.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.gmail.petrikov05.app.repository.ConnectionRepository;
import com.gmail.petrikov05.app.repository.RoleRepository;
import com.gmail.petrikov05.app.repository.impl.ConnectionRepositoryImpl;
import com.gmail.petrikov05.app.repository.impl.RoleRepositoryImpl;
import com.gmail.petrikov05.app.repository.model.Role;
import com.gmail.petrikov05.app.repository.model.UserRoleDB;
import com.gmail.petrikov05.app.service.RoleService;
import com.gmail.petrikov05.app.service.model.RoleDTO;
import com.gmail.petrikov05.app.service.model.UserRoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static RoleService instance;
    private ConnectionRepository connectionRepository;
    private RoleRepository roleRepository;

    public RoleServiceImpl(ConnectionRepository connectionRepository, RoleRepository roleRepository) {
        this.connectionRepository = connectionRepository;
        this.roleRepository = roleRepository;
    }

    public static RoleService getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl(ConnectionRepositoryImpl.getInstance(), RoleRepositoryImpl.getInstance());
        }
        return instance;
    }

    @Override
    public List<RoleDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Role> roleDBList = roleRepository.findAll(connection);
                List<RoleDTO> roleDTOList = roleDBList.stream()
                        .map(this::convertRoleDBToRoleDTO)
                        .collect(Collectors.toList());
                connection.commit();
                return roleDTOList;
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                connection.rollback();
                throw new SQLException("find role failed");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public UserRoleDTO getUserRole(String userName) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                UserRoleDB userRoleDB = roleRepository.findUserRole(connection, userName);
                UserRoleDTO userRoleDTO = convertUserRoleDBToUserRoleDTO(userRoleDB);
                connection.commit();
                return userRoleDTO;
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                connection.rollback();
                throw new SQLException("find role failed");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private UserRoleDTO convertUserRoleDBToUserRoleDTO(UserRoleDB userRoleDB) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserName(userRoleDB.getUserName());
        userRoleDTO.setRole(userRoleDB.getRole());
        return userRoleDTO;
    }

    RoleDTO convertRoleDBToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        return roleDTO;
    }

}
