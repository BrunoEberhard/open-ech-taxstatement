package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;

public class SecurityStock {
	public static final SecurityStock $ = Keys.of(SecurityStock.class);

	public Object id;
	
	@NotEmpty
	public LocalDate referenceDate;
	
	@Size(255)
	public String name;
	
	public Boolean mutation;
	
	public QuotationType quotationType;
	
	public BigDecimal quantity;
	
	@Size(EchFormats.currencyIdISO3)
	public String balanceCurrency;

	public BigDecimal unitPrice;
	
	public BigDecimal balance;
	
	public BigDecimal reductionCost;
	
	public BigDecimal exchangeRate;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public BigDecimal value;

	public Boolean blocked;
	public LocalDate blockingTo;

}
