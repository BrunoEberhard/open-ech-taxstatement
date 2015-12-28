package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;
import ch.openech.model.tax.SecurityTaxValue.QuotationType;

public class SecurityPurchaseDisposition {
	
	@NotEmpty
	public LocalDate referenceDate;
	
	public QuotationType quotationType;

	public BigDecimal quantity;
	
	@Size(EchFormats.currencyIdISO3)
	public String balanceCurrency;

	public BigDecimal bondFloor;
	
	public BigDecimal unitPrice;
	
	public BigDecimal balance;
	
	public BigDecimal reductionCost;
	
	public BigDecimal exchangeRate;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public BigDecimal value;
}
