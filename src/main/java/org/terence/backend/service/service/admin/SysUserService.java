package org.terence.backend.service.service.admin;

import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.service.vo.admin.UserVo;
import org.terence.backend.service.vo.base.PageVo;
import org.terence.backend.service.vo.base.ParamsVo;
import org.terence.backend.service.vo.base.TableData;

import java.util.List;

/**
 * @author terence
 * @since 2019/2/25 15:21
 */
public interface SysUserService {

    SysUser getUserByUsername(String username);

    SysUser registerUser(SysUser sysUser);

    UserVo getUserInfo(String accessToken);

    boolean verifyUsername(String username);

    TableData<UserVo> getList(PageVo pageVo);

    void addUser(UserVo userVo);

    void deleteUserById(long id);

    void updateUser(UserVo userVo);

    SysUser getUserById(long id);
}
