package ch.openech.action;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.transaction.Transaction;

import ch.openech.backend.TaxStatementInHeapPersistence;
import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.xml.read.StaxEch0071;
import ch.openech.xml.read.StaxEch0072;

public class TaxStatementInitialAction extends Action {

	
	public class ImportSwissDataTransaction implements Transaction<Void> {
		private static final long serialVersionUID = 1L;

		public ImportSwissDataTransaction() {
		}

		@Override
		public Void execute() {
			StaxEch0072 staxEch0072 = new StaxEch0072(TaxStatementInHeapPersistence.class.getClassLoader().getResourceAsStream("eCH0072.xml"));
			for (CountryIdentification country : staxEch0072.getCountryIdentifications()) {
				Backend.insert(country);
			}
			
			StaxEch0071 staxEch0071 = new StaxEch0071(TaxStatementInHeapPersistence.class.getClassLoader().getResourceAsStream("eCH0071_canton.xml"));
			for (Canton canton : staxEch0071.getCantons()) {
				Backend.insert(canton);
			}
			return null;
		}
		
	}

	@Override
	public void action() {
		Backend.execute(new ImportSwissDataTransaction());
	}
	
}
