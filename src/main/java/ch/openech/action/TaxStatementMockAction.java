package ch.openech.action;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.action.Action;

import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementMockAction extends Action {

	@Override
	public void action() {
		TaxStatement taxStatement = new TaxStatement();
		taxStatement.mock();
		taxStatement = Backend.save(taxStatement);
		Frontend.show(new TaxStatementPage(taxStatement));
	}

}
