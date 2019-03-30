package org.terence.backend.service.vo.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author terence
 * @since 2019/2/19 11:28
 */
@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 198130666795594263L;

    private int status = 200;

    private String message;

    public BaseResponse() {
    }

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
