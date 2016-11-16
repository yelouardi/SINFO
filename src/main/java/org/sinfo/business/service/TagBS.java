package org.sinfo.business.service;

import java.util.List;

import org.sinfo.entity.Tagg;

public interface TagBS {
	public Tagg addTag(Tagg tag);
	public Tagg getTag(Long codeTag);

	public List<Tagg> getListTag();
	Tagg getTagByCodeTag(String codeTag);
}
