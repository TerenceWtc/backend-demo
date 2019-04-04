package org.terence.backend.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.service.service.admin.GroupService;
import org.terence.backend.service.service.admin.UserService;
import org.terence.backend.service.vo.admin.UserVo;
import org.terence.backend.service.vo.base.*;

import java.sql.Date;
import java.time.LocalDate;
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
    public TableResponse<UserVo> list(@RequestParam("page") int page, @RequestParam("size") int size) {
        TableData<UserVo> userVoTableData = userService.getList(page - 1, size);
        return new TableResponse<>(userVoTableData);
    }

    @GetMapping("{id}")
    public ObjectResponse<UserVo> getUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        UserVo userVo = BeanFormat.formatUserWithoutPwd(user);
        return new ObjectResponse<>(userVo);
    }

    @PostMapping("")
    public BaseResponse addUser(@RequestBody UserVo userVo) {
        userService.addUser(userVo);
        return new BaseResponse();
    }

    @PutMapping("")
    public BaseResponse updateUser(@RequestBody UserVo userVo) {
        userService.updateUser(userVo);
        return new BaseResponse();
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
        return new BaseResponse();
    }

}
