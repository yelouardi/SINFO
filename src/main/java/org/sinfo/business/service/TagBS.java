package org.sinfo.business.service;

import java.util.List;
import org.sinfo.entity.Tagg;

/** Service Business Tag
 * @author yelouardi
 *
 */
public interface TagBS {
	
	/** Add Tag
	 * @param Tag
	 * @return Tagg
	 * @throws Exception
	 */
	public Tagg addTag(Tagg tag);
	
	/** Get Tag One By Code
	 * @param codeTag
	 * @return
	 */
	public Tagg getTagOne(Long codeTag) ;

	/** Get All Tagg
	 * @return List<Tagg>
	 */
	public List<Tagg> getListTag();
	
	/** Get Tagg By Code Tag
	 * @param codeTag
	 * @return Tagg
	 */
	Tagg getTagByCodeTag(String codeTag);
}
