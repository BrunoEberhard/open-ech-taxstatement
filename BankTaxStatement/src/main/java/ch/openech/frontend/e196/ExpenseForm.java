package ch.openech.frontend.e196;

import static ch.openech.model.tax.Expense.*;

import org.minimalj.frontend.form.Form;

import ch.openech.model.tax.Expense;

public class ExpenseForm extends Form<Expense> {

	public ExpenseForm(boolean editable) {
		super(editable, 4);
		
		line($.referenceDate, $.name);
		if (editable) {
			line($.expenseType);
			line($.iban, $.bankAccountNumber);
			line($.depotNumber);
		} else {
			line($.expenseType, $.iban, $.bankAccountNumber, $.depotNumber);
		}
		line($.amountCurrency, $.amount);
		line($.currency, $.exchangeRate);
		line($.expenses);
		line($.expensesDeductible, $.expensesDeductibleCanton);
	}

}
