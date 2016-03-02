package ch.openech.frontend.e196;

import static ch.openech.model.tax.SecurityDepot.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.SecurityDepot;

public class SecurityDepotsFormElement extends ListFormElement<SecurityDepot> {

	public SecurityDepotsFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(SecurityDepot entry) {
		add(entry.depotNumber, new ListEntryEditor(entry));
	}

	@Override
	protected Form<SecurityDepot> createForm(boolean edit) {
    	Form<SecurityDepot> form = new Form<SecurityDepot>(4);
    	form.line($.depotNumber);
    	return form;
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}

}
