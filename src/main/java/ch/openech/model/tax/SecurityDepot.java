package ch.openech.model.tax;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;
import org.minimalj.util.mock.Mocking;

import ch.openech.model.EchFormats;

public class SecurityDepot implements Mocking {
	public static final SecurityDepot $ = Keys.of(SecurityDepot.class);

	public Object id;
	
	public List<SecuritySecurity> security = new ArrayList<>();

	@NotEmpty @Size(EchFormats.depotNumber)
	public String depotNumber;
	
	@Override
	public void mock() {
		Random random = new Random();
		depotNumber = "Depot " + String.valueOf(Math.abs(random.nextInt()) % 900000000 + 100000000);
		
		for (int j = 0; j < 1 + random.nextInt(3); j++) {
			SecuritySecurity security = new SecuritySecurity();
			security.mock();
			this.security.add(security);
		}		
	}
}
