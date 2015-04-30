package org.yottabase.eureka.ui.web.core;

public class Route {

	protected String route;

	protected String action;
	
	public Route(String route, String action) {
		super();
		this.route = route;
		this.action = action;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "Route [route=" + route + ", action=" + action + "]";
	}

}
