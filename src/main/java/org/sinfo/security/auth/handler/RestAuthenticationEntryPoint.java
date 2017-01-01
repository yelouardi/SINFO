package org.sinfo.security.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sinfo.security.auth.dto.FaultDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author yelouardi
 * RestAuthenticationEntryPoint
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		FaultDto faultDto = new FaultDto("SPRING-SECURITY-1", authException.getMessage());
		writer.println(mapper.writeValueAsString(faultDto));
	}
}	
