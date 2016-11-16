package org.sinfo;

import java.util.Arrays;
import java.util.List;

import org.sinfo.entity.Topic;
import org.sinfo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SinfoApplication implements CommandLineRunner  {

	List<String> Tags =Arrays.asList("OSGI","JPA","HIBERNAT","JSF","STRUTS","AOK","SLING","DOZER","FELIX","SPRING DATA");
	@Autowired
	TopicService topicService;
	public static void main(String[] args) {
		SpringApplication.run(SinfoApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		for (String tag : Tags) {
			Topic topic=new Topic();
			topic.setTitleTopic("Topic - "+tag.toLowerCase());
			topic.setDescrTopic(tag.toLowerCase()+" =>> give a digital high-five");
			try {
				topicService.addTopic(topic, tag);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
