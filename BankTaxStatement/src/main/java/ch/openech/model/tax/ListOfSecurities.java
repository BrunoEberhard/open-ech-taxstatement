package ch.openech.model.tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.annotation.Size;
import org.minimalj.model.annotation.ViewReference;

import ch.openech.model.EchFormats;

public class ListOfSecurities {

	@ViewReference
	public List<SecurityDepot> depot = new ArrayList<>();

	@Size(EchFormats.currencyIdISO3)
	public String currency;
	
	public BigDecimal totalTaxValue;
	public BigDecimal totalGrossRevenueA, totalGrossRevenueACanton;
	public BigDecimal totalGrossRevenueB, totalGrossRevenueBCanton;
	public BigDecimal totalWithHoldingTaxClaim;
	public BigDecimal totalGrossRevenueIUP;
	public BigDecimal totalGrossRevenueConversion;
	

}
