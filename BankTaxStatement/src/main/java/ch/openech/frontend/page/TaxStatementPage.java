package ch.openech.frontend.page;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.editor.Editor.SimpleEditor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.DetailPageAction;
import org.minimalj.frontend.page.ObjectPage;
import org.minimalj.frontend.page.PageAction;
import org.minimalj.util.CloneHelper;

import ch.openech.action.TaxStatementXmlEditor;
import ch.openech.frontend.e196.TaxStatementForm;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementPage extends ObjectPage<TaxStatement> {

	public TaxStatementPage(TaxStatement taxStatement) {
		super(taxStatement);
	}
	
	@Override
	protected Form<TaxStatement> createForm() {
		return new TaxStatementForm(Form.READ_ONLY);
	}
	
	@Override
	public List<Action> getActions() {
		TaxStatement taxStatement = getObject();
		List<Action> actions = new ArrayList<>();
		actions.add(new TaxStatementEditor());
		actions.add(new DetailPageAction(this, new BankAccountTablePage(taxStatement)));
		actions.add(new DetailPageAction(this, new LiabilityAccountTablePage(taxStatement)));
		actions.add(new DetailPageAction(this, new SecurityDepotTablePage(taxStatement, SecurityDepotTablePage.SECURITIES)));
		actions.add(new DetailPageAction(this, new ExpenseTablePage(taxStatement)));

		// Im moment wird das spezielle Anzeigen oder sogar editieren der Pauschalabgabe nicht unterstützt
		// Es könnte Sinn machen Differenzen zwischen Wertschriftenverzeichnis / Pauschalabgabe im XML
		// als Use Case anzubieten. Aber momentan lasse ich dies out of scope
		// actions.add(new DetailPageAction(this, new SecurityDepotTablePage(taxStatement, SecurityDepotTablePage.LUMP)));
		actions.add(new PageAction(new TaxStatementXmlPage(taxStatement)));
		actions.add(new TaxStatementXmlEditor(this));

		return actions;
	}
	
	public class TaxStatementEditor extends SimpleEditor<TaxStatement> {

		@Override
		protected TaxStatement createObject() {
			return CloneHelper.clone(TaxStatementPage.this.getObject());
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
			TaxStatementPage.this.setObject(result);
		}
	}
}
