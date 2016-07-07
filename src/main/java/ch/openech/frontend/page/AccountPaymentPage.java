package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.ObjectPage;
import org.minimalj.util.resources.Resources;

import ch.openech.frontend.e196.AccountPaymentForm;
import ch.openech.model.tax.AccountPayment;

public class AccountPaymentPage extends ObjectPage<AccountPayment> {
	
	private final boolean accountType;
	
	public AccountPaymentPage(AccountPayment payment, boolean accountType) {
		super(payment);
		this.accountType = accountType;
	}
	
	@Override
	public String getTitle() {
		return Resources.getString(accountType ? "BankAccountPayment" : "LiabilityAccountPayment");
	}	

	@Override
	protected Form<AccountPayment> createForm() {
		return new AccountPaymentForm(Form.READ_ONLY,  accountType);
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new AccountPaymentEditor());
		return actions;
	}

	public class AccountPaymentEditor extends ObjectEditor {

		@Override
		protected Form<AccountPayment> createForm() {
			return new AccountPaymentForm(Form.EDITABLE,  accountType);
		}

		@Override
		protected Object[] getNameArguments() {
			return new Object[] { Resources.getString(accountType ? "AccountPayment" : "LiabilityAccountPayment") };
		}
		
		@Override
		protected AccountPayment save(AccountPayment object) {
			return Backend.save(object);
		}
		
	}

}