package ch.openech;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.minimalj.application.Application;
import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;

import ch.openech.action.NewTaxStatementAction;
import ch.openech.action.TaxStatementMockAction;
import ch.openech.action.TaxStatementXmlImport;
import ch.openech.backend.TaxStatementInHeapBackend;
import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.read.StaxEch0071;

public class TaxStatementApplication extends Application {

	static {
		System.setProperty("MjBackend", TaxStatementInHeapBackend.class.getName());
		for (Canton canton : StaxEch0071.getInstance().getCantons()) {
			Backend.insert(canton);
		}
	}
	
	@Override
	protected Set<String> getResourceBundleNames() {
		Set<String> resourceBundleNames = new HashSet<>();
		resourceBundleNames.add(this.getClass().getName());
		resourceBundleNames.add(this.getClass().getName() + "_xml");
		resourceBundleNames.add("ch.openech.resources.OpenEch");
		return resourceBundleNames;
	}
	
	@Override
	public List<Action> getNavigation() {
		List<Action> navigation = new ArrayList<>();
		navigation.add(new NewTaxStatementAction());
		navigation.add(new TaxStatementXmlImport());
		navigation.add(new TaxStatementMockAction("Steuerauszug mit Zufallswerten"));
		return navigation;
	}

	@Override
	public Class<?>[] getEntityClasses() {
		return new Class<?>[] { TaxStatement.class, CountryIdentification.class };
	}
}
