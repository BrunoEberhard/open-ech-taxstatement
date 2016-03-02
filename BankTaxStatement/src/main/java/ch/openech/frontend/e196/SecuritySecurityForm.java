package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecuritySecurity.*;

import org.minimalj.frontend.form.Form;
import org.minimalj.model.Keys;

import ch.openech.model.tax.SecuritySecurity;

public class SecuritySecurityForm extends Form<SecuritySecurity> {

	public SecuritySecurityForm (boolean editable) {
		super(editable, 4);
    	line($.securityName, $.issueDate);
    	line($.valorNumber, $.isin);
    	line($.country, $.city);
    	line($.currency, $.issuePrice);
    	line($.quotationType);
    	line($.securityCategory, $.securityType);
    	line($.redemptionDate, $.redemptionPrice);
    	line($.redemptionDateEarly, $.redemptionPriceEarly);
    	line($.interestRate, $.variableInterest);
    	line($.iup, $.bfp);
    	line(new SecurityTaxValueFormElement(Keys.getProperty($.taxValue)));
	}
	
}
