package com.boutique.common.dao;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

public class DaoManager {

	/** Singleton instance variable name */
	private static final DaoManager INSTANCE;

	/** Map holding all implementation daos */
	private static Map<String, Object> daoMap = new HashMap<String, Object>();
	/** All possible implementation enumerations */
	private static final ImplType[] implNames = ImplType.VALUES;

	/**
	 * Static block to create singleton instance
	 */
	static {
		try {
			INSTANCE = new DaoManager();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Empty constructor: we don't want to take its direct objects
	 */
	private DaoManager() {
	}

	/**
	 * Singleton method creates single instance of DaoManager
	 * 
	 * @return DaoManager instance.
	 */
	public static DaoManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Resolve Dao Object based on given Interface for the given Implementation
	 * type or null type
	 * 
	 * @param arg
	 *            Class<?> interfaceName.
	 * @param arg
	 *            ImplType implType. Implementation type e.g. HiberNate,JDBC,
	 *            IMPL.... or null
	 * @return Desired Dao Object.
	 */
	public <T> T getDao(final Class<?> interfaceName, ImplType implType) {

		// load implementations based on implType. in case of null implType
		// default or null will be returned
		T daoInstance = (T) getImplDao(interfaceName, implType);

		if (daoInstance == null) {
			throw new RuntimeErrorException(null, "No Dao implementation found for[" + interfaceName.getName() + "]");
		} else {
			return daoInstance;
		}

	}

	/**
	 * Resolve Dao Object based on given Interface and return appropriate
	 * implementation
	 * 
	 * @param args
	 *            Class<?> interfaceName.
	 * @return Desired Dao Object.
	 */
	public <T> T getDao(final Class<?> interfaceName) {

		return this.getDao(interfaceName, null);
	}

	private Object getImplDao(final Class<?> interfaceName, ImplType impl) {

		String className;
		Object objDao = null;

		if (impl != null) {
			className = getPath(interfaceName, impl.getValue());
			objDao = daoMap.get(className);
			return objDao;
		} else {
			className = getPath(interfaceName, ImplType.Hibernate.getValue());
			objDao = daoMap.get(className);
			if (objDao == null) {
				objDao = createImplObjet(className);
				if (objDao != null) {
					daoMap.put(className, objDao);
					return objDao;
				} else {
					for (ImplType implName : implNames) {
						if (implName.equals(ImplType.Hibernate)) {
							continue;
						}
						className = getPath(interfaceName, implName.getValue());
						objDao = daoMap.get(className);

						if (objDao != null) {
							return objDao;
						} else {
							objDao = createImplObjet(className);
							if (objDao != null) {
								daoMap.put(className, objDao);
								return objDao;
							}
						}
					}
				}
			} else {
				return objDao;
			}
		}

		return null;
	}

	private String getPath(Class<?> interfaceName, String impl) {
		return interfaceName.getPackage().getName() + "." + interfaceName.getSimpleName() + impl;
	}

	/**
	 * Attempt to create object for a single Impl, null will be returned if was
	 * not found
	 * 
	 * @param args
	 *            classString.
	 * @return Impl Object.
	 */
	private Object createImplObjet(final String classString) {
		Object obj = null;
		Class cls;
		try {
			cls = Class.forName(classString);
			obj = cls.newInstance();
		} catch (Exception e) {
		}
		return obj;
	}

	public enum ImplType {
		Hibernate("HibernateImpl"), XmlImp("XmlImpl"), Jdbc("JdbcImpl"), Impl("Impl");
		private static final ImplType[] VALUES = ImplType.values();
		private String value;

		private ImplType(final String val) {
			this.value = val;
		}

		public String getValue() {
			return this.value;
		}
	}
}
