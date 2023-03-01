package com.bit.ksh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DataBindingException;

import com.bit.framework.web.Controller;
import com.bit.model.NBoardDao;

public class NBoardAddController implements Controller {
	NBoardDao dao = new NBoardDao();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String nqid="nq01"+new SimpleDateFormat("yyyyMMdd").format(new Date())+new Date().getTime();
		String sub=(String)req.getParameter("sub");
		String id=(String)req.getParameter("id");
		String content=(String)req.getParameter("content");
		int result = dao.InsertOne(nqid, content, "/", sub, id);
		req.setAttribute("result", result);
		return "success";
	}

}
