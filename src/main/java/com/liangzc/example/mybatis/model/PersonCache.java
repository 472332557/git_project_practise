package com.liangzc.example.mybatis.model;
import lombok.Data;

public class PersonCache {

    private Integer id;

    private String cacheKey;

    private String cacheValue;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(String cacheValue) {
        this.cacheValue = cacheValue;
    }

    @Override
    public String toString() {
        return "PersonCache{" +
                "id=" + id +
                ", cacheKey='" + cacheKey + '\'' +
                ", cacheValue='" + cacheValue + '\'' +
                '}';
    }
}
