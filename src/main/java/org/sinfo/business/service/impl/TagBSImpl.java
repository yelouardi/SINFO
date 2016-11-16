package org.sinfo.business.service.impl;

import java.util.List;

import org.sinfo.business.service.TagBS;
import org.sinfo.dao.TagDao;
import org.sinfo.dao.TopicDao;
import org.sinfo.entity.Tagg;
import org.sinfo.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagBSImpl implements TagBS {

	@Autowired
	TagDao tagDao;

	@Override
	public Tagg addTag(Tagg tag) {
		return tagDao.saveAndFlush(tag);		
	}

	@Override
	public Tagg getTag(Long codeTag) {
		return tagDao.getOne(codeTag);
	}

	@Override
	public List<Tagg> getListTag() {
		return tagDao.findAll();
	}

	@Override
	public Tagg getTagByCodeTag(String codeTag) {
		return tagDao.getTagByCodeTag(codeTag);
	}

}
