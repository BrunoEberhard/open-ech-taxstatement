package ch.openech.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.minimalj.model.Code;
import org.minimalj.model.Keys;
import org.minimalj.model.properties.FlatProperties;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.repository.Repository;
import org.minimalj.repository.query.By;
import org.minimalj.repository.query.FieldCriteria;
import org.minimalj.repository.query.Query;
import org.minimalj.util.CloneHelper;
import org.minimalj.util.IdUtils;
import org.minimalj.util.StringUtils;

import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.xml.read.StaxEch0071;
import ch.openech.xml.read.StaxEch0072;

public class TaxStatementInHeapRepository implements Repository {
	
	private final StaxEch0071 staxEch0071;
	private final StaxEch0072 staxEch0072;
	
	public TaxStatementInHeapRepository() {
		staxEch0071 = new StaxEch0071(getClass().getResourceAsStream("/eCH0071_canton.xml"));
		staxEch0072 = new StaxEch0072(getClass().getResourceAsStream("/eCH0072.xml"));
		for (Canton canton : staxEch0071.getCantons()) {
			insert(canton);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T read(Class<T> clazz, Object id) {
		return (T) id;
	}

	@Override
	public <T> Object insert(T object) {
		markInMemoryObject(object);
		return object;
	}

	@Override
	public <T> void update(T object) {
		Object original = IdUtils.getId(object);
		CloneHelper.deepCopy(object, original);
		markInMemoryObject(original);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(Class<T> clazz, Query query) {
		if (clazz == CountryIdentification.class) {
			if (query == By.ALL) {
				return (List<T>) staxEch0072.getCountryIdentifications();
			} else if (query instanceof FieldCriteria) {
				FieldCriteria fieldCriteria = (FieldCriteria) query;
				if (fieldCriteria.getPath().equals(Keys.getProperty(CountryIdentification.$.countryIdISO2).getPath())) {
					List countries = new ArrayList();
					for (CountryIdentification c : staxEch0072.getCountryIdentifications()) {
						if (StringUtils.equals(c.countryIdISO2, (String) fieldCriteria.getValue())) {
							countries.add(c);
						}
					}
					return countries;
				}
			}
		} else if (clazz == Canton.class) {
			return (List<T>) staxEch0071.getCantons();
		} 
		throw new RuntimeException("Not supported");
	}

	@Override
	public <T> long count(Class<T> clazz, Query query) {
		return find(clazz, query).size();
	}
	
	@Override
	public <T> void delete(Class<T> clazz, Object id) {
		throw new RuntimeException("Not supported");
	}
	
	private static void markInMemoryObject(Object object) {
		markInHeapObject(object, new ArrayList<>());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void markInHeapObject(Object object, Collection visited) {
		if (object == null || object instanceof Code || visited.contains(object)) {
			return;
		}
		visited.add(object);
		if (IdUtils.hasId(object.getClass())) {
			IdUtils.setId(object, object);
		}
		for (PropertyInterface property : FlatProperties.getListProperties(object.getClass())) {
			List list = (List) property.getValue(object);
			if (list != null) {
				if (!(list instanceof InHeapList)) {
					property.setValue(object, new InHeapList(list));
				}
				for (Object o : list) {
					markInHeapObject(o, visited);
				}
			} else {
				property.setValue(object, new InHeapList());
			}
		}
		for (PropertyInterface property : FlatProperties.getProperties(object.getClass()).values()) {
			markInHeapObject(property.getValue(object), visited);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static class InHeapList extends ArrayList {
		private static final long serialVersionUID = 1L;
		
		public InHeapList() {
			super();
		}

		public InHeapList(Collection collection) {
			super(collection);
		}
		
		@Override
		public boolean add(Object object) {
			markInMemoryObject(object);
			return super.add(object);
		}
		
	}
	
}
