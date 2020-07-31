package mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler { //인터페이스
	public String 
	    process(HttpServletRequest request
	    		, HttpServletResponse response) throws Exception;
}