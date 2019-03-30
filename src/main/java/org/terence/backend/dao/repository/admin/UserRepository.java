package org.terence.backend.dao.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.terence.backend.dao.entity.admin.User;

import java.util.Optional;

/**
 * @Author: terence
 * @Date: 2019/2/20 15:14
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
}
