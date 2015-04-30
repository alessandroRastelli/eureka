package org.yottabase.eureka.ui.web.config;

import java.util.ArrayList;
import java.util.List;

import org.yottabase.eureka.ui.web.core.Route;

public class RouterConfiguration {
	
	public List<Route> getRoutes(){
		
		List<Route> routes = new ArrayList<Route>();
		
		// per aggiungere una nuova rotta copiare la riga e configurare opportunamente
		routes.add(new Route("search", "org.yottabase.eureka.ui.web.action.SearchAction"));
		routes.add(new Route("apiSearch", "org.yottabase.eureka.ui.web.action.ApiSearchAction"));
		routes.add(new Route("apiAutocomplete", "org.yottabase.eureka.ui.web.action.ApiAutocompleteAction"));
		
		return routes;
	}

}
