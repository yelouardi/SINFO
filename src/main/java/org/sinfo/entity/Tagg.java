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

@Entity
@Table(name="tagg")
public class Tagg implements Serializable {

	public Tagg() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2543470042633449654L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  Long codeTag ;
	
	private String titleTag;
	
	@OneToMany(mappedBy="tagg",fetch = FetchType.LAZY )
	private List<Topic> topics;

	public Tagg(String codeTag) {
		this.titleTag=codeTag.toString().toUpperCase();
	}

	public Long getCodeTag() {
		return codeTag;
	}

	public void setCodeTag(Long codeTag) {
		this.codeTag = codeTag;
	}

	public String getTitleTag() {
		return titleTag;
	}

	public void setTitleTag(String titleTag) {
		this.titleTag = titleTag;
	}

	@JsonIgnore
	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Tagg [codeTag=" + codeTag + ", titleTag=" + titleTag +"]";
	}
	
	

}
