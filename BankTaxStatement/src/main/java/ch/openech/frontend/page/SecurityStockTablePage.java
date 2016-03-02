package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityStock.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;

import ch.openech.frontend.e196.SecurityStockForm;
import ch.openech.model.tax.SecuritySecurity;
import ch.openech.model.tax.SecurityStock;

public class SecurityStockTablePage extends TablePageWithDetail<SecurityStock, SecurityStockPage> {
	public static final Object[] COLUMNS = {$.referenceDate, $.name, $.quantity};

	private final SecuritySecurity security;
	
	public SecurityStockTablePage(SecuritySecurity security) {
		super(SecurityStockTablePage.COLUMNS);
		this.security = security;
	}
	
	@Override
	protected List<SecurityStock> load() {
		return security.stock;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewBankSecurityStockEditor());
		return actions;
	}

	@Override
	protected SecurityStockPage createDetailPage(SecurityStock payment) {
		return new SecurityStockPage(payment);
	}
	
	@Override
	protected SecurityStockPage updateDetailPage(SecurityStockPage page, SecurityStock payment) {
		page.setObject(payment);
		return page;
	}
	
	public class NewBankSecurityStockEditor extends NewDetailEditor<SecurityStock> {
		@Override
		protected Form<SecurityStock> createForm() {
			return new SecurityStockForm(Form.EDITABLE);
		}		
		
		@Override
		protected SecurityStock save(SecurityStock changedObject) {
			security.stock.add(changedObject);
			return changedObject;
		}
	}
}
