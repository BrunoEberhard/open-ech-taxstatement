package ch.openech.generator;

import static ch.openech.xml.read.StaxEch.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.minimalj.util.StringUtils;

import ch.openech.xml.write.EchNamespaceUtil;

public class TaxStatementResourcesGenerator {

	private static final String[] REMOVE = new String[] {
			"Typdefinition der ", "Typdefinition für die ", "Typdefinition für des ", "Typdefinition des ", "Typdefinition für eine ", "Typdefinition zu den ", "Typdefinition zum ", "Typdefinition für den "
	};
	
	public void read(int rootNumber, String version) throws XMLStreamException, IOException {
		int pos = version.indexOf('.');
		String major = version.substring(0, pos);
		String minor = version.substring(pos + 1);

		String rootNamespaceURI = EchNamespaceUtil.schemaURI(rootNumber, major);
		String rootNamespaceLocation = EchNamespaceUtil.schemaLocation(rootNamespaceURI, minor);

		process(rootNamespaceLocation);
	}

	public void process(String namespaceLocation) throws XMLStreamException, IOException {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLEventReader xml = null;
		try {
			xml = inputFactory.createXMLEventReader(new URL(namespaceLocation).openStream());
			process(xml);
		} catch (Exception e) {
			// this could happen in SBB ;)
			InputStream stream = EchNamespaceUtil.getLocalCopyOfSchema(namespaceLocation);
			xml = inputFactory.createXMLEventReader(stream);
			process(xml);
		}
	}

	private void process(XMLEventReader xml) throws XMLStreamException, IOException {
		while (xml.hasNext()) {
			XMLEvent event = xml.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				String startName = startElement.getName().getLocalPart();
				Attribute a = startElement.getAttributeByName(new QName("name"));
				if (startName.endsWith("Type") && a != null) {
					String typeName = a.getValue();
					if (typeName.endsWith("Type")) {
						typeName = typeName.substring(0, typeName.length() - 4);
					}
					typeName = StringUtils.upperFirstChar(typeName);
					type(typeName, xml);
				} else {
					process(xml);
				}
			} else if (event.isEndElement()) {
				return;
			}
		}
	}

	private void type(String typeName, XMLEventReader xml) throws XMLStreamException, IOException {
		while (xml.hasNext()) {
			XMLEvent event = xml.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				String startName = startElement.getName().getLocalPart();
				if (startName.equals("attribute") || startName.equals("element")) {
					Attribute a = startElement.getAttributeByName(new QName("name"));
					String name = a.getValue();
					annotated(typeName, name, xml);
				} else if (startName.startsWith("any")) {
					skip(xml);
				} else if (startName.equals("annotation")) {
					annotation(typeName, null, xml);
				} else if (startName.equals("restriction")) {
					restriction(typeName, xml);
				} else {
					type(typeName, xml);
				}
			} else if (event.isEndElement()) {
				return;
			}
		}
	}

	private void restriction(String typeName, XMLEventReader xml) throws XMLStreamException, IOException {
		while (xml.hasNext()) {
			XMLEvent event = xml.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				String startName = startElement.getName().getLocalPart();
				if (startName.equals("enumeration")) {
					Attribute a = startElement.getAttributeByName(new QName("value"));
					String value = a.getValue();
					value = value.replace(".", "_");
					annotated(typeName, value, xml);
				} else
					skip(xml);
			} else if (event.isEndElement()) {
				return;
			}
		}
	}

	// attribute or enumration
	private void annotated(String typeName, String attributeName, XMLEventReader xml) throws XMLStreamException, IOException {
		while (xml.hasNext()) {
			XMLEvent event = xml.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				String startName = startElement.getName().getLocalPart();
				if (startName.equals("annotation")) {
					annotation(typeName, attributeName, xml);
				} else
					skip(xml);
			} else if (event.isEndElement()) {
				return;
			}
		}
	}

	private void annotation(String typeName, String attributeName, XMLEventReader xml) throws XMLStreamException, IOException {
		while (xml.hasNext()) {
			XMLEvent event = xml.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				String startName = startElement.getName().getLocalPart();
				if (startName.equals("documentation")) {
					Attribute lang = (Attribute) startElement.getAttributes().next();
					// funktioniert wegen prefix nicht:
					// Attribute lang = startElement.getAttributeByName(new
					// QName("lang"));
					if (lang != null && lang.getValue().equals("de")) {
						documentation(typeName, attributeName, xml);
					} else
						skip(xml);
				} else
					skip(xml);
			} else if (event.isEndElement()) {
				return;
			}
		}
	}

	private void documentation(String typeName, String attributeName, XMLEventReader xml) throws XMLStreamException, IOException {
		while (xml.hasNext()) {
			XMLEvent event = xml.nextEvent();
			if (event.isCharacters()) {
				String string = event.asCharacters().getData();
				if (!StringUtils.isBlank(string)) {
					if (string.endsWith(".")) {
						string = string.substring(0, string.length()-1);
					}
					for (String r : REMOVE) {
						if (string.startsWith(r)) {
							string = string.substring(r.length());
						}
					}
					String output = typeName;
					if (attributeName != null) {
						output = output + "." + attributeName;
					}
					boolean description = string.contains(" ");
					if (description) {
						output = output + ".description";
					}
					output = output + " = " + string;
					System.out.println(output);
				}
			} else if (event.isEndElement()) {
				return;
			}
		}
	}

	public static void main(String... args) throws XMLStreamException, IOException {
		TaxStatementResourcesGenerator generator = new TaxStatementResourcesGenerator();
		generator.read(196, "1.0");
	}

}
