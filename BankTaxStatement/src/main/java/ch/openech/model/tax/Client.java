package ch.openech.model.tax;

import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;

import ch.openech.model.EchFormats;
import ch.openech.model.types.MrMrs;

public class Client {
	
	// The extension elements are ignored at the moment
	// @Size(1024) public byte[] other;

	@NotEmpty @Size(EchFormats.clientNumber)
	public String clientNumber;
	
	@Size(EchFormats.tin) // min size: 9
	public String tin;
	
	public MrMrs salutation;
	
	@NotEmpty @Size(EchFormats.firstName)
	public String firstName;
	
	@NotEmpty @Size(EchFormats.lastName)
	public String lastName;

}
