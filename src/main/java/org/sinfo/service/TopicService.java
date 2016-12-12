package org.sinfo.service;

import java.util.List;
import org.sinfo.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/** Service Topic
 * @author yelouardi
 *
 */
public interface TopicService {
	
	/** Add Topic
	 * @param topic
	 * @param tag
	 * @return Topic
	 * @throws Exception
	 */
	Topic addTopic(Topic topic, String tag) throws Exception;
	
	/** Get All Topics
	 * @return List<Topic> Topics
	 * 
	 */
	List<Topic> getListTopic();
	
	
	/**Get List Topics By Code Tag
	 * @param codeTag
	 * @return List<Topic>
	 */
	List<Topic> getListTopicByTag(Long codeTag);
	
	/**Get Topic By ID
	 * @param id
	 * @return
	 */
	Topic getTopicById(Long id);
	
	/** Get Topic By Page and Size
	 * @param pageRequest
	 * @return
	 */
	Page<Topic> findAllPageable(PageRequest pageRequest) ;


}
