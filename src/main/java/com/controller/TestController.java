package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/test")
	public String test(ModelMap m) {
		System.out.println("��������˿��Ʋ�....");
		m.put("msg", "����,springmvc��ʼ����󹦸��");
		return "index";    //==>  /view/index.jsp
	}
}