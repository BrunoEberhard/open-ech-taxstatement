package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecurityPayment.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.SecurityPayment;

public class SecurityPaymentElement extends ListFormElement<SecurityPayment> {

	public SecurityPaymentElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(SecurityPayment entry) {
		add(entry.name, new ListEntryEditor(entry));
	}

	@Override
	protected Form<SecurityPayment> createForm(boolean edit) {
//    	Form<SecurityPayment> form = new Form<SecurityPayment>(2);
//    	form.line($.paymentDate, $.exDate);
//    	form.line($.name);
//    	form.line($.quotationType, $.quantity);   	
//    	form.line($.amountCurrency, $.amount);
//    	form.line($.amountPerUnit);
//    	form.line($.currency, $.exchangeRate);
//    	form.line($.grossRevenueA, $.grossRevenueACanton);
//    	form.line($.grossRevenueB, $.grossRevenueBCanton);
//      	form.line($.totalWithHoldingTaxClaim);
//          	
//    	form.line($.lumpSumTaxCredit);
//    	form.line($.lumpSumTaxCreditPercent, $.lumpSumTaxCreditAmount);
//    	form.line($.nonRecoverableTaxPercent, $.nonRecoverableTaxAmount);
//    	form.line($.additionalWithHoldingTaxUSA);
//
//    	form.line($.iup, $.conversion);
//    	form.line($.gratis, $.securitiesLending);
//    	form.line($.lendingFee, $.retrocession);
//    	form.line($.undefined, $.kursliste);
//    	form.line($.sign);

    	Form<SecurityPayment> form = new Form<SecurityPayment>(5);
    	form.line($.paymentDate, $.exDate, $.name);
    	form.line($.quotationType, $.quantity, $.amountCurrency, $.amount, $.amountPerUnit);
    	form.line($.currency, $.exchangeRate);
    	form.line($.grossRevenueA, $.grossRevenueACanton, $.grossRevenueB, $.grossRevenueBCanton, $.totalWithHoldingTaxClaim);
          	
    	form.line($.lumpSumTaxCredit);
    	form.line($.lumpSumTaxCreditPercent, $.lumpSumTaxCreditAmount, $.nonRecoverableTaxPercent, $.nonRecoverableTaxAmount, $.additionalWithHoldingTaxUSA);

    	form.line($.iup, $.conversion, $.gratis, $.securitiesLending, $.lendingFee);
    	form.line($.retrocession, $.undefined, $.kursliste, $.sign);
    	
    	return form;
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}

}
