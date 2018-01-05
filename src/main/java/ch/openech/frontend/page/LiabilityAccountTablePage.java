package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;

import ch.openech.frontend.e196.AccountForm;
import ch.openech.model.tax.Account;
import ch.openech.model.tax.TaxStatement;

// die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
// pretty much the same as BankAccountTablePage
public class LiabilityAccountTablePage extends SimpleTableEditorPage<Account> {

	private final TaxStatement taxStatement;
	
	public LiabilityAccountTablePage(TaxStatement taxStatement) {
		super(BankAccountTablePage.COLUMNS);
		this.taxStatement = taxStatement;
	}

	@Override
	protected List<Account> load() {
		return taxStatement.listOfLiabilities.bankAccount;
	}

	@Override
	protected Form<Account> createForm(boolean editable, boolean newObject) {
		return new AccountForm(editable, Account.LIABILITY_ACCOUNT);
	}

	@Override
	protected Account save(Account changedObject) {
		taxStatement.listOfLiabilities.bankAccount.add(changedObject);
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
		return new AccountPage(this, account, Account.LIABILITY_ACCOUNT);
	}
	
	
//	public class NewLiabilityAccountEditor extends NewDetailEditor {
//
//		@Override
//		protected Object[] getNameArguments() {
//			return new Object[]{Resources.getString("LiabilityAccount")};
//		}
//		
//	}
}
