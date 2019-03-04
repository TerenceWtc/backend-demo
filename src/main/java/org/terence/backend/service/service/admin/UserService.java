package org.terence.backend.service.service.admin;

import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.service.vo.admin.UserVo;
import org.terence.backend.service.vo.base.ObjectResponse;

/**
 * @author terence
 * @since 2019/2/25 15:21
 */
public interface UserService {

    User getUserByUsername(String username);

    User registerUser(User user);

    ObjectResponse<UserVo> getUserInfo(String accessToken);
}
