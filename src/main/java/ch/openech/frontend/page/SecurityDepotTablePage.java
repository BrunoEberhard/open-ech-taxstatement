package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityDepot.$;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.SimpleTableEditorPage;
import org.minimalj.util.IdUtils;
import org.minimalj.util.resources.Resources;

import ch.openech.model.tax.SecurityDepot;
import ch.openech.model.tax.TaxStatement;

public class SecurityDepotTablePage extends SimpleTableEditorPage<SecurityDepot> {

	public static final boolean SECURITIES = true;
	public static final boolean LUMP = false;
	
	private static final Object[] columns = {$.depotNumber};
	private TaxStatement taxStatement;
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
		taxStatement = Backend.read(TaxStatement.class, IdUtils.getId(taxStatement));
		return securityDepotType ? taxStatement.listOfSecurities.depot : taxStatement.listOfLumpSumTaxCredit.depot;
	}
	
	@Override
	protected Form<SecurityDepot> createForm(boolean editable, boolean newObject) {
    	Form<SecurityDepot> form = new Form<SecurityDepot>(editable);
    	form.line($.depotNumber);
    	return form;
	}
	
	@Override
	protected SecurityDepot save(SecurityDepot editedObject, SecurityDepot originalObject) {
		return Backend.save(editedObject);
	}
	
	@Override
	protected SecurityDepot save(SecurityDepot changedObject) {
		SecurityDepot savedObject = Backend.save(changedObject);
		taxStatement.listOfSecurities.depot.add(savedObject);
		Backend.save(taxStatement);
		return savedObject;
	}
	
	@Override
	public List<Action> getTableActions() {
		List<Action> actions = new ArrayList<>();
		if (securityDepotType) {
			actions.add(new TableNewObjectEditor());
		}
		return actions;
	}
	
	@Override
	protected SecurityTablePage getDetailPage(SecurityDepot depot) {
		return new SecurityTablePage(depot, securityDepotType);
	}
	
}
