package org.sinfo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	List<String> Tags =Arrays.asList("Osgi","Jpa","Hibernat","Jsf","Struts","Aok","Sling","Dozer","Felix","Spring", "PoolManagerHttp");

	@Autowired
	private UserService userService;
	@Test
	public void contextLoads() {
		try {

			String text="";
			for (String tag : Tags) {
				for(int i=1;i<5;i++){
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


		} catch (Exception e) {
			LOGGER.error("Topic Service ERROR :",e);
		}	
	}
	
	/**@Test
	@DatabaseSetup("data-user.xml")
	public void testFind() throws Exception {
		User user = userService.getUserByName("user1");
		assertEquals(1, user.getIdUser().longValue());	}**/

}
