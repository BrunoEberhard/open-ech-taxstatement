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
	
	// only sec
	public List<SecurityPurchaseDisposition> purchase = new ArrayList<>();
	
	// only sec
	public SecurityPurchaseDisposition disposition;
	
	@NotEmpty
	public LocalDate paymentDate;

	public LocalDate exDate;
	
	@Size(255)
	public String name;
	
	public QuotationType quotationType;

	public BigDecimal quantity;
	
	// for lump: required, for sec: optional, default CHF
	@Size(EchFormats.currencyIdISO3)
	public String amountCurrency;
	
	public BigDecimal amount;
	public BigDecimal amountPerUnit;
	
	// for lump: required, for sec: optional
	public BigDecimal exchangeRate;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public BigDecimal grossRevenueA; // only sec
	public BigDecimal grossRevenueACanton; // only sec
	public BigDecimal grossRevenueB; // for lump: required, for sec: optional
	public BigDecimal grossRevenueBCanton; 
	public BigDecimal totalWithHoldingTaxClaim; // only sec
	
	public Boolean lumpSumTaxCredit; // only sec
	public BigDecimal lumpSumTaxCreditPercent;
	public BigDecimal lumpSumTaxCreditAmount;

	public BigDecimal nonRecoverableTaxPercent;
	public BigDecimal nonRecoverableTaxAmount;
	public BigDecimal additionalWithHoldingTaxUSA;
	
	// only sec
	public Boolean iup;
	public Boolean conversion;
	public Boolean gratis;
	public Boolean securitiesLending;
	public Boolean lendingFee;
	public Boolean retrocession;
	
	// all
	public Boolean undefined;
	public Boolean kursliste = true;
	public Boolean sign;
}
