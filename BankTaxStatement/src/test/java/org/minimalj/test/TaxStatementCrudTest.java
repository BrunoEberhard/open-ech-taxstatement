package org.minimalj.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.minimalj.backend.Persistence;
import org.minimalj.transaction.criteria.By;

import ch.openech.backend.TaxStatementInHeapPersistence;
import ch.openech.model.common.Canton;
import ch.openech.model.tax.SecuritySecurity;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.read.StaxEch0071;
import junit.framework.Assert;

public class TaxStatementCrudTest {

	private static Persistence persistence;

	@BeforeClass
	public static void setupPersistence() {
		// persistence = new SqlPersistence(SqlPersistence.embeddedDataSource(), TaxStatement.class);
		persistence = new TaxStatementInHeapPersistence();
		if (persistence.read(Canton.class, By.all(), 1).isEmpty()) {
			StaxEch0071 staxEch0071 = new StaxEch0071(TaxStatementInHeapPersistence.class.getClassLoader().getResourceAsStream("eCH0071_canton.xml"));
			for (Canton canton : staxEch0071.getCantons()) {
				persistence.insert(canton);
			}
		}
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
