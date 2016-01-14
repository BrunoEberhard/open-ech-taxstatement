package ch.openech;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.minimalj.application.Application;
import org.minimalj.frontend.action.Action;

import ch.openech.action.TaxStatementEditor;
import ch.openech.action.TaxStatementMockAction;
import ch.openech.action.TaxStatementXmlImport;
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

//	private static final TaxStatement TaxStatement = new TaxStatement();
//	
//	public static TaxStatement getTaxStatement() {
//		return TaxStatement;
//	}
	
	@Override
	protected Set<String> getResourceBundleNames() {
		Set<String> resourceBundleNames = new HashSet<>();
		resourceBundleNames.add(this.getClass().getName());
		resourceBundleNames.add(this.getClass().getName() + "_xml");
		return resourceBundleNames;
	}

//	@Override
//	public Page createDefaultPage() {
//		return new TaxStatementPage(getTaxStatement());
//	}
	
	@Override
	public List<Action> getNavigation() {
		List<Action> navigation = new ArrayList<>();
		navigation.add(new TaxStatementEditor("Neuer Steuerauszug erstellen"));
		navigation.add(new TaxStatementXmlImport());
		navigation.add(new TaxStatementMockAction("Steuerauszug aus Zufallswerten"));

//		navigation.add(new TaxStatementEditor(getTaxStatement()));
//		navigation.add(new PageAction(new TaxStatementPage(getTaxStatement())));
//		navigation.add(new PageAction(new BankAccountTablePage(getTaxStatement())));
//		navigation.add(new PageAction(new LiabilityAccountTablePage(getTaxStatement())));
//		navigation.add(new PageAction(new SecurityDepotTablePage(getTaxStatement())));
//		navigation.add(new PageAction(new TaxStatementXmlPage(getTaxStatement())));
		return navigation;
	}

	@Override
	public Class<?>[] getEntityClasses() {
		return new Class<?>[] { TaxStatement.class, BankAccount.class, BankAccountPayment.class, Expense.class, SecurityDepot.class,
				SecuritySecurity.class, SecurityTaxValue.class, SecurityPayment.class, SecurityStock.class, SecurityPurchaseDisposition.class, CountryIdentification.class, Canton.class };
	}
}
