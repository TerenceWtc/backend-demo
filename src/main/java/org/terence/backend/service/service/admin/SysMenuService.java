package org.terence.backend.service.service.admin;

import org.terence.backend.service.vo.admin.SysMenuVo;

import java.util.List;

/**
 * @author terence
 * @since 2019/3/1 17:07
 */
public interface SysMenuService {

    /** find the menu list that current user can see
     * @param accessToken access token
     * @return List<SysMenuVo>
     */
    List<SysMenuVo> getMenuList(String accessToken);
}
