package com.liangzc.example.webservice;

import com.alibaba.fastjson.JSONObject;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService
public class WebServiceDemo {

    @WebMethod(operationName = "nameTest")
    public String getName(@WebParam(name = "param") String id){
        Map<String, Object> result = new HashMap<>();
        if(id.equals("1")){
            result.put("code", "ok");
            result.put("msg", "这是webservice例子！");
            return JSONObject.toJSONString(result);
        }
        result.put("code", "fail");
        result.put("msg", "这是Java例子！");
        return JSONObject.toJSONString(result);

    }

}
