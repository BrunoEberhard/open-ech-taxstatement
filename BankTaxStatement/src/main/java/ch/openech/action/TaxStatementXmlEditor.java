package ch.openech.action;

import java.util.Collections;
import java.util.List;

import org.minimalj.frontend.action.Action;
import org.minimalj.frontend.editor.Editor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.StringFormElement;
import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;
import org.minimalj.model.validation.ValidationMessage;
import org.minimalj.util.StringUtils;

import ch.openech.action.TaxStatementXmlEditor.XmlValue;
import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.EchSchemaValidation;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.read.StaxEch0196;
import ch.openech.xml.write.WriterEch0196;

public class TaxStatementXmlEditor extends Editor<XmlValue, TaxStatement> {
	private final TaxStatementPage taxStatementPage;
	
	public TaxStatementXmlEditor(TaxStatementPage taxStatementPage) {
		this.taxStatementPage = taxStatementPage;
	}
	
	@Override
	protected Form<XmlValue> createForm() {
		Form<XmlValue> form = new Form<XmlValue>(true) {
			@Override
			protected int getColumnWidthPercentage() {
				return 400;
			}
		};
		form.line(XmlValue.$.requireValid);
		form.line(new StringFormElement(Keys.getProperty(XmlValue.$.xml), true));
		return form;
	}
	
	@Override
	protected List<Action> createAdditionalActions() {
		// No Demo - Action, even in Dev - Mode
		return Collections.emptyList();
	}
	
	public static class XmlValue {
		public static final XmlValue $ = Keys.of(XmlValue.class);
		
		public Boolean requireValid = true;
		
		@Size(1024*1024) @NotEmpty
		public String xml;
	}


	@Override
	protected void validate(XmlValue value, List<ValidationMessage> validationMessages) {
		if (value.requireValid && !StringUtils.isBlank(value.xml)) {
			String result = EchSchemaValidation.validate(value.xml);
			if (!EchSchemaValidation.OK.equals(result)) {
				validationMessages.add(new ValidationMessage(XmlValue.$.xml, result));
			}		
		}
	}
	
	@Override
	protected XmlValue createObject() {
		XmlValue xmlValue = new XmlValue();
		String s;
		try {
			s = WriterEch0196.writeToString(taxStatementPage.getObject());
		} catch (Exception x) {
			s = "Writer failed: " + x.getLocalizedMessage();
		}
		xmlValue.xml = s;
		boolean validAtStart = EchSchemaValidation.OK.equals(EchSchemaValidation.validate(s));
		xmlValue.requireValid = validAtStart;
		return xmlValue;
	}
	
	@Override
	protected TaxStatement save(XmlValue xmlValue) {
		TaxStatement taxStatement = new StaxEch0196().process(xmlValue.xml);
		return taxStatement;
	}
	
	@Override
	protected void finished(TaxStatement result) {
		taxStatementPage.setObject(result);
	}
}
