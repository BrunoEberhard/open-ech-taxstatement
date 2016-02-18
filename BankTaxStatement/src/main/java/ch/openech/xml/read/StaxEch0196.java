package ch.openech.xml.read;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.minimalj.model.properties.Properties;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.util.CloneHelper;
import org.minimalj.util.FieldUtils;
import org.minimalj.util.StringUtils;

import ch.openech.model.XmlConstants;
import ch.openech.model.organisation.UidStructure;
import ch.openech.model.tax.ListOfAccounts;
import ch.openech.model.tax.SecurityDepot;
import ch.openech.model.tax.TaxStatement;
import ch.openech.model.types.EchCode;

public class StaxEch0196 {

	public static final String PKG = TaxStatement.class.getPackage().getName();
	
	public TaxStatement process(String xmlString) {
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLEventReader xml = inputFactory.createXMLEventReader(new StringReader(xmlString));
			
			TaxStatement ts = (TaxStatement) process(xml);
			xml.close();
			return ts;
		} catch (XMLStreamException x) {
			throw new RuntimeException(x);
		}
	}

	private Object process(XMLEventReader xml) throws XMLStreamException {
		Object object = null;
		while (xml.hasNext()) {
			XMLEvent event = xml.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				String startName = startElement.getName().getLocalPart();
				if ("uid".equals(startName)) {
					UidStructure uid = new UidStructure();
					StaxEch0097.uidStructure(xml, uid);
					return uid;
				}
				String className = StringUtils.upperFirstChar(startName);
				if (XmlConstants.LIST_OF_LIABILITIES.equalsIgnoreCase(className) || XmlConstants.LIST_OF_BANK_ACCOUNTS.equalsIgnoreCase(className)) {
					className = ListOfAccounts.class.getName();
				} else if (XmlConstants.DEPOT.equalsIgnoreCase(className)) {
					className = SecurityDepot.class.getName();
				} else {
					className = PKG + "." + className;
				}
				try {
					Class<?> clazz = Class.forName(className);
					object = CloneHelper.newInstance(clazz);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Class not found: " + className + " for element " + startName);
				}
				Iterator<?> i = startElement.getAttributes();
				while (i.hasNext()) {
					Attribute attribute = (Attribute) i.next();
					
					String name = attribute.getName().getLocalPart();
					if ("id".equals(name)) {
						name = "statementId";
					} else if ("schemaLocation".equals(name)) {
						continue;
					}
					
					PropertyInterface property = Properties.getProperty(object.getClass(), name);
					if (property != null) {
						String stringValue = attribute.getValue();
						Object value;
						if (EchCode.class.isAssignableFrom(property.getClazz())) {
							StaxEch.enuum(stringValue, object, property);
						} else {
							value = FieldUtils.parse(stringValue, property.getClazz());
							property.setValue(object, value);
						}
 					}
				}
				break;
			}
		}
		
		while (xml.hasNext()) {
			XMLEvent event = xml.peek();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				String startName = startElement.getName().getLocalPart();
				Object value = process(xml);
				PropertyInterface property = Properties.getProperty(object.getClass(), startName);
				Object oldValue = property.getValue(object);
				if (property.getClazz() == List.class) {
					if (oldValue == null) {
						oldValue = new ArrayList<>();
						property.setValue(object, oldValue);
					}
					((List) oldValue).add(value);
				} else if (!property.isFinal()) {
					property.setValue(object, value);
				} else if (value != null) {
					CloneHelper.deepCopy(value, property.getValue(object));
				}
			} else {
				event = xml.nextEvent();
				if (event.isEndElement()) {
					break;
				}
			}
		}
		return object;
	}
	
}
