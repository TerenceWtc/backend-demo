package org.terence.backend.service.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author terence
 * @since 2019/3/1 16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private String userId;

    private String username;

    private String name;

    private String groupId;

    private String groupName;
}
