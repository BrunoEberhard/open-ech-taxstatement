package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecurityPayment.*;

import org.minimalj.frontend.form.Form;

import ch.openech.model.tax.SecurityPayment;

public class SecurityPaymentForm extends Form<SecurityPayment> {

	public SecurityPaymentForm(boolean editable) {
		super(editable, 5);

    	line($.paymentDate, $.exDate, $.name);
    	line($.quotationType, $.quantity, $.amountCurrency, $.amount, $.amountPerUnit);
    	line($.currency, $.exchangeRate);
    	line($.grossRevenueA, $.grossRevenueACanton, $.grossRevenueB, $.grossRevenueBCanton, $.totalWithHoldingTaxClaim);
          	
    	line($.lumpSumTaxCredit);
    	line($.lumpSumTaxCreditPercent, $.lumpSumTaxCreditAmount, $.nonRecoverableTaxPercent, $.nonRecoverableTaxAmount, $.additionalWithHoldingTaxUSA);

    	line($.iup, $.conversion, $.gratis, $.securitiesLending, $.lendingFee);
    	line($.retrocession, $.undefined, $.kursliste, $.sign);

//		two column version
//		super(editable, 2);		
//    	line($.paymentDate, $.exDate);
//    	line($.name);
//    	line($.quotationType, $.quantity);   	
//    	line($.amountCurrency, $.amount);
//    	line($.amountPerUnit);
//    	line($.currency, $.exchangeRate);
//    	line($.grossRevenueA, $.grossRevenueACanton);
//    	line($.grossRevenueB, $.grossRevenueBCanton);
//      line($.totalWithHoldingTaxClaim);
//          	
//    	line($.lumpSumTaxCredit);
//    	line($.lumpSumTaxCreditPercent, $.lumpSumTaxCreditAmount);
//    	line($.nonRecoverableTaxPercent, $.nonRecoverableTaxAmount);
//    	line($.additionalWithHoldingTaxUSA);
//
//    	line($.iup, $.conversion);
//    	line($.gratis, $.securitiesLending);
//    	line($.lendingFee, $.retrocession);
//    	line($.undefined, $.kursliste);
//    	line($.sign);
	}
}
