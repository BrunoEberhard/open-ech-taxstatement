package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityDepot.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.editor.Editor.NewObjectEditor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage.TablePageWithDetail;
import org.minimalj.util.resources.Resources;

import ch.openech.model.tax.SecurityDepot;
import ch.openech.model.tax.TaxStatement;

public class SecurityDepotTablePage extends TablePageWithDetail<SecurityDepot, SecurityTablePage> {

	public static final boolean SECURITIES = true;
	public static final boolean LUMP = false;
	
	private static final Object[] columns = {$.depotNumber};
	private final TaxStatement taxStatement;
	private final boolean securityDepotType;
	
	public SecurityDepotTablePage(TaxStatement taxStatement, boolean securityDepotType) {
		super(columns);
		this.taxStatement = taxStatement;
		this.securityDepotType = securityDepotType;
	}
	
	@Override
	public String getTitle() {
		return Resources.getString((securityDepotType ? "Security" : "Lump") + getClass().getSimpleName());
	}
	
	@Override
	protected List<SecurityDepot> load() {
		return securityDepotType ? taxStatement.listOfSecurities.depot : taxStatement.listOfLumpSumTaxCredit.depot;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		if (securityDepotType) {
			actions.add(new NewSecurityDepotEditor());
		}
		return actions;
	}
	
	@Override
	protected SecurityTablePage createDetailPage(SecurityDepot depot) {
		return new SecurityTablePage(depot, securityDepotType);
	}
	
	@Override
	protected SecurityTablePage updateDetailPage(SecurityTablePage page, SecurityDepot depot) {
		page.setDepot(depot);
		return page;
	}
	
	public class NewSecurityDepotEditor extends NewObjectEditor<SecurityDepot> {
		@Override
		protected Form<SecurityDepot> createForm() {
	    	Form<SecurityDepot> form = new Form<SecurityDepot>(1);
	    	form.line($.depotNumber);
	    	return form;
		}		
		
		@Override
		protected SecurityDepot save(SecurityDepot newObject) {
			taxStatement.listOfSecurities.depot.add(newObject);
			return newObject;
		}
		
		@Override
		protected void finished(SecurityDepot result) {
			SecurityDepotTablePage.this.refresh();
		}
	}
}
