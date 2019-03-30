package org.terence.backend.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.service.service.admin.UserService;
import org.terence.backend.service.vo.admin.UserVo;
import org.terence.backend.service.vo.base.BaseResponse;
import org.terence.backend.service.vo.base.ObjectResponse;
import org.terence.backend.service.vo.base.TableData;
import org.terence.backend.service.vo.base.TableResponse;

import java.util.List;

/**
 * @author terence
 * @since 2019/3/1 16:32
 */
@RestController
@RequestMapping("admin/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getUserInfo")
    public ObjectResponse<UserVo> getUserInfo(@RequestParam("accessToken") String accessToken) {
        UserVo userVo =  userService.getUserInfo(accessToken);
        return new ObjectResponse<>(userVo);
    }

    @GetMapping("list")
    public TableResponse<UserVo> list() {
        List<UserVo> userVoList = userService.getList();
        return new TableResponse<>(new TableData<>(1L, userVoList));
    }

    @GetMapping("{username}")
    public ObjectResponse<UserVo> getUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        UserVo userVo = BeanFormat.formatUser(user);
        return new ObjectResponse<>(userVo);
    }

    @PutMapping("{username}")
    public BaseResponse updateUser(@PathVariable String username, @RequestParam("name") String name) {
        User user = userService.getUserByUsername(username);
        user.setName(name);
        userService.updateUser(user);
        return new BaseResponse();
    }

    @DeleteMapping("{username}")
    public BaseResponse deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new BaseResponse();
    }

}
