package org.sinfo.config;
import java.util.Arrays;

import org.sinfo.entity.User;
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
 * @author yelouardi
 *RedisSessionConfig
 */
@Configuration	
@EnableRedisHttpSession
@ConditionalOnProperty(name = "use.redis.session.store", havingValue = "true")
public class RedisSessionConfig {
	 @Bean
	 @Order(value = 0)
	 public FilterRegistrationBean sessionRepositoryFilterRegistration(SessionRepositoryFilter springSessionRepositoryFilter) {
	  FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	  filterRegistrationBean.setFilter(new DelegatingFilterProxy(springSessionRepositoryFilter));
	  filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
	  return filterRegistrationBean;
	 }

	  @Primary
	  @Bean
	  public RedisTemplate<String,User> redisTemplate(RedisConnectionFactory connectionFactory) {
	    RedisTemplate<String, User> template = new RedisTemplate<String, User>();
	    template.setKeySerializer(new StringRedisSerializer());
	    template.setHashKeySerializer(new StringRedisSerializer());
	    template.setConnectionFactory(connectionFactory);
	    return template;
	  }
	  	
	  @Bean
	  public JedisConnectionFactory connectionFactory() {
	      JedisConnectionFactory connection = new JedisConnectionFactory();
	      connection.setHostName("redis");
	      return connection;
	    }
}	