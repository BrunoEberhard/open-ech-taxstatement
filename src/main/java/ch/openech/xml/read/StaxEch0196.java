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

import org.minimalj.backend.Backend;
import org.minimalj.model.Code;
import org.minimalj.model.properties.Properties;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.transaction.criteria.By;
import org.minimalj.util.CloneHelper;
import org.minimalj.util.Codes;
import org.minimalj.util.FieldUtils;
import org.minimalj.util.GenericUtils;
import org.minimalj.util.StringUtils;

import ch.openech.model.common.CountryIdentification;
import ch.openech.model.organisation.UidStructure;
import ch.openech.model.tax.TaxStatement;
import ch.openech.model.types.EchCode;

public class StaxEch0196 {

	public static final String PKG = TaxStatement.class.getPackage().getName();
	
	public TaxStatement process(String xmlString) {
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLEventReader xml = inputFactory.createXMLEventReader(new StringReader(xmlString));
			
			TaxStatement ts = process(xml, TaxStatement.class);
			xml.close();
			return ts;
		} catch (XMLStreamException x) {
			throw new RuntimeException(x);
		}
	}

	private <T> T process(XMLEventReader xml, Class<T> clazz) throws XMLStreamException {
		T object = null;
		while (xml.hasNext()) {
			XMLEvent event = xml.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				String startName = startElement.getName().getLocalPart();
				if ("uid".equals(startName)) {
					UidStructure uid = new UidStructure();
					StaxEch0097.uidStructure(xml, uid);
					return (T) uid;
				}
				object = CloneHelper.newInstance(clazz);
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
						if (EchCode.class.isAssignableFrom(property.getClazz())) {
							StaxEch.enuum(stringValue, object, property);
						} else {
							Object value;
							if (CountryIdentification.class == property.getClazz()) {
								value = countryIdentification(stringValue);										
							} else if (Code.class.isAssignableFrom(property.getClazz())) {
								Class codeClazz = property.getClazz();
								value = Codes.findCode(codeClazz, stringValue);
							} else {
								value = FieldUtils.parse(stringValue, property.getClazz());
							}
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
				PropertyInterface property = Properties.getProperty(object.getClass(), startName);
				Class<?> elementClass = property.getClazz();
				if (elementClass == List.class) {
					Object list = property.getValue(object);
					if (list == null) {
						list = new ArrayList<>();
						property.setValue(object, list);
					}
					Object value = process(xml, GenericUtils.getGenericClass(property.getType()));
					((List) list).add(value);
				} else {
					Object value = process(xml, elementClass);
					if (!property.isFinal()) {
						property.setValue(object, value);
					} else if (value != null) {
						CloneHelper.deepCopy(value, property.getValue(object));
					}
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
	
	private CountryIdentification countryIdentification(String iso) {
		CountryIdentification country = null;
		if (!StringUtils.isBlank(iso)) {
			List<CountryIdentification> countries = Backend.read(CountryIdentification.class, By.field(CountryIdentification.$.countryIdISO2, iso), 1);
			if (!countries.isEmpty()) {
				country = countries.get(0);
			} else {
				country = new CountryIdentification();
				country.countryIdISO2 = iso;
				country.countryNameShort = iso;
			}
		}
		return country;
	}
	
}
