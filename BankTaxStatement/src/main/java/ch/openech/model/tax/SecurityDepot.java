package ch.openech.model.tax;

import java.util.List;

import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;
import org.minimalj.model.annotation.ViewReference;

import ch.openech.model.EchFormats;

public class SecurityDepot {

	public Object id;

	@ViewReference
	public List<SecuritySecurity> security;

	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";
	
	@NotEmpty @Size(EchFormats.depotNumber)
	public String depotNumber;
}
