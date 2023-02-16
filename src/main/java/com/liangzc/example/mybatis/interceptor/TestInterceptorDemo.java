package com.liangzc.example.mybatis.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({@Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})
})
public class TestInterceptorDemo implements Interceptor {

    //执行拦截方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("-------------拦截前----------------");
        Object proceed = invocation.proceed();
        System.out.println("-------------拦截后----------------");
        return proceed;
    }

    // 决定是否触发 intercept()方法
    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
