package com.bit.ksh.controller;

import javax.servlet.http.HttpServletRequest;

import com.bit.framework.web.Controller;

public class IndexController implements Controller{

	@Override
	public String execute(HttpServletRequest req) {
		System.out.println(req.getParameter("test"));
		return "success";
	}

}
