package org.terence.backend.dao.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.terence.backend.dao.entity.admin.SysGroup;

/**
 * @author terence
 * @since 2019/2/20 15:16
 */
public interface SysGroupRepository extends JpaRepository<SysGroup, Long>, JpaSpecificationExecutor<SysGroup> {

}
