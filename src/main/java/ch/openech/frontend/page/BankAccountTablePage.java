package ch.openech.frontend.page;

import static ch.openech.model.tax.Account.$;

import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;
import org.minimalj.util.IdUtils;

import ch.openech.frontend.e196.AccountForm;
import ch.openech.model.tax.Account;
import ch.openech.model.tax.TaxStatement;

//die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
public class BankAccountTablePage extends SimpleTableEditorPage<Account> {
	public static final Object[] COLUMNS = {$.bankAccountNumber, $.iban, $.bankAccountName, $.bankAccountCurrency, $.taxValue.balance};

	private TaxStatement taxStatement;
	
	public BankAccountTablePage(TaxStatement taxStatement) {
		super(BankAccountTablePage.COLUMNS);
		this.taxStatement = taxStatement;
	}

	@Override
	protected List<Account> load() {
		taxStatement = Backend.read(TaxStatement.class, IdUtils.getId(taxStatement));
		return taxStatement.listOfBankAccounts.bankAccount;
	}
	
	@Override
	protected Form<Account> createForm(boolean editable, boolean newObject) {
		return new AccountForm(editable,  Account.BANK_ACCOUNT);
	}

	@Override
	protected Account save(Account editedObject, Account originalObject) {
		return Backend.save(editedObject);
	}
	
	@Override
	protected Account save(Account changedObject) {
		Account saved = Backend.save(changedObject);
		taxStatement.listOfBankAccounts.bankAccount.add(saved);
		Backend.save(taxStatement);
		return saved;
	}

	@Override
	protected AccountPage getDetailPage(Account account) {
		return new AccountPage(this, account, Account.BANK_ACCOUNT);
	}
}
