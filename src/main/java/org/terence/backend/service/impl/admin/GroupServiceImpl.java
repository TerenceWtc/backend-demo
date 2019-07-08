package org.terence.backend.service.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.repository.admin.GroupRepository;
import org.terence.backend.dao.repository.admin.specification.GroupSpec;
import org.terence.backend.service.service.admin.GroupService;

import java.util.List;
import java.util.Optional;

/**
 * @author terence
 * @since 2019/4/4 9:21
 */
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public SysGroup getGroupById(long id) {
        Optional<SysGroup> group = groupRepository.findById(id);
        return group.orElse(null);
    }

    @Override
    public SysGroup getGroupByUserId(long userId) {
        Optional<SysGroup> group = groupRepository.findOne(GroupSpec.findOneByUserId(userId));
        return group.orElse(null);
    }

    @Override
    public List<SysGroup> getGroupIdAndName() {
        List<SysGroup> sysGroupList = groupRepository.findAll();
        return sysGroupList;
    }
}
