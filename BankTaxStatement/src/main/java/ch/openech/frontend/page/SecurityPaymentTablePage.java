package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityPayment.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;

import ch.openech.frontend.e196.SecurityPaymentForm;
import ch.openech.model.tax.SecurityPayment;
import ch.openech.model.tax.SecuritySecurity;

public class SecurityPaymentTablePage extends TablePageWithDetail<SecurityPayment, SecurityPaymentPage> {
	public static final Object[] COLUMNS = {$.paymentDate, $.paymentDate, $.amountCurrency, $.amount};

	private final SecuritySecurity security;
	
	public SecurityPaymentTablePage(SecuritySecurity security) {
		super(SecurityPaymentTablePage.COLUMNS);
		this.security = security;
	}
	
	@Override
	protected List<SecurityPayment> load() {
		return security.payment;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewBankSecurityPaymentEditor());
		return actions;
	}

	@Override
	protected SecurityPaymentPage createDetailPage(SecurityPayment payment) {
		return new SecurityPaymentPage(payment);
	}
	
	@Override
	protected SecurityPaymentPage updateDetailPage(SecurityPaymentPage page, SecurityPayment payment) {
		page.setObject(payment);
		return page;
	}
	
	public class NewBankSecurityPaymentEditor extends NewDetailEditor<SecurityPayment> {
		@Override
		protected Form<SecurityPayment> createForm() {
			return new SecurityPaymentForm(Form.EDITABLE);
		}		
		
		@Override
		protected SecurityPayment save(SecurityPayment changedObject) {
			security.payment.add(changedObject);
			return changedObject;
		}
	}
}
