package ch.openech.frontend.e196;

import static ch.openech.model.tax.AccountPayment.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.AccountPayment;

public class BankAccountPaymentsFormElement extends ListFormElement<AccountPayment> {

	public BankAccountPaymentsFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(AccountPayment entry) {
		add(entry.name, new ListEntryEditor(entry));
	}

	@Override
	protected Form<AccountPayment> createForm(boolean edit) {
    	Form<AccountPayment> form = new Form<AccountPayment>(2);
    	form.line($.paymentDate, $.name);
    	form.line($.amountCurrency, $.amount);
    	form.line($.currency, $.exchangeRate);
    	form.line($.grossRevenueA, $.grossRevenueB);
    	form.line($.withHoldingTaxClaim);
    	
    	return form;
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}   

}
