package ch.openech.action;

import org.minimalj.frontend.Frontend;

import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.read.StaxEch0196;

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
	protected TaxStatement save(XmlValue xmlValue) {
		TaxStatement taxStatement = new StaxEch0196().process(xmlValue.xml);
		return taxStatement;
	}

	@Override
	protected void finished(TaxStatement result) {
		Frontend.show(new TaxStatementPage(result));
	}
}
