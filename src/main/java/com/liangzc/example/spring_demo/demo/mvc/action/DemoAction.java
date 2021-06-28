package com.liangzc.example.spring_demo.demo.mvc.action;

import com.liangzc.example.spring_demo.annotation.LzcAutowired;
import com.liangzc.example.spring_demo.annotation.LzcController;
import com.liangzc.example.spring_demo.annotation.LzcRequestMapping;
import com.liangzc.example.spring_demo.annotation.LzcRequestParam;
import com.liangzc.example.spring_demo.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//虽然，用法一样，但是没有功能
@LzcController
@LzcRequestMapping("/demo")
public class DemoAction {

  	@LzcAutowired
	private IDemoService demoService;

	@LzcRequestMapping("/query")
	public void query(HttpServletRequest req, HttpServletResponse resp,
                      @LzcRequestParam("name") String name){
//		String result = demoService.get(name);
		String result = "My name is " + name;
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@LzcRequestMapping("/add")
	public void add(HttpServletRequest req, HttpServletResponse resp,
                    @LzcRequestParam("a") Integer a, @LzcRequestParam("b") Integer b){
		try {
			resp.getWriter().write(a + "+" + b + "=" + (a + b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@LzcRequestMapping("/sub")
	public void sub(HttpServletRequest req, HttpServletResponse resp,
                    @LzcRequestParam("a") Double a, @LzcRequestParam("b") Double b){
		try {
			resp.getWriter().write(a + "-" + b + "=" + (a - b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@LzcRequestMapping("/remove")
	public String  remove(@LzcRequestParam("id") Integer id){
		return "" + id;
	}

}
