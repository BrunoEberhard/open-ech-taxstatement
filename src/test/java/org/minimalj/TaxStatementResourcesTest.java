package org.minimalj;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Test;
import org.minimalj.util.resources.MultiResourceBundle;

import ch.openech.TaxStatementApplication;

public class TaxStatementResourcesTest {

	@Test
	public void testResourceBundleAvailable() {
		Locale locale = Locale.GERMAN;
		try {
			List<ResourceBundle> resourceBundles = new ArrayList<>();
			resourceBundles.add(ResourceBundle.getBundle(TaxStatementApplication.class.getName(), locale));
			resourceBundles.add(ResourceBundle.getBundle(TaxStatementApplication.class.getName() + "_xml", locale));
			resourceBundles.add(ResourceBundle.getBundle("OpenEch", locale));
			new MultiResourceBundle(resourceBundles);
		} catch (MissingResourceException x) {
			Assert.fail(x.getMessage());
		}
	}
}
