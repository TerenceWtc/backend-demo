package org.terence.backend.service.vo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author terence
 * @since 2019/2/26 14:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectResponse<T> extends BaseResponse {

    T data;

    public ObjectResponse() {
    }

    public ObjectResponse(T data) {
        this.data = data;
    }
}
