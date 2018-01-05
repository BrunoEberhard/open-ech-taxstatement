package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.DetailPageAction;
import org.minimalj.frontend.page.ObjectPage;
import org.minimalj.frontend.page.TablePage;
import org.minimalj.util.resources.Resources;

import ch.openech.frontend.e196.AccountForm;
import ch.openech.model.tax.Account;

public class AccountPage extends ObjectPage<Account> {
	
	private final TablePage<?> tablePage;
	private final boolean accountType;
	private AccountPaymentTablePage paymentTablePage;
	
	public AccountPage(TablePage<?> tablePage, Account account, boolean accountType) {
		super(account);
		this.tablePage = tablePage;
		this.accountType = accountType;
		
		paymentTablePage = new AccountPaymentTablePage(account, accountType);
	}

	@Override
	public String getTitle() {
		return Resources.getString(accountType ? "BankAccount" : "LiabilityAccount");
	}
	
	@Override
	protected Form<Account> createForm() {
		return new AccountForm(Form.READ_ONLY,  accountType);
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new AccountEditor());
		actions.add(new DetailPageAction(this, paymentTablePage));
		return actions;
	}

	@Override
	public void setObject(Account account) {
		super.setObject(account);
		paymentTablePage.setBankAccount(account);
	}
	
	public class AccountEditor extends ObjectEditor {

		@Override
		protected Form<Account> createForm() {
			return new AccountForm(Form.EDITABLE,  accountType);
		}

		@Override
		protected Object[] getNameArguments() {
			return new Object[] { Resources.getString(accountType ? "BankAccount" : "LiabilityAccount") };
		}
		
		@Override
		protected Account save(Account object) {
			return Backend.save(object);
		}
		
		@Override
		protected void finished(Account result) {
			super.finished(result);
			tablePage.refresh();
		}
		
	}

}