package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.Frontend.IContent;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.Page;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;

import ch.openech.frontend.e196.BankAccountForm;
import ch.openech.frontend.page.LiabilityAccountTablePage.LiabilityAccountPage;
import ch.openech.model.tax.BankAccount;
import ch.openech.model.tax.TaxStatement;

// pretty much the same as BankAccountTablePage
public class LiabilityAccountTablePage extends TablePageWithDetail<BankAccount, LiabilityAccountPage> {

	private final TaxStatement taxStatement;
	
	public LiabilityAccountTablePage(TaxStatement taxStatement) {
		super(BankAccountTablePage.COLUMNS);
		this.taxStatement = taxStatement;
	}

	@Override
	protected List<BankAccount> load() {
		return taxStatement.listOfLiabilities.bankAccount;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewLiabilityAccountEditor());
		return actions;
	}

	@Override
	protected LiabilityAccountPage createDetailPage(BankAccount account) {
		return new LiabilityAccountPage(account);
	}
	
	@Override
	protected LiabilityAccountPage updateDetailPage(LiabilityAccountPage page, BankAccount account) {
		page.setAccount(account);
		return page;
	}
	
	public class LiabilityAccountPage extends Page {
		private Form<BankAccount> form;
		
		public LiabilityAccountPage(BankAccount account) {
			form = new BankAccountForm(Form.READ_ONLY,  BankAccount.LIABILITY_ACCOUNT);
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

	public class NewLiabilityAccountEditor extends NewDetailEditor<BankAccount> {
		@Override
		protected Form<BankAccount> createForm() {
			return new BankAccountForm(Form.EDITABLE,  BankAccount.LIABILITY_ACCOUNT);
		}		
		
		@Override
		protected BankAccount save(BankAccount changedObject) {
			taxStatement.listOfLiabilities.bankAccount.add(changedObject);
			return changedObject;
		}
	}
}
