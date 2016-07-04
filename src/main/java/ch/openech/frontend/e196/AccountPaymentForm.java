package ch.openech.frontend.e196;

import static ch.openech.model.tax.AccountPayment.*;

import org.minimalj.frontend.form.Form;

import ch.openech.model.tax.AccountPayment;

public class AccountPaymentForm extends Form<AccountPayment> {

	public AccountPaymentForm(boolean editable, boolean accountType) {
		super(editable, 4);
		
		addTitle("Bezeichnung");
		line($.paymentDate, $.name, $.amountCurrency, $.amount);
		
		addTitle("Ertrag");
		if (accountType) {
			line($.currency, $.exchangeRate);
			line($.grossRevenueA, $.grossRevenueB, $.withHoldingTaxClaim);
		} else {
			line($.grossRevenueB);
		}
	}

}
