package org.sinfo.security.auth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.sinfo.entity.User;
import org.sinfo.exception.run.UserNotFoundException;
import org.sinfo.security.auth.bo.SecurityUser;
import org.sinfo.security.auth.dto.UserDto;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author yelouardi
 * CustomerAuthService
 */
@Service("customerAuthService")
public class CustomerAuthService implements UserDetailsService {
	private static final Logger LOGGER=Logger.getLogger(CustomerAuthService.class);
	@Autowired
	UserService userService;

	
		
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
	};

	public UserDto getLoggedUser() {
		SecurityUser securityUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.info("SECURITY USER : "+securityUser.toString());
		return new UserDto(securityUser.getUsername(), securityUser.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));
	};

}
