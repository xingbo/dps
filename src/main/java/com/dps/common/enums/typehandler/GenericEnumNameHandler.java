package com.dps.common.enums.typehandler;

import com.dps.common.enums.GenericEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handler for Enum Name conversion.
 */
public class GenericEnumNameHandler extends BaseTypeHandler<GenericEnum> {
    private final GenericEnum[] enums;

    public GenericEnumNameHandler(Class<GenericEnum> type) {
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
        preparedStatement.setString(i, genericEnum.getName());

    }

    @Override
    public GenericEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        } else {
            return valueOfEnum(resultSet.getString(columnName));
        }
    }

    @Override
    public GenericEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        } else {
            return valueOfEnum(resultSet.getString(columnIndex));
        }
    }

    @Override
    public GenericEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return valueOfEnum(callableStatement.getString(columnIndex));
        }
    }

    private GenericEnum valueOfEnum(String name) {
        for (GenericEnum status : enums) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return null;
    }
}
