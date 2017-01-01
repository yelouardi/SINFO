package org.sinfo.service.impl;

import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.business.service.UserBS;
import org.sinfo.entity.User;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Service Business Topic
 * @author yelouardi
 *	
 */
@Component
public class UserServiceImpl implements UserService	 {
	@Autowired
	UserBS userBS;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=UserServiceImpl.class, message = "Service  User =>>getUserByNameAndPassword ", type = level.INFO)
	public User getUserByNameAndPassword(String username,String password) {
			return userBS.getUserByNameAndPassword(username, password);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=UserServiceImpl.class, message = "Service  User =>>getUserByID ", type = level.INFO)
	public User getUserById(Long id) {
			return userBS.getUserById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Loggable(calss=UserServiceImpl.class, message = "Service  User =>>getUserByName ", type = level.INFO)
	public User getUserByName(String username) {
		return userBS.getUserByName(username);
	}



}	
