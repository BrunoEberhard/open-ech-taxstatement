package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;
import org.minimalj.model.annotation.Sizes;
import org.minimalj.util.mock.Mocking;

import ch.openech.model.EchFormats;
import ch.openech.model.organisation.UidStructure;

@Sizes(EchFormats.class)
public class SecuritySecurity implements Mocking {
	public static final SecuritySecurity $ = Keys.of(SecuritySecurity.class);

	public Object id;
	
	public List<SecurityTaxValue> taxValue = new ArrayList<>();
	
	public List<SecurityPayment> payment = new ArrayList<>();

	// not for lump
	public List<SecurityStock> stock = new ArrayList<>();

	// not for lump
	public UidStructure uid;
	
	public String valorNumber;
	public String isin;
	
	// for lump: required, for sec: optional, default CH
	@Size(EchFormats.countryIdISO2)
	public String country = "CH";
	
	@Size(EchFormats.town)
	public String city;

	// for lump: required, for sec: optional, default CHF
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	public String quotationType;
	
	public BigDecimal nominalValue;
	
	public String securityCategory = "SHARE";
	public String securityType;
	
	// sec only
	
	@NotEmpty
	public String securityName;
	
	public LocalDate issueDate;
	public LocalDate redemptionDate, redemptionDateEarly;
	
	public BigDecimal issuePrice;
	
	public BigDecimal redemptionPrice, redemptionPriceEarly;
	
	public BigDecimal interestRate;
	
	public Boolean variableInterest;
	
	public Boolean iup;
	
	public Boolean bfp;
	
	@Override
	public void mock() {
		securityName = "" + Math.random();
	}

}
