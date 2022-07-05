package com.liangzc.example.web_start;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class WebController {

    @RequestMapping("/start")
    @ResponseBody
    public String start(@RequestParam("id") Integer id){
        return "hello lzc start"+id;
    }


    @RequestMapping("/filter")
    @ResponseBody
    public String filter(@RequestParam("id") Integer id){
        return "hello lzc"+id;
    }

    @PostMapping("/postTest")
    @ResponseBody
    public String postDemo(@RequestParam("id") Integer id){
        return "post"+ id;
    }

    @PostMapping("/postJsonTest")
    @ResponseBody
    public String postDemo(@RequestBody User user){
        return "post"+ user;
    }


    @PostMapping("/responseTest")
    @ResponseBody
    public String responseTest(@RequestBody User user) {
        log.info("==================user:{},person:{}", JSON.toJSONString(user));

        return "post" + user;
    }
}
