package ch.openech.frontend.e196;

import static ch.openech.model.tax.AccountPayment.*;

import org.minimalj.frontend.form.Form;

import ch.openech.model.tax.AccountPayment;

public class AccountPaymentForm extends Form<AccountPayment> {

	public AccountPaymentForm(boolean editable, boolean accountType) {
		super(editable, 2);
		
		line($.paymentDate, $.name);
		line($.amountCurrency, $.amount);
		line($.exchangeRate, $.currency);
		if (accountType) {
			line($.grossRevenueA, $.grossRevenueB);
			line($.withHoldingTaxClaim);
		} else {
			line($.grossRevenueB);
		}
	}

}
