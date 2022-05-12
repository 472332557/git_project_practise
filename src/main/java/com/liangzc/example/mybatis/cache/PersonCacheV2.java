package com.liangzc.example.mybatis.cache;

import com.liangzc.example.mybatis.config.JDBCTemplateConfig;
import com.liangzc.example.mybatis.model.PersonCache;
import org.apache.ibatis.cache.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class PersonCacheV2 implements Cache {

    private String id;

    static JdbcTemplate jdbcTemplate;

    static {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JDBCTemplateConfig.class);
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }

    public PersonCacheV2(String id) {
        this.id = id;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("---------------------插入缓存------------------------");
        int update = jdbcTemplate.update("insert into person_cache(cache_key,cache_value) values (?,?)",
                key.toString(), value.toString());
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("-------------------------从缓存中获取-------------------------------");
        PersonCache personCache = jdbcTemplate.query("select * from person_cache where cache_key = ?",
                new BeanPropertyRowMapper<>(PersonCache.class), key.toString()).get(0);

        System.out.println(personCache);
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println("-------------------------从缓存中删除根据key-------------------------------");
        int update = jdbcTemplate.update("delete from person_cache where cache_key = ?", key.toString());
        return update;
    }

    @Override
    public void clear() {
        System.out.println("-----------------------清空缓存----------------------------");
        jdbcTemplate.update("delete from person_cache");
    }

    @Override
    public int getSize() {
        return 0;
    }
}
