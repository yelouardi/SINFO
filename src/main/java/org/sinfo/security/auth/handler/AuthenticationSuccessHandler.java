package org.sinfo.security.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.sinfo.security.auth.bo.SecurityUser;
import org.sinfo.security.auth.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author yelouardi
 * AuthenticationSuccessHandler
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
private static final Logger LOGGER=Logger.getLogger(AuthenticationSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		SecurityUser userSecurity =  (SecurityUser) authentication.getPrincipal();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();

		UserDto userDto = new UserDto(request.getHeader("x-auth-token"),userSecurity.getUserNo(), userSecurity.getUsername(), userSecurity.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));
		LOGGER.info("------------>>  HEADER :"+request.getHeaderNames());
		mapper.writeValue(writer, userDto);
		writer.flush();
	}
}