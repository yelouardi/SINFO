package org.sinfo.business.service;

import java.util.List;

import org.sinfo.entity.Topic;

public interface TopicBS {
	
	public Topic getTopic(Long codeTopic);
	public List<Topic> getListTopic();
	public List<Topic> getListTopicByTag(Long codeTag);
	Topic addTopic(Topic topic);

}
