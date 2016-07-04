package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.ObjectPage;

import ch.openech.frontend.e196.SecurityTaxValueForm;
import ch.openech.model.tax.SecurityTaxValue;

public class SecurityTaxValuePage extends ObjectPage<SecurityTaxValue> {
	
	public SecurityTaxValuePage(SecurityTaxValue payment) {
		super(payment);
	}

	@Override
	protected Form<SecurityTaxValue> createForm() {
		return new SecurityTaxValueForm(Form.READ_ONLY);
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new SecurityTaxValueEditor());
		return actions;
	}

	public class SecurityTaxValueEditor extends ObjectEditor {

		@Override
		protected Form<SecurityTaxValue> createForm() {
			return new SecurityTaxValueForm(Form.EDITABLE);
		}

		@Override
		protected SecurityTaxValue save(SecurityTaxValue object) {
			return Backend.save(object);
		}
		
	}

}