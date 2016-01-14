package ch.openech.frontend.e196;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.BankAccount;

public class BankAccountsFormElement extends ListFormElement<BankAccount> {

	public BankAccountsFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(BankAccount entry) {
		add(entry.currency + " " + entry.bankAccountName, new ListEntryEditor(entry));
	}

	@Override
	protected Form<BankAccount> createForm(boolean edit) {
		return new BankAccountForm(edit);
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}
	
}
