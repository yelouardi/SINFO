package org.sinfo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "topic")
public class Topic implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6719570281465278876L;
	@Id
	@GeneratedValue	(strategy=GenerationType.AUTO)
	private int idTopic;
	private String codeTopic;
	private String titleTopic;
	@Lob
	private String descrTopic;
	public int getIdTopic() {
		return idTopic;
	}
	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}
	public String getCodeTopic() {
		return codeTopic;
	}
	public void setCodeTopic(String codeTopic) {
		this.codeTopic = codeTopic;
	}
	public String getTitleTopic() {
		return titleTopic;
	}
	public void setTitleTopic(String titleTopic) {
		this.titleTopic = titleTopic;
	}
	public String getDescrTopic() {
		return descrTopic;
	}
	public void setDescrTopic(String descrTopic) {
		this.descrTopic = descrTopic;
	}
	

}
