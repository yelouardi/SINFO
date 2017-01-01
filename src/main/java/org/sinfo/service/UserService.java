package org.sinfo.service;

import org.sinfo.entity.User;
import org.sinfo.security.auth.dto.UserDto;

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
	
	public void autologin(String username, String password);
	
	public UserDto getLoggedUser() ;


	
}
