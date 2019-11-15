package org.terence.backend.web.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.terence.backend.service.service.admin.SysMenuService;
import org.terence.backend.service.vo.admin.SysMenuVo;
import org.terence.backend.service.vo.base.ObjectResponse;

import java.util.List;

/**
 * @author terence
 * @since 2019/3/1 17:01
 */
@RestController
@RequestMapping("admin/sysMenu")
@Api(tags = "SysMenuController", description = "菜单")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @Autowired
    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @ApiOperation(value = "获取角色可见菜单列表")
    @GetMapping("getMenuList")
    public ObjectResponse<List<SysMenuVo>> getMenuList(@RequestParam("accessToken") String accessToken) {
        List<SysMenuVo> sysMenuVoList = sysMenuService.getMenuList(accessToken);
        return new ObjectResponse<>(sysMenuVoList);
    }
}
