package ch.openech.frontend.e196;

import static ch.openech.model.tax.BankAccountPayment.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.BankAccountPayment;

public class BankAccountPaymentsFormElement extends ListFormElement<BankAccountPayment> {

	public BankAccountPaymentsFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(BankAccountPayment entry) {
		add(entry.name, new ListEntryEditor(entry));
	}

	@Override
	protected Form<BankAccountPayment> createForm(boolean edit) {
    	Form<BankAccountPayment> form = new Form<BankAccountPayment>(2);
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
