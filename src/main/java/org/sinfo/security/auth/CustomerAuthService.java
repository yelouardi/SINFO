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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

	
		
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
	
	public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            LOGGER.debug(String.format("Auto login %s successfully!", username));
        }
    }

	public UserDto getLoggedUser() {
		SecurityUser securityUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.info("SECURITY USER : "+securityUser.toString());
		return new UserDto(securityUser.getUsername(), securityUser.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));
	}

}
