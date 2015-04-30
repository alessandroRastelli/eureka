package org.yottabase.eureka.ui.web.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yottabase.eureka.ui.web.config.RouterConfiguration;


public class Router extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Map<String, Route> routes = new HashMap<String, Route>();
	
	@Override
	public void init() throws ServletException {
		super.init();
		RouterConfiguration routerConfiguration = new RouterConfiguration();
		
		for(Route route: routerConfiguration.getRoutes()){
			this.routes.put(route.route, route);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		Route route = this.matchRoute(request);
		System.out.println(route);
		String actionName = route.getAction();
		Action action = null;

		try {
			action =  (Action) Class.forName(actionName).newInstance();
		} catch (InstantiationException e) {
			request.getRequestDispatcher("error-404.jsp").forward(request, response);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			request.getRequestDispatcher("error-404.jsp").forward(request, response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			request.getRequestDispatcher("error-404.jsp").forward(request, response);
			e.printStackTrace();
		}
		
        action.run(request, response);
        
	}
	
	protected Route matchRoute(HttpServletRequest request){
		
		String servletPath = request.getServletPath();
		String routeStr = servletPath.substring(1, servletPath.length() - ".do".length() );
		
		Route route = this.routes.get(routeStr);
		
		if(route == null){
			throw new RuntimeException("Rotta non trovata");
		}
		
		return route;
	}
	
	
	
	

}
