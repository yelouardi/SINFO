package org.sinfo;

import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.sinfo.dao.RoleDao;
import org.sinfo.dao.UserDao;
import org.sinfo.entity.Role;
import org.sinfo.entity.Topic;
import org.sinfo.entity.User;
import org.sinfo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication	
public class SinfoRunUp implements CommandLineRunner 	{
	private final static Logger LOGGER=Logger.getLogger(SinfoRunUp.class);
	// implements CommandLineRunner 
	List<String> Tags =Arrays.asList("Osgi","Jpa","Hibernat","Jsf","Struts","Aok","Sling","Dozer","Felix","Spring", "PoolManagerHttp");
	@Autowired
	TopicService topicService;
	@Autowired
	UserDao userdao;	
	@Autowired
	RoleDao roledao;	
	public static void main(String[] args) {
		SpringApplication.run(SinfoRunUp.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		String text="";
		for (String tag : Tags) {
			for(int i=1;i<25;i++){
				if(i % 2 ==0){
					text="HTTP and DI are beyond the scope of this article (though coming soon) but a little explanation of what is going on won't cause us harm.\n" + 
							"\n" + 
							"The class is decorated with an @Injectable decorator which tells Angular that this class is meant to be used as a provider to other components. Jsonp rather than HTTP is going to be used to make API request because of CORS so we inject the service into PetService.\n" + 
							"\n" + 
							"The class has 3 members - a private property which just holds the base Url of the Petfinder API, a method to retrieve list of pets based on type and another method to get a pet by it's Id.";
				}else{
					text="Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus";
				}
			Topic topic=new Topic();
			topic.setTitleTopic("Topic - "+tag.toLowerCase()+ "Course "+i);
			topic.setDescrTopic(text);
			try {
				topicService.addTopic(topic, tag);
			} catch (Exception e) {
				LOGGER.error("Test Sinfo RUN UP ",e);
			}
			}
			
		}
		
		Role role = new Role("ROLE_ADM");
		roledao.save(role);
		userdao.save(new User("yelouardi","yassine@1986",role));
		userdao.save(new User("yelouardi1","yassine@1986",role));

		
	}
}
