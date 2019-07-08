package org.terence.backend.service.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.terence.backend.common.exception.jwt.TokenException;
import org.terence.backend.common.utils.jwt.IUserJwtInfo;
import org.terence.backend.common.utils.jwt.JwtHelper;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysMenu;
import org.terence.backend.dao.entity.base.TreeNode;
import org.terence.backend.dao.repository.admin.MenuRepository;
import org.terence.backend.dao.repository.admin.specification.MenuSpec;
import org.terence.backend.service.service.admin.GroupService;
import org.terence.backend.service.service.admin.MenuService;
import org.terence.backend.service.vo.admin.MenuVo;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author terence
 * @since 2019/3/1 17:07
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final UserAuthConfig userAuthConfig;

    private final MenuRepository menuRepository;

    private final GroupService groupService;

    @Autowired
    public MenuServiceImpl(UserAuthConfig userAuthConfig, GroupService groupService, MenuRepository menuRepository) {
        this.userAuthConfig = userAuthConfig;
        this.groupService = groupService;
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuVo> getMenuList(String accessToken) {
        IUserJwtInfo userJwtInfo;
        try {
            userJwtInfo = JwtHelper.getInfoFromToken(accessToken, userAuthConfig.getPublicKeyPath());
        } catch (Exception e) {
            throw new TokenException("invalid token");
        }
        SysGroup sysGroup = groupService.getGroupByUserId(Long.parseLong(userJwtInfo.getId()));
        List<SysMenu> sysMenuList = menuRepository.findAll(MenuSpec.findAllByGroupId(sysGroup.getId()));
        List<MenuVo> menuVoList = new ArrayList<>();
        sysMenuList.forEach(item -> {
                    MenuVo menuVo = BeanFormat.formatMenuVo().getMapperFacade().map(item, MenuVo.class);
                    menuVoList.add(menuVo);
                });
        return build(menuVoList, -1);
    }

    private <T extends TreeNode> List<T> build(List<T> treeNodes, int root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (root == treeNode.getParentId()) {
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }
}
