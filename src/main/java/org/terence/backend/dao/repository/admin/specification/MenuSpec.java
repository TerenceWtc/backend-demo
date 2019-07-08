package org.terence.backend.dao.repository.admin.specification;

import org.springframework.data.jpa.domain.Specification;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysMenu;

import javax.persistence.criteria.*;

/**
 * @author terence
 * @since 2019/2/25 9:39
 */
public class MenuSpec {

    public static Specification<SysMenu> findAllByGroupId(long groupId) {
        return (Root<SysMenu> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Join<SysMenu, SysGroup> join = root.join("sysGroup", JoinType.LEFT);
            return builder.equal(join.get("id"), groupId);
        };
    }
}
