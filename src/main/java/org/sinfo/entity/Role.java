package org.sinfo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author yelouardi
 * Role
 */
@Entity
@Table(name = "role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})	
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8870297207988537917L;
	public Role() {
	}
	public Role(String nameRole) {
		super();
		this.nameRole = nameRole;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idRole;

	private String nameRole;
	@OneToMany(mappedBy="role",fetch=FetchType.LAZY)
	private List<User> listUsers;
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	@JsonIgnore
	public List<User> getListUsers() {
		return listUsers;
	}
	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	
}
