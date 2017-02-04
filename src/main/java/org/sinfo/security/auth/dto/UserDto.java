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
	private String token;
	private Long userNo;
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
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
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public UserDto(String token, Long userNo, String username, List<String> authorities) {
		this.token=token;
		this.userNo=userNo;
		this.userName=username;
		this.authorities=authorities;
	}
	
	

}
