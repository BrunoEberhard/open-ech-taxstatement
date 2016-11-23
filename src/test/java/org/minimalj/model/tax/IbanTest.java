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
			Assert.assertTrue(iban.validateNullSafe().isEmpty());
		}
	}
	
	@Test
	public void testValidExample() {
		Iban iban = new Iban();
		iban.number = "DE68 2105 0170 0012 3456 78";
		Assert.assertTrue(iban.validateNullSafe().isEmpty());
	}

	@Test
	public void testinvalidExample() {
		Iban iban = new Iban();
		iban.number = "DE68 2105 0170 0012 3456 79";
		Assert.assertFalse(iban.validateNullSafe().isEmpty());
	}

	@Test
	public void testTooShort() {
		Iban iban = new Iban();
		iban.number = "DE6";
		Assert.assertFalse(iban.validateNullSafe().isEmpty());
	}

	@Test
	public void testWrongFormat() {
		Iban iban = new Iban();
		iban.number = "DE68 2105 0170 0012 3456 6A";
		Assert.assertFalse(iban.validateNullSafe().isEmpty());
		iban.number = "DEA8 2105 0170 0012 3456 79";
		Assert.assertFalse(iban.validateNullSafe().isEmpty());
	}

}
