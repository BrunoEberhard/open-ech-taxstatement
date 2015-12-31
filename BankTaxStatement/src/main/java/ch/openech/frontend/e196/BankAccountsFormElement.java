package ch.openech.frontend.e196;

import static ch.openech.model.tax.BankAccount.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.Keys;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.frontend.e08.CountryFormElement;
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
    	Form<BankAccount> form = new Form<BankAccount>(2);
    	form.line($.iban, $.bankAccountNumber);
    	form.line($.bankAccountName, new CountryFormElement(Keys.getProperty($.bankAccountCountry)));
    	form.line($.bankAccountCurrency);   	
    	form.line($.openingDate, $.closingDate);
    	
    	form.line($.getHasTaxValue());
    	form.line($.taxValue.referenceDate, $.taxValue.name);
    	form.line($.taxValue.balanceCurrency, $.taxValue.balance);
    	form.line($.taxValue.exchangeRate);
    	form.line($.taxValue.currency, $.taxValue.value);
    	
    	form.line(new BankAccountPaymentsFormElement(Keys.getProperty($.payment)));
    	
    	form.line($.currency);   	
    	form.line($.totalGrossRevenueA, $.totalGrossRevenueB);
    	form.line($.totalTaxValue, $.totalWithHoldingTaxClaim);
    	
    	form.addDependecy($.getHasTaxValue(), $.taxValue);
    	return form;
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}
	
}
