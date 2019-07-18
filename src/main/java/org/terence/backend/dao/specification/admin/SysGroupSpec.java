package org.terence.backend.dao.specification.admin;

import org.springframework.data.jpa.domain.Specification;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysUser;

import javax.persistence.criteria.*;

/**
 * @author terence
 * @since 2019/2/22 16:44
 */
public class SysGroupSpec {

    public static Specification<SysGroup> findOneByUserId(long userId) {
        return (Root<SysGroup> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Join<SysGroup, SysUser> join = root.join("sysUser", JoinType.LEFT);
            return builder.equal(join.get("id"), userId);
        };
    }

    public static Specification<SysGroup> findOneByUsername(String username) {
        return (Root<SysGroup> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Join<SysGroup, SysUser> join = root.join("sysUser", JoinType.LEFT);
            return builder.equal(join.get("username"), username);
        };
    }
}
