package ch.openech.frontend.page;

import static ch.openech.model.tax.BankAccount.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.editor.Editor.NewObjectEditor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage;
import org.minimalj.model.Keys;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.model.validation.EmptyValidator;
import org.minimalj.model.validation.ValidationMessage;
import org.minimalj.util.StringUtils;

import ch.openech.frontend.e196.BankAccountForm;
import ch.openech.model.tax.BankAccount;
import ch.openech.model.tax.TaxStatement;

public class BankAccountTablePage extends TablePage<BankAccount> {

	public static final Object[] COLUMNS = {$.bankAccountNumber, $.iban, $.bankAccountName, $.bankAccountCurrency, $.taxValue.balance};
	private final TaxStatement taxStatement;
	
	public BankAccountTablePage(TaxStatement taxStatement) {
		super(COLUMNS);
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
	
	private static final PropertyInterface ibanProperty = Keys.getProperty($.iban);

	public class NewBankAccountEditor extends NewObjectEditor<BankAccount> {

		@Override
		protected Form<BankAccount> createForm() {
			return new BankAccountForm(Form.EDITABLE, BankAccount.BANK_ACCOUNT);
		}		
		
		@Override
		protected BankAccount save(BankAccount changedObject) {
			taxStatement.listOfBankAccounts.bankAccount.add(changedObject);
			return changedObject;
		}
		
		@Override
		protected void finished(BankAccount result) {
			BankAccountTablePage.this.refresh();
		}
		
		@Override
		protected void validate(BankAccount object, List<ValidationMessage> validationMessages) {
			if (StringUtils.isEmpty(object.iban)) {
				validationMessages.add(new ValidationMessage(ibanProperty, EmptyValidator.createMessage(ibanProperty)));
			}
		}
	}
}
