package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.editor.Editor.NewObjectEditor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage;

import ch.openech.frontend.e196.BankAccountForm;
import ch.openech.model.tax.BankAccount;
import ch.openech.model.tax.TaxStatement;

// pretty much the same as BankAccountTablePage
public class LiabilityAccountTablePage extends TablePage<BankAccount> {

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
	
	public class NewLiabilityAccountEditor extends NewObjectEditor<BankAccount> {
		@Override
		protected Form<BankAccount> createForm() {
			return new BankAccountForm(Form.EDITABLE,  BankAccount.LIABILITY_ACCOUNT);
		}		
		
		@Override
		protected BankAccount save(BankAccount changedObject) {
			taxStatement.listOfLiabilities.bankAccount.add(changedObject);
			return changedObject;
		}
		
		@Override
		protected void finished(BankAccount result) {
			LiabilityAccountTablePage.this.refresh();
		}
	}
}
