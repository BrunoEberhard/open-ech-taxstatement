package ch.openech.action;

import java.io.StringWriter;

import org.minimalj.frontend.editor.Editor;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.StringFormElement;
import org.minimalj.model.Keys;
import org.minimalj.model.annotation.Size;

import ch.openech.action.TaxStatementXmlEditor.XmlValue;
import ch.openech.frontend.page.TaxStatementPage;
import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.read.StaxEch0196;
import ch.openech.xml.write.EchSchema;
import ch.openech.xml.write.WriterEch0196;

public class TaxStatementXmlEditor extends Editor<XmlValue, TaxStatement> {
	private final TaxStatementPage taxStatementPage;
	
	public TaxStatementXmlEditor(TaxStatementPage taxStatementPage) {
		this.taxStatementPage = taxStatementPage;
	}
	
	@Override
	protected Form<XmlValue> createForm() {
		Form<XmlValue> form = new Form<XmlValue>(false) {
			@Override
			protected int getColumnWidthPercentage() {
				return 400;
			}
		};
		form.line(new StringFormElement(Keys.getProperty(XmlValue.$.xml), true));
		return form;
	}
	
	public static class XmlValue {
		public static final XmlValue $ = Keys.of(XmlValue.class);
		
		@Size(1024*1024)
		public String xml;
	}

	@Override
	protected XmlValue createObject() {
		XmlValue xmlValue = new XmlValue();
		StringWriter stringWriter = new StringWriter();
		WriterEch0196 w = new WriterEch0196(stringWriter, EchSchema.getNamespaceContext(196, "1.0"));
		String s;
		try {
			w.write(taxStatementPage.getTaxStatement());
			s = stringWriter.toString();
		} catch (Exception x) {
			s = "Writer failed: " + x.getLocalizedMessage();
		}
		xmlValue.xml = s;
		return xmlValue;
	}
	
	@Override
	protected TaxStatement save(XmlValue xmlValue) {
		TaxStatement taxStatement = new StaxEch0196().process(xmlValue.xml);
		return taxStatement;
	}
	
	@Override
	protected void finished(TaxStatement result) {
		taxStatementPage.setTaxStatement(result);
	}
}