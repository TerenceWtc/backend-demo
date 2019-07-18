package org.terence.backend.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.common.utils.orika.JsonFormat;
import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.service.service.admin.SysUserService;
import org.terence.backend.service.vo.admin.UserVo;
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
     * @return UserVo
     */
    @GetMapping("getUserInfo")
    public ObjectResponse<UserVo> getUserInfo(@RequestParam("accessToken") String accessToken) {
        UserVo userVo = sysUserService.getUserInfo(accessToken);
        return new ObjectResponse<>(userVo);
    }

    @GetMapping("list")
    public TableResponse<UserVo> list(String pageInfo) {
        PageVo pageVo = JsonFormat.json2PageVo(pageInfo);
        TableData<UserVo> userVoTableData = sysUserService.getList(pageVo);
        return new TableResponse<>(userVoTableData);
    }

    @PostMapping("")
    public BaseResponse addUser(@RequestBody UserVo userVo) {
        sysUserService.addUser(userVo);
        return new BaseResponse();
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteUser(@PathVariable long id) {
        sysUserService.deleteUserById(id);
        return new BaseResponse();
    }

    @PutMapping("")
    public BaseResponse updateUser(@RequestBody UserVo userVo) {
        sysUserService.updateUser(userVo);
        return new BaseResponse();
    }

    @GetMapping("{id}")
    public ObjectResponse<UserVo> getUser(@PathVariable long id) {
        SysUser sysUser = sysUserService.getUserById(id);
        UserVo userVo = BeanFormat.formatUserWithoutPwd(sysUser);
        return new ObjectResponse<>(userVo);
    }

}
