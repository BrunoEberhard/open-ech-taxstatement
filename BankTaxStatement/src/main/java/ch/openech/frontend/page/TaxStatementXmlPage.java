package ch.openech.frontend.page;

import java.io.StringWriter;

import org.minimalj.frontend.page.HtmlPage;
import org.minimalj.util.StringUtils;

import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.write.EchSchema;
import ch.openech.xml.write.WriterEch0196;

public class TaxStatementXmlPage extends HtmlPage {
	
	public TaxStatementXmlPage(TaxStatement taxStatement) {
		super(createHtml(taxStatement), "XML");
	}

	private static String createHtml(TaxStatement taxStatement) {
		StringWriter stringWriter = new StringWriter();
		WriterEch0196 w = new WriterEch0196(stringWriter, EchSchema.getNamespaceContext(196, "1.0"));
		try {
			w.write(taxStatement);
			return "<html><body><pre>\n" + StringUtils.escapeHTML(stringWriter.toString()) + "\n</pre></body></html>";
		} catch (Exception x) {
			return "Writer failed: " + x.getLocalizedMessage();
		}
	}
}
