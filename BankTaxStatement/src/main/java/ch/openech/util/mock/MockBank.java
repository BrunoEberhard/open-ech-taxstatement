package ch.openech.util.mock;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// https://www.snb.ch/de/iabout/stat/bchpub/id/statpub_bankench_hist
public class MockBank {

	private static List<String> names = new ArrayList<String>(300);
	private static int pos = -1;
	
	public static String getName() {
		if (names.isEmpty()) readNames();
		pos = (pos + 1) % names.size();
		return names.get(pos);
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
	
}
