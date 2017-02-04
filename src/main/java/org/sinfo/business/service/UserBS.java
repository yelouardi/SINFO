package org.sinfo.business.service;

import org.sinfo.entity.User;

public interface UserBS {

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

	/**getUserByName
	 * @param username
	 * @return
	 */
	public User getUserByName(String username);

	
}
