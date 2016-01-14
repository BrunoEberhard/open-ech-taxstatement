package ch.openech.action;

import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.editor.Editor.SimpleEditor;
import org.minimalj.frontend.form.Form;

import ch.openech.frontend.e196.TaxStatementForm;
import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.tax.TaxStatement;

public class NewTaxStatementAction extends SimpleEditor<TaxStatement> {

	@Override
	protected TaxStatement createObject() {
		return new TaxStatement();
	}

	@Override
	protected Form<TaxStatement> createForm() {
		return new TaxStatementForm(Form.EDITABLE);
	}

	@Override
	protected TaxStatement save(TaxStatement taxStatement) {
		// Backend.insert(taxStatement);
		return taxStatement;
	}

	@Override
	protected void finished(TaxStatement result) {
		Frontend.show(new TaxStatementPage(result));
	}
}
