package ca.com.diogo.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ServletUtil {

	// private final static Logger LOGGER =
	// Logger.getLogger(ServletUtil.class.getName());

	public ServletUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void writeXML(HttpServletResponse response, String xml) throws IOException {
		if (xml != null) {
			PrintWriter writer = response.getWriter();
			response.setContentType("application/xml);charset=UTF-8");
			writer.write(xml);
			writer.close();
		}
	}

	public static void writeJSON(HttpServletResponse response, String json) throws IOException {
		if (json != null) {
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			writer.write(json);
			writer.close();
		}
	}
}
