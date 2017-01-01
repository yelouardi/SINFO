package org.sinfo.config;
import java.util.Arrays;

import org.sinfo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * The Class RedisSessionConfig.
 *
 * @author yelouardi
 * RedisSessionConfig
 */
@Configuration	
@EnableRedisHttpSession
@ConditionalOnProperty(name = "use.redis.session.store", havingValue = "true")
public class RedisSessionConfig {
	
	/** The host. */
	@Value("${spring.redis.host}")
	String host;
	 
 	/**
 	 * Session repository filter registration.
 	 *
 	 * @param springSessionRepositoryFilter the spring session repository filter
 	 * @return the filter registration bean
 	 */
 	@SuppressWarnings("rawtypes")
	@Bean
	 @Order(value = 0)
	 public FilterRegistrationBean sessionRepositoryFilterRegistration(SessionRepositoryFilter springSessionRepositoryFilter) {
	  FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	  filterRegistrationBean.setFilter(new DelegatingFilterProxy(springSessionRepositoryFilter));
	  filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
	  return filterRegistrationBean;
	 }

	  /**
  	 * Redis template.
  	 *
  	 * @param connectionFactory the connection factory
  	 * @return the redis template
  	 */
  	@Primary
	  @Bean
	  public RedisTemplate<String,User> redisTemplate(RedisConnectionFactory connectionFactory) {
	    RedisTemplate<String, User> template = new RedisTemplate<String, User>();
	    template.setKeySerializer(new StringRedisSerializer());
	    template.setHashKeySerializer(new StringRedisSerializer());
	    template.setConnectionFactory(connectionFactory);
	    return template;
	  }
	  	
	  /**
  	 * Connection factory.
  	 *
  	 * @return the jedis connection factory
  	 */
  	@Bean
	  public JedisConnectionFactory connectionFactory() {
	      JedisConnectionFactory connection = new JedisConnectionFactory();
	      connection.setHostName(host);
	      return connection;
	    }
}	