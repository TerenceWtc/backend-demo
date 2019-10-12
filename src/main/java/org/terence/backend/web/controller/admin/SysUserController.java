package org.terence.backend.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.common.utils.orika.JsonFormat;
import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.service.service.admin.SysUserService;
import org.terence.backend.service.vo.admin.SysUserVo;
import org.terence.backend.service.vo.base.*;

/**
 * @author terence
 * @since 2019/3/1 16:32
 */
@RestController
@RequestMapping("admin/sysUser")
public class SysUserController {

    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * Return current sysUser insensitive information
     * @param accessToken: jwt access token
     * @return SysUserVo
     */
    @GetMapping("getUserInfo")
    public ObjectResponse<SysUserVo> getUserInfo(@RequestParam("accessToken") String accessToken) {
        SysUserVo sysUserVo = sysUserService.getUserInfo(accessToken);
        return new ObjectResponse<>(sysUserVo);
    }

    @GetMapping("list")
    public TableResponse<SysUserVo> list(String pageInfo) {
        PageVo pageVo = JsonFormat.json2PageVo(pageInfo);
        TableData<SysUserVo> userVoTableData = sysUserService.getList(pageVo);
        return new TableResponse<>(userVoTableData);
    }

    @PostMapping("")
    public BaseResponse addUser(@RequestBody SysUserVo sysUserVo) {
        sysUserService.addUser(sysUserVo);
        return new BaseResponse();
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteUser(@PathVariable long id) {
        sysUserService.deleteUserById(id);
        return new BaseResponse();
    }

    @PutMapping("")
    public BaseResponse updateUser(@RequestBody SysUserVo sysUserVo) {
        sysUserService.updateUser(sysUserVo);
        return new BaseResponse();
    }

    @GetMapping("{id}")
    public ObjectResponse<SysUserVo> getUser(@PathVariable long id) {
        SysUser sysUser = sysUserService.getUserById(id);
        SysUserVo sysUserVo = BeanFormat.formatUserWithoutPwd(sysUser);
        return new ObjectResponse<>(sysUserVo);
    }

}
