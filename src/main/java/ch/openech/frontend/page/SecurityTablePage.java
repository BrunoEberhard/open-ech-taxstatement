package ch.openech.frontend.page;

import static ch.openech.model.tax.SecuritySecurity.$;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;

import ch.openech.frontend.e196.SecuritySecurityForm;
import ch.openech.model.tax.SecurityDepot;
import ch.openech.model.tax.SecuritySecurity;

public class SecurityTablePage extends SimpleTableEditorPage<SecuritySecurity> {

	private static final Object[] columns = {$.securityName, $.isin, $.currency, $.nominalValue};
	private SecurityDepot depot;
	private final boolean securityDepotType;

	public SecurityTablePage(SecurityDepot depot, final boolean securityDepotType) {
		super(columns);
		this.depot = depot;
		this.securityDepotType = securityDepotType;
	}

	public void setDepot(SecurityDepot depot) {
		this.depot = depot;
		refresh();
	}
	
	@Override
	protected List<SecuritySecurity> load() {
		return depot.security;
	}
	
	@Override
	protected Form<SecuritySecurity> createForm(boolean editable, boolean newObject) {
		return new SecuritySecurityForm(editable);
	}
	
	@Override
	protected SecuritySecurity save(SecuritySecurity newObject) {
		depot.security.add(newObject);
		return newObject;
	}
	
	@Override
	public List<Action> getTableActions() {
		List<Action> actions = new ArrayList<>();
		if (securityDepotType) {
			actions.add(new TableNewObjectEditor());
		}
		return actions;
	}

	@Override
	protected SecurityPage getDetailPage(SecuritySecurity security) {
		return new SecurityPage(security);
	}

}
