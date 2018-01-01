package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;
import org.minimalj.util.resources.Resources;

import ch.openech.frontend.e196.AccountForm;
import ch.openech.model.tax.Account;
import ch.openech.model.tax.TaxStatement;

// die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
// pretty much the same as BankAccountTablePage
public class LiabilityAccountTablePage extends TablePageWithDetail<Account, AccountPage> {

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
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewLiabilityAccountEditor());
		return actions;
	}

	@Override
	protected AccountPage createDetailPage(Account account) {
		return new AccountPage(account, Account.LIABILITY_ACCOUNT);
	}
	
	@Override
	protected AccountPage updateDetailPage(AccountPage page, Account account) {
		page.setObject(account);
		return page;
	}
	
	public class NewLiabilityAccountEditor extends NewDetailEditor {
		@Override
		protected Form<Account> createForm() {
			return new AccountForm(Form.EDITABLE, Account.LIABILITY_ACCOUNT);
		}		

		@Override
		protected Object[] getNameArguments() {
			return new Object[]{Resources.getString("LiabilityAccount")};
		}
		
		@Override
		protected Account save(Account changedObject) {
			taxStatement.listOfLiabilities.bankAccount.add(changedObject);
			return changedObject;
		}
	}
}
