package ch.openech.model.tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;

public class ListOfExpenses {

	public List<Expense> expense = new ArrayList<>();
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";
	
	public BigDecimal totalExpenses;
	public BigDecimal totalExpensesDeductible;
	public BigDecimal totalExpensesDeductibleCanton;
	
}
