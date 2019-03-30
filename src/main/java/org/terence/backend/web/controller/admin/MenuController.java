package org.terence.backend.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.terence.backend.service.service.admin.MenuService;
import org.terence.backend.service.vo.admin.MenuVo;
import org.terence.backend.service.vo.base.ObjectResponse;

import java.util.List;

/**
 * @author terence
 * @since 2019/3/1 17:01
 */
@RestController
@RequestMapping("admin/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("getMenuList")
    public ObjectResponse<List<MenuVo>> getMenuList(@RequestParam("accessToken") String accessToken) {
        List<MenuVo> menuVoList = menuService.getMenuList(accessToken);
        return new ObjectResponse<>(menuVoList);
    }
}
