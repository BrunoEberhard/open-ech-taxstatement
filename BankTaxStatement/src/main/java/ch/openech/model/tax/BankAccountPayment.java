package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;

public class BankAccountPayment {
	public static final BankAccountPayment $ = Keys.of(BankAccountPayment.class);

	public Object id;
	
	@NotEmpty
	public LocalDate paymentDate;
	
	@Size(255) // schema is missing size
	public String name;
	
	@Size(EchFormats.currencyIdISO3)
	public String amountCurrency;

	public BigDecimal amount;
	
	public BigDecimal exchangeRate;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public BigDecimal grossRevenueA;
	public BigDecimal grossRevenueB;
	public BigDecimal withHoldingTaxClaim;

	
}
