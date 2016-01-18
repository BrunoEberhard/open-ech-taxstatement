package ch.openech.model.tax;

import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;
import ch.openech.model.organisation.UidStructure;

public class Institution {

	@NotEmpty
	public final UidStructure uid = new UidStructure();

	// The extension elements are ignored at the moment
	// @Size(1024) public byte[] otherId;
	
	// TODO validate: if not empty -> size == 20
	@Size(20)
	public String lei;
	
	@NotEmpty @Size(EchFormats.organisationName)
	public String name;

	// The extension elements are ignored at the moment
	// @Size(1024) public byte[] other;
}
