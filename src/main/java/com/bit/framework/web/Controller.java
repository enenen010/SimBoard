package com.bit.framework.web;

import javax.servlet.http.HttpServletRequest;

public interface Controller {
	String execute(HttpServletRequest req);
}
