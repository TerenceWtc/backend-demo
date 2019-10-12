package org.terence.backend.service.service.admin;

import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.service.vo.admin.SysUserVo;
import org.terence.backend.service.vo.base.PageVo;
import org.terence.backend.service.vo.base.TableData;

/**
 * @author terence
 * @since 2019/2/25 15:21
 */
public interface SysUserService {

    SysUser getUserByUsername(String username);

    SysUser registerUser(SysUser sysUser);

    SysUserVo getUserInfo(String accessToken);

    boolean verifyUsername(String username);

    TableData<SysUserVo> getList(PageVo pageVo);

    void addUser(SysUserVo sysUserVo);

    void deleteUserById(long id);

    void updateUser(SysUserVo sysUserVo);

    SysUser getUserById(long id);
}
