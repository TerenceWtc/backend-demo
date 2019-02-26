package org.terence.backend.service.vo.base;

import lombok.Data;

import java.util.List;

/**
 * @author terence
 * @since 2019/2/26 14:46
 */
@Data
class TableData<T> {

    long total;
    List<T> rows;
}
