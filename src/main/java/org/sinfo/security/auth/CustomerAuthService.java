package org.sinfo.security.auth;
import java.util.ArrayList;
import java.util.List;

import org.sinfo.entity.User;
import org.sinfo.exception.run.UserNotFoundException;
import org.sinfo.security.auth.bo.SecurityUser;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author yelouardi
 * CustomerAuthService
 */
@Service
public class CustomerAuthService implements UserDetailsService {
	UserService userService;
    @Autowired
	public CustomerAuthService(UserService userService) {
		this.userService = userService;
	}

	
		
	@Override
	public SecurityUser loadUserByUsername(String username) {
		try{
		List<GrantedAuthority> authorities = new ArrayList<>();
		User user=userService.getUserByName(username);
		
			authorities.add(()-> user.getRole().getNameRole());
			return new SecurityUser(1L, user.getUsername(), user.getPassword(), authorities);
		}catch(Exception ex){
			throw new UserNotFoundException(String.format(username));
		}
	}


}
