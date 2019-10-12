package org.terence.backend.dao.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.terence.backend.dao.entity.admin.SysDictionary;

/**
 * @author terence
 * @since 2019/7/25 13:31
 */
public interface SysDictionaryRepository extends JpaRepository<SysDictionary, Long>, JpaSpecificationExecutor<SysDictionary> {
}
