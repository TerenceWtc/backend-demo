package org.terence.backend.service.impl.admin;

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
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.dao.repository.admin.SysUserRepository;
import org.terence.backend.dao.specification.admin.SysUserSpec;
import org.terence.backend.service.service.admin.SysGroupService;
import org.terence.backend.service.service.admin.SysUserService;
import org.terence.backend.service.vo.admin.SysUserVo;
import org.terence.backend.service.vo.base.PageVo;
import org.terence.backend.service.vo.base.TableData;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author terence
 * @since 2019/2/25 15:22
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserRepository sysUserRepository;

    private final UserAuthConfig userAuthConfig;

    private final SysGroupService sysGroupService;

    @Autowired
    public SysUserServiceImpl(SysUserRepository sysUserRepository, UserAuthConfig userAuthConfig, SysGroupService sysGroupService) {
        this.sysUserRepository = sysUserRepository;
        this.userAuthConfig = userAuthConfig;
        this.sysGroupService = sysGroupService;
    }

    @Override
//    @Cacheable(value = "sysUser", key = "#p0")
    public SysUser getUserByUsername(String username) {
        Optional<SysUser> user = sysUserRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public SysUser registerUser(SysUser sysUser) {
        SysGroup sysGroup = sysGroupService.getGroupById(-1L);
        sysUser.setSysGroup(sysGroup);
        sysUser.setPassword(new BCryptPasswordEncoder(CommonConstant.PASSWORD_ENCORDER_SALT).encode(sysUser.getPassword()));
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUserVo getUserInfo(String accessToken) {
        IUserJwtInfo userJwtInfo;
        try {
            userJwtInfo = JwtHelper.getInfoFromToken(accessToken, userAuthConfig.getPublicKeyPath());
        } catch (Exception e) {
            throw new TokenException("invalid token");
        }
        Optional<SysUser> result = sysUserRepository.findById(Long.valueOf(userJwtInfo.getId()));
        SysUser sysUser;
        if (result.isPresent()) {
            sysUser = result.get();
        } else {
            // TODO
            throw new NullValueException("");
        }
        return new SysUserVo(sysUser.getId() + "", sysUser.getUsername(), sysUser.getName(), sysUser.getSysGroup().getId() + "", sysUser.getSysGroup().getName());
    }

    @Override
    public boolean verifyUsername(String username) {
        Optional<SysUser> userOptional = sysUserRepository.findByUsername(username);
        return userOptional.isPresent();
    }

    @Override
    public TableData<SysUserVo> getList(PageVo pageVo) {
        PageRequest pageRequest = PageRequest.of(pageVo.getPage() - 1, pageVo.getSize());
        Page<SysUser> userPage = sysUserRepository.findAll(SysUserSpec.searchAll(pageVo.getParamsVoList()), pageRequest);
        List<SysUserVo> sysUserVos = new ArrayList<>();
        userPage.getContent().forEach(user -> sysUserVos.add(new SysUserVo(user.getId() + "", user.getUsername(), user.getName(), user.getSysGroup().getId() + "", user.getSysGroup().getName())));

        return new TableData<>(userPage.getTotalElements(), sysUserVos);
    }

    @Override
    public void addUser(SysUserVo sysUserVo) {
        SysUser sysUser = BeanFormat.formatUserAndUserVo().getMapperFacade().map(sysUserVo, SysUser.class);
        SysGroup sysGroup = sysGroupService.getGroupById(-1L);
        sysUser.setSysGroup(sysGroup);
        sysUser.setPassword(new BCryptPasswordEncoder(CommonConstant.PASSWORD_ENCORDER_SALT).encode(sysUser.getPassword()));
        sysUserRepository.save(sysUser);
    }

    @Override
//    @CacheEvict(value = "sysUser", key = "#p0")
    public void deleteUserById(long id) {
        sysUserRepository.deleteById(id);
//        System.out.println("delete sysUser: " + id);
    }

    @Override
//    @CachePut(value = "sysUser", key = "#p0.username")
    public void updateUser(SysUserVo sysUserVo) {
        // TODO:增量更新
        SysUser sysUserNew = BeanFormat.formatUserAndUserVo().getMapperFacade().map(sysUserVo, SysUser.class);
        Optional<SysUser> userOld = sysUserRepository.findById(sysUserNew.getId());
        if (userOld.isPresent()) {
            SysUser sysUser = userOld.get();
            BeanFormat.formatUser().getMapperFacade().map(sysUserNew, sysUser);
            sysUserRepository.save(sysUser);
        }
    }

    @Override
    public SysUser getUserById(long id) {
        Optional<SysUser> user = sysUserRepository.findById(id);
        return user.orElse(null);
    }
}
