package org.sinfo.security.auth.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest request = (HttpServletRequest) req;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with");
    //response.setHeader("SESSION ID ",  request.getSession().getId());
    if (request.getMethod()!="OPTIONS") {
      try {
		chain.doFilter(req, res);
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ServletException e) {
		e.printStackTrace();
	}
    } else {
    }
  }

  public void init(FilterConfig filterConfig) {}

  public void destroy() {}

}
