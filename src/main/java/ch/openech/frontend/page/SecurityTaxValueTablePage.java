package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityTaxValue.$;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;

import ch.openech.frontend.e196.SecurityTaxValueForm;
import ch.openech.model.tax.SecuritySecurity;
import ch.openech.model.tax.SecurityTaxValue;

//die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
public class SecurityTaxValueTablePage extends TablePageWithDetail<SecurityTaxValue, SecurityTaxValuePage> {
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
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewSecurityTaxValueEditor());
		return actions;
	}

	@Override
	protected SecurityTaxValuePage createDetailPage(SecurityTaxValue taxValue) {
		return new SecurityTaxValuePage(taxValue);
	}
	
	@Override
	protected SecurityTaxValuePage updateDetailPage(SecurityTaxValuePage page, SecurityTaxValue payment) {
		page.setObject(payment);
		return page;
	}
	
	public class NewSecurityTaxValueEditor extends NewDetailEditor {
		@Override
		protected Form<SecurityTaxValue> createForm() {
			return new SecurityTaxValueForm(Form.EDITABLE);
		}		
		
		@Override
		protected SecurityTaxValue save(SecurityTaxValue changedObject) {
			security.taxValue.add(changedObject);
			return changedObject;
		}
	}
}
