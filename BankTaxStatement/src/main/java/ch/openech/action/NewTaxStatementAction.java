package ch.openech.action;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.editor.Editor.NewObjectEditor;
import org.minimalj.frontend.form.Form;

import ch.openech.frontend.e196.TaxStatementForm;
import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.tax.TaxStatement;

public class NewTaxStatementAction extends NewObjectEditor<TaxStatement> {

	@Override
	protected Form<TaxStatement> createForm() {
		return new TaxStatementForm(Form.EDITABLE);
	}

	@Override
	protected TaxStatement save(TaxStatement taxStatement) {
		return Backend.save(taxStatement);
	}

	@Override
	protected void finished(TaxStatement result) {
		Frontend.show(new TaxStatementPage(result));
	}
}
