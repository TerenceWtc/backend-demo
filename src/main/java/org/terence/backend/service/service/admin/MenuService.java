package org.terence.backend.service.service.admin;

import org.terence.backend.service.vo.admin.MenuVo;
import org.terence.backend.service.vo.base.ObjectResponse;

import java.util.List;

/**
 * @author terence
 * @since 2019/3/1 17:07
 */
public interface MenuService {

    ObjectResponse<List<MenuVo>> getMenuList(String accessToken);
}
