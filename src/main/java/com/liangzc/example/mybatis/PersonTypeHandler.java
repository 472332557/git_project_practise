package com.liangzc.example.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class PersonTypeHandler extends BaseTypeHandler<List> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {
        String toString = parameter.stream().collect(Collectors.joining()).toString();
        ps.setString(i, toString);
    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String rsString = rs.getString(columnName);
        return Arrays.asList(rsString);
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String rsString = rs.getString(columnIndex);
        return Arrays.asList(rsString);
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String csString = cs.getString(columnIndex);
        return Arrays.asList(csString);
    }
}
