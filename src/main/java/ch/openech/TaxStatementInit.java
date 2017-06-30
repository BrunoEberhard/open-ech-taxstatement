package ch.openech;

import org.minimalj.backend.Backend;
import org.minimalj.transaction.Transaction;

import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.xml.read.StaxEch0071;
import ch.openech.xml.read.StaxEch0072;

public class TaxStatementInit implements Transaction<Void> {
	private static final long serialVersionUID = 1L;

	@Override
	public Void execute() {
		StaxEch0071 staxEch0071 = new StaxEch0071(getClass().getResourceAsStream("/eCH0071_canton.xml"));
		for (Canton canton : staxEch0071.getCantons()) {
			Backend.insert(canton);
		}

		StaxEch0072 staxEch0072 = new StaxEch0072(getClass().getResourceAsStream("/eCH0072.xml"));
		for (CountryIdentification country : staxEch0072.getCountryIdentifications()) {
			Backend.insert(country);
		}
		
		return null;
	}

}
