package org.terence.backend.service.vo.base;

import lombok.Data;
/**
 * @author terence
 * @since 2019/2/19 11:28
 */
@Data
public class BaseResponse {

    private int status = 200;

    private String message;

    public BaseResponse() {
    }

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
