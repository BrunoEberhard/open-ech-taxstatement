package ch.openech.frontend.page;

import static ch.openech.model.tax.AccountPayment.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;

import ch.openech.frontend.e196.AccountPaymentForm;
import ch.openech.model.tax.Account;
import ch.openech.model.tax.AccountPayment;

public class AccountPaymentTablePage extends TablePageWithDetail<AccountPayment, AccountPaymentPage> {
	public static final Object[] COLUMNS = {$.paymentDate, $.paymentDate, $.amountCurrency, $.amount};

	private Account account;
	private final boolean accountType;
	
	public AccountPaymentTablePage(Account account, boolean accountType) {
		super(AccountPaymentTablePage.COLUMNS);
		this.account = account;
		this.accountType = accountType;
	}
	
	public void setBankAccount(Account account) {
		this.account = account;
		refresh();
	}
	
	@Override
	protected List<AccountPayment> load() {
		return account.payment;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewBankAccountPaymentEditor());
		return actions;
	}

	@Override
	protected AccountPaymentPage createDetailPage(AccountPayment payment) {
		return new AccountPaymentPage(payment, accountType);
	}
	
	@Override
	protected AccountPaymentPage updateDetailPage(AccountPaymentPage page, AccountPayment payment) {
		page.setObject(payment);
		return page;
	}
	
	public class NewBankAccountPaymentEditor extends NewDetailEditor<AccountPayment> {
		@Override
		protected Form<AccountPayment> createForm() {
			return new AccountPaymentForm(Form.EDITABLE,  Account.BANK_ACCOUNT);
		}		
		
		@Override
		protected AccountPayment save(AccountPayment changedObject) {
			account.payment.add(changedObject);
			return changedObject;
		}
	}
}
