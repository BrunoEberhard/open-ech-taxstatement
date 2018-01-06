package ch.openech.frontend.page;

import static ch.openech.model.tax.AccountPayment.$;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;
import org.minimalj.frontend.page.TablePage;
import org.minimalj.util.IdUtils;
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
		account = Backend.read(Account.class, IdUtils.getId(account));
		return account.payment;
	}
	
	@Override
	protected Form<AccountPayment> createForm(boolean editable, boolean newObject) {
		return new AccountPaymentForm(editable,  Account.BANK_ACCOUNT);
	}
	
	@Override
	protected AccountPayment save(AccountPayment editedObject, AccountPayment originalObject) {
		return Backend.save(editedObject);
	}
	
	@Override
	protected AccountPayment save(AccountPayment changedObject) {
		AccountPayment savedObject = Backend.save(changedObject);
		account.payment.add(savedObject);
		Backend.save(account);
		return savedObject;
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
