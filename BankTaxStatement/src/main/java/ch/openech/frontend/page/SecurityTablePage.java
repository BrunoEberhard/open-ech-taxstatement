package ch.openech.frontend.page;

import static ch.openech.model.tax.SecuritySecurity.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.editor.Editor.NewObjectEditor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage;

import ch.openech.frontend.e196.SecuritySecurityForm;
import ch.openech.model.tax.SecurityDepot;
import ch.openech.model.tax.SecuritySecurity;

public class SecurityTablePage extends TablePage<SecuritySecurity> {

	private static final Object[] columns = {$.securityName, $.isin, $.currency, $.nominalValue};
	private final SecurityDepot depot;
	private final boolean securityDepotType;

	public SecurityTablePage(SecurityDepot depot, final boolean securityDepotType) {
		super(columns);
		this.depot = depot;
		this.securityDepotType = securityDepotType;
	}

	@Override
	protected List<SecuritySecurity> load() {
		return depot.security;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		if (securityDepotType) {
			actions.add(new NewSecurityEditor());
		}
		return actions;
	}
	
	public class NewSecurityEditor extends NewObjectEditor<SecuritySecurity> {
		@Override
		protected Form<SecuritySecurity> createForm() {
			return new SecuritySecurityForm(Form.EDITABLE);
		}		
		
		@Override
		protected SecuritySecurity save(SecuritySecurity newObject) {
			depot.security.add(newObject);
			return newObject;
		}
		
		@Override
		protected void finished(SecuritySecurity result) {
			SecurityTablePage.this.refresh();
		}
	}

}
