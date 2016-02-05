package ch.openech.frontend.e196;

import static ch.openech.model.tax.Account.*;

import org.minimalj.frontend.form.Form;
import org.minimalj.model.Keys;

import ch.openech.frontend.e08.CountryFormElement;
import ch.openech.model.tax.Account;

public class AccountForm extends Form<Account> {

	public AccountForm(boolean editable, boolean accountType) {
		super(editable, 2);
		
		line($.iban, $.bankAccountNumber);
		line($.bankAccountName, new CountryFormElement(Keys.getProperty($.bankAccountCountry)));
		line($.bankAccountCurrency);
		line($.openingDate, $.closingDate);

		line($.getHasTaxValue());
		line($.taxValue.referenceDate, $.taxValue.name);
		line($.taxValue.balanceCurrency, $.taxValue.balance);
		line($.taxValue.exchangeRate);
		line($.taxValue.currency, $.taxValue.value);

		line(new BankAccountPaymentsFormElement(Keys.getProperty($.payment)));

		line($.currency);
		if (accountType == Account.BANK_ACCOUNT) {
			line($.totalGrossRevenueA, $.totalGrossRevenueB);
			line($.totalTaxValue, $.totalWithHoldingTaxClaim);
		} else {
			line($.totalGrossRevenueB, $.totalTaxValue);
		}

		addDependecy($.getHasTaxValue(), $.taxValue);
	}

}
