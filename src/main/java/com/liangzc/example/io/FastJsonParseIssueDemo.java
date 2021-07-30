package com.liangzc.example.io;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用fastJson将文件序列化转义后，文件会被清空，线上bug，巨坑
 */
public class FastJsonParseIssueDemo {

    public static void main(String[] args) {

        String filePath = "D:\\env\\uhome-test.properties";

        File file = new File(filePath);

        System.out.println("File："+file.length());

        FileSystemResource fileSystemResource = new FileSystemResource(filePath);

        System.out.println("fileSystemResource："+fileSystemResource.getFile().length());

        Map<String, Object> map = new HashMap<>();
        map.put("requestId", 1);
        map.put("file", fileSystemResource);

        System.out.println(JSON.toJSONString(map));


        System.out.println("json转义后的大小"+fileSystemResource.getFile().length());



    }
}
