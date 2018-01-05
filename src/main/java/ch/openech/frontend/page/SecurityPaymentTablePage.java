package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityPayment.$;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;

import ch.openech.frontend.e196.SecurityPaymentForm;
import ch.openech.model.tax.SecurityPayment;
import ch.openech.model.tax.SecuritySecurity;

//die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
public class SecurityPaymentTablePage extends SimpleTableEditorPage<SecurityPayment> {
	public static final Object[] COLUMNS = {$.paymentDate, $.paymentDate, $.amountCurrency, $.amount};

	private SecuritySecurity security;
	
	public SecurityPaymentTablePage(SecuritySecurity security) {
		super(SecurityPaymentTablePage.COLUMNS);
		this.security = security;
	}
	
	public void setSecurity(SecuritySecurity security) {
		this.security = security;
		refresh();
	}
	
	@Override
	protected List<SecurityPayment> load() {
		return security.payment;
	}
	
	@Override
	public List<Action> getTableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new TableNewObjectEditor());
		return actions;
	}

	@Override
	protected SecurityPaymentPage getDetailPage(SecurityPayment payment) {
		return new SecurityPaymentPage(payment);
	}
	
	@Override
	protected Form<SecurityPayment> createForm(boolean editable, boolean newObject) {
		return new SecurityPaymentForm(editable);
	}

	@Override
	protected SecurityPayment save(SecurityPayment changedObject) {
		security.payment.add(changedObject);
		return changedObject;
	}
}
