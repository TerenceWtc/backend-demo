package org.terence.backend.dao.repository.admin.specification;

import org.springframework.data.jpa.domain.Specification;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.dao.entity.admin.Menu;

import javax.persistence.criteria.*;

/**
 * @author terence
 * @since 2019/2/25 9:39
 */
public class MenuSpec {

    public static Specification<Menu> findAllByGroupId(long groupId) {
        return (Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Join<Menu, Group> join = root.join("group", JoinType.LEFT);
            return builder.equal(join.get("id"), groupId);
        };
    }
}
