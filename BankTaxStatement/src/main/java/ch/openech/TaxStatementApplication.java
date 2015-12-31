package ch.openech;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.application.Application;
import org.minimalj.frontend.action.Action;

import ch.openech.action.NewTaxStatementAction;
import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.model.tax.BankAccount;
import ch.openech.model.tax.BankAccountPayment;
import ch.openech.model.tax.Expense;
import ch.openech.model.tax.SecurityDepot;
import ch.openech.model.tax.SecurityPayment;
import ch.openech.model.tax.SecurityPurchaseDisposition;
import ch.openech.model.tax.SecuritySecurity;
import ch.openech.model.tax.SecurityStock;
import ch.openech.model.tax.SecurityTaxValue;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementApplication extends Application {

	@Override
	public List<Action> getNavigation() {
		List<Action> navigation = new ArrayList<>();
		navigation.add(new NewTaxStatementAction());
		return navigation;
	}

	@Override
	public Class<?>[] getEntityClasses() {
		return new Class<?>[] { TaxStatement.class, BankAccount.class, BankAccountPayment.class, Expense.class, SecurityDepot.class,
				SecuritySecurity.class, SecurityTaxValue.class, SecurityPayment.class, SecurityStock.class, SecurityPurchaseDisposition.class, CountryIdentification.class, Canton.class };
	}
}
