package org.sinfo.business.service.impl;

import java.util.List;
import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.business.service.TopicBS;
import org.sinfo.dao.TagDao;
import org.sinfo.dao.TopicDao;
import org.sinfo.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Service Business Topic
 * @author yelouardi
 *
 */
@Component
public class TopicBSImpl implements TopicBS {
	@Autowired
	TagDao tagDao;
	@Autowired
	TopicDao topicDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	@Loggable(calss=TopicBSImpl.class, message = "Service Business Topic =>> Add Topic ", type = level.INFO)
	public Topic addTopic(Topic topic) {
			return topicDao.saveAndFlush(topic);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TopicBSImpl.class, message = "Service Business Topic =>> Get Topic By Code One ", type = level.INFO)
	public Topic getTopic(Long codeTopic) {
		return topicDao.getOne(codeTopic);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TopicBSImpl.class, message = "Service Business Topic =>> Get ALL Topic ", type = level.INFO)
	public List<Topic> getListTopic() {
		return  topicDao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TopicBSImpl.class, message = "Service Business Topic =>> Get All Topic By Tag Code ", type = level.INFO)
	public List<Topic> getListTopicByTag(Long codeTag) {
		return tagDao.getTopicsByCodeTag(codeTag);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TopicBSImpl.class, message = "Service Business Topic =>> Get All Topic By Page AND Size ", type = level.INFO)
	public Page<Topic> findAllPageable(PageRequest pageRequest) {
		return topicDao.findAll(pageRequest);
	}



}	
