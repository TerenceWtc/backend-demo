package org.terence.backend.dao.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.terence.backend.dao.entity.admin.Group;

/**
 * @author terence
 * @since 2019/2/20 15:16
 */
public interface GroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {

}
