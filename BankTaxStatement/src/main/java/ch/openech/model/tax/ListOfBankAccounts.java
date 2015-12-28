package ch.openech.model.tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.Keys;
import org.minimalj.model.Rendering;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.ViewReference;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;

/**
 * Kontenverzeichnis
 */
public class ListOfBankAccounts implements Rendering {
	public static final ListOfBankAccounts $ = Keys.of(ListOfBankAccounts.class);
	
	@ViewReference
	public final List<BankAccount> bankAccount = new ArrayList<>();
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";
	
	public BigDecimal totalTaxValue;
	public BigDecimal totalGrossRevenueA;
	public BigDecimal totalGrossRevenueB;
	public BigDecimal totalWithHoldingTaxClaim;
	
	@Override
	public String render(RenderType renderType) {
		StringBuffer s = new StringBuffer(200);
		TaxRenderUtils.appendMoney(s, this, $.totalTaxValue, currency).append("\n");
		TaxRenderUtils.appendMoney(s, this, $.totalGrossRevenueA, currency).append("\n");
		TaxRenderUtils.appendMoney(s, this, $.totalGrossRevenueB, currency).append("\n");
		TaxRenderUtils.appendMoney(s, this, $.totalWithHoldingTaxClaim, currency);
		return s.toString();
	}
	
}
