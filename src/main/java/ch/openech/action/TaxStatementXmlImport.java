package ch.openech.action;

import org.minimalj.frontend.Frontend;

import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.tax.TaxStatement;

public class TaxStatementXmlImport extends TaxStatementXmlEditor {

	public TaxStatementXmlImport() {
		super(null);
	}

	@Override
	protected XmlValue createObject() {
		XmlValue xmlValue = new XmlValue();
		xmlValue.xml = "";
		return xmlValue;
	}

	@Override
	protected void finished(TaxStatement result) {
		Frontend.show(new TaxStatementPage(result));
	}
}
