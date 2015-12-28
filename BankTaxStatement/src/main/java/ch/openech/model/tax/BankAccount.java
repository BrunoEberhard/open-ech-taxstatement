package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.minimalj.model.annotation.Size;
import org.minimalj.model.annotation.Sizes;
import org.minimalj.model.annotation.ViewReference;

import ch.openech.model.EchFormats;

@Sizes(EchFormats.class)
public class BankAccount {
	public Object id;
	
	public BankAccountTaxValue taxValue;
	
	@ViewReference
	public List<BankAccountPayment> payment;
	
	public String iban;  // not empty for bank account (may be empty for liability)
	public String bankAccountNumber;
	public String bankAccountName;
	
	@Size(EchFormats.countryIdISO2)
	public String bankAccountCountry;
	
	@Size(EchFormats.currencyIdISO3)
	public String bankAccountCurrency;
	
	public LocalDate openingDate, closingDate;
	
	@Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";
	
	public BigDecimal totalTaxValue;
	public BigDecimal totalGrossRevenueA; // empty for liability
	public BigDecimal totalGrossRevenueB;
	public BigDecimal totalWithHoldingTaxClaim;  // empty for liability

}
