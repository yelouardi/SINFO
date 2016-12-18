package org.sinfo.business.service.impl;

import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.business.service.UserBS;
import org.sinfo.dao.UserDao;
import org.sinfo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Service Business Topic
 * @author yelouardi
 *	
 */
@Component
public class UserBSImpl implements UserBS {
	@Autowired
	UserDao userDao;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	@Loggable(calss=UserBSImpl.class, message = "Service Business User =>>getUserByNameAndPassword ", type = level.INFO)
	public User getUserByNameAndPassword(String username,String password) {
			return userDao.getUserByNameAndPassword(username, password);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	@Loggable(calss=UserBSImpl.class, message = "Service Business User =>>getUserByID ", type = level.INFO)
	public User getUserById(Long id) {
			return userDao.findOne(id);	
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	@Loggable(calss=UserBSImpl.class, message = "Service Business User =>>getUserByName ", type = level.INFO)
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}



}	
