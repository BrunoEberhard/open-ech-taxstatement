package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecuritySecurity.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.Keys;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.SecuritySecurity;

public class SecuritySecurityFormElement extends ListFormElement<SecuritySecurity> {

	public SecuritySecurityFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(SecuritySecurity entry) {
		add(entry.securityName, new ListEntryEditor(entry));
	}

	@Override
	protected Form<SecuritySecurity> createForm(boolean edit) {
    	Form<SecuritySecurity> form = new Form<SecuritySecurity>(4);
    	form.line($.securityName, $.issueDate);
    	form.line($.valorNumber, $.isin);
    	form.line($.country, $.city);
    	form.line($.currency, $.issuePrice);
    	form.line($.quotationType);
    	form.line($.securityCategory, $.securityType);
    	form.line($.redemptionDate, $.redemptionPrice);
    	form.line($.redemptionDateEarly, $.redemptionPriceEarly);
    	form.line($.interestRate, $.variableInterest);
    	form.line($.iup, $.bfp);
    	form.line(new SecurityTaxValueFormElement(Keys.getProperty($.taxValue)));
    	return form;
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}

}
