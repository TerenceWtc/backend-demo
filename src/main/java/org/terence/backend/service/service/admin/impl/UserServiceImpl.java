package org.terence.backend.service.service.admin.impl;

import com.alibaba.fastjson.JSONArray;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.terence.backend.common.constant.CommonConstant;
import org.terence.backend.common.exception.jwt.TokenException;
import org.terence.backend.common.exception.util.NullValueException;
import org.terence.backend.common.utils.jwt.IUserJwtInfo;
import org.terence.backend.common.utils.jwt.JwtHelper;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.dao.repository.admin.UserRepository;
import org.terence.backend.dao.repository.admin.specification.UserSpec;
import org.terence.backend.service.service.admin.GroupService;
import org.terence.backend.service.service.admin.UserService;
import org.terence.backend.service.vo.admin.UserVo;
import org.terence.backend.service.vo.base.ParamsVo;
import org.terence.backend.service.vo.base.TableData;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author terence
 * @since 2019/2/25 15:22
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserAuthConfig userAuthConfig;

    private final GroupService groupService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserAuthConfig userAuthConfig, GroupService groupService) {
        this.userRepository = userRepository;
        this.userAuthConfig = userAuthConfig;
        this.groupService = groupService;
    }

    @Override
//    @Cacheable(value = "user", key = "#p0")
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public User registerUser(User user) {
        Group group = groupService.getGroupById(-1L);
        user.setGroup(group);
        user.setPassword(new BCryptPasswordEncoder(CommonConstant.PASSWORD_ENCORDER_SALT).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserVo getUserInfo(String accessToken) {
        IUserJwtInfo userJwtInfo;
        try {
            userJwtInfo = JwtHelper.getInfoFromToken(accessToken, userAuthConfig.getPublicKeyPath());
        } catch (Exception e) {
            throw new TokenException("invalid token");
        }
        Optional<User> result = userRepository.findById(Long.valueOf(userJwtInfo.getId()));
        User user;
        if (result.isPresent()) {
            user = result.get();
        } else {
            // TODO
            throw new NullValueException("");
        }
        return new UserVo(user.getId() + "", user.getUsername(), user.getName(), user.getGroup().getId() + "", user.getGroup().getName());
    }

    @Override
    public boolean verifyUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.isPresent();
    }

    @Override
    public TableData<UserVo> getList(int page, int size, List<ParamsVo> paramsVoList) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(UserSpec.searchAll(paramsVoList), pageRequest);
        List<UserVo> userVos = new ArrayList<>();
        userPage.getContent().forEach(user -> userVos.add(new UserVo(user.getId() + "", user.getUsername(), user.getName(), user.getGroup().getId() + "", user.getGroup().getName())));

        return new TableData<>(userPage.getTotalElements(), userVos);
    }

    @Override
    public void addUser(UserVo userVo) {
        User user = BeanFormat.formatUserAndUserVo().getMapperFacade().map(userVo, User.class);
        user.setCreateTime(Date.valueOf(LocalDate.now()));
        user.setCreateBy("admin");
        Group group = groupService.getGroupById(-1L);
        user.setGroup(group);
        user.setPassword(new BCryptPasswordEncoder(CommonConstant.PASSWORD_ENCORDER_SALT).encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
//    @CacheEvict(value = "user", key = "#p0")
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
//        System.out.println("delete user: " + id);
    }

    @Override
//    @CachePut(value = "user", key = "#p0.username")
    public void updateUser(UserVo userVo) {
        // TODO:增量更新
        User userNew = BeanFormat.formatUserAndUserVo().getMapperFacade().map(userVo, User.class);
        Optional<User> userOld = userRepository.findById(userNew.getId());
        if (userOld.isPresent()) {
            User user = userOld.get();
            BeanFormat.formatUser().getMapperFacade().map(userNew, user);
            userRepository.save(user);
        }
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
}
