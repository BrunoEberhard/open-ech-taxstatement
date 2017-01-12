package org.minimalj.test;

import org.junit.Assert;
import org.junit.Test;
import org.minimalj.application.Application;
import org.minimalj.backend.Backend;

import ch.openech.TaxStatementApplication;
import ch.openech.backend.TaxStatementInHeapRepository;
import ch.openech.model.tax.SecuritySecurity;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementCrudTest {

	@Test
	public void testCrud() {
		Application.setThreadInstance(new TaxStatementApplication());
		Backend.getInstance().setRepository(new TaxStatementInHeapRepository());
		
		TaxStatement taxStatement = new TaxStatement();
		taxStatement.mock();
		Object id = Backend.insert(taxStatement);
		
		taxStatement = Backend.read(TaxStatement.class, id);
		
		SecuritySecurity security = new SecuritySecurity();
		security.mock();
		
		int countBefore = taxStatement.listOfSecurities.depot.get(0).security.size();
		taxStatement.listOfSecurities.depot.get(0).security.add(security);
		Assert.assertEquals(countBefore + 1, taxStatement.listOfSecurities.depot.get(0).security.size());
		
		Assert.assertEquals(0, taxStatement.listOfSecurities.depot.get(0).security.get(0).taxValue.size());
	}
}
