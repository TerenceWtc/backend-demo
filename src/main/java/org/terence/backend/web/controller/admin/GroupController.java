package org.terence.backend.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.dao.entity.admin.Group;
import org.terence.backend.service.service.admin.GroupService;
import org.terence.backend.service.vo.admin.GroupVo;
import org.terence.backend.service.vo.base.ObjectResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author terence
 * @since 2019/4/5 15:08
 */
@RestController
@RequestMapping("admin/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("idAndName")
    public ObjectResponse<List<GroupVo>> getGroupIdAndName() {
        List<Group> groupList = groupService.getGroupIdAndName();
        List<GroupVo> groupVoList = new ArrayList<>();
        groupList.forEach(item -> groupVoList.add(BeanFormat.formatGroupVo().getMapperFacade().map(item, GroupVo.class)));
        return new ObjectResponse<>(groupVoList);
    }
}
