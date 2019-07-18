package org.terence.backend.service.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.terence.backend.dao.entity.admin.SysGroup;
import org.terence.backend.dao.repository.admin.SysGroupRepository;
import org.terence.backend.dao.specification.admin.SysGroupSpec;
import org.terence.backend.service.service.admin.SysGroupService;

import java.util.List;
import java.util.Optional;

/**
 * @author terence
 * @since 2019/4/4 9:21
 */
@Service
public class SysGroupServiceImpl implements SysGroupService {

    private final SysGroupRepository sysGroupRepository;

    @Autowired
    public SysGroupServiceImpl(SysGroupRepository sysGroupRepository) {
        this.sysGroupRepository = sysGroupRepository;
    }

    @Override
    public SysGroup getGroupById(long id) {
        Optional<SysGroup> group = sysGroupRepository.findById(id);
        return group.orElse(null);
    }

    @Override
    public SysGroup getGroupByUserId(long userId) {
        Optional<SysGroup> group = sysGroupRepository.findOne(SysGroupSpec.findOneByUserId(userId));
        return group.orElse(null);
    }

    @Override
    public List<SysGroup> getGroupIdAndName() {
        List<SysGroup> sysGroupList = sysGroupRepository.findAll();
        return sysGroupList;
    }
}
