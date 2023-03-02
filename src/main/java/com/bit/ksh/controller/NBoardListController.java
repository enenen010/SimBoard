package com.bit.ksh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.framework.web.Controller;
import com.bit.model.NBoardDao;
import com.bit.model.NBoardDto;

public class NBoardListController implements Controller {
	NBoardDao dao = new NBoardDao();
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String sub = (String) req.getParameter("sub");
		String id = (String)req.getParameter("id");
		int page =  req.getParameter("page")!=null
				    ?Integer.parseInt(req.getParameter("page"))
					:1;
		int pLeagth =  req.getParameter("pagemax")!=null
					   ?Integer.parseInt(req.getParameter("pagemax"))
					   :10;
		sub=sub==null?"":sub;
		id=id==null?"":id;
		System.out.println(page+"  "+pLeagth);
		List<NBoardDto> list = dao.SelectList(sub, id, page, pLeagth);
		
		//페이징 구현
		int pageMax = dao.SelectCount(sub, id);
		pageMax = pageMax/pLeagth + ((pageMax%pLeagth)>0?1:0);
		req.setAttribute("list", list);
		req.setAttribute("pageStart", (page-1)*pLeagth);
		req.setAttribute("pageMax", pageMax);
		
		return "success";
	}

}
