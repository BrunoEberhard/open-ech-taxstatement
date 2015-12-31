package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;
import org.minimalj.model.annotation.Sizes;

import ch.openech.model.EchFormats;

@Sizes(EchFormats.class)
public class Expense {
	public static final Expense $ = Keys.of(Expense.class);

	public Object id;
	
	public LocalDate referenceDate;
	
	@Size(255) // schema is missing size
	public String name;
	
	public String iban;
	public String bankAccountNumber;
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
	
}
