package ch.openech;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.minimalj.application.Application;
import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.action.ActionGroup;
import org.minimalj.frontend.page.HtmlPage;
import org.minimalj.frontend.page.Page;

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
		ActionGroup actions = new ActionGroup("Steuerauszug");
		actions.add(new NewTaxStatementAction());
		actions.add(new TaxStatementXmlImport());
		actions.add(new TaxStatementMockAction());
		return Collections.singletonList(actions);
	}

	@Override
	public Class<?>[] getEntityClasses() {
		return new Class<?>[] { TaxStatement.class, CountryIdentification.class };
	}
	
	@Override
	public Page createDefaultPage() {
		return new HtmlPage("TaxStatement_Hilfe.html", "Hinweise zu Steuerauszug (Open-eCH)");
	}
}
