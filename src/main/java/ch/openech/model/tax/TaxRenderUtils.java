package ch.openech.model.tax;

import org.minimalj.model.Keys;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.util.StringUtils;
import org.minimalj.util.resources.Resources;

public class TaxRenderUtils {

	public static StringBuffer appendMoney(StringBuffer s, Object object, Object amountKey, String currency) {
		PropertyInterface property = Keys.getProperty(amountKey);
		s.append(Resources.getPropertyName(property));
		s.append(": ");
		Object value = property.getValue(object);
		if (value != null) {
			s.append(value.toString());
		} else {
			s.append("0.00");
		}
		if (!StringUtils.isEmpty(currency)) {
			s.append(" " );
			s.append(currency);
		}
		return s;
	}
}
