package ch.openech.frontend.e196;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.Account;

public class BankAccountsFormElement extends ListFormElement<Account> {

	public BankAccountsFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(Account entry) {
		add(entry.currency + " " + entry.bankAccountName, new ListEntryEditor(entry));
	}

	@Override
	protected Form<Account> createForm(boolean edit) {
		return new AccountForm(edit,  Account.BANK_ACCOUNT);
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}
	
}
