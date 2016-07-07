package ch.openech.model.tax;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;

public class SecurityDepot {
	public static final SecurityDepot $ = Keys.of(SecurityDepot.class);

	public Object id;
	
	public List<SecuritySecurity> security = new ArrayList<>();

	@NotEmpty @Size(EchFormats.depotNumber)
	public String depotNumber;
}
