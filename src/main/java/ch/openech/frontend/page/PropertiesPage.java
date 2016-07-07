package ch.openech.frontend.page;

import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.minimalj.frontend.page.HtmlPage;

public class PropertiesPage extends HtmlPage {

	public PropertiesPage() {
		super(getPropertiesAsHtml(), "System Properties");
	}
	
	private static String getPropertiesAsHtml() {
		StringBuilder s = new StringBuilder(5000);
		s.append("<body><html><code>");
		Properties props = System.getProperties();
		Set<String> keys = new TreeSet<>();
		props.keySet().stream().forEach((key) -> keys.add((String) key));
		for (String key : keys) {
			s.append(key).append(": ").append(props.get(key)).append("<br>");
		}
		s.append("</code></body></html>");
		return s.toString();
	}

}
