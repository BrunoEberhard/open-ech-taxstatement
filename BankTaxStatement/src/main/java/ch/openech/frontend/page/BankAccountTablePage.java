package ch.openech.frontend.page;

import static ch.openech.model.tax.BankAccount.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.Frontend.IContent;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.Page;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;

import ch.openech.frontend.e196.BankAccountForm;
import ch.openech.frontend.page.BankAccountTablePage.BankAccountPage;
import ch.openech.model.tax.BankAccount;
import ch.openech.model.tax.TaxStatement;

public class BankAccountTablePage extends TablePageWithDetail<BankAccount, BankAccountPage> {
	public static final Object[] COLUMNS = {$.bankAccountNumber, $.iban, $.bankAccountName, $.bankAccountCurrency, $.taxValue.balance};

	private final TaxStatement taxStatement;
	
	public BankAccountTablePage(TaxStatement taxStatement) {
		super(BankAccountTablePage.COLUMNS);
		this.taxStatement = taxStatement;
	}

	@Override
	protected List<BankAccount> load() {
		return taxStatement.listOfBankAccounts.bankAccount;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewBankAccountEditor());
		return actions;
	}

	@Override
	protected BankAccountPage createDetailPage(BankAccount account) {
		return new BankAccountPage(account);
	}
	
	@Override
	protected BankAccountPage updateDetailPage(BankAccountPage page, BankAccount account) {
		page.setAccount(account);
		return page;
	}
	
	public class BankAccountPage extends Page {
		private Form<BankAccount> form;
		
		public BankAccountPage(BankAccount account) {
			form = new BankAccountForm(Form.READ_ONLY,  BankAccount.BANK_ACCOUNT);
			form.setObject(account);
		}

		@Override
		public IContent getContent() {
			return form.getContent();
		}
		
		public void setAccount(BankAccount account) {
			form.setObject(account);
		}
	}

	public class NewBankAccountEditor extends NewDetailEditor<BankAccount> {
		@Override
		protected Form<BankAccount> createForm() {
			return new BankAccountForm(Form.EDITABLE,  BankAccount.BANK_ACCOUNT);
		}		
		
		@Override
		protected BankAccount save(BankAccount changedObject) {
			taxStatement.listOfBankAccounts.bankAccount.add(changedObject);
			return changedObject;
		}
	}
}
