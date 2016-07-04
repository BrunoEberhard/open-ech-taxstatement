package org.minimalj.test;

import org.junit.Test;
import org.minimalj.application.Application;
import org.minimalj.backend.Backend;

import ch.openech.TaxStatementApplication;
import ch.openech.backend.TaxStatementInHeapPersistence;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.read.StaxEch0196;
import ch.openech.xml.write.WriterEch0196;
import junit.framework.Assert;

public class TaxStatementXmlTest {

	@Test
	public void testXmlConversion() {
		Application.setThreadInstance(new TaxStatementApplication());
		Backend.getInstance().setPersistence(new TaxStatementInHeapPersistence());
		
		TaxStatement taxStatementOriginal = new TaxStatement();
		taxStatementOriginal.mock();
		
		String xml1 = WriterEch0196.writeToString(taxStatementOriginal);
		
		TaxStatement taxStatementRead = new StaxEch0196().process(xml1);
		
		String xml2 = WriterEch0196.writeToString(taxStatementRead);
		
		Assert.assertEquals("Writer should write the same after reading its own output", xml1, xml2);
	}
	
}
