package ch.openech.frontend;

import static ch.openech.model.tax.TaxStatement.*;

import org.minimalj.frontend.editor.Wizard;
import org.minimalj.frontend.editor.WizardStep;
import org.minimalj.frontend.form.Form;

import ch.openech.model.tax.TaxStatement;

public class TaxStatementWizard extends Wizard<TaxStatement> {

	private TaxStatement taxStatement = new TaxStatement();
	
	@Override
	protected WizardStep<?> getFirstStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TaxStatement save() {
		// TODO Auto-generated method stub
		return null;
	}

	public class TaxStatementStep implements WizardStep<TaxStatement> {
		@Override
		public Form<TaxStatement> createForm() {
			Form<TaxStatement> form = new Form<>(4);
			form.line($.taxPeriod, $.statementId, $.minorVersion, $.creationDate);
			form.line($.periodFrom, $.periodTo, $.canton);
			form.line($.institution.name, $.institution.lei, $.institution.uid.value);
			return form;
		}
		
		@Override
		public TaxStatement createObject() {
			return TaxStatementWizard.this.taxStatement;
		}

		@Override
		public String getTitle() {
			return "Bankauszug";
		}

		@Override
		public WizardStep<?> getNextStep() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public WizardStep<?> getPreviousStep() {
			return null;
		}
	}
}
