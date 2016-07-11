package ch.openech.model.tax;

import java.math.BigDecimal;

import org.minimalj.model.validation.Validatable;
import org.minimalj.util.StringUtils;
import org.minimalj.util.mock.Mocking;

public class Iban implements Validatable, Mocking {

	public String number;

	@Override
	public String validate() {
		if (StringUtils.isEmpty(number)) {
			return null;
		}
		
		if (number.length() < 12) {
			return "Ungültiges Format";
		}
		
		for (int i = 0; i<number.length(); i++) {
			char c = number.charAt(i);
			if (i < 2) {
				if (!(c >= 'A' && c <= 'Z')) {
					return "Ungültiges Format";
				}
			} else {
				if (!(Character.isDigit(c) || c == ' ')) {
					return "Ungültiges Format";
				}
			}
		}

		String countryAtEnd = number.substring(4) + number.substring(0, 4);
		int checksum = checksum(countryAtEnd);
		
		if (checksum != 1) {
			return "Ungültige IBAN";
		}
		
		return null;
	}

	private static BigDecimal DIVISOR_97 = BigDecimal.valueOf(97);
	
	private int checksum(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				sb.append(Integer.toString(10 + c - 'A'));
			} else if (Character.isDigit(c)) {
				sb.append(c);
			}
		}
		BigDecimal value = new BigDecimal(sb.toString());
		return value.remainder(DIVISOR_97).intValue();
	}

	@Override
	public void mock() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<22; i++) {
			if (i > 0 && i % 5 == 0) {
				sb.append(' ');
			} else {
				sb.append("" + (int)(Math.random() * 10.0));
			}
		}
		int remainder = checksum(sb.toString() + "CH00");
		int checksum = 98 - remainder;
		
		this.number = "CH" + (checksum < 10 ? "0" : "") + checksum + " " + sb.toString();
	}
	
}
