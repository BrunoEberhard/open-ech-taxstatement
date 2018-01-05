package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityStock.$;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;

import ch.openech.frontend.e196.SecurityStockForm;
import ch.openech.model.tax.SecuritySecurity;
import ch.openech.model.tax.SecurityStock;

//die TablePage könnten mit den neuen Unterklassen ab MJ 1.13.0.0 vereinfacht werden
public class SecurityStockTablePage extends SimpleTableEditorPage<SecurityStock> {
	public static final Object[] COLUMNS = {$.referenceDate, $.name, $.quantity};

	private SecuritySecurity security;
	
	public SecurityStockTablePage(SecuritySecurity security) {
		super(SecurityStockTablePage.COLUMNS);
		this.security = security;
	}
	
	public void setSecurity(SecuritySecurity security) {
		this.security = security;
		refresh();
	}	
	
	@Override
	protected List<SecurityStock> load() {
		return security.stock;
	}
	
	@Override
	public List<Action> getTableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new TableNewObjectEditor());
		return actions;
	}

	@Override
	protected SecurityStockPage getDetailPage(SecurityStock payment) {
		return new SecurityStockPage(payment);
	}

	@Override
	protected Form<SecurityStock> createForm(boolean editable, boolean newObject) {
		return new SecurityStockForm(editable);
	}

	@Override
	protected SecurityStock save(SecurityStock changedObject) {
		security.stock.add(changedObject);
		return changedObject;
	}

}
