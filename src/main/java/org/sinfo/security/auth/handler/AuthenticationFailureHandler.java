package org.sinfo.security.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sinfo.security.auth.dto.FaultDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author yelouardi
 * AuthenticationFailureHandler
 */
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		FaultDto faultDto = new FaultDto("SPRING-SECURITY-1", exception.getMessage());
		writer.println(mapper.writeValueAsString(faultDto));
	}
}	
