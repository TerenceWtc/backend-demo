package org.terence.backend.service.service.admin;

import org.terence.backend.dao.entity.admin.User;

/**
 * @author terence
 * @since 2019/2/25 15:21
 */
public interface UserService {

    User getUserByUsername(String username);

    User registerUser(User user);
}
