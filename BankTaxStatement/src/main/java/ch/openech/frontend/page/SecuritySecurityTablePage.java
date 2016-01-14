package ch.openech.frontend.page;

import java.util.List;

import org.minimalj.frontend.page.TablePage;

import ch.openech.model.tax.SecuritySecurity;

public class SecuritySecurityTablePage extends TablePage<SecuritySecurity> {

	private final List<SecuritySecurity> securities;
	
	public SecuritySecurityTablePage(List<SecuritySecurity> securities) {
		super(new Object[] { SecuritySecurity.$.securityName });
		this.securities = securities;
	}

	@Override
	protected List<SecuritySecurity> load() {
		return securities;
	}

}
