package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.ObjectPage;

import ch.openech.frontend.e196.ExpenseForm;
import ch.openech.model.tax.Expense;

public class ExpensePage extends ObjectPage<Expense> {
	
	public ExpensePage(Expense expense) {
		super(expense);
	}

	@Override
	protected Form<Expense> createForm() {
		return new ExpenseForm(Form.READ_ONLY);
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new ExpenseEditor());
		return actions;
	}

	public class ExpenseEditor extends ObjectEditor {

		@Override
		protected Form<Expense> createForm() {
			return new ExpenseForm(Form.EDITABLE);
		}

		@Override
		protected Expense save(Expense object) {
			return Backend.save(object);
		}
		
	}

}