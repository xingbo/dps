package com.dps.common.vo;

public class DpsBizException extends Exception {

    private static final long serialVersionUID = 1525947262783912223L;

    private String code;
    private String errMsg;

    public DpsBizException(String code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public DpsBizException(String code, String errMsg, Throwable cause) {
        super(cause);
        this.code = code;
        this.errMsg = errMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
