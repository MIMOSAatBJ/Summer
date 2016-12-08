package com.doumob.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doumob.base.BaseController;

@RestController
@RequestMapping("webservice")
public class WebServiceController extends BaseController {
	
	@RequestMapping("test")
	public String test(){
		System.out.println("haha");
		return "aaaa";
	}

}
