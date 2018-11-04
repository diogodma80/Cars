package ca.com.diogo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

public class RegexUtil {
	//private final static Logger LOGGER = Logger.getLogger(RegexUtil.class.getName());
	private static final Pattern regexAll = Pattern.compile("/cars");
	private static final Pattern regexById = Pattern.compile("/cars/([0-9]*)");

	// Check if the URL has the pattern "*/cars/id"
	public static Long matchId(String requestUri) throws ServletException {
		// Check the ID
		Matcher matcher = regexById.matcher(requestUri);
		if (matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			if (s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		
		return null;
	}

	// Check if the URL has the pattern "*/cars/"
	public boolean matchAll(String requestUri) throws ServletException {
		Matcher matcher = regexAll.matcher(requestUri);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
}