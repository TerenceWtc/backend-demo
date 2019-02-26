package org.terence.backend.service.vo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * @author terence
 * @since 2019/2/26 14:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableResponse<T> extends BaseResponse {

    @NonNull
    TableData<T> data;
}
