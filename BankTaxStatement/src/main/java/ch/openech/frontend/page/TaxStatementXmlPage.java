package ch.openech.frontend.page;

import java.io.StringWriter;

import org.minimalj.frontend.Frontend.IContent;
import org.minimalj.frontend.form.Form;
import org.minimalj.frontend.form.element.StringFormElement;
import org.minimalj.frontend.page.Page;
import org.minimalj.model.Keys;
import org.minimalj.model.annotation.Size;

import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.write.EchSchema;
import ch.openech.xml.write.WriterEch0196;

public class TaxStatementXmlPage extends Page {
	private final TaxStatement taxStatement;
	
	public TaxStatementXmlPage(TaxStatement taxStatement) {
		this.taxStatement = taxStatement;
	}
	
	@Override
	public IContent getContent() {
		Form<XmlValue> form = new Form<XmlValue>(false) {
			@Override
			protected int getColumnWidthPercentage() {
				return 400;
			}
		};
		form.line(new StringFormElement(Keys.getProperty(XmlValue.$.xml), true));
		XmlValue xmlValue = new XmlValue();

		
		StringWriter stringWriter = new StringWriter();
		WriterEch0196 w = new WriterEch0196(stringWriter, EchSchema.getNamespaceContext(196, "1.0"));
		String s;
		try {
			w.write(taxStatement);
			s = stringWriter.toString();
		} catch (Exception x) {
			s = "Writer failed: " + x.getLocalizedMessage();
		}

		xmlValue.xml = s;
		form.setObject(xmlValue);
		return form.getContent();
	}
	
	public static class XmlValue {
		public static final XmlValue $ = Keys.of(XmlValue.class);
		
		@Size(1024*1024)
		public String xml;
	}
}
