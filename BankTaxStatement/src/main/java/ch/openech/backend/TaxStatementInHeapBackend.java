package ch.openech.backend;

import org.minimalj.backend.Backend;
import org.minimalj.transaction.PersistenceTransaction;
import org.minimalj.transaction.Transaction;

public class TaxStatementInHeapBackend extends Backend {

	private final TaxStatementInHeapPersistence persistence;

	public TaxStatementInHeapBackend() {
		this.persistence = new TaxStatementInHeapPersistence();
	}
	
	//

	@Override
	public <T> T doExecute(Transaction<T> transaction) {
		if (transaction instanceof PersistenceTransaction) {
			return persistence.execute((PersistenceTransaction<T>) transaction);
		} else {
			return transaction.execute();
		}
	}
	
}
