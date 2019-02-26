package org.terence.backend.dao.repository.admin.specification;

import org.springframework.data.jpa.domain.Specification;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.dao.entity.admin.User;

import javax.persistence.criteria.*;

/**
 * @author terence
 * @since 2019/2/22 16:44
 */
public class GroupSpec {

    public static Specification<Group> findOneByUserId(long userId) {
        return (Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Join<Group, User> join = root.join("user", JoinType.LEFT);
            return builder.equal(join.get("id"), userId);
        };
    }

    public static Specification<Group> findOneByUsername(String username) {
        return (Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Join<Group, User> join = root.join("user", JoinType.LEFT);
            return builder.equal(join.get("username"), username);
        };
    }
}
