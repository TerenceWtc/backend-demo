package org.terence.backend.service.service.admin;

import org.terence.backend.dao.entity.admin.SysGroup;

import java.util.List;

/**
 * @author terence
 * @since 2019/4/4 9:21
 */
public interface GroupService {

    SysGroup getGroupById(long l);

    SysGroup getGroupByUserId(long parseLong);

    List<SysGroup> getGroupIdAndName();
}
