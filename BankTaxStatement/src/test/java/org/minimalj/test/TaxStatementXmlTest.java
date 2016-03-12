package org.minimalj.test;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.openech.backend.TaxStatementInHeapBackend;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.read.StaxEch0196;
import ch.openech.xml.write.WriterEch0196;
import junit.framework.Assert;

public class TaxStatementXmlTest {

	@BeforeClass
	public static void setupPersistence() {
		System.setProperty("MjBackend", TaxStatementInHeapBackend.class.getName());
	}
	
	@Test
	public void testXmlConversion() {
		TaxStatement taxStatementOriginal = new TaxStatement();
		taxStatementOriginal.mock();
		
		String xml1 = WriterEch0196.writeToString(taxStatementOriginal);
		
		TaxStatement taxStatementRead = new StaxEch0196().process(xml1);
		
		String xml2 = WriterEch0196.writeToString(taxStatementRead);
		
		Assert.assertEquals("Writer should write the same after reading its own output", xml1, xml2);
	}
	
}
