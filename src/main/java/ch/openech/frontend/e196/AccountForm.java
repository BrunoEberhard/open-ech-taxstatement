package ch.openech.frontend.e196;

import static ch.openech.model.tax.Account.$;

import org.minimalj.frontend.form.Form;
import org.minimalj.model.Keys;

import ch.openech.frontend.e08.CountryFormElement;
import ch.openech.model.tax.Account;

public class AccountForm extends Form<Account> {

	public AccountForm(boolean editable, boolean accountType) {
		super(editable, 4);
		
		addTitle("Bezeichnung");
		
		line($.iban, $.bankAccountNumber, $.bankAccountName, $.bankAccountCurrency);
		line($.openingDate, $.closingDate, editable ? new CountryFormElement(Keys.getProperty($.bankAccountCountry)) : $.bankAccountCountry);

		addTitle("Steuerwert");
		
		line($.getHasTaxValue());
		line($.taxValue.referenceDate, $.taxValue.name, $.taxValue.balanceCurrency, $.taxValue.balance);
		line($.taxValue.currency, $.taxValue.value, $.taxValue.exchangeRate);

		addTitle("Total");
		line($.currency);
		if (accountType == Account.BANK_ACCOUNT) {
			line($.totalGrossRevenueA, $.totalGrossRevenueB, $.totalTaxValue, $.totalWithHoldingTaxClaim);
		} else {
			line($.totalGrossRevenueB, $.totalTaxValue);
		}

		// wenn hasTaxValue auf false gesetzt wird sollen die Werte in taxValue verschwinden
		addDependecy($.getHasTaxValue(), $.taxValue);
	}
	
	@Override
	protected int getColumnWidthPercentage() {
		return 110;
	}

}
