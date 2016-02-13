package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;

public class SecurityPayment {
	public static final SecurityPayment $ = Keys.of(SecurityPayment.class);

	public Object id;
	
	public List<SecurityPurchaseDisposition> purchase = new ArrayList<>();
	
	public SecurityPurchaseDisposition disposition;
	
	@NotEmpty
	public LocalDate paymentDate;

	public LocalDate exDate;
	
	@Size(255)
	public String name;
	
	public QuotationType quotationType;

	public BigDecimal quantity;
	
	@Size(EchFormats.currencyIdISO3)
	public String amountCurrency;
	public BigDecimal amount;
	public BigDecimal amountPerUnit;
	
	public BigDecimal exchangeRate;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public BigDecimal grossRevenueA;
	public BigDecimal grossRevenueACanton;
	public BigDecimal grossRevenueB;
	public BigDecimal grossRevenueBCanton;
	public BigDecimal totalWithHoldingTaxClaim;
	public BigDecimal grossRevenueIUP;
	
	public Boolean lumpSumTaxCredit;
	public BigDecimal lumpSumTaxCreditPercent;
	public BigDecimal lumpSumTaxCreditAmount;

	public BigDecimal nonRecoverableTaxPercent;
	public BigDecimal nonRecoverableTaxAmount;
	public BigDecimal additionalWithHoldingTaxUSA;
	
	public Boolean iup;
	public Boolean conversion;
	public Boolean gratis;
	public Boolean securitiesLending;
	public Boolean lendingFee;
	public Boolean retrocession;
	public Boolean undefined;
	public Boolean kursliste = true;
	public Boolean sign;
}
