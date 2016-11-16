package org.sinfo.service;

import java.util.List;

import org.sinfo.entity.Tagg;
import org.sinfo.entity.Topic;

public interface TopicService {
	
	Topic addTopic(Topic topic, String tag) throws Exception;
	List<Topic> getListTopic();
	List<Topic> getListByTag(Long codeTag);

}
