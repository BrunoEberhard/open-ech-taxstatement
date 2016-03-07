package ch.openech.frontend.e196;

import static ch.openech.model.tax.TaxStatement.*;

import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.FormElement;
import org.minimalj.frontend.form.element.TextFormElement;
import org.minimalj.model.Keys;

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
			line($.totalGrossRevenueA, $.totalGrossRevenueACanton);
			line($.totalGrossRevenueB, $.totalGrossRevenueBCanton);
			line($.totalTaxValue, $.totalWithHoldingTaxClaim);
			
			addTitle("Kontenverzeichnis");
			line($.listOfBankAccounts.totalGrossRevenueA, $.listOfBankAccounts.totalGrossRevenueB);
			line($.listOfBankAccounts.totalTaxValue, $.listOfBankAccounts.totalWithHoldingTaxClaim);
			line(new BankAccountsFormElement(Keys.getProperty($.listOfBankAccounts.bankAccount)));
			
			addTitle("Schuldenverzeichnis");
			line($.listOfLiabilities.totalGrossRevenueA, $.listOfLiabilities.totalGrossRevenueB);
			line($.listOfLiabilities.totalTaxValue, $.listOfLiabilities.totalWithHoldingTaxClaim);
			line(new BankAccountsFormElement(Keys.getProperty($.listOfLiabilities.bankAccount)));
			
			addTitle("Spesen");
			line($.listOfExpenses.currency, $.listOfExpenses.totalExpenses, $.listOfExpenses.totalExpensesDeductible, $.listOfExpenses.totalExpensesDeductibleCanton);
			line(new ExpensesFormElement(Keys.getProperty($.listOfExpenses.expense)));
			
			addTitle("Wertschriftenverzeichnis");
			line($.listOfSecurities.totalGrossRevenueA, $.listOfSecurities.totalGrossRevenueB);
			line($.listOfSecurities.totalTaxValue, $.listOfSecurities.totalWithHoldingTaxClaim);
			line($.listOfSecurities.totalGrossRevenueIUP, $.listOfSecurities.totalGrossRevenueConversion);
			line(new SecurityDepotsFormElement(Keys.getProperty($.listOfSecurities.depot)));
		}
	}
	
	public void line(FormElement<?> element) {
		if (editable) {
			super.line((Object) element);
		}
	}
}
