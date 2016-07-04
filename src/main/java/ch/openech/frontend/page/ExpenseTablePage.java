package ch.openech.frontend.page;

import static ch.openech.model.tax.Expense.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;

import ch.openech.frontend.e196.ExpenseForm;
import ch.openech.model.tax.Expense;
import ch.openech.model.tax.TaxStatement;

public class ExpenseTablePage extends TablePageWithDetail<Expense, ExpensePage> {
	public static final Object[] COLUMNS = {$.referenceDate, $.name, $.amountCurrency, $.amount};

	private final TaxStatement taxStatement;
	
	public ExpenseTablePage(TaxStatement taxStatement) {
		super(ExpenseTablePage.COLUMNS);
		this.taxStatement = taxStatement;
	}

	@Override
	protected List<Expense> load() {
		return taxStatement.listOfExpenses.expense;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewBankExpenseEditor());
		return actions;
	}

	@Override
	protected ExpensePage createDetailPage(Expense expense) {
		return new ExpensePage(expense);
	}
	
	@Override
	protected ExpensePage updateDetailPage(ExpensePage page, Expense expense) {
		page.setObject(expense);
		return page;
	}
	
	public class NewBankExpenseEditor extends NewDetailEditor<Expense> {
		@Override
		protected Form<Expense> createForm() {
			return new ExpenseForm(Form.EDITABLE);
		}		
		
		@Override
		protected Expense save(Expense changedObject) {
			taxStatement.listOfExpenses.expense.add(changedObject);
			return changedObject;
		}
	}
}
