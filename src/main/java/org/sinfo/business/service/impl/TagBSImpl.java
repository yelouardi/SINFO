package org.sinfo.business.service.impl;

import java.util.List;
import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.business.service.TagBS;
import org.sinfo.dao.TagDao;
import org.sinfo.entity.Tagg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**Service Business Tag
 * @author yelouardi
 *
 */
@Component
public class TagBSImpl implements TagBS {

	@Autowired
	TagDao tagDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TagBSImpl.class, message = "Service Business Tag =>> Add Tag ", type = level.INFO)
	public Tagg addTag(Tagg tag) {
		return tagDao.saveAndFlush(tag);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TagBSImpl.class, message = "Service Business Tag =>> Get One Tag By Code ", type = level.INFO)
	public Tagg getTagOne(Long codeTag) {
		return tagDao.getOne(codeTag);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TagBSImpl.class, message = "Service Business Tag =>> Get ALL Tag ", type = level.INFO)
	public List<Tagg> getListTag() {
		return tagDao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=TagBSImpl.class, message = "Service Business Tag =>> Get Tag By Code ", type = level.INFO)
	public Tagg getTagByCodeTag(String codeTag) {
		return tagDao.getTagByCodeTag(codeTag);
	}

}
