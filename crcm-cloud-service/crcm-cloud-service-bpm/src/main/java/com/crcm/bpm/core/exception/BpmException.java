package com.crcm.bpm.core.exception;

import com.crcm.bpm.core.common.BpmError;
import lombok.Builder;

import java.io.Serializable;

/**
 * Bpmn流程异常
 * @Date 2020.9.21
 */
@Builder
public class BpmException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1252245323939553787L;

    // BpmnErrorEnum
    private BpmError bpmError;

    public BpmError getBpmError() {
        return bpmError;
    }

    public void setBpmError(BpmError bpmError) {
        this.bpmError = bpmError;
    }

    public BpmException(BpmError bpmError) {
        super(bpmError.getMessage());
        this.bpmError = bpmError;
    }

    public BpmException(String message, BpmError bpmError) {
        super(message);
        this.bpmError = bpmError;
    }

    public BpmException(String message, Throwable cause, BpmError bpmError) {
        super(message, cause);
        this.bpmError = bpmError;
    }

    public BpmException(Throwable cause, BpmError bpmError) {
        super(cause);
        this.bpmError = bpmError;
    }

    public BpmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BpmError bpmError) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.bpmError = bpmError;
    }
}
