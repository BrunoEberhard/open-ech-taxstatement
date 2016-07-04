package ch.openech.frontend.e196;

import static ch.openech.model.tax.TaxStatement.*;

import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.TextFormElement;

import ch.openech.frontend.e07.CantonFormElement;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementForm extends Form<TaxStatement> {

	public TaxStatementForm(boolean editable) {
		super(editable, 4);
		
		addTitle("Steuerauszug");
		line($.taxPeriod, $.statementId, $.minorVersion, $.creationDate);
		line($.periodFrom, $.periodTo, editable ? new CantonFormElement($.canton) : new TextFormElement($.canton));
		line($.institution.name, $.institution.lei, $.institution.uid.value);

		addTitle("Kunde");
		line($.getMainClient().clientNumber, $.getMainClient().tin);
		line($.getMainClient().salutation, $.getMainClient().firstName, $.getMainClient().lastName);
		
		if (!editable) {
			addTitle("Zusammenfassung");
			line($.totalGrossRevenueA, $.totalGrossRevenueACanton, $.totalGrossRevenueB, $.totalGrossRevenueBCanton);
			line($.totalTaxValue, $.totalWithHoldingTaxClaim);
			
			addTitle("Kontenverzeichnis");
			line($.listOfBankAccounts.totalGrossRevenueA, $.listOfBankAccounts.totalGrossRevenueB);
			line($.listOfBankAccounts.totalTaxValue, $.listOfBankAccounts.totalWithHoldingTaxClaim);
			
			addTitle("Schuldenverzeichnis");
			line($.listOfLiabilities.totalGrossRevenueB);
			line($.listOfLiabilities.totalTaxValue);
			
			addTitle("Spesen");
			line($.listOfExpenses.totalExpenses, $.listOfExpenses.totalExpensesDeductible, $.listOfExpenses.totalExpensesDeductibleCanton);
			
			addTitle("Wertschriftenverzeichnis");
			line($.listOfSecurities.totalGrossRevenueA, $.listOfSecurities.totalGrossRevenueACanton, $.listOfSecurities.totalGrossRevenueB, $.listOfSecurities.totalGrossRevenueBCanton);
			line($.listOfSecurities.totalTaxValue, $.listOfSecurities.totalWithHoldingTaxClaim);
			line($.listOfSecurities.totalGrossRevenueIUP, $.listOfSecurities.totalGrossRevenueConversion);
		}
	}

}
