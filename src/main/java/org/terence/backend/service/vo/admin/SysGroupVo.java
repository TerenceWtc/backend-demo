package org.terence.backend.service.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author terence
 * @since 2019/4/5 15:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysGroupVo implements Serializable {

    private static final long serialVersionUID = -7162278906089002129L;

    private String groupId;

    private String code;

    private String name;
}
