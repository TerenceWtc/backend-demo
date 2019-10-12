package org.terence.backend.service.service.admin;

import org.terence.backend.service.vo.admin.SysMenuVo;

import java.util.List;

/**
 * @author terence
 * @since 2019/3/1 17:07
 */
public interface SysMenuService {

    List<SysMenuVo> getMenuList(String accessToken);
}
