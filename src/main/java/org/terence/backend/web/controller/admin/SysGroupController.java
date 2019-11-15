package org.terence.backend.web.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.service.service.admin.SysGroupService;
import org.terence.backend.service.vo.admin.SysGroupVo;
import org.terence.backend.service.vo.base.ObjectResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author terence
 * @since 2019/4/5 15:08
 */
@RestController
@RequestMapping("admin/sysGroup")
@Api(tags = "SysGroupController", description = "角色")
public class SysGroupController {

    private final SysGroupService sysGroupService;

    @Autowired
    public SysGroupController(SysGroupService sysGroupService) {
        this.sysGroupService = sysGroupService;
    }

    @ApiOperation(value = "获取角色列表")
    @GetMapping("groupIdAndName")
    public ObjectResponse<List<SysGroupVo>> getGroupIdAndName() {
        List<SysGroup> sysGroupList = sysGroupService.getGroupIdAndName();
        List<SysGroupVo> sysGroupVoList = new ArrayList<>();
        sysGroupList.forEach(item -> sysGroupVoList.add(BeanFormat.formatGroupVo().getMapperFacade().map(item, SysGroupVo.class)));
        return new ObjectResponse<>(sysGroupVoList);
    }
}
