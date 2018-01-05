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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.minimalj.application.Application;
import org.minimalj.application.Configuration;
import org.minimalj.backend.Backend;
import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.action.ActionGroup;
import org.minimalj.frontend.impl.nanoserver.NanoWebServer;
import org.minimalj.frontend.page.HtmlPage;
import org.minimalj.frontend.page.Page;
import org.minimalj.repository.memory.InMemoryRepository;
import org.minimalj.repository.query.By;
import org.minimalj.util.resources.MultiResourceBundle;

import ch.openech.action.NewTaxStatementAction;
import ch.openech.action.TaxStatementInitialAction;
import ch.openech.action.TaxStatementMockAction;
import ch.openech.action.TaxStatementXmlImport;
import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementApplication extends Application {

	public TaxStatementApplication() {
		if (!Configuration.available("MjRepository")) {
			Configuration.set("MjRepository", InMemoryRepository.class.getName());
		}
		Configuration.set("MjInit", TaxStatementInit.class.getName());
	}
	
	@Override
	public ResourceBundle getResourceBundle(Locale locale) {
		List<ResourceBundle> resourceBundles = new ArrayList<>();
		resourceBundles.add(ResourceBundle.getBundle(this.getClass().getName(), locale));
		resourceBundles.add(ResourceBundle.getBundle(this.getClass().getName()+ "_xml", locale));
		resourceBundles.add(ResourceBundle.getBundle("OpenEch", locale));
		return new MultiResourceBundle(resourceBundles);
	}

	@Override
	public List<Action> getNavigation() {
		ActionGroup actions = new ActionGroup("Steuerauszug");
		actions.add(new NewTaxStatementAction());
		actions.add(new TaxStatementXmlImport());
		actions.add(new TaxStatementMockAction());

		// Nach dem Start fehlen die Kantone und Länder in der Datenbank
		if (Backend.find(Canton.class, By.all()).isEmpty()) {
			actions.add(new TaxStatementInitialAction());
		}
		
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
	
	public static void main(String[] args) {
		NanoWebServer.start(new TaxStatementApplication());
	}
}
