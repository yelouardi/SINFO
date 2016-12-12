package org.sinfo;

import org.sinfo.config.MapSessionConfig;
import org.sinfo.config.RedisSessionConfig;
import org.sinfo.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yelouardi
 * SinfoApplication
 */
@SpringBootApplication
@EnableSwagger2
@Import({SecurityConfig.class,RedisSessionConfig.class,MapSessionConfig .class})
public class SinfoApplication{
	public static void main(String[] args) {
		SpringApplication.run(SinfoApplication.class, args);
	}
	/**
	 * SwagerV2 API Implementation
	 * @return
	 */
	 @Bean
	    public Docket newsApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("topic")
	                .apiInfo(apiInfo())
	                .select()
	                .paths(new Predicate<String>() {
	                    @Override 
	                    public boolean apply(String str) {
	                        return str.matches("/topics.*");
	                    }               
	            })
	                .build();
	    }
	     
		private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("SINFO Project  REST Sample with Swagger")
	                .description("SINFO Project  REST Sample with Swagger")
	                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
	                .contact("Yassine EL OUARDI : ing.elouardi@gmail.com")
	                .version("2.0")
	                .build();
	    }	

	
}
