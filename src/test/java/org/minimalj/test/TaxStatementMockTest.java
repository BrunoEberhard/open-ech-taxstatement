package org.minimalj.test;

import org.junit.Assert;
import org.junit.Test;

import ch.openech.model.EchSchemaValidation;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.write.WriterEch0196;

public class TaxStatementMockTest {

	@Test
	public void testMock() {
		TaxStatement taxStatementOriginal = new TaxStatement();
		taxStatementOriginal.mock();
		
		String xml = WriterEch0196.writeToString(taxStatementOriginal);
		
		String validationMessage = EchSchemaValidation.validate(xml);
		Assert.assertTrue(validationMessage, EchSchemaValidation.OK.equals(validationMessage));
	}
}
