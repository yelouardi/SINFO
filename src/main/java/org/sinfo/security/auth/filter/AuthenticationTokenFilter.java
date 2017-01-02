package org.sinfo.security.auth.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.sinfo.security.auth.TokenAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationTokenFilter implements Filter {

private static final Logger logger = Logger.getLogger(AuthenticationTokenFilter.class);

@Override
public void init(FilterConfig fc) throws ServletException {
    logger.info("Init AuthenticationTokenFilter");
}

@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
    SecurityContext context = SecurityContextHolder.getContext();
    if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
    	Map<String,String[]> params = req.getParameterMap();
        for (Entry<String, String[]> param : params.entrySet()) {
        	logger.info("PARAM  AuthenticationTokenFilter ====>> "+param.getKey()+":"+param.getValue());
		}
    } else {
        Map<String,String[]> params = req.getParameterMap();
        for (Entry<String, String[]> param : params.entrySet()) {
        	logger.info("PARAM  AuthenticationTokenFilter ====>> "+param.getKey()+":"+param.getValue());
		}
        if (!params.isEmpty() && params.containsKey("auth_token")) {
            String token = params.get("auth_token")[0];
            if (token != null) {
                Authentication auth = new TokenAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
    }

    fc.doFilter(req, res);
}

@Override
public void destroy() {

}
}
