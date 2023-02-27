package com.bit.ksh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bit.framework.web.Controller;
import com.bit.model.NBoardDao;
import com.bit.model.NBoardDto;

public class NBoardDetailController implements Controller {
	NBoardDao dao = new NBoardDao();

	@Override
	public String execute(HttpServletRequest req) {
		String nqid = (String) req.getParameter("nqid");
		NBoardDto obj = dao.SelectOne(nqid);
		req.setAttribute("obj", obj);
		
		return "success";
	}

}
