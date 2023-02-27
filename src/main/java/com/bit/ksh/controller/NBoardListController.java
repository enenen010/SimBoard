package com.bit.ksh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bit.framework.web.Controller;
import com.bit.model.NBoardDao;
import com.bit.model.NBoardDto;

public class NBoardListController implements Controller {
	NBoardDao dao = new NBoardDao();
	
	@Override
	public String execute(HttpServletRequest req) {
		String content = (String) req.getParameter("content");
		String id = (String)req.getParameter("id");
		content=content==null?"":content;
		id=id==null?"":id;
		List<NBoardDto> list = dao.SelectList(content, id);
		req.setAttribute("list", list);
		
		return "success";
	}

}
