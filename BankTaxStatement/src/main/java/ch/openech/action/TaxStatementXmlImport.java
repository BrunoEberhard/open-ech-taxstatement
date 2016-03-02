package ch.openech.action;

import java.util.Collections;
import java.util.List;

import org.minimalj.backend.Backend;
import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.action.Action;
import org.minimalj.model.validation.ValidationMessage;
import org.minimalj.util.StringUtils;

import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.EchSchemaValidation;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.read.StaxEch0196;

public class TaxStatementXmlImport extends TaxStatementXmlEditor {

	public TaxStatementXmlImport() {
		super(null);
	}

	@Override
	protected List<Action> createAdditionalActions() {
		// No Demo - Action, even in Dev - Mode
		return Collections.emptyList();
	}

	@Override
	protected XmlValue createObject() {
		XmlValue xmlValue = new XmlValue();
		xmlValue.xml = "";
		return xmlValue;
	}

	@Override
	protected void validate(XmlValue value, List<ValidationMessage> validationMessages) {
		if (!StringUtils.isBlank(value.xml)) {
			String result = EchSchemaValidation.validate(value.xml);
			if (!EchSchemaValidation.OK.equals(result)) {
				validationMessages.add(new ValidationMessage(XmlValue.$.xml, result));
			}		
		}
	}
	
	@Override
	protected TaxStatement save(XmlValue xmlValue) {
		TaxStatement taxStatement = new StaxEch0196().process(xmlValue.xml);
		return Backend.save(taxStatement);
	}

	@Override
	protected void finished(TaxStatement result) {
		Frontend.show(new TaxStatementPage(result));
	}
}
