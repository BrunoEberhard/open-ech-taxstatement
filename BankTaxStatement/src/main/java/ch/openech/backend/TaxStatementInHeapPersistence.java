package ch.openech.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.minimalj.backend.Persistence;
import org.minimalj.model.Code;
import org.minimalj.model.properties.FlatProperties;
import org.minimalj.model.properties.PropertyInterface;
import org.minimalj.transaction.PersistenceTransaction;
import org.minimalj.transaction.criteria.Criteria;
import org.minimalj.util.CloneHelper;
import org.minimalj.util.IdUtils;

import ch.openech.model.common.Canton;
import ch.openech.model.common.CountryIdentification;
import ch.openech.xml.read.StaxEch0071;
import ch.openech.xml.read.StaxEch0072;

public class TaxStatementInHeapPersistence implements Persistence {

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
			return (List<T>) StaxEch0072.getInstance().getCountryIdentifications();
		} else if (clazz == Canton.class) {
			return (List<T>) StaxEch0071.getInstance().getCantons();
		} else {
			throw new RuntimeException("Not supported");
		}
	}

	@Override
	public <T> void delete(Class<T> clazz, Object id) {
		throw new RuntimeException("Not supported");
	}
	
	private static void markInMemoryObject(Object object) {
		markInHeapObject(object, new HashSet<>());
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
			}
		}
		for (PropertyInterface property : FlatProperties.getProperties(object.getClass()).values()) {
			markInHeapObject(property.getValue(object), visited);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static class InHeapList extends ArrayList {
		private static final long serialVersionUID = 1L;

		public InHeapList(Collection collection) {
			super(collection);
		}
		
		@Override
		public boolean add(Object object) {
			markInMemoryObject(object);
			return super.add(object);
		}
		
	}

	public <T> T execute(PersistenceTransaction<T> transaction) {
		return transaction.execute(this);
	}

}
