package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.DetailPageAction;
import org.minimalj.frontend.page.ObjectPage;

import ch.openech.frontend.e196.SecuritySecurityForm;
import ch.openech.model.tax.SecuritySecurity;

public class SecurityPage extends ObjectPage<SecuritySecurity> {

	public SecurityPage(SecuritySecurity security) {
		super(security);
	}

	@Override
	protected Form<SecuritySecurity> createForm() {
		return new SecuritySecurityForm(Form.READ_ONLY);
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new SecuritySecurityEditor());
		actions.add(new DetailPageAction(this, new SecurityPaymentTablePage(getObject())));
		actions.add(new DetailPageAction(this, new SecurityStockTablePage(getObject())));
		actions.add(new DetailPageAction(this, new SecurityTaxValueTablePage(getObject())));
		return actions;
	}

	public class SecuritySecurityEditor extends ObjectEditor {

		@Override
		protected Form<SecuritySecurity> createForm() {
			return new SecuritySecurityForm(Form.EDITABLE);
		}

		@Override
		protected SecuritySecurity save(SecuritySecurity object) {
			return Backend.save(object);
		}
		
	}

}
