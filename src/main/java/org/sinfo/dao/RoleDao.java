package org.sinfo.dao;

import org.sinfo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/** RoleDao
 * @author yelouardi
 *
 */
public interface RoleDao extends JpaRepository<Role, Long> {

}
