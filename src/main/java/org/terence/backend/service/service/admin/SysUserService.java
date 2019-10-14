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

    /** find user by username
     * @param username login username
     * @return SysUser
     */
    SysUser getUserByUsername(String username);

    /** register a new account
     * @param sysUser sysUser information
     * @return SysUser
     */
    SysUser registerUser(SysUser sysUser);

    /** get user information by access token
     * @param accessToken access token
     * @return SysUserVo
     */
    SysUserVo getUserInfo(String accessToken);

    /** verify if username exist
     * @param username login username
     * @return boolean
     */
    boolean verifyUsername(String username);

    /** get user list by page
     * @param pageVo page parameters
     * @return TableData<SysUserVo>
     */
    TableData<SysUserVo> getList(PageVo pageVo);

    /** add user
     * @param sysUserVo sysUser information
     */
    void addUser(SysUserVo sysUserVo);

    /** delete user
     * @param id user id
     */
    void deleteUserById(long id);

    /** update user
     * @param sysUserVo sysUser information
     */
    void updateUser(SysUserVo sysUserVo);

    /** find user by id
     * @param id user id
     * @return SysUser
     */
    SysUser getUserById(long id);
}
