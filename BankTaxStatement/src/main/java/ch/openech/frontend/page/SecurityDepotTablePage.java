package ch.openech.frontend.page;

import static ch.openech.model.tax.SecurityDepot.*;

import java.util.ArrayList;
import java.util.List;

import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.editor.Editor.NewObjectEditor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.page.TablePage;

import ch.openech.model.tax.SecurityDepot;
import ch.openech.model.tax.TaxStatement;

public class SecurityDepotTablePage extends TablePage<SecurityDepot> {

	private static final Object[] columns = {$.depotNumber, $.currency};
	private final TaxStatement taxStatement;
	
	public SecurityDepotTablePage(TaxStatement taxStatement) {
		super(columns);
		this.taxStatement = taxStatement;
	}

	@Override
	protected List<SecurityDepot> load() {
		return taxStatement.listOfSecurities.depot;
	}
	
	@Override
	public List<Action> getActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new NewSecurityDepotEditor());
		return actions;
	}
	
	@Override
	public void action(SecurityDepot depot) {
		Frontend.showDetail(this, new SecurityTablePage(depot));
	}
	
	public class NewSecurityDepotEditor extends NewObjectEditor<SecurityDepot> {
		@Override
		protected Form<SecurityDepot> createForm() {
	    	Form<SecurityDepot> form = new Form<SecurityDepot>(1);
	    	form.line($.depotNumber);
	    	form.line($.currency);
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
