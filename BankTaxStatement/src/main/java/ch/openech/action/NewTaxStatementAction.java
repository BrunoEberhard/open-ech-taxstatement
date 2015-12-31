package ch.openech.action;

import static ch.openech.model.tax.TaxStatement.*;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.editor.Editor.SimpleEditor;
import org.minimalj.frontend.form.Form;
import org.minimalj.model.Keys;

import ch.openech.frontend.e196.BankAccountsFormElement;
import ch.openech.frontend.e196.ExpensesFormElement;
import ch.openech.frontend.e196.SecurityDepotsFormElement;
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
		form.line($.taxPeriod, $.statementId, $.minorVersion, $.creationDate);
		form.line($.periodFrom, $.periodTo, $.canton);
		form.line($.instituion.name, $.instituion.lei, $.instituion.uid.value);
		
		form.addTitle("Zusammenfassung");
		form.line($.totalGrossRevenueA, $.totalGrossRevenueACanton);
		form.line($.totalGrossRevenueB, $.totalGrossRevenueBCanton);
		form.line($.totalTaxValue, $.totalWithHoldingTaxClaim);

		form.addTitle("Kontenverzeichnis");
		form.line($.listOfBankAccounts.totalGrossRevenueA, $.listOfBankAccounts.totalGrossRevenueB);
		form.line($.listOfBankAccounts.totalTaxValue, $.listOfBankAccounts.totalWithHoldingTaxClaim);
		form.line(new BankAccountsFormElement(Keys.getProperty($.listOfBankAccounts.bankAccount)));
		
		form.addTitle("Schuldenverzeichnis");
		form.line($.listOfLiabilities.totalGrossRevenueA, $.listOfLiabilities.totalGrossRevenueB);
		form.line($.listOfLiabilities.totalTaxValue, $.listOfLiabilities.totalWithHoldingTaxClaim);
		form.line(new BankAccountsFormElement(Keys.getProperty($.listOfLiabilities.bankAccount)));

		form.addTitle("Spesen");
		form.line($.listOfExpenses.currency, $.listOfExpenses.totalExpenses, $.listOfExpenses.totalExpensesDeductible, $.listOfExpenses.totalExpensesDeductibleCanton);
		form.line(new ExpensesFormElement(Keys.getProperty($.listOfExpenses.expense)));
		
		form.addTitle("Wertschriftenverzeichnis");
		form.line(new SecurityDepotsFormElement(Keys.getProperty($.listOfSecurities.depot)));
		form.line($.listOfSecurities.totalGrossRevenueA, $.listOfSecurities.totalGrossRevenueB);
		form.line($.listOfSecurities.totalTaxValue, $.listOfSecurities.totalWithHoldingTaxClaim);
		form.line($.listOfSecurities.totalGrossRevenueIUP, $.listOfSecurities.totalGrossRevenueConversion);
		
		return form;
	}

	@Override
	protected TaxStatement save(TaxStatement taxStatement) {
		Backend.insert(taxStatement);
		return taxStatement;
	}


}
