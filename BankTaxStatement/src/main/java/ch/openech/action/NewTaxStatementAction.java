package ch.openech.action;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.editor.Editor.SimpleEditor;
import org.minimalj.frontend.form.Form;

import ch.openech.model.tax.TaxStatement;

public class NewTaxStatementAction extends SimpleEditor<TaxStatement> {

	@Override
	protected TaxStatement createObject() {
		return new TaxStatement();
	}

	@Override
	protected Form<TaxStatement> createForm() {
		Form<TaxStatement> form = new Form<>(4);
		form.addTitle("Steuerauszug");
		form.line(TaxStatement.$.taxPeriod, TaxStatement.$.statementId, TaxStatement.$.minorVersion, TaxStatement.$.creationDate);
		form.line(TaxStatement.$.periodFrom, TaxStatement.$.periodTo, TaxStatement.$.canton);
		form.line(TaxStatement.$.instituion.name, TaxStatement.$.instituion.lei, TaxStatement.$.instituion.uid.value);
		form.addTitle("Details");
		
		form.addTitle("Zusammenfassung");
		form.line(TaxStatement.$.totalGrossRevenueA, TaxStatement.$.totalGrossRevenueACanton);
		form.line(TaxStatement.$.totalGrossRevenueB, TaxStatement.$.totalGrossRevenueBCanton);
		form.line(TaxStatement.$.totalTaxValue, TaxStatement.$.totalWithHoldingTaxClaim);
//		form.addTitle("Kontenverzeichnis");
		
		return form;
	}

	@Override
	protected TaxStatement save(TaxStatement taxStatement) {
		Backend.insert(taxStatement);
		return taxStatement;
	}


}
