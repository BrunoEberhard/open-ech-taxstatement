package ch.openech.action;

import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.action.Action;

import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementMockAction extends Action {

	public TaxStatementMockAction(String name) {
		super(name);
	}
	
	@Override
	public void action() {
		TaxStatement taxStatement = new TaxStatement();
		taxStatement.mock();
		Frontend.show(new TaxStatementPage(taxStatement));
	}

}
