package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;
import org.minimalj.model.annotation.Sizes;
import org.minimalj.model.annotation.ViewReference;

import ch.openech.model.EchFormats;
import ch.openech.model.organisation.UidStructure;

@Sizes(EchFormats.class)
public class SecuritySecurity {

	public Object id;
	
	public final List<SecurityTaxValue> taxValue = new ArrayList<>();

	@ViewReference
	public List<SecurityPayment> payment;

	public final List<SecurityStock> stock = new ArrayList<>();

	public UidStructure uid;
	
	public String valorNumber;
	public String isin;
	
	@Size(EchFormats.countryIdISO2)
	public String country = "CH";
	
	@Size(EchFormats.town)
	public String city;

	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public String quotationType;
	
	public BigDecimal nominalValue;
	
	public String securityCategory = "SHARE";
	public String securityType;
	
	@NotEmpty
	public String securityName;
	
	public LocalDate issueDate;
	public LocalDate redemptionDate, redemptionDateEarly;
	
	public BigDecimal issuePrice;
	
	public BigDecimal redemptionPrice, rdemptionPriceEarly;
	
	public BigDecimal interestRate;
	
	public Boolean variableInterest;
	
	public Boolean iup;
	
	public Boolean bfp;

}
