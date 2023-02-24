package com.bit.framework.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.BiConsumer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DispatchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	Map<String, Controller> handler = new HashMap<>();
	Map<String, String> handlerName = new HashMap<>();
	Map<String, String> results = new HashMap<String, String>();
	String prefix = "/WEB-INF/views/";
	String suffix = ".jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		HashMap<String, Controller> mapper = new HashMap<String, Controller>();
		Map<String, String> results2 = new HashMap<String, String>();
		Map<String, String> mapperNames = new HashMap<String, String>();
		String fname = config.getServletName()!=null?config.getServletName():"bit";
		Properties prop = new Properties();
		
		try (
				InputStream is = config.getServletContext().getClassLoader().getResourceAsStream(fname+".properties");
				InputStream is2 = config.getServletContext().getClassLoader().getResourceAsStream("webconfig.properties");
				InputStream is3 = config.getServletContext().getClassLoader().getResourceAsStream("result.properties");
				){
			prop.load(is);
			for(Entry<Object, Object> etr : prop.entrySet()) {
				mapper.put(
						etr.getKey().toString(), 
						(Controller)Class.forName(etr.getValue().toString()).newInstance()
						);
				mapperNames.put(etr.getKey().toString(), etr.getValue().toString());
			}
			//액션 매핑 <액션결과, 사이트경로>
			prop.clear();
			prop.load(is3);
			for(Entry<Object, Object> etr : prop.entrySet()) {
				results2.put(
						etr.getKey().toString(), 
						etr.getValue().toString()
						);
			}
			prop.clear();
			prop.load(is2);
			prefix=prop.getProperty("prefix")!=null?prop.getProperty("prefix"):"notpatherror";
			handler.putAll(mapper);
			handlerName.putAll(mapperNames);
			results.putAll(results2);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDo(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDo(req, resp);
	}
	
	protected void doDo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewPath = req.getRequestURI().substring(req.getContextPath().length());//context 까지의 경로 편집
		
		String result=handler.get(viewPath).execute(req);
		if(result.startsWith("redirect:")) {
			resp.sendRedirect(result.substring("redirect:".length())+".do");
		}else {
			req.getRequestDispatcher(prefix+results.get(ActionPlus(viewPath,result))).forward(req, resp);
		}
	}
	
	protected String ActionPlus(String viewpath,String result) {
		if(result.startsWith("redirect:")) {
			return viewpath;
		}else {
			String actionResult = handlerName.get(viewpath)+"-"+result;
			return actionResult;
		}
	}

}
