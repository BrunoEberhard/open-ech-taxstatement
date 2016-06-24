/*
	Copyright (C) 2016, Bruno Eberhard, bruno.eberhard@pop.ch
	
	This file is part of Open-eCH.
	
	Open-eCH is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as
	published by the Free Software Foundation, either version 3 of the
	License, or (at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.
	
	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package ch.openech;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.minimalj.application.Application;
import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.action.ActionGroup;
import org.minimalj.frontend.page.HtmlPage;
import org.minimalj.frontend.page.Page;

import ch.openech.action.NewTaxStatementAction;
import ch.openech.action.TaxStatementMockAction;
import ch.openech.action.TaxStatementXmlImport;
import ch.openech.backend.TaxStatementInHeapPersistence;
import ch.openech.model.common.CountryIdentification;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementApplication extends Application {

	public TaxStatementApplication() {
		Backend.getInstance().setPersistence(new TaxStatementInHeapPersistence());
	}
	
	@Override
	protected Set<String> getResourceBundleNames() {
		Set<String> resourceBundleNames = new HashSet<>();
		resourceBundleNames.add(this.getClass().getName());
		resourceBundleNames.add(this.getClass().getName() + "_xml");
		resourceBundleNames.add("OpenEch");
		return resourceBundleNames;
	}
	
	@Override
	public List<Action> getNavigation() {
		ActionGroup actions = new ActionGroup("Steuerauszug");
		actions.add(new NewTaxStatementAction());
		actions.add(new TaxStatementXmlImport());
		actions.add(new TaxStatementMockAction());
		return Collections.singletonList(actions);
	}

	@Override
	public Class<?>[] getEntityClasses() {
		return new Class<?>[] { TaxStatement.class, CountryIdentification.class };
	}
	
	@Override
	public Page createDefaultPage() {
		return new HtmlPage("TaxStatement_Hilfe.html", "Hinweise zu Steuerauszug (Open-eCH)");
	}
}
