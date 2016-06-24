package ch.openech.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.minimalj.backend.Persistence;
import org.minimalj.model.Code;
import org.minimalj.model.Keys;
import org.minimalj.model.properties.FlatProperties;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.transaction.criteria.By;
import org.minimalj.transaction.criteria.Criteria;
import org.minimalj.transaction.criteria.FieldCriteria;
import org.minimalj.util.CloneHelper;
import org.minimalj.util.IdUtils;
import org.minimalj.util.StringUtils;

import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.xml.read.StaxEch0071;
import ch.openech.xml.read.StaxEch0072;

public class TaxStatementInHeapPersistence extends Persistence {
	
	private final StaxEch0071 staxEch0071;
	private final StaxEch0072 staxEch0072;
	
	public TaxStatementInHeapPersistence() {
		staxEch0071 = new StaxEch0071(TaxStatementInHeapPersistence.class.getClassLoader().getResourceAsStream("eCH0071_canton.xml"));
		staxEch0072 = new StaxEch0072(TaxStatementInHeapPersistence.class.getClassLoader().getResourceAsStream("eCH0072.xml"));
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
	public <T> List<T> read(Class<T> clazz, Criteria criteria, int maxResults) {
		if (clazz == CountryIdentification.class) {
			if (criteria == By.ALL) {
				return (List<T>) staxEch0072.getCountryIdentifications();
			} else if (criteria instanceof FieldCriteria) {
				FieldCriteria fieldCriteria = (FieldCriteria) criteria;
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
	public <T> void delete(Class<T> clazz, Object id) {
		throw new RuntimeException("Not supported");
	}
	
	@Override
	public <ELEMENT> List<ELEMENT> getList(String listName, Object parentId) {
		throw new IllegalStateException("LazyList should not be needed with " + this.getClass().getSimpleName());
	}
	
	@Override
	public <ELEMENT> ELEMENT add(String listName, Object parentId, ELEMENT element) {
		throw new IllegalStateException("LazyList should not be needed with " + this.getClass().getSimpleName());
	}

	@Override
	public void remove(String listName, Object parentId, int position) {
		throw new IllegalStateException("LazyList should not be needed with " + this.getClass().getSimpleName());
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

	@Override
	public void startTransaction(int transactionIsolationLevel) {
		// not supported
	}
	
	@Override
	public void endTransaction(boolean commit) {
		// not supported
	}
	
}
