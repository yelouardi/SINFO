package org.sinfo.service.impl;

import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.business.service.UserBS;
import org.sinfo.entity.User;
import org.sinfo.security.auth.CustomerAuthService;
import org.sinfo.security.auth.bo.SecurityUser;
import org.sinfo.security.auth.dto.UserDto;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/** Service Business Topic
 * @author yelouardi
 *	
 */
@Component
public class UserServiceImpl implements UserService	 {
	
	private static final Logger LOGGER=Logger.getLogger(UserService.class);

	@Autowired
	UserBS userBS;
	
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerAuthService userDetailsService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=UserServiceImpl.class, message = "Service  User =>>getUserByNameAndPassword ", type = level.INFO)
	public User getUserByNameAndPassword(String username,String password) {
			return userBS.getUserByNameAndPassword(username, password);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=UserServiceImpl.class, message = "Service  User =>>getUserByID ", type = level.INFO)
	public User getUserById(Long id) {
			return userBS.getUserById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=UserServiceImpl.class, message = "Service  User =>>getUserByName ", type = level.INFO)
	public User getUserByName(String username) {
		return userBS.getUserByName(username);
	}

	@Override
	public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            LOGGER.debug(String.format("Auto login %s successfully!", username));
        }
    }

	@Override
	public UserDto getLoggedUser() {
		SecurityUser securityUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.info("SECURITY USER : "+securityUser.toString());
		return new UserDto(securityUser.getUsername(), securityUser.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));
	}


}	
