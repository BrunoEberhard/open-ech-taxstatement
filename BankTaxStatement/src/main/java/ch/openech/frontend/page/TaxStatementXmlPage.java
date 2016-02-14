package ch.openech.frontend.page;

import java.io.StringWriter;

import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.Frontend.IContent;
import org.minimalj.frontend.page.Page;
import org.minimalj.util.StringUtils;

import ch.openech.model.tax.TaxStatement;
import ch.openech.xml.write.EchSchema;
import ch.openech.xml.write.WriterEch0196;

// HtmlPage cannot be used because it would immediately load all data
public class TaxStatementXmlPage extends Page {
	
	private final TaxStatement taxStatement;
	
	public TaxStatementXmlPage(TaxStatement taxStatement) {
		this.taxStatement = taxStatement;
	}

	@Override
	public IContent getContent() {
		return Frontend.getInstance().createHtmlContent(createHtml(taxStatement));
	}
	
	@Override
	public String getTitle() {
		return "XML";
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
