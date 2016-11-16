package org.sinfo.dao;

import org.sinfo.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
	
public interface TopicDao extends JpaRepository<Topic, Long> {

}
