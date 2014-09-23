package com.dps.common.enums.typehandler;

import com.dps.common.enums.GenericEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handler for Enum ID conversion.
 */
public class GenericEnumIDHandler extends BaseTypeHandler<GenericEnum> {

    private final GenericEnum[] enums;

    public GenericEnumIDHandler(Class<GenericEnum> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        this.enums = type.getEnumConstants();

        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an valid enum type");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, GenericEnum genericEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, genericEnum.getId());

    }

    @Override
    public GenericEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        } else {
            return valueOfEnum(resultSet.getInt(columnName));
        }
    }

    @Override
    public GenericEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        } else {
            return valueOfEnum(resultSet.getInt(columnIndex));
        }
    }

    @Override
    public GenericEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return valueOfEnum(callableStatement.getInt(columnIndex));
        }
    }

    private GenericEnum valueOfEnum(int id) {
        for (GenericEnum status : enums) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }
}
