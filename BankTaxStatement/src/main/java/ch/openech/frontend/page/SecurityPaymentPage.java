package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.ObjectPage;

import ch.openech.frontend.e196.SecurityPaymentForm;
import ch.openech.model.tax.SecurityPayment;

public class SecurityPaymentPage extends ObjectPage<SecurityPayment> {
	
	public SecurityPaymentPage(SecurityPayment payment) {
		super(payment);
	}

	@Override
	protected Form<SecurityPayment> createForm() {
		return new SecurityPaymentForm(Form.READ_ONLY);
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new SecurityPaymentEditor());
		return actions;
	}

	public class SecurityPaymentEditor extends ObjectEditor {

		@Override
		protected Form<SecurityPayment> createForm() {
			return new SecurityPaymentForm(Form.EDITABLE);
		}

		@Override
		protected SecurityPayment save(SecurityPayment object) {
			return Backend.save(object);
		}
		
	}

}