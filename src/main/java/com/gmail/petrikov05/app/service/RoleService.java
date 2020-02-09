package com.gmail.petrikov05.app.service;

import java.util.List;

import com.gmail.petrikov05.app.service.model.RoleDTO;
import com.gmail.petrikov05.app.service.model.UserRoleDTO;

public interface RoleService {

    List<RoleDTO> findAll();

    UserRoleDTO getUserRole(String userName);

}

