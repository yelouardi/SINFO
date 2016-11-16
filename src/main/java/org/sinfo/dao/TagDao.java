package org.sinfo.dao;

import org.sinfo.entity.Tagg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagDao extends JpaRepository<Tagg, Long> {
@Query("select tg from Tagg tg where tg.titleTag like %?1")
public Tagg	 getTagByCodeTag(String codeTag);
}
