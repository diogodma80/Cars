package ca.com.diogo.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jettison.JettisonFeature;

public class MyApplication extends Application {

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		// Configures the package to scan the annotated classes
		properties.put("jersey.config.server.provider.packages", "ca.com.diogo");
		return properties;
	}
}
