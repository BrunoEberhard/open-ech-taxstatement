package ch.openech.frontend.page;

import static ch.openech.model.tax.Expense.$;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;
import org.minimalj.util.IdUtils;

import ch.openech.frontend.e196.ExpenseForm;
import ch.openech.model.tax.Expense;
import ch.openech.model.tax.TaxStatement;

//die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
public class ExpenseTablePage extends SimpleTableEditorPage<Expense> {
	public static final Object[] COLUMNS = {$.referenceDate, $.name, $.amountCurrency, $.amount};

	private TaxStatement taxStatement;
	
	public ExpenseTablePage(TaxStatement taxStatement) {
		super(ExpenseTablePage.COLUMNS);
		this.taxStatement = taxStatement;
	}

	@Override
	protected List<Expense> load() {
		taxStatement = Backend.read(TaxStatement.class, IdUtils.getId(taxStatement));
		return taxStatement.listOfExpenses.expense;
	}

	@Override
	protected Form<Expense> createForm(boolean editable, boolean newObject) {
		return new ExpenseForm(editable);
	}
	
	@Override
	protected Expense save(Expense editedObject, Expense originalObject) {
		return Backend.save(editedObject);
	}
	
	@Override
	protected Expense save(Expense changedObject) {
		Expense savedObject = Backend.save(changedObject);
		taxStatement.listOfExpenses.expense.add(savedObject);
		Backend.save(taxStatement);
		return savedObject;
	}
	
	@Override
	public List<Action> getTableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new TableNewObjectEditor());
		return actions;
	}

	@Override
	protected ExpensePage getDetailPage(Expense expense) {
		return new ExpensePage(expense);
	}
}
