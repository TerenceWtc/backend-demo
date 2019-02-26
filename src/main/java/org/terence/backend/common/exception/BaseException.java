package org.terence.backend.common.exception;

/**
 * @author terence
 * @since 2019/2/19 11:25
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -501759530637533302L;
    private int status = 500;

    int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseException() {
    }

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }
}
