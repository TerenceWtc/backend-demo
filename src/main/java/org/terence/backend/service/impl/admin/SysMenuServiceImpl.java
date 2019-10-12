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
import org.terence.backend.dao.repository.admin.SysMenuRepository;
import org.terence.backend.dao.specification.admin.SysMenuSpec;
import org.terence.backend.service.service.admin.SysGroupService;
import org.terence.backend.service.service.admin.SysMenuService;
import org.terence.backend.service.vo.admin.SysMenuVo;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author terence
 * @since 2019/3/1 17:07
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    private final UserAuthConfig userAuthConfig;

    private final SysMenuRepository sysMenuRepository;

    private final SysGroupService sysGroupService;

    @Autowired
    public SysMenuServiceImpl(UserAuthConfig userAuthConfig, SysGroupService sysGroupService, SysMenuRepository sysMenuRepository) {
        this.userAuthConfig = userAuthConfig;
        this.sysGroupService = sysGroupService;
        this.sysMenuRepository = sysMenuRepository;
    }

    @Override
    public List<SysMenuVo> getMenuList(String accessToken) {
        IUserJwtInfo userJwtInfo;
        try {
            userJwtInfo = JwtHelper.getInfoFromToken(accessToken, userAuthConfig.getPublicKeyPath());
        } catch (Exception e) {
            throw new TokenException("invalid token");
        }
        SysGroup sysGroup = sysGroupService.getGroupByUserId(Long.parseLong(userJwtInfo.getId()));
        List<SysMenu> sysMenuList = sysMenuRepository.findAll(SysMenuSpec.findAllByGroupId(sysGroup.getId()));
        List<SysMenuVo> sysMenuVoList = new ArrayList<>();
        sysMenuList.forEach(item -> {
                    SysMenuVo sysMenuVo = BeanFormat.formatMenuVo().getMapperFacade().map(item, SysMenuVo.class);
                    sysMenuVoList.add(sysMenuVo);
                });
        return build(sysMenuVoList, -1);
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
