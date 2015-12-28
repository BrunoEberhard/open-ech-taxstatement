package ch.openech.frontend.e196;

import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ObjectFormElement;
import org.minimalj.model.Rendering.RenderType;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.ListOfBankAccounts;

public class BankAccountsFormElement extends ObjectFormElement<ListOfBankAccounts> {

	public BankAccountsFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected Form<ListOfBankAccounts> createFormPanel() {
		return null;
	}

	@Override
	protected void show(ListOfBankAccounts a) {
		add(a.render(RenderType.PLAIN_TEXT), getEditorAction());
	}

}
