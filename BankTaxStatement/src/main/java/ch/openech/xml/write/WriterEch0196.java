package ch.openech.xml.write;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.properties.Properties;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.util.FieldUtils;

import ch.openech.model.organisation.UidStructure;
import ch.openech.model.tax.TaxStatement;
import ch.openech.model.types.EchCode;

public class WriterEch0196 extends WriterElement {

	protected final EchSchema context;
	
	public final String URI;
	public final WriterEch0007 ech7;
	public final WriterEch0097 ech97;
	
	public WriterEch0196(Writer writer, EchSchema context) {
		super(writer, context.getNamespaceURI(196));
		
		this.context = context;
		
		URI = context.getNamespaceURI(196);
		ech7 = new WriterEch0007(context);
		ech97 = new WriterEch0097(context);
	}
	
	public String getURI() {
		return URI;
	}

	public void write(TaxStatement taxStatement) {
		try {
			startDocument(context, 196, "taxStatement");
			write(this, taxStatement, URI);
			endDocument();
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}

	private void write(WriterElement child, Object object, String URI) throws Exception {
		List<PropertyInterface> elementProperties = new ArrayList<>();
		for (PropertyInterface property : Properties.getProperties(object.getClass()).values()) {
			if (FieldUtils.isAllowedPrimitive(property.getClazz())) {
				String name = property.getName();
				Object value = property.getValue(object);
				if (name.equals("statementId")) {
					name = "id";
				}
				child.writeAttribute(name, value != null ? value.toString() : "");
			} else if (EchCode.class.isAssignableFrom(property.getClazz())) {
				String name = property.getName();
				EchCode code = (EchCode) property.getValue(object);
				child.writeAttribute(name, code != null ? code.getValue() : "");
			} else {
				elementProperties.add(property);
			}
		}
		for (PropertyInterface property : elementProperties) {
			String name = property.getName();
			Object value = property.getValue(object);

			if (value instanceof UidStructure) {
				ech97.uidStructure(child, "uid", (UidStructure) value);
			} else if (value != null && value.getClass().getName().startsWith("ch.openech")) {
				WriterElement child2 = child.create(URI, name);
				write(child2, value, URI);
			} else if (value instanceof List) {
				List<?> list = (List<?>) value;
				for (Object item : list) {
					WriterElement child2 = child.create(URI, name);
					write(child2, item, URI);
				}
			}
		}
	}

	public static String writeToString(TaxStatement taxStatement) {
		StringWriter stringWriter = new StringWriter();
		WriterEch0196 w = new WriterEch0196(stringWriter, EchSchema.getNamespaceContext(196, "1.0"));
		w.write(taxStatement);
		return stringWriter.toString();
	}

}
