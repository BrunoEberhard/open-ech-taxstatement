package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecurityStock.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.SecurityStock;

public class SecurityStockFormElement extends ListFormElement<SecurityStock> {

	public SecurityStockFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(SecurityStock entry) {
		add(entry.name, new ListEntryEditor(entry));
	}

	@Override
	protected Form<SecurityStock> createForm(boolean edit) {
    	Form<SecurityStock> form = new Form<SecurityStock>(4);
    	form.line($.name);
    	form.line($.quotationType);   	
    	form.line($.mutation);   	
    	form.line($.quantity, $.unitPrice);   	
    	form.line($.balanceCurrency, $.balance);
    	form.line($.reductionCost);    	
    	form.line($.exchangeRate);
    	form.line($.currency, $.value);
    	form.line($.blocked, $.blockingTo);
    	return form;
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}

}
