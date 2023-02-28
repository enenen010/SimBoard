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
		
		//페이징 구현
		int pLeagth = 10;
		int pageMax = dao.SelectCount(content, id);
		pageMax = pageMax/pLeagth + pageMax%pLeagth>0?1:0;
		
		req.setAttribute("list", list);
		req.setAttribute("pageMax", pageMax);
		
		return "success";
	}

}
