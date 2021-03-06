package ch.openech.xml.write;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.Keys;
import org.minimalj.model.properties.Properties;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.util.FieldUtils;

import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.model.organisation.UidStructure;
import ch.openech.model.tax.Expense;
import ch.openech.model.tax.TaxStatement;
import ch.openech.model.types.EchCode;

public class WriterEch0196 extends WriterElement {

	private static String EXPENSE_TYPE = Keys.getProperty(Expense.$.expenseType).getName();
	
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
			String name = property.getName();
			if (FieldUtils.isAllowedPrimitive(property.getClazz())) {
				Object value = property.getValue(object);
				if (name.equals("statementId")) {
					name = "id";
				}
				if (value != null) {
					child.writeAttribute(name, value.toString());
				}
			} else if (EchCode.class.isAssignableFrom(property.getClazz())) {
				EchCode code = (EchCode) property.getValue(object);
				child.writeAttribute(name, code != null ? code.getValue() : "");
			} else if (CountryIdentification.class == property.getClazz()) {
				CountryIdentification country = (CountryIdentification) property.getValue(object);
				child.writeAttribute(name, country != null ? country.countryIdISO2 : "");			
			} else if (Canton.class == property.getClazz()) {
				Canton canton = (Canton) property.getValue(object);
				child.writeAttribute(name, canton != null ? canton.id : "");					
			} else {
				// expenseType wird nur im Java Modell und nicht vom XML verwendet
				if (!name.equals(EXPENSE_TYPE)) {
					elementProperties.add(property);
				}
			}
		}
		for (PropertyInterface property : elementProperties) {
			String name = property.getName();
			if (name.equals("id")) {
				continue;
			}
			Object value = property.getValue(object);

			if (value instanceof UidStructure) {
				ech97.uidStructure(child, "uid", (UidStructure) value);
			} else if (value instanceof List) {
				List<?> list = (List<?>) value;
				for (Object item : list) {
					WriterElement child2 = child.create(URI, name);
					write(child2, item, URI);
				}
			} else if (value != null && value.getClass().getName().startsWith("ch.openech")) {
				WriterElement child2 = child.create(URI, name);
				write(child2, value, URI);
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
