package org.sinfo.business.service;

import java.util.List;
import org.sinfo.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TopicBS {

	/**Get Topic By ID
	 * @param id
	 * @return
	 */
	public Topic getTopic(Long codeTopic);
	
	/** Get All Topics
	 * @return List<Topic> Topics
	 * 
	 */
	public List<Topic> getListTopic();
	
	/**Get List Topics By Code Tag
	 * @param codeTag
	 * @return List<Topic>
	 */
	public List<Topic> getListTopicByTag(Long codeTag);
	
	/** Add Topic
	 * @param topic
	 * @param tag
	 * @return Topic
	 * @throws Exception
	 */
	Topic addTopic(Topic topic);
	
	
	/** Get Topic By Page and Size
	 * @param pageRequest
	 * @return
	 */
	Page<Topic> findAllPageable(PageRequest 	pageRequest);

	

}
