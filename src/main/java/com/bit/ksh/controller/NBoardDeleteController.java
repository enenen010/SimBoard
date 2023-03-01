package com.bit.ksh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.framework.web.Controller;
import com.bit.model.NBoardDao;

public class NBoardDeleteController implements Controller {
	NBoardDao dao = new NBoardDao();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String nqid=(String)req.getParameter("nqid");
		int result = dao.DeleteOne(nqid);
		req.setAttribute("result", result);
		return "success";
	}

}
