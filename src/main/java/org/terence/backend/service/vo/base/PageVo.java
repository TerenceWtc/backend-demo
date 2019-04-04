package org.terence.backend.service.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author terence
 * @since 2019/4/2 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {

    private int page;

    private int size;

    private int total;
}
