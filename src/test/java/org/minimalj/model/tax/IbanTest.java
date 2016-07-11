package org.minimalj.model.tax;

import org.junit.Assert;
import org.junit.Test;

import ch.openech.model.tax.Iban;

public class IbanTest {

	@Test
	public void testMock() {
		for (int i = 0; i<100; i++) {
			Iban iban = new Iban();
			iban.mock();
			Assert.assertTrue("Mock generates invalid iban", iban.validate() == null);
		}
	}
	
	@Test
	public void testValidExample() {
		Iban iban = new Iban();
		iban.number = "DE68 2105 0170 0012 3456 78";
		Assert.assertNull(iban.validate());
	}

	@Test
	public void testinvalidExample() {
		Iban iban = new Iban();
		iban.number = "DE68 2105 0170 0012 3456 79";
		Assert.assertNotNull(iban.validate() == null);
	}

	@Test
	public void testTooShort() {
		Iban iban = new Iban();
		iban.number = "DE6";
		Assert.assertNotNull(iban.validate() == null);
	}

	@Test
	public void testWrongFormat() {
		Iban iban = new Iban();
		iban.number = "DE68 2105 0170 0012 3456 6A";
		Assert.assertNotNull(iban.validate() == null);
		iban.number = "DEA8 2105 0170 0012 3456 79";
		Assert.assertNotNull(iban.validate() == null);
	}

}
