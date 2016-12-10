package org.sinfo.service.impl;

import java.util.List;
import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.business.service.TagBS;
import org.sinfo.business.service.TopicBS;
import org.sinfo.entity.Tagg;
import org.sinfo.entity.Topic;
import org.sinfo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/** Service Impl Topic	
 * @author yelouardi
 *
 */
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	TagBS tagBs;
	@Autowired
	TopicBS topicBs;	

/**
 * {@inheritDoc}
 */
	@Loggable(calss=TopicServiceImpl.class, message = "Service Topic =>> Add Topic ", type = level.INFO)
	@Override
	public Topic addTopic(Topic topic, String codeTag) throws Exception {
		if (topic != null && codeTag != null) {
			Tagg tag = tagBs.getTagByCodeTag(codeTag);
			if (tag == null) {
				tag = new Tagg(codeTag);
				tag=tagBs.addTag(tag);
			}
			topic.setTag(tag);
			topic=topicBs.addTopic(topic);
		} else {
			throw new NullPointerException("Data Error NULL ");
		}
		return topic;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	@Loggable(calss=TopicServiceImpl.class, message = "Service Topic =>> Get ALL Topic ", type = level.INFO)
	public List<Topic> getListTopic() {
		return topicBs.getListTopic();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TopicServiceImpl.class, message = "Service Topic =>> Get ALL Topic By Code Tag ", type = level.INFO)
	public List<Topic> getListTopicByTag(Long codeTag) {
		// TODO Auto-generated method stub
		return topicBs.getListTopicByTag(codeTag);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TopicServiceImpl.class, message = "Service Topic =>> Get Topic By ID ", type = level.INFO)
	public Topic getTopicById(Long id) {
		return topicBs.getTopic(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TopicServiceImpl.class, message = "Service Topic =>> Get ALL Topic By Page and Size ", type = level.INFO)
	public Page<Topic> findAllPageable(PageRequest pageRequest) {
		return topicBs.findAllPageable(pageRequest);
	}

}
