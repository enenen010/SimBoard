package com.bit.ksh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.framework.web.Controller;

public class IndexController implements Controller{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req.getParameter("test"));
		return "success";
	}

}
