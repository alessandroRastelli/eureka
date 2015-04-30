package org.yottabase.eureka.ui.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yottabase.eureka.ui.web.core.Action;

public class SearchAction implements Action{
	

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("view/home.jsp").forward(request, response);
	}

}
