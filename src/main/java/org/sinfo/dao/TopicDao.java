package org.sinfo.dao;

import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
	
/** DAO Topic
 * @author yelouardi
 *
 */
@Loggable(calss=TopicDao.class, message = "DAO Tag  ", type = level.INFO)
public interface TopicDao extends JpaRepository<Topic, Long> {	
}
