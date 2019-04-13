package org.terence.backend.service.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author terence
 * @since 2019/4/11 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamsVo {

    private String direction;

    private String property;

}
