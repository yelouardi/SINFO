package org.sinfo.controller;

import org.sinfo.security.auth.AuthService;
import org.sinfo.security.auth.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yelouardi
 * UserController
 */
@RestController
public class UserController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/user")
    public UserDto getUser() {
        return authService.getLoggedUser();
    }

}	
