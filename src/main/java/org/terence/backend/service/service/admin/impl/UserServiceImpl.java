package org.terence.backend.service.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.terence.backend.common.constant.CommonConstant;
import org.terence.backend.common.exception.jwt.TokenException;
import org.terence.backend.common.exception.util.NullValueException;
import org.terence.backend.common.utils.jwt.IUserJwtInfo;
import org.terence.backend.common.utils.jwt.JwtHelper;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.dao.repository.admin.GroupRepository;
import org.terence.backend.dao.repository.admin.UserRepository;
import org.terence.backend.dao.repository.admin.specification.GroupSpec;
import org.terence.backend.service.service.admin.UserService;
import org.terence.backend.service.vo.admin.UserVo;
import org.terence.backend.service.vo.base.ObjectResponse;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import java.util.Optional;

/**
 * @author terence
 * @since 2019/2/25 15:22
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    private final UserAuthConfig userAuthConfig;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository, UserAuthConfig userAuthConfig) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.userAuthConfig = userAuthConfig;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public User registerUser(User user) {
        Optional<Group> group = groupRepository.findById(-1L);
        if (group.isPresent()) {
            user.setGroup(group.get());
        } else {
            // TODO
            throw new NullValueException("");
        }
        user.setPassword(new BCryptPasswordEncoder(CommonConstant.PASSWORD_ENCORDER_SALT).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public ObjectResponse<UserVo> getUserInfo(String accessToken) {
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
        UserVo userVo = new UserVo(user.getId() + "", user.getUsername(), user.getName(), user.getGroup().getId() + "", user.getGroup().getName());
        return new ObjectResponse<>(userVo);
    }

    @Override
    public boolean verifyUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.isPresent();
    }
}
