package org.terence.backend.service.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author terence
 * @since 2019/3/1 16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = -5309272160315307566L;

    private String userId;

    private String username;

    private String password;

    private String name;

    private String email;

    private String gender;

    private String mobile;

    private String description;

    private String groupId;

    private String groupName;

    public SysUserVo(String userId, String username, String name, String groupId, String groupName) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.groupId = groupId;
        this.groupName = groupName;
    }
}
