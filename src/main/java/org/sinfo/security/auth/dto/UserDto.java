package org.sinfo.security.auth.dto;

import java.util.List;

/**
 * @author yelouardi
 * UserDto
 */
public class UserDto {
	private String userName;
	private String password;
	private List<String> authorities;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public UserDto(String username, List<String> authorities) {
		this.userName=username;
		this.authorities=authorities;
	}
	
	

}
