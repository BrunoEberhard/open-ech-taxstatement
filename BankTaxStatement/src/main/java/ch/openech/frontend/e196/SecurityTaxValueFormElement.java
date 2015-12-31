package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecurityTaxValue.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.SecurityTaxValue;

public class SecurityTaxValueFormElement extends ListFormElement<SecurityTaxValue> {

	public SecurityTaxValueFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(SecurityTaxValue entry) {
		add(entry.name, new ListEntryEditor(entry));
	}

	@Override
	protected Form<SecurityTaxValue> createForm(boolean edit) {
    	Form<SecurityTaxValue> form = new Form<SecurityTaxValue>(4);
    	form.line($.name);
    	form.line($.quotationType);   	
    	form.line($.quantity);   	
    	form.line($.balanceCurrency);
    	form.line($.unitPrice, $.balance);
    	form.line($.exchangeRate);
    	form.line($.currency, $.value);
    	form.line($.blocked, $.blockingTo);
    	form.line($.undefined, $.kursliste);
    	return form;
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}

}
