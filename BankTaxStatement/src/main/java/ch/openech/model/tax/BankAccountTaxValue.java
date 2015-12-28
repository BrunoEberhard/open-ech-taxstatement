package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;

public class BankAccountTaxValue {

	@NotEmpty
	public LocalDate referenceDate;
	
	@Size(255) // schema is missing size
	public String name;
	
	@Size(EchFormats.currencyIdISO3)
	public String balanceCurrency;

	public BigDecimal balance;
	
	public BigDecimal exchangeRate;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public BigDecimal value;

}
