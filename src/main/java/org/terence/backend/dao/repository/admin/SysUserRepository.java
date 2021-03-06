package org.terence.backend.dao.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.terence.backend.dao.entity.admin.SysUser;

import java.util.Optional;

/**
 * @Author: terence
 * @Date: 2019/2/20 15:14
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

    /** find user by username
     * @param username login username
     * @return Optional<SysUser>
     */
    Optional<SysUser> findByUsername(String username);
}
