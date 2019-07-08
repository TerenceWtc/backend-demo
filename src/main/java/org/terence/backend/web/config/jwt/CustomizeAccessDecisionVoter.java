package org.terence.backend.web.config.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.terence.backend.common.exception.jwt.GroupNotAssignedException;
import org.terence.backend.common.utils.NullValueUtil;
import org.terence.backend.common.utils.jwt.IUserJwtInfo;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.entity.admin.SysMenu;
import org.terence.backend.dao.repository.admin.GroupRepository;
import org.terence.backend.dao.repository.admin.MenuRepository;
import org.terence.backend.dao.repository.admin.specification.GroupSpec;
import org.terence.backend.dao.repository.admin.specification.MenuSpec;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author terence
 * @since 2019/2/20 9:54
 */
@Component("accessDecisionVoter")
public class CustomizeAccessDecisionVoter implements AccessDecisionVoter {
    private final Logger logger = LoggerFactory.getLogger(CustomizeAccessDecisionVoter.class);

    private final GroupRepository groupRepository;

    private final MenuRepository menuRepository;

    @Autowired
    public CustomizeAccessDecisionVoter(GroupRepository groupRepository, MenuRepository menuRepository) {
        this.groupRepository = groupRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection collection) {
        if (NullValueUtil.judgeNull(authentication)) {
            return ACCESS_DENIED;
        }
        int result = ACCESS_ABSTAIN;
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof IUserJwtInfo)) {
            return ACCESS_DENIED;
        }
        String idStr = ((IUserJwtInfo) principal).getId();
        long userId = Long.parseLong(idStr);
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        final String method = request.getMethod();
        final String requestURI = request.getRequestURI();

        // 查询这个人的角色
        Optional<SysGroup> groupOptional = groupRepository.findOne(GroupSpec.findOneByUserId(userId));
        SysGroup sysGroup;
        if (groupOptional.isPresent()) {
            sysGroup = groupOptional.get();
        } else {
            // 没有分配权限
            throw new GroupNotAssignedException("SysGroup not assigned!");
        }

        // 查询这个角色的权限
        List<SysMenu> sysMenuList = menuRepository.findAll(MenuSpec.findAllByGroupId(sysGroup.getId()));

        // 判断请求的资源在不在权限里

        if (sysMenuList.size() != 0) {
            result = ACCESS_GRANTED;
        }

        return result;
    }
}
