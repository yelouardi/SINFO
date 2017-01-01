package org.sinfo.service;

import org.sinfo.entity.User;

public interface UserService {

	/**Get User By login and password
	 * @param id
	 * @return
	 */
	public User getUserByNameAndPassword(String username,String password);
	
	/**Get User By ID
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);

	/** getUserByName
	 * @param username
	 * @return
	 */
	public User getUserByName(String username);

	
}
