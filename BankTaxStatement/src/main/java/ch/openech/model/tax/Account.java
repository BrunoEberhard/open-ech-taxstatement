package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.Size;
import org.minimalj.model.annotation.Sizes;

import ch.openech.model.EchFormats;

@Sizes(EchFormats.class)
public class Account {
	public static final Account $ = Keys.of(Account.class);
	
	public static final boolean BANK_ACCOUNT = true;
	public static final boolean LIABILITY_ACCOUNT = false;
	
	public Object id;

	public BankAccountTaxValue taxValue;
	
	public List<AccountPayment> payment = new ArrayList<>();
	
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
	
	//
	
	public Boolean getHasTaxValue() {
		if (Keys.isKeyObject(this)) return Keys.methodOf(this, "hasTaxValue", Boolean.class);
		return taxValue != null;
	}

	public void setHasTaxValue(Boolean hasTaxValue) {
		if (Boolean.TRUE.equals(hasTaxValue)) {
			if (taxValue == null) {
				taxValue = new BankAccountTaxValue();
			}
		} else if (Boolean.FALSE.equals(hasTaxValue)) {
			taxValue = null;
		}
	}
	
}
