package org.terence.backend.dao.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.terence.backend.dao.entity.admin.Menu;

/**
 * @author terence
 * @since 2019/2/25 9:37
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {
}
