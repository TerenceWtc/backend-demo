package org.terence.backend.service.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author terence
 * @since 2019/2/26 14:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableData<T> implements Serializable {

    private static final long serialVersionUID = 542448062795432169L;

    long total;
    List<T> rows;
}
