package ch.openech.frontend.page;

import static ch.openech.model.tax.Account.$;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;

import ch.openech.frontend.e196.AccountForm;
import ch.openech.model.tax.Account;
import ch.openech.model.tax.TaxStatement;

//die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
public class BankAccountTablePage extends SimpleTableEditorPage<Account> {
	public static final Object[] COLUMNS = {$.bankAccountNumber, $.iban, $.bankAccountName, $.bankAccountCurrency, $.taxValue.balance};

	private final TaxStatement taxStatement;
	
	public BankAccountTablePage(TaxStatement taxStatement) {
		super(BankAccountTablePage.COLUMNS);
		this.taxStatement = taxStatement;
	}

	@Override
	protected List<Account> load() {
		return taxStatement.listOfBankAccounts.bankAccount;
	}
	
	@Override
	protected Form<Account> createForm(boolean editable, boolean newObject) {
		return new AccountForm(editable,  Account.BANK_ACCOUNT);
	}

	@Override
	protected Account save(Account changedObject) {
		taxStatement.listOfBankAccounts.bankAccount.add(changedObject);
		return changedObject;
	}

	@Override
	public List<Action> getTableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new TableNewObjectEditor());
		return actions;
	}

	@Override
	protected AccountPage getDetailPage(Account account) {
		return new AccountPage(this, account, Account.BANK_ACCOUNT);
	}

}
