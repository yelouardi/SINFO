package org.sinfo.controller;

import javax.servlet.http.HttpSession;

import org.sinfo.security.auth.dto.UserDto;
import org.sinfo.service.SecurityService;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yelouardi
 * UserController
 */
@RestController
public class UserController {
    
    
    private final UserService userService;
    
    private final SecurityService securityService;
    
    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
		this.userService = userService;
		this.securityService = securityService;
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@RequestParam("email")String email,@RequestParam("password")String password) {
    	securityService.autologin(email, password);
        return "hello";

    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserDto getUser() {
        return userService.getLoggedUser();
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }

}	
