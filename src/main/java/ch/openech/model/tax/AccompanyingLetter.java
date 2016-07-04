package ch.openech.model.tax;

import org.minimalj.model.annotation.Size;

/**
 * Begleitschreiben
 *
 */
public class AccompanyingLetter {

	// The extension elements are ignored at the moment
	// @Size(1024) public byte[] other;

	@Size(200)
	public String fileName;
	public Integer fileSize;
	public byte[] fileData;
}
