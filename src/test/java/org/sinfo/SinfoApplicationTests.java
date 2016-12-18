package org.sinfo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sinfo.entity.Role;
import org.sinfo.entity.Topic;
import org.sinfo.entity.User;
import org.sinfo.service.TopicService;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })	
@SpringBootTest
public class SinfoApplicationTests {
	private static final Logger LOGGER=Logger.getLogger(SinfoApplicationTests.class);
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private UserService userService;
	@Test
	public void contextLoads() {
		try {
			Topic topic=new Topic();
			User user=new User();
			Role role=new Role();
			role.setNameRole("ROLE_TEST");
			user.setUsername("TEST");
			user.setPassword("TEST");
			user.setRole(role);
			//when(topic.getUser()).thenReturn(user);
			//when(user.getRole()).thenReturn(role);
			assertEquals(user.getRole().getNameRole(),role.getNameRole());
			topic.setCodeTopic("TOPIC-CODE-TEST");
			topic.setDescrTopic("TEST UNIT MOCKITO");
			topic.setTitleTopic("TITLE TEST UNIT MOCKITO");
			Topic addTopic = topicService.addTopic(topic, "TEST");
			assertEquals(addTopic.getCodeTopic(),"TOPIC-CODE-TEST");
			//Topic topicById = topicService.getTopicById(addTopic.getIdTopic());
			//assertEquals(topicById.getCodeTopic(),"TOPIC-CODE-TEST");
		} catch (Exception e) {
			LOGGER.error("Topic Service ERROR :",e);
		}	
	}
	
	@Test
	@DatabaseSetup("data-user.xml")
	public void testFind() throws Exception {
		User user = userService.getUserByName("user1");
		assertEquals(1, user.getIdUser().longValue());	}

}
