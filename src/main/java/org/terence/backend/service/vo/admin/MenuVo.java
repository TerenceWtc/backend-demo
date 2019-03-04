package org.terence.backend.service.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author terence
 * @since 2019/3/1 17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {

    private String title;

    private String code;

    private String icon;
}
