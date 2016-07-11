package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.Enabled;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;
import org.minimalj.model.annotation.Sizes;

import ch.openech.model.EchFormats;

@Sizes(EchFormats.class)
public class Expense {
	public static final Expense $ = Keys.of(Expense.class);

	public static enum ExpenseType { ACCOUNT, DEPOT };
	public ExpenseType expenseType = ExpenseType.ACCOUNT;
	
	public Object id;
	
	public LocalDate referenceDate;
	
	@Size(255) @NotEmpty // schema is missing size
	public String name;
	
	@Enabled("isBankAccount")
	public String iban, bankAccountNumber;

	@Enabled("!isBankAccount")
	public String depotNumber;
	
	@Size(EchFormats.currencyIdISO3)
	public String amountCurrency;

	public BigDecimal amount;
	
	public BigDecimal exchangeRate;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public BigDecimal expenses;
	public BigDecimal expensesDeductible;
	public BigDecimal expensesDeductibleCanton;
	
	//

	public boolean isBankAccount() {
		return expenseType == ExpenseType.ACCOUNT;
	}
	
//	public Boolean getTypeAccount() {
//		if (Keys.isKeyObject(this)) return Keys.methodOf(this, "getTypeAccount", Boolean.class);
//		updateExpenseType();
//		return expenseType == ExpenseType.ACCOUNT;
//	}
//
//	public Boolean getTypeDepot() {
//		if (Keys.isKeyObject(this)) return Keys.methodOf(this, "getTypeDepot", Boolean.class);
//		updateExpenseType();
//		return expenseType == ExpenseType.DEPOT;
//	}
//
//	public void updateExpenseType() {
//		if (!StringUtils.isEmpty(depotNumber)) {
//			expenseType = ExpenseType.DEPOT;
//		} else {
//			expenseType = ExpenseType.ACCOUNT;
//		}
//	}
//
//	public void setTypeAccount(Boolean typeAccount) {
//	}
	
}
