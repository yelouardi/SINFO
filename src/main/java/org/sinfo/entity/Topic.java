package org.sinfo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topic")
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6719570281465278876L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTopic;
	private String codeTopic;
	private String titleTopic;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codeTag")
	private Tagg tag;
	@Lob
	private String descrTopic;

	public Long getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(Long idTopic) {
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

	public Tagg getTag() {
		return tag;
	}

	public void setTag(Tagg tag) {
		this.tag = tag;
	}

}
