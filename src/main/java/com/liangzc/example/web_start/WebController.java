package com.liangzc.example.web_start;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.liangzc.example.easyexcel.DemoData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

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


    @PostMapping("/singleTest")
    @ResponseBody
    public String   singleTest() {
        log.info("==================singleTest start");

        SingleTest.instace.getExecutor().execute(()->{

            log.info("==================线程执行中========{}",Thread.currentThread().getName());
            ArrayBlockingQueue<String> transferQueue = SingleTest.transferQueue;
            ArrayBlockingQueue<String> transferQueue1 = SingleTest.transferQueue;
            ArrayBlockingQueue<String> transferQueue2 = SingleTest.instace.transferQueue;

            System.out.println(transferQueue.getClass() == transferQueue1.getClass());
            System.out.println(transferQueue1.getClass() == transferQueue2.getClass());
        });

        return "";
    }

    @GetMapping("/download")
    public void easyexcel(HttpServletResponse response) throws IOException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream()).head(head()).sheet("模板").doWrite(data());
    }


    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 100; i++) {
            DemoData data = new DemoData();
            data.setName("列名"+i);
            data.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            data.setNumber(2673);
            list.add(data);
        }
        return list;
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(Lists.newArrayList("字符串"));
        list.add(Lists.newArrayList("数字"));
        list.add(Lists.newArrayList("日期"));
        list.add(Lists.newArrayList("复杂头","复杂头1"));
        list.add(Lists.newArrayList("复杂头","复杂头2"));
        return list;
    }
}
