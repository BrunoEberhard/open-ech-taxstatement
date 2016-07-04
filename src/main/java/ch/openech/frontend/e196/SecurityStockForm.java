package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecurityStock.*;

import org.minimalj.frontend.form.Form;

import ch.openech.model.tax.SecurityStock;

public class SecurityStockForm extends Form<SecurityStock> {

	public SecurityStockForm(boolean editable) {
		super(editable, 2);

    	line($.name);
    	line($.quotationType);   	
    	line($.mutation);   	
    	line($.quantity, $.unitPrice);   	
    	line($.balanceCurrency, $.balance);
    	line($.reductionCost);    	
    	line($.exchangeRate);
    	line($.currency, $.value);
    	line($.blocked, $.blockingTo);
	}
}
