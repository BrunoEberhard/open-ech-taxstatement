package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.ObjectPage;

import ch.openech.frontend.e196.SecurityStockForm;
import ch.openech.model.tax.SecurityStock;

public class SecurityStockPage extends ObjectPage<SecurityStock> {
	
	public SecurityStockPage(SecurityStock payment) {
		super(payment);
	}

	@Override
	protected Form<SecurityStock> createForm() {
		return new SecurityStockForm(Form.READ_ONLY);
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new SecurityStockEditor());
		return actions;
	}

	public class SecurityStockEditor extends ObjectEditor {

		@Override
		protected Form<SecurityStock> createForm() {
			return new SecurityStockForm(Form.EDITABLE);
		}

		@Override
		protected SecurityStock save(SecurityStock object) {
			return Backend.save(object);
		}
		
	}

}