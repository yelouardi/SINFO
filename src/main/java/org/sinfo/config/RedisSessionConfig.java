package org.sinfo.config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * @author yelouardi
 *RedisSessionConfig
 */
@Configuration	
@EnableRedisHttpSession
@ConditionalOnProperty(name = "use.redis.session.store", havingValue = "true")
public class RedisSessionConfig {
}	