package org.sinfo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

/**
 * @author yelouardi
 * MapSessionConfig
 */
@Configuration
@EnableSpringHttpSession
@ConditionalOnProperty(name = "use.redis.session.store", havingValue = "false", matchIfMissing = true)
public class MapSessionConfig {
  @Bean
  public SessionRepository<?> sessionRepository() {
    return new MapSessionRepository();
  }
}	
