package ch.openech.action;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.editor.Editor.SimpleEditor;
import org.minimalj.frontend.form.Form;

import ch.openech.frontend.e196.TaxStatementForm;
import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementEditor extends SimpleEditor<TaxStatement> {

	private final TaxStatement taxStatement;

	public TaxStatementEditor(TaxStatement taxStatement) {
		this.taxStatement = taxStatement;
	}
	
	@Override
	protected TaxStatement createObject() {
		return taxStatement;
	}

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
