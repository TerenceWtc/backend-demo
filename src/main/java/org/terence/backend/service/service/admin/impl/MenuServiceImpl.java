package org.terence.backend.service.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.terence.backend.common.exception.jwt.TokenException;
import org.terence.backend.common.exception.util.NullValueException;
import org.terence.backend.common.utils.jwt.IUserJwtInfo;
import org.terence.backend.common.utils.jwt.JwtHelper;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.dao.entity.admin.Menu;
import org.terence.backend.dao.repository.admin.GroupRepository;
import org.terence.backend.dao.repository.admin.MenuRepository;
import org.terence.backend.dao.repository.admin.specification.GroupSpec;
import org.terence.backend.dao.repository.admin.specification.MenuSpec;
import org.terence.backend.service.service.admin.MenuService;
import org.terence.backend.service.vo.admin.MenuVo;
import org.terence.backend.service.vo.base.ObjectResponse;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author terence
 * @since 2019/3/1 17:07
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final UserAuthConfig userAuthConfig;

    private final MenuRepository menuRepository;

    private final GroupRepository groupRepository;

    @Autowired
    public MenuServiceImpl(UserAuthConfig userAuthConfig, MenuRepository menuRepository, GroupRepository groupRepository) {
        this.userAuthConfig = userAuthConfig;
        this.menuRepository = menuRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public ObjectResponse<List<MenuVo>> getMenuList(String accessToken) {
        IUserJwtInfo userJwtInfo;
        try {
            userJwtInfo = JwtHelper.getInfoFromToken(accessToken, userAuthConfig.getPublicKeyPath());
        } catch (Exception e) {
            throw new TokenException("invalid token");
        }
        Optional<Group> groupOptional = groupRepository.findOne(GroupSpec.findOneByUserId(Long.valueOf(userJwtInfo.getId())));
        if (!groupOptional.isPresent()) {
            // TODO
            throw new NullValueException("");
        }
        List<Menu> menuList = menuRepository.findAll(MenuSpec.findAllByGroupId(groupOptional.get().getId()));
        List<MenuVo> menuVoList = menuList.stream()
                .map(item -> new MenuVo(item.getTitle(), item.getCode(), item.getIcon()))
                .collect(Collectors.toList());
        return new ObjectResponse<>(menuVoList);
    }
}
