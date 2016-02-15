package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.Frontend.IContent;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.editor.Editor.SimpleEditor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.DetailPageAction;
import org.minimalj.frontend.page.Page;
import org.minimalj.frontend.page.PageAction;
import org.minimalj.util.CloneHelper;

import ch.openech.frontend.e196.TaxStatementForm;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementPage extends Page {

	private final TaxStatement taxStatement;
	private TaxStatementForm form;
	
	public TaxStatementPage(TaxStatement taxStatement) {
		this.taxStatement = taxStatement;
	}
	
	public TaxStatement getTaxStatement() {
		return taxStatement;
	}
	
	public void setTaxStatement(TaxStatement taxStatement) {
		CloneHelper.deepCopy(taxStatement, this.taxStatement);
		form.setObject(taxStatement);
	}

	@Override
	public IContent getContent() {
		form = new TaxStatementForm(Form.READ_ONLY);
		form.setObject(taxStatement);
		return form.getContent();
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new TaxStatementEditor());
		actions.add(new DetailPageAction(this, new BankAccountTablePage(taxStatement)));
		actions.add(new DetailPageAction(this, new LiabilityAccountTablePage(taxStatement)));
		actions.add(new DetailPageAction(this, new SecurityDepotTablePage(taxStatement)));
		actions.add(new DetailPageAction(this, new SecurityDepotTablePage(taxStatement)));
		actions.add(new PageAction(new TaxStatementXmlPage(taxStatement)));
//		actions.add(new TaxStatementXmlEditor(this));

		return actions;
	}
	
	public class TaxStatementEditor extends SimpleEditor<TaxStatement> {

		@Override
		protected TaxStatement createObject() {
			return CloneHelper.clone(TaxStatementPage.this.taxStatement);
		}

		@Override
		protected Form<TaxStatement> createForm() {
			return new TaxStatementForm(Form.EDITABLE);
		}

		@Override
		protected TaxStatement save(TaxStatement taxStatement) {
			CloneHelper.deepCopy(taxStatement, TaxStatementPage.this.taxStatement);
			return TaxStatementPage.this.taxStatement;
		}

		@Override
		protected void finished(TaxStatement result) {
			form.setObject(result);
		}
	}
}
