package ch.openech.frontend.e196;

import static ch.openech.model.tax.Expense.*;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.ListFormElement;
import org.minimalj.model.properties.PropertyInterface;

import ch.openech.model.tax.Expense;

public class ExpensesFormElement extends ListFormElement<Expense> {

	public ExpensesFormElement(PropertyInterface property) {
		super(property);
	}

	@Override
	protected void showEntry(Expense entry) {
		add(entry.currency + " " + entry.name, new ListEntryEditor(entry));
	}

	@Override
	protected Form<Expense> createForm(boolean edit) {
    	Form<Expense> form = new Form<Expense>(4);
    	form.line($.name, $.referenceDate);
    	form.line($.iban, $.bankAccountNumber);
    	form.line($.depotNumber);
    	
    	form.line($.amountCurrency, $.amount, $.exchangeRate);
    	
    	form.line($.currency, $.expenses);
    	
    	form.line($.expensesDeductible, $.expensesDeductibleCanton);
    	return form;
	}
	
	@Override
	protected Action[] getActions() {
		return new Action[] {
			new AddListEntryEditor()
		};
	}

}
