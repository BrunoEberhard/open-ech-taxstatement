package ch.openech.frontend.page;

import static ch.openech.model.tax.AccountPayment.$;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;
import org.minimalj.frontend.page.TablePage;
import org.minimalj.util.resources.Resources;

import ch.openech.frontend.e196.AccountPaymentForm;
import ch.openech.model.tax.Account;
import ch.openech.model.tax.AccountPayment;

//die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
public class AccountPaymentTablePage extends SimpleTableEditorPage<AccountPayment> {
	public static final Object[] COLUMNS = {$.paymentDate, $.amountCurrency, $.amount};

	private Account account;
	private final boolean accountType;
	
	public AccountPaymentTablePage(Account account, boolean accountType) {
		super(AccountPaymentTablePage.COLUMNS);
		this.account = account;
		this.accountType = accountType;
	}
	
	@Override
	public String getTitle() {
		String className = Resources.getString(accountType ? "AccountPayment" : "LiabilityAccountPayment");
		return MessageFormat.format(Resources.getString(TablePage.class.getSimpleName() + ".title"), className);
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
	protected Form<AccountPayment> createForm(boolean editable, boolean newObject) {
		return new AccountPaymentForm(editable,  Account.BANK_ACCOUNT);
	}
	
	@Override
	protected AccountPayment save(AccountPayment changedObject) {
		account.payment.add(changedObject);
		return changedObject;
	}
	
	@Override
	public List<Action> getTableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new TableNewObjectEditor());
		return actions;
	}

	@Override
	protected AccountPaymentPage getDetailPage(AccountPayment payment) {
		return new AccountPaymentPage(payment, accountType);
	}
	
}
