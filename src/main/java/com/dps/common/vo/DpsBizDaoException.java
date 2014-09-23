package com.dps.common.vo;

/**
 * A sub-exception for DAO relative exception.
 */
public class DpsBizDaoException extends DpsBizException {

    private static final String ERROR_CODE_DAO = "500";

    private static final String ERROR_MSG_DAO = "Database connection issue:";

    private String sql;

    public DpsBizDaoException(String code, String errMsg, String sql) {
        super(code, errMsg);
        this.sql = sql;
    }

    public DpsBizDaoException(Throwable e) {
        super(ERROR_CODE_DAO, ERROR_MSG_DAO, e);
    }

    public String getSql() {
        return sql;
    }
}
