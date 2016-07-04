package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecurityTaxValue.*;

import org.minimalj.frontend.form.Form;

import ch.openech.model.tax.SecurityTaxValue;

public class SecurityTaxValueForm extends Form<SecurityTaxValue> {

	public SecurityTaxValueForm(boolean editable) {
		super(editable, 4);

    	line($.referenceDate, $.name, $.quotationType, $.quantity);
    	line($.balanceCurrency, $.unitPrice, $.balance);
    	line($.currency, $.value, $.exchangeRate);
    	line($.blocked, $.blockingTo, $.undefined, $.kursliste);
	}
}
