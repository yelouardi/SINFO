package org.sinfo.dao;

import org.sinfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author yelouardi
 * UserDao
 */
public interface UserDao extends JpaRepository<User, Long>{

	@Query("select  u from User u where u.username =  ?#{[0]} and u.password=?#{[1]}")
	public User getUserByNameAndPassword(String username,String password);
	
	@Query("select u from User u where u.username =  ?1")
	public User getUserByName(String username);
}
