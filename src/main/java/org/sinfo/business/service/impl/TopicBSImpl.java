package org.sinfo.business.service.impl;

import java.util.List;

import org.sinfo.business.service.TopicBS;
import org.sinfo.dao.TagDao;
import org.sinfo.dao.TopicDao;
import org.sinfo.entity.Tagg;
import org.sinfo.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TopicBSImpl implements TopicBS {
	@Autowired
	TagDao tagDao;
	@Autowired
	TopicDao topicDao;
	
	@Transactional
	@Override
	public Topic addTopic(Topic topic) {
			return topicDao.saveAndFlush(topic);		
	}

	@Override
	public Topic getTopic(Long codeTopic) {
		return topicDao.getOne(codeTopic);
	}

	@Override
	public List<Topic> getListTopic() {
		return  topicDao.findAll();
	}

	@Override
	public List<Topic> getListTopicByTag(Long codeTag) {
		Tagg tag=tagDao.findOne(codeTag);
		return tag.getTopics();
	}



}
