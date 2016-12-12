package org.sinfo.entity;

import java.util.List;

/**
 * @author yelouardi
 * TopicPage
 */
public class TopicPage {
	
	private int number;
	private int numberOfElements;
	private int size;
	
	private List<Topic> topics;
	private int totalPages;
	private long totalElements;
	

	public int getNumber() {
		return this.number;
	}

	public int getSize() {
		return this.size;
	}

	public int getNumberOfElements() {
		return this.numberOfElements;
	}

	public List getTopics() {
		return this.topics;
	}

	
	

	public int getTotalPages() {
		return this.totalPages;
	}

	public long getTotalElements() {
		return this.totalElements;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	
	

}