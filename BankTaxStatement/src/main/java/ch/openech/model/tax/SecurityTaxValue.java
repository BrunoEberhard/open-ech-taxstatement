package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;

public class SecurityTaxValue {
	
	@NotEmpty
	public LocalDate referenceDate;
	
	@Size(255) // schema is missing size
	public String name;
	
	public static enum QuotationType {
		PIECE, PERCENT;
	}
	
	public QuotationType quotationType;
	
	public BigDecimal quantity;
	
	@Size(EchFormats.currencyIdISO3)
	public String balanceCurrency;

	public BigDecimal unitPrice;
	
	public BigDecimal balance;
	
	public BigDecimal exchangeRate;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public BigDecimal value;

	public Boolean blocked;
	public LocalDate blockingTo;

	public Boolean undefined;
	
	public Boolean kursliste;

}
