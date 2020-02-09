package com.gmail.petrikov05.app.service;

import java.util.List;

import com.gmail.petrikov05.app.service.model.UserDTO;
import com.gmail.petrikov05.app.service.model.UserLoginDTO;

public interface UserService {

    List<UserDTO> findAll();

    boolean verification(UserLoginDTO userLoginDTO);

}
