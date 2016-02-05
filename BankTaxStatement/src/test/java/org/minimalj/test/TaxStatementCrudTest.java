package org.minimalj.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.minimalj.backend.sql.SqlPersistence;

import ch.openech.model.tax.SecuritySecurity;
import ch.openech.model.tax.TaxStatement;
import junit.framework.Assert;

public class TaxStatementCrudTest {

	private static SqlPersistence persistence;

	@BeforeClass
	public static void setupPersistence() {
		persistence = new SqlPersistence(SqlPersistence.embeddedDataSource(), TaxStatement.class);
	}

	@Test
	public void testCrud() {
		TaxStatement taxStatement = new TaxStatement();
		taxStatement.mock();
		Object id = persistence.insert(taxStatement);
		
		taxStatement = persistence.read(TaxStatement.class, id);
		
		SecuritySecurity security = new SecuritySecurity();
		security.mock();
		
		int countBefore = taxStatement.listOfSecurities.depot.get(0).security.size();
		taxStatement.listOfSecurities.depot.get(0).security.add(security);
		Assert.assertEquals(countBefore + 1, taxStatement.listOfSecurities.depot.get(0).security.size());
		
		Assert.assertEquals(0, taxStatement.listOfSecurities.depot.get(0).security.get(0).taxValue.size());
	}
}
