package org.terence.backend.service.service.admin;

import org.terence.backend.dao.entity.admin.SysGroup;

import java.util.List;

/**
 * @author terence
 * @since 2019/4/4 9:21
 */
public interface SysGroupService {

    /** get group by id
     * @param id group id
     * @return SysGroup
     */
    SysGroup getGroupById(long id);

    /** find the group where current user in
     * @param userId user id
     * @return SysGroup
     */
    SysGroup getGroupByUserId(long userId);

    /** get group list
     * @return List<SysGroup>
     */
    List<SysGroup> getGroupIdAndName();
}
