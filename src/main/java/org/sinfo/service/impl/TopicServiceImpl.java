package org.sinfo.service.impl;

import java.util.List;

import org.sinfo.business.service.TagBS;
import org.sinfo.business.service.TopicBS;
import org.sinfo.entity.Tagg;
import org.sinfo.entity.Topic;
import org.sinfo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	TagBS tagBs;
	@Autowired
	TopicBS topicBs;

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
			throw new Exception("Data Error NULL ");
		}
		return topic;
	}

	@Override
	public List<Topic> getListTopic() {
		// TODO Auto-generated method stub
		return topicBs.getListTopic();
	}

	@Override
	public List<Topic> getListByTag(Long codeTag) {
		if (codeTag != null) {
			Tagg tag = tagBs.getTag(codeTag);
			return tag.getTopics();
		}
		return null;
	}

}
