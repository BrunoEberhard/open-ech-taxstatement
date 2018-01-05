package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityTaxValue.$;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;

import ch.openech.frontend.e196.SecurityTaxValueForm;
import ch.openech.model.tax.SecuritySecurity;
import ch.openech.model.tax.SecurityTaxValue;

//die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
public class SecurityTaxValueTablePage extends SimpleTableEditorPage<SecurityTaxValue> {
	public static final Object[] COLUMNS = {$.referenceDate, $.name, $.quantity};

	private SecuritySecurity security;
	
	public SecurityTaxValueTablePage(SecuritySecurity security) {
		super(SecurityTaxValueTablePage.COLUMNS);
		this.security = security;
	}
	
	public void setSecurity(SecuritySecurity security) {
		this.security = security;
		refresh();
	}		
	
	@Override
	protected List<SecurityTaxValue> load() {
		return security.taxValue;
	}
	
	@Override
	protected Form<SecurityTaxValue> createForm(boolean editable, boolean newObject) {
		return new SecurityTaxValueForm(editable);
	}
	
	@Override
	public List<Action> getTableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new TableNewObjectEditor());
		return actions;
	}

	@Override
	protected SecurityTaxValuePage getDetailPage(SecurityTaxValue taxValue) {
		return new SecurityTaxValuePage(taxValue);
	}
	
}
