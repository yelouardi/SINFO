package org.sinfo.dao;

import java.util.List;

import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.business.service.impl.TopicBSImpl;
import org.sinfo.entity.Tagg;
import org.sinfo.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** DAO Tag
 * @author yelouardi
 *
 */
@Loggable(calss=TagDao.class, message = "DAO Tag  ", type = level.INFO)
public interface TagDao extends JpaRepository<Tagg, Long> {
	
/** Get Tagg By Code Tagg
 * @param codeTag
 * @return Tagg
 */
@Query("select tg from Tagg tg where tg.titleTag like %?1")
public Tagg	 getTagByCodeTag(String codeTag);

/** Get All Topic By Code Tag
 * @param codeTag
 * @return List<Topic
 */
@Query("select tc from Topic tc where tc.tagg.codeTag =  ?#{[0]}")
public List<Topic>	 getTopicsByCodeTag(Long codeTag);
}
