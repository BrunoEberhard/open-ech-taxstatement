package ch.openech.util.mock;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.AnnotationUtil;

import ch.openech.model.tax.Institution;

// https://www.snb.ch/de/iabout/stat/bchpub/id/statpub_bankench_hist
public class MockBank {

	private static List<String> names = new ArrayList<String>(300);
	private static int pos = -1;
	
	public static String getName() {
		if (names.isEmpty()) readNames();
		pos = (pos + 1) % names.size();
		return limitLength(names.get(pos), AnnotationUtil.getSize(Keys.getProperty(Institution.$.name)));
	}
	
	private static void readNames() {
		try {
			InputStream inputStream = MockBank.class.getResourceAsStream("/swiss_banks.txt");
			readNames(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void readNames(InputStream fis) throws Exception {
		Scanner scanner = new Scanner(fis, "UTF-8");
		scanner.useDelimiter("\n");
		while (scanner.hasNextLine()) {
			String name = scanner.nextLine();
			names.add(name);
		}
		Collections.shuffle(names);
		scanner.close();
	}
	
	private static String limitLength(String s, int maxLength) {
		return s.length() > maxLength ? s.substring(0, maxLength) : s;
	}
	
}
