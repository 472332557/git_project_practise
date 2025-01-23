package com.liangzc.example.mybatis.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.awt.*;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.*;
import java.util.List;

@Intercepts({@Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class TableHeadInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();//获得被代理对象
        Method method = invocation.getMethod();//被代理方法
        Object[] args = invocation.getArgs();//参数
        MappedStatement mappedStatement = (MappedStatement) args[0];


        Map<String, String> tableNames = new HashMap<>();
        tableNames.put("person", "own_01.person");

        Object object1 = args[1];
        Map<String, Object> paramMap = new HashMap<>();
        if (object1 instanceof Map) {
            paramMap = (Map<String, Object>) object1;
        }
        BoundSql boundSql = mappedStatement.getBoundSql(object1);
        Configuration configuration = mappedStatement.getConfiguration();
//        MappedStatement mappedStatement1 = configuration.getMappedStatement(mappedStatement.getId());
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();


        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        System.out.println("替换之前的sql：" + sql);
        if (paramMap.containsKey("name")) {
            //需要替换
            for (Map.Entry<String, String> entry : tableNames.entrySet()) {
                if (!sql.contains(entry.getKey())) {
                    continue;
                }
                sql = sql.replace(entry.getKey(), entry.getValue());
            }
        }
        System.out.println("替换之后的sql：" + sql);
        args[0] = this.copyFromNewSql(mappedStatement, boundSql, sql);

        System.out.println("-------------拦截前----------------");
        Object proceed = invocation.proceed();
        System.out.println("-------------拦截后----------------");
        return proceed;
    }

    private Object copyFromNewSql(MappedStatement mappedStatement, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = this.copyFromBoundSql(mappedStatement, boundSql, sql);
        StaticSqlSource staticSqlSource = new StaticSqlSource(mappedStatement.getConfiguration(), sql, newBoundSql.getParameterMappings());
        //构建新的MappedStatement对象
        return copyFromMappedStatement(mappedStatement, staticSqlSource);
    }

    private Object copyFromMappedStatement(MappedStatement ms, StaticSqlSource staticSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), staticSqlSource, SqlCommandType.SELECT);
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuffer keyProperties = new StringBuffer();
            String[] arr$ = ms.getKeyProperties();
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String keyProperty = arr$[i$];
                keyProperties.append(keyProperty).append(",");
            }

            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    private BoundSql copyFromBoundSql(MappedStatement mappedStatement, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        Iterator i$ = boundSql.getParameterMappings().iterator();

        while (i$.hasNext()) {
            ParameterMapping mapping = (ParameterMapping) i$.next();
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {


    }
}
