package com.liangzc.example.spring_demo.demo.service.impl;


import com.liangzc.example.spring_demo.annotation.LzcService;
import com.liangzc.example.spring_demo.demo.service.IDemoService;

/**
 * 核心业务逻辑
 */
@LzcService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
